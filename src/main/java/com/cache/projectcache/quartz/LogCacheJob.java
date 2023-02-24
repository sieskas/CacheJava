package com.cache.projectcache.quartz;

import com.cache.projectcache.service.ICacheService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogCacheJob implements Job {

    private final ICacheService cacheService;

    @Autowired
    public LogCacheJob(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("LogCacheJob.execute");
        cacheService.getAllCache().forEach(System.out::println);
    }
}
