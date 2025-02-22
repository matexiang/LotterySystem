package com.lxy.lotterysystem.common.utils;


import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.CodeGenerator;
import cn.hutool.captcha.generator.RandomGenerator;

public class CaptchaUtil {

    /**
     * 生成随机验证码
     * @param length 几位验证码
     * @return
     */
    public static String getCaptcha(int length){
        // 自定义纯数字的验证码（随机4位数字，可重复）
        RandomGenerator randomGenerator = new RandomGenerator("1234567890",length);
        LineCaptcha lineCaptcha = cn.hutool.captcha.CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.setGenerator((CodeGenerator) randomGenerator);
        // 重新生成code
        lineCaptcha.createCode();
        return lineCaptcha.getCode();

    }


}
