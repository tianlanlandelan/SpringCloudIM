package com.kyle.mycommon.mybatis;

import com.kyle.mycommon.mybatis.provider.BaseDeleteProvider;
import com.kyle.mycommon.mybatis.provider.BaseInsertProvider;
import com.kyle.mycommon.mybatis.provider.BaseSelectProvider;
import com.kyle.mycommon.mybatis.provider.BaseUpdateProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-07-12 15:27:00
 *
 * @param <K>
 */
public interface BaseMapper<K> {
    /**
     * 插入操作
     * @param entity
     * @return
     */
    @InsertProvider(type = BaseInsertProvider.class,method = "insert")
    Integer baseInsert(K entity);

    /**
     * 根据Id删除数据，要求必须有id字段
     * @param entity
     * @return
     */
    @DeleteProvider(type = BaseDeleteProvider.class,method = "deleteById")
    Integer baseDeleteById(K entity);

    /**
     * 根据条件删除
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @param and 多个查询条件组合方式 null:不指定查询条件  true:多个查询条件用AND连接  false:多个查询条件用OR连接
     * @return DELETE FROM router  WHERE name = #{name} AND serviceName = #{serviceName}
     */
    @SelectProvider(type= BaseDeleteProvider.class,method = "deleteByCondition")
    List<K> baseDeleteByCondition(K entity,Boolean and);

    /**
     * 根据id 更新数据，空值不更新 ，要求必须有id字段
     * @param entity
     * @return
     */
    @UpdateProvider(type = BaseUpdateProvider.class,method = "updateById")
    Integer baseUpdateById(K entity);


    /**
     * 根据Id 查找数据，要求必须有id 字段
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectById")
    K baseSelectById(K entity);

    /**
     * 查询全部数据
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectAll")
    List<K> baseSelectAll(K entity);

    /**
     * 带条件的查询，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 传入对象中带@SortAttribute注解的字段作为排序字段
     * @param entity 实体对象
     * @param and 多个查询条件组合方式  true:AND  false:OR
     * @param asc 排序方式  null:不指定排序方式  true:按指定排序字段升序   false:按指定排序字段降序
     * @return SELECT id,name... FROM router  WHERE name = #{name} AND serviceName = #{serviceName}  ORDER BY createTime ASC
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectByCondition")
    List<K> baseSelectByCondition(K entity,Boolean and,Boolean asc);


    /**
     * 查询记录总数
     * 返回的是 "SELECT COUNT(1) FROM 表名" 的结果
     * 不带查询条件
     * @param entity
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectCount")
    Integer baseSelectCount(K entity);

    /**
     * 根据条件查询记录总数
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * @param entity
     * @param and 多个查询条件组合方式 null:不指定查询条件  true:多个查询条件用AND连接  false:多个查询条件用OR连接
     * @return SELECT COUNT(1) FROM router WHERE name = #{name} AND serviceName = #{serviceName}
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectCountByCondition")
    Integer baseSelectCountByCondition(K entity,Boolean and);


    /**
     * 分页查询
     * 返回 “SELECT 所有字段 FROM 表名 LIMIT startRows,pageSize” 的结果
     * 不带查询条件
     * @param entity
     * @param startRows
     * @param pageSize
     * @return
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectPageList")
    List<K> baseSelectPageList(K entity,int startRows,int pageSize);

    /**
     * 加条件的分页查询
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * @param entity
     * @param and 多个查询条件组合方式 null:不指定查询条件  true:多个查询条件用AND连接  false:多个查询条件用OR连接
     * @param asc 排序方式  null:不指定排序方式  true:按指定排序字段升序   false:按指定排序字段降序
     * @param startRows 起始行数
     * @param pageSize 查询条数
     * @return SELECT id,name... FROM router  WHERE name = #{name} AND serviceName = #{serviceName}  ORDER BY createTime ASC LIMIT #{startRows},#{pageSize}
     */
    @SelectProvider(type = BaseSelectProvider.class,method = "selectPageListByCondition")
    List<K> baseSelectPageListByCondition(K entity,Boolean and,Boolean asc,int startRows,int pageSize);

}
