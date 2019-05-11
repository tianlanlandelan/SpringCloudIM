package com.kyle.mycommon.entity;


public class SMSEntity {
    /**
    * 用户ID
    */
     private String phone;
    /**
    * 类型
    */
     private Integer type;
    /**
    * 短信模板ID
    */
     private String templateId;
    /**
     * 验证码
     * 模板里第一个参数
     * 123456
     */
     private String codeStr;
     /**
     * 有效时间
     * 模板里第二个参数
     * 单位：分钟
     */
     private String minuteStr;

    /**
     *  返回结果
     *  {data={templateSMS={dateCreated=20180827170721, smsMessageSid=a21809d2dbe84872878a3e9cd9a3da17}}, statusCode=000000}
     */
    private String result;
    /**
    * 返回码
    */
     private String statusCode;

    /**
     * 创建对象时需指定手机号和发送类型
     * @param phone 接收手机号
     * @param type  发送类型
     */
    public SMSEntity(String phone, Integer type) {
        this.phone = phone;
        this.type = type;
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


    @Override
    public String toString() {
        return "SMSLog{" +
                "phone='" + phone + '\'' +
                ", type=" + type +
                ", templateId='" + templateId + '\'' +
                ", codeStr='" + codeStr + '\'' +
                ", minuteStr='" + minuteStr + '\'' +
                ", result='" + result + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
