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
     * 根据索引字段查询（忽略id字段），多个查询条件之间用And连接
     * 要求索引字段使用@IndexAttribute注解
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectByIndexAnd")
    List<K> baseSelectByIndexAnd(K entity);


    /**
     * 根据索引字段查询（忽略id字段），多个查询条件之间用Or连接
     * 要求索引字段使用@IndexAttribute注解
     * @param entity
     * @return
     */
    @SelectProvider(type= BaseSelectProvider.class,method = "selectByIndexOr")
    List<K> baseSelectByIndexOr(K entity);

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
