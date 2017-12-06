package com.chinhvd.service;

import java.util.Collection;

import org.springframework.data.geo.Metric;

/**
 * Created by ChinhVD on 12/5/17.
 */
public interface PublicMetrics {
    Collection<Metric> metrics();
}
