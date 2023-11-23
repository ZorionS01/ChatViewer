package com.szw.chatblog.config;

import com.szw.chatblog.task.LikeTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ChatViewer
 */
@Configuration
public class QuartzConfig {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";

    @Bean
    public JobDetail quartzDetail(){
        // 添加LikeTask
        return JobBuilder
                //Quartz 提供的一个用于创建 JobDetail 的构造器模式。LikeTask.class 是要执行的任务的类，通常是实现了 org.quartz.Job 接口的类。
                .newJob(LikeTask.class)
                //设置 JobDetail 的身份信息
                .withIdentity(LIKE_TASK_IDENTITY)
                //确保任务的持久性，即使没有触发器与之关联，任务的定义也会保留在 Quartz 调度器中
                .storeDurably().build();
    }

    @Bean
    public Trigger quartzTrigger(){
        //创建简单的时间表（调度规则）
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                //设置触发任务的时间间隔
                .withIntervalInMinutes(5)
                //任务将一直重复触发，直到明确取消
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(quartzDetail())
                .withIdentity(LIKE_TASK_IDENTITY)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
