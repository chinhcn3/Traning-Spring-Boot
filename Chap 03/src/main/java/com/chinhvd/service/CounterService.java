package com.chinhvd.service;

/**
 * Created by ChinhVD on 12/5/17.
 */
public interface CounterService {
    void increment(String metricName);
    void decrement(String metricName);
    void reset(String metricName);
}
