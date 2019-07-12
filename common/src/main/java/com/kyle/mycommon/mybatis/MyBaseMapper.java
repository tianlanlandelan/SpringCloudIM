package com.kyle.mycommon.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 一个基础的Mapper，实现通用的增删改查操作，所有Mapper继承该Mapper后，即可使用
 * 每个方法使用的对象T为具体实体类(建议使用MyBaseEntity)：必须包含 allFields(查询字段列表)字段和tableName(表名)字段
 * 提供：
 * 1.根据ID查询功能
 * 2.查询全量列表功能
 * 3.分页查询功能
 * 4.查询总记录数功能
 * 5.逻辑删除和物理删除功能
 * @author yangkaile
 * @date 2018-11-28 15:49:09
 */
public interface MyBaseMapper<K> {

    /**
     * 根据Id查询记录
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、id(记录ID)属性的对象
     * @return
     */
    @Select("SELECT ${allFields} FROM ${tableName} WHERE id = #{id}")
    <T> K baseGetById(T map);

    /**
     * 根据Id查询记录（不包含已删除的记录）
     * 使用该方法时数据库字段的限制：数值型的isDelete字段 0 表示正常数据；1 表示数据已删除
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、id(记录ID)属性的对象
     * @return
     */
    @Select("SELECT ${allFields} FROM ${tableName} WHERE id = #{id} AND isDelete = isDelete")
    <T> K baseGetByIdNotDelete(T map);

    /**
     * 查询全量列表
     * 使用${}方式解析表名和查询字段列表，避免mybatis解析时将其加上单引号
     * @param map
     * @param <T> 包含allFields(查询字段列表)和tableName(表名)属性的对象
     * @return 返回一个Map对象的List
     */
    @Select("SELECT ${allFields} FROM ${tableName}")
    <T> List<K> baseGetAll(T map);
    /**
     * 查询全量列表（不包含已删除的记录）
     * 使用该方法时数据库字段的限制：数值型的isDelete字段 0 表示正常数据；1 表示数据已删除
     * @param map
     * @param <T> 包含allFields(查询字段列表)和tableName(表名)属性的对象
     * @return 返回一个Map对象的List
     */
    @Select("SELECT ${allFields} FROM ${tableName} WHERE isDelete = 0")
    <T> List<K> baseGetAllNotDelete(T map);

    /**
     * 分页查询
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、startRows(分页查询开始记录行号)、pageSize(页面大小)属性的对象
     * @return
     */
    @Select("SELECT ${allFields} FROM ${tableName} LIMIT #{startRows},#{pageSize}")
    <T> List<K> baseGetPageList(T map);

    /**
     * 分页查询（不包含已删除的记录）
     * 使用该方法时数据库字段的限制：数值型的isDelete字段 0 表示正常数据；1 表示数据已删除
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、startRows(分页查询开始记录行号)、pageSize(页面大小)属性的对象
     * @return
     */
    @Select("SELECT ${allFields} FROM ${tableName} WHERE isDelete = 0  LIMIT #{startRows},#{pageSize}")
    <T> List<K> baseGetPageListNotDelete(T map);

    /**
     * 查询总记录数（包含全部数据）
     * @param map
     * @param <T> 包含tableName(表名)属性的对象
     * @return 记录条数
     */
    @Select("SELECT COUNT(1) FROM  ${tableName}")
    <T> Integer baseGetCount(T map);

    /**
     * 查询总记录数（不包含已删除的记录）
     * 使用该方法时数据库字段的限制：数值型的isDelete字段 0 表示正常数据；1 表示数据已删除
     * @param map
     * @param <T> 包含tableName(表名)属性的对象
     * @return 记录条数
     */
    @Select("SELECT COUNT(1) FROM  ${tableName} where isDelete = 0")
    <T> Integer baseGetCountNotDelete(T map);


    /**
     * 物理删除，执行delete操作，删除表中指定id的记录
     * @param map
     * @param <T> 包含tableName(表名)、id(记录ID)属性的对象
     * @return 成功删除的记录数
     */
    @Delete("DELETE FROM ${tableName} WHERE id = #{id}")
    <T> Integer baseDeleteById(T map);

    /**
     * 逻辑删除，执行update操作，将表中的isDelete字段设置为1
     * 使用该方法时数据库字段的限制：数值型的isDelete字段 0 表示正常数据；1 表示数据已删除
     * @param map
     * @param <T> 包含tableName(表名)、id(记录ID)属性的对象
     * @return 成功删除的记录数
     */
    @Update("UPDATE ${tableName} set isDelete = 1 WHERE id = #{id}")
    <T> Integer baseSoftDeleteById(T map);

}
