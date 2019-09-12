package com.kyle.im.common.mybatis;

/**
 *  数据库字段实体类
 * @author yangkaile
 * @date 2019-09-12 15:40:23
 */
public class SqlField {
    /**
     * 字段名
     */
    private String name;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段说明
     */
    private String comment;
    /**
     * 是否必填
     */
    private boolean notNull;

    public SqlField(String name, String type){
        this.name = name;
        this.type = type;
    }
    public SqlField(String name, String type,String comment){
        this.name = name;
        this.type = type;
        this.comment = comment;
    }

    public SqlField(String name, String type,String comment,boolean notNull){
        this.name = name;
        this.type = type;
        this.comment = comment;
        this.notNull = notNull;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    @Override
    public String toString() {
        return "SqlField{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", notNull=" + notNull +
                '}';
    }
}
