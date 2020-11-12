package com.fsoft.atm.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fsoft.atm.util.SortByAmountDesc;

@ExtendWith(MockitoExtension.class)
public class AtmRespositoryTest {

	@MockBean
	private SortByAmountDesc sortByAmountDesc = new SortByAmountDesc();
	@InjectMocks
	private AtmRepository atmRepository;

	@Order(1)
	@Test
	public void getAvailableQuntitiesInAtm() {
		assertEquals("Balance : 200s=0,50s=0,20s=0,10s=0,5s=0,1s=0,Total =0",
				atmRepository.getAvailableQuntitiesInAtm());
	}

	@Test
	@Order(2)
	public void deposit() {
		Map<Long, Long> deposit = new HashMap<>();
		deposit.put(10L, 8L);
		deposit.put(5L, 20L);
		assertEquals("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180", atmRepository.deposit(deposit));

		// 20s: 3, 5s: 18, 1s: 4
		deposit = new HashMap<>();
		deposit.put(20L, 3L);
		deposit.put(5L, 18L);
		deposit.put(1L, 4L);
		assertEquals("Balance : 200s=0,50s=0,20s=3,10s=8,5s=38,1s=4,Total =334", atmRepository.deposit(deposit));

		assertEquals("Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259",
				atmRepository.withdraw(75L));

		assertEquals("Dispensed: 10s=7,5s=10,1s=2,\n" + "Balance : 200s=0,50s=0,20s=0,10s=0,5s=27,1s=2,Total =137",
				atmRepository.withdraw(122L));
	}

//	@Test
//	@Order(3)
//	public void withdraw() {
//		
//		assertEquals("Dispensed: 20s=3,10s=1,5s=1,\n"
//				+ "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259", atmRepository.withdraw(75L));
//
//		assertEquals("Dispensed: 10s=7,5s=10,1s=2,\n"
//				+ "Balance : 200s=0,50s=0,20s=0,10s=0,5s=27,1s=2,Total =137", atmRepository.withdraw(122L));
//		
//	}

	@Test
	@Order(4)
	public void test() {
		assertThrows(NumberFormatException.class, ()-> {Integer.parseInt("One");});
	}
	

}
