package com.graphql.deliveryShare2.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;

@Component
public class ReportDataFetcher {
    @Autowired
    private ReportRepository reportRepository;
  
    public DataFetcher<?> allReports () {
      return environment -> {
        return reportRepository.findAll();
      };
    }
  
    public DataFetcher<?> Report () {
      return environment -> {
        int seq = environment.getArgument("seq");
        return reportRepository.findBySeq(seq);
      };
    }
  
}
