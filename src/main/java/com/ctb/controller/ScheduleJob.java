package com.ctb.controller;

import com.ctb.common.CxyOrderService;
import com.ctb.util.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * Created by jayway on 2017/6/27.
 */
@Configuration
@Controller
public class ScheduleJob {
    private Logger logger = Logger.getLogger(ScheduleJob.class);
    @Autowired
    private CxyOrderService orderService;
    @Scheduled(cron = "0 0 */4 * * ?") //每4小时执行一次，限定6次
    public void scheduler() {

        System.out.println("定时任务开始: "+ DateUtils.formatDate(new Date()));
    }

}
