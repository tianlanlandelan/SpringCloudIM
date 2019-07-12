package com.kyle.mycommon.mybatis;

import com.kyle.mycommon.mybatis.provider.BaseDeleteProvider;
import com.kyle.mycommon.mybatis.provider.BaseInsertProvider;
import com.kyle.mycommon.mybatis.provider.BaseSelectProvider;
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
     * 插入操作 ，
     * @param entity
     * @return
     */
    @InsertProvider(type = BaseInsertProvider.class,method = "insert")
    Integer baseInsert(K entity);

    /**
     * 基本的
     * @param entity
     * @return
     */
    @DeleteProvider(type = BaseDeleteProvider.class,method = "deleteById")
    Integer baseDeleteById(K entity);



    /**
     * 根据Id 查找数据，要求必须有id 字段
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectById")
    K baseSelectById(K entity);

    @SelectProvider(type= BaseSelectProvider.class,method = "selectAll")
    List<K> baseSelectAll(K entity);

    @SelectProvider(type= BaseSelectProvider.class,method = "selectByIndex")
    List<K> baseSelectByIndex(K entity);




}
