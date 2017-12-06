package com.chinhvd;

import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ChinhVD on 12/5/17.
 */
@Configuration
public class ActuatorConfig {
    @Bean
    public InMemoryTraceRepository traceRepository() {
        InMemoryTraceRepository traceRepo = new InMemoryTraceRepository();
        traceRepo.setCapacity(1000);
        return traceRepo;
    } }
