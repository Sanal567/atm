package com.fsoft.atm.service;

import java.util.Map;

public interface AtmService {
  
    public String deposit(Map<Long,Long> deposit);

    public String withdraw(Long withdrawlAmount);
}
