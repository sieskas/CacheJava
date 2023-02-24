package com.cache.projectcache.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(LogCacheJob.class).withIdentity("cacheJob").storeDurably().build();
    }

    @Bean
    public Trigger myTrigger(JobDetail job) {
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("myTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20).repeatForever())
                .build();
    }
}
