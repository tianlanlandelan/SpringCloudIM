package com.kyle.mycommon.mybatis;

import com.kyle.mycommon.response.ResultData;
import com.kyle.mycommon.util.Constants;

/**
 * 负责存储和解析MyBaseEntity对象
 * @author yangkaile
 * @date 2018-11-29 14:06:11
 */
public class MyBaseUtils {

    /**
     * 统一的分页查询方法，可以在Service层直接调用
     * @param currentPage
     * @param pageSize
     * @param mapper
     * @param baseEntity
     * @param <T>
     * @return
     */
    public static <T extends MyBaseMapper> ResultData getPageList(int currentPage, int pageSize, T mapper, MyBaseEntity baseEntity){
        if(currentPage < 1 || pageSize < 0 || pageSize > Constants.MAX_PAGE_SIZE){
            return ResultData.error("非法数据");
        }else {
            PageList pageList = new PageList();
            //查询第一页数据时返回记录总条数
            if(currentPage == 1){
                pageList.setTotal(mapper.baseGetCount(baseEntity));
            }
            baseEntity.setPageSize(pageSize);
            baseEntity.setStartRows((currentPage - 1) * pageSize);
            pageList.setData(mapper.baseGetPageList(baseEntity));
            return ResultData.success(pageList);
        }
    }


}
