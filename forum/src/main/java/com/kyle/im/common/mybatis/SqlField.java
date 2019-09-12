package com.kyle.im.common.mybatis;

/**
 *  数据库字段实体类
 * @author yangkaile
 * @date 2019-09-12 15:40:23
 */
public class SqlField {
    private String name;
    private String type;
    private String comment;

    public SqlField(String name, String type){
        this.name = name;
        this.type = type;
    }
    public SqlField(String name, String type,String comment){
        this.name = name;
        this.type = type;
        this.comment = comment;
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

    @Override
    public String toString() {
        return "SqlField{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value='" + comment + '\'' +
                '}';
    }
}
