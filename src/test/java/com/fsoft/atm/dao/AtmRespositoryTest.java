package com.fsoft.atm.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fsoft.atm.util.SortByAmountDesc;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
public class AtmRespositoryTest extends AtmRepository {

    @MockBean
    private SortByAmountDesc sortByAmountDesc = new SortByAmountDesc();
    @InjectMocks
    private AtmRepository atmRepository;

    @Order(0)
    @Test
    public void testgetAvailableQuntitiesInAtm() {
        assertEquals("Balance : 1s=0,5s=0,10s=0,20s=0,50s=0,200s=0,Total =0",
                atmRepository.getAvailableQuntitiesInAtm());
    }

    public void produceWithdawalResponse() {

    }

}
