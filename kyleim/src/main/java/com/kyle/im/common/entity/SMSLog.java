package com.kyle.im.common.entity;


import com.kyle.im.common.mybatis.BaseEntity;
import com.kyle.im.common.mybatis.annotation.*;

import java.util.Date;


@TableAttribute(name = "sms_log",comment = "短信发送记录")
public class SMSLog extends BaseEntity{
    public static final int Logon = 0;
    public static final int ReSetPassword = 1;

    public static final int NoUse = 0;
    public static final int Used = 1;


    @FieldAttribute
    @AutoIncrKeyAttribute
    private Integer id;
    /**
    * 用户ID
    */
    @IndexAttribute
    @FieldAttribute(value = "手机号",notNull = true)
     private String phone;
    /**
    * 类型
    */
    @FieldAttribute(value = "短信类型",notNull = true)
    private Integer type = Logon;
    /**
    * 短信模板ID
    */
    @FieldAttribute(value = "短信模板编号")
    private String templateId;
    /**
     * 验证码
     * 模板里第一个参数
     * 123456
     */
    @IndexAttribute
    @FieldAttribute(value = "验证码")
     private String codeStr;
     /**
     * 有效时间
     * 模板里第二个参数
     * 单位：分钟
     */
     @FieldAttribute(value = "有效时间")
     private String minuteStr;

    /**
     *  返回结果
     *  {data={templateSMS={dateCreated=20180827170721, smsMessageSid=a21809d2dbe84872878a3e9cd9a3da17}}, statusCode=000000}
     */
    @FieldAttribute(value = "短信服务器返回结果")
    private String result;

    /**
    * 返回码
    */
    @FieldAttribute(value = "业务返回码")
     private String statusCode;

    /**
     * 发送时间，短信验证码5分钟内有效
     */
    @SortAttribute
    @FieldAttribute(value = "短信发送时间")
    private Date createTime = new Date();

    /**
     * 验证码是否已使用  0:未使用 1:已使用
     */
    @FieldAttribute(value = "短信是否已使用")
    private int isUsed = NoUse;

    public SMSLog(){
        super();
    }

    /**
     * 创建对象时需指定手机号和发送类型
     * @param phone 接收手机号
     * @param type  发送类型
     */
    public SMSLog(String phone, Integer type) {
        this.phone = phone;
        this.type = type;
    }

    /**
     * 是否是有效的验证码
     * 要求成功发送，发送时间在5分钟内，且未使用过
     * @return
     */
    public boolean isEfficientVerificationCode(){
        return true;
//        return (System.currentTimeMillis() - createTime.getTime() < 5 * 60 * 1000
//                && isUsed == 0
//                && statusCode.equals("000000")
//        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone(){
           return this.phone;
     }
     public void setPhone(String phone){
           this.phone = phone;
     }
     public Integer getType(){
           return this.type;
     }
     public void setType(Integer type){
           this.type = type;
     }
     public String getTemplateId(){
           return this.templateId;
     }
     public void setTemplateId(String templateId){
           this.templateId = templateId;
     }
     public String getCodeStr(){
           return this.codeStr;
     }
     public void setCodeStr(String codeStr){
           this.codeStr = codeStr;
     }
     public String getMinuteStr(){
           return this.minuteStr;
     }
     public void setMinuteStr(String minuteStr){
           this.minuteStr = minuteStr;
     }
     public String getResult(){
           return this.result;
     }
     public void setResult(String result){
           this.result = result;
     }
     public String getStatusCode(){
           return this.statusCode;
     }
     public void setStatusCode(String stateCode){
           this.statusCode = stateCode;
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
        return "SMS{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                ", templateId='" + templateId + '\'' +
                ", codeStr='" + codeStr + '\'' +
                ", minuteStr='" + minuteStr + '\'' +
                ", result='" + result + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", createTime=" + createTime +
                ", isUsed=" + isUsed +
                '}';
    }
}
