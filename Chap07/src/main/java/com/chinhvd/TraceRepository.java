package com.chinhvd;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.tools.Trace;

/**
 * Created by ChinhVD on 12/5/17.
 */
public interface TraceRepository {
    List<Trace> findAll();
    void add(Map<String, Object> traceInfo);
}
