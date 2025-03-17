package com.lxy.lotterysystem.controller;

import com.lxy.lotterysystem.common.CommonResult;
import com.lxy.lotterysystem.common.errorcode.ControllerErrorCodeConstants;
import com.lxy.lotterysystem.common.exception.ControllerException;
import com.lxy.lotterysystem.common.utils.JacksonUtil;
import com.lxy.lotterysystem.controller.param.CreatePrizeParam;
import com.lxy.lotterysystem.controller.param.PageParam;
import com.lxy.lotterysystem.controller.result.FindPrizeListResult;
import com.lxy.lotterysystem.service.PictureService;
import com.lxy.lotterysystem.service.PrizeService;
import com.lxy.lotterysystem.service.dto.PageListDTO;
import com.lxy.lotterysystem.service.dto.PrizeDTO;
import com.lxy.lotterysystem.service.impl.PictureServiceimpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@RestController
public class PrizeController {
    private static final Logger logger =  LoggerFactory.getLogger(PrizeController.class);

    @Autowired
    private PictureService pictureService;
    @Autowired
    private PrizeService prizeService;

    @RequestMapping("/pic/upload")
    public String uploadPic(MultipartFile file){
        PictureServiceimpl prizeService;
        return pictureService.savePicture(file);
    }

    /**
     * 创建奖品
     * @param param
     * @param picFile
     * @return
     */
    @RequestMapping("/prize/create")
    public CommonResult<Long> createPrize(@Valid @RequestPart("param") CreatePrizeParam param,
                                          @RequestPart("prizePic") MultipartFile picFile) {
        logger.info("createPrize CreatePrizeParam:{}",
                JacksonUtil.writeValueAsString(param));
        return CommonResult.success(
                prizeService.createPrize(param, picFile));
    }

    @RequestMapping("/prize/find-list")
    public CommonResult<FindPrizeListResult> findPrizeList(PageParam param){

        logger.info("findPrizeList PageParam:{} ",
        JacksonUtil.writeValueAsString(param));

        PageListDTO<PrizeDTO>  pageListDTO =prizeService.findPrizeList(param);
        return CommonResult.success(convertTOFindPrizeListResult(pageListDTO));
    }
    private FindPrizeListResult convertTOFindPrizeListResult(PageListDTO<PrizeDTO> pageListDTO){

        if(pageListDTO == null){
            throw new ControllerException(ControllerErrorCodeConstants.FIND_PRIZE_LIST_ERROR);
        }

        FindPrizeListResult result = new FindPrizeListResult();
        result.setTotal(pageListDTO.getTotal());
        result.setRecords(
                pageListDTO.getRecords().stream().map(prizeDTO -> {
                    FindPrizeListResult.PrizeInfo prizeInfo = new FindPrizeListResult.PrizeInfo();
                    prizeInfo.setPrizeId(prizeDTO.getPrizeId());
                    prizeInfo.setPrizeName(prizeDTO.getName());
                    prizeInfo.setDescription(prizeDTO.getDescription());
                    prizeInfo.setImageUrl(prizeDTO.getImageUrl());
                    prizeInfo.setPrice(prizeDTO.getPrice());
                    return prizeInfo;
                }).collect(Collectors.toList())
        );
        return  result;
    }

}
