package com.nic.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PeriodTest {

    
    @org.junit.Test
    public void testGetPeriod(){
        for (int i = 1; i < 100; i++) {
            String periodName = this.getPeriodNameById(i);
            System.out.println(i + " - " + periodName + " - " + this.getPeriodIdByPeriodName(periodName));
        }
    }
    
    @org.junit.Test
    public void testGetPeriodId(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(0l));
        for (int i = 0; i < 100; i++) {
            calendar.add(Calendar.MONTH, 1);
            System.out.println(format.format(calendar.getTime()) + " - " + this.getPeriodIdByDate(calendar.getTime()) + " - " + this.getPeriodNameByDate(calendar.getTime()));
        }
    }

    /**
     * 根据期间编号获取期间名称
     * @param periodId
     * @return
     */
    public String getPeriodNameById(Integer periodId) {
        Integer season = periodId%4==0?4:periodId%4;
        Integer year = 1970 + (season==4?(int)Math.floor(periodId/4)-1:(int)Math.floor(periodId/4));
        return this.getPeriodName(year, season);
    }
    
    /**
     * 根据日期获取期间名称
     * @param date
     * @return
     */
    public String getPeriodNameByDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return this.getPeriodName(calendar.get(Calendar.YEAR), this.getSeason(date));
    }
    
    /**
     * 根据年、季度生成期间名称
     * @param year
     * @param season
     * @return
     */
    public String getPeriodName(Integer year, Integer season){
        return year + "年第" + season + "季度";
    }

    /**
     * 根据日期获取期间编号
     * @param date
     * @return
     */
    public Integer getPeriodIdByDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer periodId = (calendar.get(Calendar.YEAR)-1970)*4 + this.getSeason(date);
        return periodId;
    }
    
    /**
     * 根据期间名称获取期间编号
     * @param periodName
     * @return
     */
    public Integer getPeriodIdByPeriodName(String periodName){
        String yearStr = periodName.substring(0, 4);
        String seasonStr = periodName.substring(6, 7);
        
        Integer year = Integer.parseInt(yearStr);
        Integer season = Integer.parseInt(seasonStr);
        
        return (year-1970) * 4 + season;
    }
    
    /**
     * 根据日期获取季度
     * @param date
     * @return
     */
    public Integer getSeason(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int season = month%3==0?month/3:month/3+1;
        return season;
    }
}
