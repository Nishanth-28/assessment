/**
 *
 * @author CJP@venkat
 * @version 1.0
 * @since
 */
package com.advice.forms;

/**
 *
 * @author cjp
 */
public class UserLogInForm {

    private String userId;

    private String password;
    private String captchas;
    private String captchaImage;
     private String captchasValue;
     private String captcha;
     private String activate;
     private String authorMsg;

    public String getAuthorMsg() {
        return authorMsg;
    }

    public void setAuthorMsg(String authorMsg) {
        this.authorMsg = authorMsg;
    }

    public String getActivate() {
        return activate;
    }

    public void setActivate(String activate) {
        this.activate = activate;
    }
    

    /**
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getCaptchas() {
        return captchas;
    }

    /**
     *
     * @param captchas
     */
    public void setCaptchas(String captchas) {
        this.captchas = captchas;
    }

    public String getCaptchaImage() {
        return captchaImage;
    }

    public void setCaptchaImage(String captchaImage) {
        this.captchaImage = captchaImage;
    }

    public String getCaptchasValue() {
        return captchasValue;
    }

    public void setCaptchasValue(String captchasValue) {
        this.captchasValue = captchasValue;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
