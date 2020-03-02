package com.nixsolutions.dto;

public class RegistrationUserDto extends UserDto {
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "RegistrationUserDto{" +
                "captcha='" + captcha + '\'' +
                '}';
    }
}
