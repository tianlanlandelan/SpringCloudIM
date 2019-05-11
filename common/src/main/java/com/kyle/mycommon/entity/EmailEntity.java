package com.kyle.mycommon.entity;

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

    @Override
    public String toString() {
        return "EmailLog{" +
                "type=" + type +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
