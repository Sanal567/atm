package com.fsoft.atm.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Repository;

import com.fsoft.atm.util.SortByAmountDesc;

@Repository
public class AtmRepository {

    private long totalAmount = 0L;
    private int currentDenominationIndex;
    private List<Long> availabe$Bills = Arrays.asList(200L, 50L, 20L, 10L, 5L, 1L);

//    @Autowired
//    private SortByAmountDesc sortByAmountDesc;

    private Map<Long, Long> amountAndQuantity = new TreeMap<>(new SortByAmountDesc());
    private Map<Long, Long> withdrawlDenominations = new TreeMap<>(new SortByAmountDesc());
    private String response;

    // 200, 50, 20, 10, 5, and 1 dollar bills
    protected AtmRepository() {
        amountAndQuantity.put(1L, 0L);
        amountAndQuantity.put(5L, 0L);
        amountAndQuantity.put(10L, 0L);
        amountAndQuantity.put(50L, 0L);
        amountAndQuantity.put(200L, 0L);
        amountAndQuantity.put(20L, 0L);
    }

    public String deposit(Map<Long, Long> deposit) {

        deposit.entrySet().stream().forEach(entry -> {
            amountAndQuantity.put(entry.getKey(), amountAndQuantity.get(entry.getKey()) + entry.getValue());
            totalAmount += entry.getKey() * entry.getValue();
        });

        return getAvailableQuntitiesInAtm();
    }

    public String withdraw(long withdrawlAmount) {
        withdrawRecursion(withdrawlAmount, availabe$Bills.get(currentDenominationIndex++));
        return response;
    }

    protected void withdrawRecursion(Long withdrawlAmount, Long denminations) {
        long withDrawlAmountInHiger$s = withdrawlAmount / denminations;
        long availabe$sQuantityInATM = amountAndQuantity.get(denminations);
      
        if (withDrawlAmountInHiger$s > 0 && availabe$sQuantityInATM > 0) {
            long remainingAmount = 0;

            if (availabe$sQuantityInATM >= withDrawlAmountInHiger$s) {
                withdrawDenomination(denminations, withDrawlAmountInHiger$s);
                remainingAmount = withdrawlAmount % denminations;

            } else {
                withdrawDenomination(denminations, availabe$sQuantityInATM);
                remainingAmount = withdrawlAmount - availabe$sQuantityInATM * denminations;
            }
            if (remainingAmount > 0)
                withdrawRecursion(remainingAmount, availabe$Bills.get(currentDenominationIndex++));
            else
                produceWithdawalResponse();

        } else if (denminations == 1) {
            withdrawlDenominations = new TreeMap<>(new SortByAmountDesc());
            currentDenominationIndex = 0;
            response = "No possible quantities to dispense specified amount";
        } else
            withdrawRecursion(withdrawlAmount, availabe$Bills.get(currentDenominationIndex++));

    }

    protected void withdrawDenomination(Long denminations, Long withDrawlAmountInDenomination$s) {
        amountAndQuantity.put(denminations, amountAndQuantity.get(denminations) - withDrawlAmountInDenomination$s);

        withdrawlDenominations.put(denminations, withDrawlAmountInDenomination$s);
        totalAmount -= denminations * withDrawlAmountInDenomination$s;
    }

    protected void produceWithdawalResponse() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dispensed: ");

        withdrawlDenominations.entrySet().stream().forEach(entry -> {
            builder.append(entry.getKey() + "s=" + entry.getValue() + ",");
        });

        // System.out.println();
        withdrawlDenominations = new TreeMap<>( new SortByAmountDesc());
        currentDenominationIndex = 0;
        response = builder.append("\n" + getAvailableQuntitiesInAtm()).toString();
    }

    protected String getAvailableQuntitiesInAtm() {
        StringBuilder responseAvailableQuantities = new StringBuilder();
        responseAvailableQuantities.append("Balance : ");

        amountAndQuantity.entrySet().stream().forEach(entry -> {
            responseAvailableQuantities.append(entry.getKey() + "s=" + entry.getValue() + ",");
        });

        responseAvailableQuantities.append("Total =" + totalAmount);
        return responseAvailableQuantities.toString();
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    protected String test() throws ArithmeticException{
    	throw new ArithmeticException();
    }
    
    
}
