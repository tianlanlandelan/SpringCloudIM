package com.kyle.mycommon.provider;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

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

    @SelectProvider(type= BaseProvider.class,method = "selectById")
    K baseSelectById(K entity);
}
