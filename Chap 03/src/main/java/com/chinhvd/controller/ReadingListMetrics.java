package com.chinhvd.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Metric;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import com.chinhvd.service.PublicMetrics;

@Component
public class ReadingListMetrics implements PublicMetrics {

  private JdbcOperations jdbc;

  @Autowired
  public ReadingListMetrics(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }
  
  @Override
  public Collection<Metric> metrics() {
    int count = jdbc.queryForObject(
        "select count(*) from Book", Integer.class);
    List<Metric> metrics = new ArrayList<Metric>();
    metrics.add(new Metric("books.count", count));
    return metrics;
  }
  
}
