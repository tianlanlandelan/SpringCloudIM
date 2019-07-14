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
     * 根据条件删除，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @return
     */
    @SelectProvider(type= BaseDeleteProvider.class,method = "deleteByConditionAnd")
    List<K> baseDeleteByConditionAnd(K entity);

    /**
     * 根据条件删除，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "deleteByConditionOr")
    List<K> baseDeleteByConditionOr(K entity);


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
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectByConditionAnd")
    List<K> baseSelectByConditionAnd(K entity);


    /**
     * 带条件的查询，该查询为动态查询，不可缓存
     * 传入的对象中带@IndexAttribute注解的字段有值的都作为查询条件
     * 多个查询条件用And连接
     * @param entity 实体对象
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectByConditionOr")
    List<K> baseSelectByConditionOr(K entity);

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


}
