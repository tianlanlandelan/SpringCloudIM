package com.kyle.mycommon.entity;

import com.kyle.mycommon.mybatis.annotation.TableAttribute;

import java.util.Date;

@TableAttribute("email_log")
public class EmailEntity {
    /**
    * 邮件发送类型
    */
     private Integer type;
    /**
    * 收件人
    */
     private String email;
    /**
    * 标题
    */
     private String title;
    /**
    * 内容
    */
     private String content;
    /**
     * 验证码
     * 包含验证码的邮件，需要将验证码单独填写，方便查询
    */
     private String code;


     private String result;
    /**
     * 状态码
     * 0 成功
     * 1 失败
     */
     private int statusCode = 0;

    /**
     * 发送时间，验证码邮件的有效时间为5分钟
     */
    private Date createTime = new Date();

    /**
     * 验证码是否已使用 0:未使用 1:已使用
     */
    private int isUsed = 0;

    public EmailEntity(){
        super();
    }

    /**
     * 是否是有效的验证码
     * 要求成功发送，发送时间在5分钟内，且未使用过
     * @return
     */
    public boolean isEfficientVerificationCode(){
        return (System.currentTimeMillis() - createTime.getTime() < 5 * 60 * 1000
                && isUsed == 0
                && statusCode == 0
        );
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(int isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "EmailEntity{" +
                "type=" + type +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", statusCode=" + statusCode +
                ", createTime=" + createTime +
                ", isUsed=" + isUsed +
                '}';
    }
}
