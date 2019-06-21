package com.monster.model.entity.base;

import com.monster.utils.DataUtil;
import java.text.SimpleDateFormat;
import java.util.*;
import lombok.Data;

/**
 * @author Administrator
 * @Date 2019/6/21 0021
 * 日期查询转换类 曲线救国
 */
@Data
public class BaseDate {

    private Date lastY;
    private Date nextY;

    private Date lastYm;
    private Date nextYm;

    private Date lastYmd;
    private Date nextYmd;

    private Date lastYmdH;
    private Date nextYmdH;

    private Date lastYmdHm;
    private Date nextYmdHm;

    private Date lastYmdHms;
    private Date nextYmdHms;

    private Date curDate;

    public static BaseDate of(String dateStr){
        BaseDate baseDate = new BaseDate();
        if(DataUtil.isEmptyObj(dateStr)){
            return baseDate;
        }
        SimpleDateFormat sdf = null;
        try {
            if(dateStr.contains("/")){
                if(dateStr.length() == 4){
                    sdf = new SimpleDateFormat("yyyy");
                }else if(dateStr.length() == 7){
                    sdf = new SimpleDateFormat("yyyy/MM");
                }else if(dateStr.length() == 10){
                    sdf = new SimpleDateFormat("yyyy/MM/dd");
                }else if(dateStr.length() == 13){
                    sdf = new SimpleDateFormat("yyyy/MM/dd HH");
                }else if(dateStr.length() == 16){
                    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                }else{
                    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                }
            }else if(dateStr.contains("-")){
                if(dateStr.length() == 4){
                    sdf = new SimpleDateFormat("yyyy");
                }if(dateStr.length() == 7){
                    sdf = new SimpleDateFormat("yyyy-MM");
                }if(dateStr.length() == 10){
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }else if(dateStr.length() == 13){
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH");
                }else if(dateStr.length() == 16){
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                }else{
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                }
            }
            baseDate.setCurDate(sdf.parse(dateStr));

           return baseDate.calculateDate(baseDate);
        }catch (Exception e){
            throw new RuntimeException("字符串转时间错误");
        }
    }

    private BaseDate calculateDate(BaseDate baseDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获得各种时间节点
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate.getCurDate());

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int millisecond = calendar.get(Calendar.SECOND);

        calendar.setTime(sdf.parse(year+"-01-01 00:00:00"));
        baseDate.setLastY(calendar.getTime());
        calendar.add(Calendar.YEAR,1);
        baseDate.setNextY(calendar.getTime());

        calendar.setTime(sdf.parse(year+"-"+month+ "-01 00:00:00"));
        baseDate.setLastYm(calendar.getTime());
        calendar.add(Calendar.MONTH,1);
        baseDate.setNextYm(calendar.getTime());

        calendar.setTime(sdf.parse(year+"-"+month+"-"+day+" 00:00:00"));
        baseDate.setLastYmd(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH,1);
        baseDate.setNextYmd(calendar.getTime());

        calendar.setTime(sdf.parse(year+"-"+month+"-"+day+" "+hour+":00:00"));
        baseDate.setLastYmdH(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY,1);
        baseDate.setNextYmdH(calendar.getTime());

        calendar.setTime(sdf.parse(year+"-"+month+"-"+day+" "+hour+":"+minute+":00"));
        baseDate.setLastYmdHm(calendar.getTime());
        calendar.add(Calendar.MINUTE,1);
        baseDate.setNextYmdHm(calendar.getTime());

        calendar.setTime(sdf.parse(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+ millisecond));
        baseDate.setLastYmdHms(calendar.getTime());
        calendar.add(Calendar.SECOND,1);
        baseDate.setNextYmdHms(calendar.getTime());

        return baseDate;
    }
}
