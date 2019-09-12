package com.kyle.im.common.mybatis;

import com.kyle.im.common.util.Console;
import com.kyle.im.user.entity.UserInfo;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-09-12 09:22:16
 */
public class MySqlTableCreater {

    public static void main(String[] args){
        UserInfo userInfo = new UserInfo();
        Class cls = userInfo.getClass();

        String tableName = SqlFieldReader.getTableName(cls);

        StringBuilder builder = new StringBuilder();
        builder.append("create table ")
                .append(tableName)
                .append("( \n");

        List<SqlField> fieldMap = SqlFieldReader.getFieldList(cls);
        for(SqlField field : fieldMap){
            builder.append(field.getName())
                    .append(" ")
                    .append(TypeCaster.getType(field.getType()))
                    .append(", \n");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        builder.append("); \n");

        SqlField key = SqlFieldReader.getKey(cls);
        if(key != null){
            builder.append("alter table ")
                    .append(tableName)
                    .append(" add primary key (")
                    .append(key.getName())
                    .append("); \n");
        }


        List<SqlField> indexMap = SqlFieldReader.getIndexList(cls);
        for(SqlField field:indexMap){
            builder.append("alter table ")
                    .append(tableName)
                    .append(" add index ")
                    .append(tableName)
                    .append("_index_")
                    .append(field.getName())
                    .append(" (")
                    .append(field.getName())
                    .append("); \n");
        }



        Console.print("",builder.toString());





    }
}
