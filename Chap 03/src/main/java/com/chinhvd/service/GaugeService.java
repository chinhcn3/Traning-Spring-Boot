package com.chinhvd.service;

/**
 * Created by ChinhVD on 12/5/17.
 */
public interface GaugeService {
    void submit(String metricName, double value);
}
