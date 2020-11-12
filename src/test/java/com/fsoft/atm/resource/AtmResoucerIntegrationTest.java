package com.fsoft.atm.resource;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class AtmResoucerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	void contextLoads() {
		assertThat(port).isNotNull();
		assertThat(restTemplate).isNotNull();
	}

	@Test
	@Order(2)
	public void deposit() {
		Map<Long, Long> deposit = new HashMap<>();
		deposit.put(1L, 1L);
		deposit.put(10L, -1L);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/deposit", deposit, String.class))
				.contains("Incorrect deposit amount");

		deposit = new HashMap<>();
		deposit.put(1L, 0L);
		deposit.put(10L, 0L);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/deposit", deposit, String.class))
				.contains("Deposit amount cannot be zero");

		deposit = new HashMap<>();
		deposit.put(10L, 8L);
		deposit.put(5L, 20L);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/deposit", deposit, String.class))
				.contains("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180");
		
		// 20s: 3, 5s: 18, 1s: 4
		deposit = new HashMap<>();
		deposit.put(20L, 3L);
		deposit.put(5L, 18L);
		deposit.put(1L, 4L);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/deposit", deposit, String.class))
		.contains("Balance : 200s=0,50s=0,20s=3,10s=8,5s=38,1s=4,Total =334");
		
		
	}

	@Test
	public void withdraw() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/withdraw/" + 0, String.class))
				.contains("Incorrect or insufficient funds");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/withdraw/" + -20, String.class))
				.contains("Incorrect or insufficient funds");

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/withdraw/" + 75, String.class))
				.contains("Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259");
		
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/withdraw/" + 122, String.class))
		.contains("Dispensed: 10s=7,5s=10,1s=2,\n"
				+ "Balance : 200s=0,50s=0,20s=0,10s=0,5s=27,1s=2,Total =137");
		

		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/withdraw/" + 297, String.class))
		.contains("Incorrect or insufficient funds");

	}

}
