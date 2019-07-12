package com.kyle.mycommon.provider;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author yangkaile
 * @date 2019-07-12 15:27:00
 *
 * @param <K>
 */
public interface BaseMapper<K> {
    /**
     * 基本的插入操作
     * @param entity
     * @return
     */
    @InsertProvider(type = BaseProvider.class,method = "insert")
    Integer baseInsert(K entity);

    /**
     * 根据Id 查找数据，要求必须有id 字段
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseProvider.class,method = "selectById")
    K baseSelectById(K entity);

    /**
     * 根据Id 删除数据，要求必须有id 字段
     * @param entity
     * @return
     */
    @DeleteProvider(type= BaseProvider.class,method = "deleteById")
    Integer baseDeleteById(K entity);


    /**
     * 查询全部数据
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseProvider.class,method = "selectAll")
    List<K> baseSelectAll(K entity);

    /**
     * 根据索引字段查询数据
     * 1. @IndexAttribute 注解的字段有值的作为查询条件
     * 2. 多个查询条件是 AND 关系
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseProvider.class,method = "selectByIndexWithOr")
    List<K> baseSelectByIndexWithOr(K entity);

    /**
     * 根据索引字段查询数据
     * 1. @IndexAttribute 注解的字段有值的作为查询条件
     * 2. 多个查询条件是 AND 关系
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseProvider.class,method = "selectByIndexWithAnd")
    List<K> baseSelectByIndexWithAnd(K entity);
}
