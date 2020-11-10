package com.fsoft.atm.service;

import java.util.Map;

import com.fsoft.atm.dao.AtmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {

  private boolean isAllDenominationsZero;

  @Autowired
  private AtmRepository atmRepository;

  @Override
  public String deposit(Map<Long, Long> deposit) {
    isAllDenominationsZero = true;

    for (Map.Entry<Long, Long> entry : deposit.entrySet()) {

      if (entry.getValue() < 0) {
        return "Incorrect deposit amount";

      } else if (entry.getValue() == 0)
        continue;

      else
        isAllDenominationsZero = false;

    }

    if (isAllDenominationsZero)
      return "Deposit amount cannot be zero";
    else
      return atmRepository.deposit(deposit);
  }

  @Override
  public String withdraw(Long withdrawlAmount) {
    if (withdrawlAmount == 0 || withdrawlAmount < 0 || atmRepository.getTotalAmount() < withdrawlAmount)
      return "Incorrect or insufficient funds";
    else
      return atmRepository.withdraw(withdrawlAmount);
  }

}
