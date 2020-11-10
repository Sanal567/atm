package com.fsoft.atm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import com.fsoft.atm.dao.AtmRepository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AtmServiceTest {
    private Map<Long, Long> deposit;

    @Mock
    private AtmRepository atmRepository;
    @InjectMocks
    private AtmServiceImpl atmService;

    @Test
    public void testDepositWithNegativeAmount() {
        deposit = new HashMap<>();
        deposit.put(1L, 1L);
        deposit.put(10L, -1L);
        assertEquals("Incorrect deposit amount", atmService.deposit(deposit));
    }

    @Test
    public void depositAmountCannotBeZero(){
        deposit = new HashMap<>();
        deposit.put(1L, 0L);
        deposit.put(10L, 0L);
        assertEquals("Deposit amount cannot be zero", atmService.deposit(deposit));
    }

    
    @Disabled
    @Test
    public void depostPositiveTestCase(){
        deposit = new HashMap<>();
        deposit.put(10L, 8L);
        deposit.put(5L, 20L);
        when(atmRepository.deposit(deposit)).thenReturn("value");
        assertEquals("expected", atmService.deposit(deposit));
    }


}
