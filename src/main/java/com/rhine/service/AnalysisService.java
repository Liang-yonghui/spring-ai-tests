package com.rhine.service;

import reactor.core.publisher.Flux;

public interface AnalysisService {

    public Flux<String> analysis(String text);
}
