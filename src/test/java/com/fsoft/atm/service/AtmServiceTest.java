package com.fsoft.atm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fsoft.atm.dao.AtmRepository;

@ExtendWith(MockitoExtension.class)
public class AtmServiceTest {
	private Map<Long, Long> deposit;

	@Mock
	private AtmRepository atmRepository;
	@InjectMocks
	private AtmServiceImpl atmService;

	@Test
	public void depositWithNegativeAmount() {
		deposit = new HashMap<>();
		deposit.put(1L, 1L);
		deposit.put(10L, -1L);
		assertEquals("Incorrect deposit amount", atmService.deposit(deposit));
	}

	@Test
	public void depositAmountCannotBeZero() {
		deposit = new HashMap<>();
		deposit.put(1L, 0L);
		deposit.put(10L, 0L);
		assertEquals("Deposit amount cannot be zero", atmService.deposit(deposit));
	}

	// @Disabled
	@Test
	public void depostPositiveTestCase() {
		deposit = new HashMap<>();
		deposit.put(10L, 8L);
		deposit.put(5L, 20L);
		when(atmRepository.deposit(deposit)).thenReturn("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180");
		assertEquals("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180", atmService.deposit(deposit));
	}

	@Test
	public void withdraw() {
		assertEquals("Incorrect or insufficient funds", atmService.withdraw(-20L));
		assertEquals("Incorrect or insufficient funds", atmService.withdraw(0L));

		when(atmRepository.getTotalAmount()).thenReturn(333L);
		when(atmRepository.withdraw(75L)).thenReturn(
				"Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259");
		assertEquals("Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259",
				atmService.withdraw(75L));
	}

}
