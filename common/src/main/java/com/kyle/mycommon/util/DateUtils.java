package com.kyle.mycommon.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author yangkaile
 * @date 2019-05-30 08:55:13
 */
public class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class.getSimpleName());

    public static String getDate(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
    public static String getDate(){
        return getDate(new Date());
    }

    public static String getDateTime(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static String getDateTime(){
        return getDateTime(new Date());
    }

    public static String getTimeMask(Date date){
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(date);
    }

    public static String getTimeMask(){
        return getTimeMask(new Date());
    }

    public static void main(String[] args){
        logger.info(DateUtils.getDate());
        logger.info(DateUtils.getDateTime());
        logger.info(DateUtils.getTimeMask());
    }
}
