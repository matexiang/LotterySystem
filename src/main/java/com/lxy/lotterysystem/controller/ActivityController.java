package com.lxy.lotterysystem.controller;

import com.lxy.lotterysystem.common.CommonResult;
import com.lxy.lotterysystem.common.errorcode.ControllerErrorCodeConstants;
import com.lxy.lotterysystem.common.exception.ControllerException;
import com.lxy.lotterysystem.common.utils.JacksonUtil;
import com.lxy.lotterysystem.controller.param.CreateActivityParam;
import com.lxy.lotterysystem.controller.param.CreatePrizeParam;
import com.lxy.lotterysystem.controller.param.PageParam;
import com.lxy.lotterysystem.controller.result.CreateActivityResult;
import com.lxy.lotterysystem.controller.result.FindActivityListResult;
import com.lxy.lotterysystem.controller.result.GetActivityDetailResult;
import com.lxy.lotterysystem.service.ActivityService;
import com.lxy.lotterysystem.service.dto.ActivityDTO;
import com.lxy.lotterysystem.service.dto.ActivityDetailDTO;
import com.lxy.lotterysystem.service.dto.CreateActivityDTO;
import com.lxy.lotterysystem.service.dto.PageListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
public class ActivityController {

    private static final Logger logger =  LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    private ActivityService activityService;


    /**
     * 创建活动
     * @param param
     * @return
     */
    @RequestMapping("/activity/create")
    public CommonResult<CreateActivityResult> createActivity(
            @RequestBody @Validated CreateActivityParam param){

        logger.info("createActivity CreateActivityParam:{}",
                JacksonUtil.writeValueAsString(param));

        return CommonResult.success(
                convertCreateActivityResult(activityService.createActivity(param)));


    }

    @RequestMapping("/activity/find-list")
    public CommonResult<FindActivityListResult> findActivityList(PageParam param){

        logger.info("FindActivityListResult PageParm:{}",param);
        return CommonResult.success(
                convertFindActivityListResult(activityService.findActivityList(param)));
    }

    @RequestMapping("/activity-detail/find")
    public CommonResult<GetActivityDetailResult> getActivityDetail(Long activityId){
        logger.info("getActivityDetail activityId:{}",activityId);
        ActivityDetailDTO detailDTO = activityService.getActivityDetail(activityId);
        return CommonResult.success(convertGetActivityDetailResult(detailDTO));
    }

    private GetActivityDetailResult convertGetActivityDetailResult(ActivityDetailDTO detailDTO) {
        if(null == detailDTO){
            throw new ControllerException(ControllerErrorCodeConstants.GET_ACTIVITY_DETAIL_ERROR);
        }

        GetActivityDetailResult result = new GetActivityDetailResult();
        result.setActivityId(detailDTO.getActivityId());
        result.setActivityName(detailDTO.getActivityName());
        result.setDesc(detailDTO.getDesc());
        result.setValid(detailDTO.valid());
        result.setPrizes(
                detailDTO.getPrizeDTOList().stream()
                        .sorted(Comparator.comparingInt(prizeDTO ->prizeDTO.getTiers().getCode()))
                        .map(prizeDTO -> {
                            GetActivityDetailResult.Prize prize = new GetActivityDetailResult.Prize();
                            prize.setPrizeId(prizeDTO.getPrizeId());
                            prize.setName(prizeDTO.getName());
                            prize.setImageUrl(prizeDTO.getImageUrl());
                            prize.setPrice(prizeDTO.getPrice());
                            prize.setDescription(prizeDTO.getDescription());
                            prize.setTiers(prizeDTO.getTiers().getMessage());
                            prize.setPrizeAmount(prizeDTO.getPrizeAmount());
                            prize.setValid(prizeDTO.valid());
                            return prize;

                        }).collect(Collectors.toList())
        );
        result.setUsers(
                detailDTO.getUserDTOList().stream()
                        .map(userDTO -> {
                            GetActivityDetailResult.User user = new GetActivityDetailResult.User();
                            user.setUserId(userDTO.getUserId());
                            user.setUserName(userDTO.getUserName());
                            user.setValid(userDTO.valid());
                            return user;
                        }).collect(Collectors.toList())
        );


        return result;
    }

    private FindActivityListResult convertFindActivityListResult(PageListDTO<ActivityDTO> activityList) {
        if (null == activityList) {

            throw new ControllerException(ControllerErrorCodeConstants.FIND_ACTIVITY_LIST_ERROR);
        }
        FindActivityListResult result = new FindActivityListResult();
        result.setTotal(activityList.getTotal());
        result.setRecords(
                activityList.getRecords()
                        .stream()
                        .map(activityDTO -> {
                            FindActivityListResult.ActivityInfo activityInfo = new FindActivityListResult.ActivityInfo();

                            activityInfo.setActivityId(activityDTO.getActivityId());
                            activityInfo.setActivityName(activityDTO.getActivityName());
                            activityInfo.setDescription(activityDTO.getDescription());
                            activityInfo.setValid(activityDTO.valid());

                            return activityInfo;

                                }).collect(Collectors.toList())
        );

        return result;
    }


    private CreateActivityResult convertCreateActivityResult(CreateActivityDTO createActivityDTO) {
        if (null == createActivityDTO) {
            throw new ControllerException(ControllerErrorCodeConstants.CREATE_ACTIVITY_ERROR);
        }
        CreateActivityResult result = new CreateActivityResult();
        result.setActivityId(createActivityDTO.getActivityId());
        return result;
    }



}
