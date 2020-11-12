package com.fsoft.atm.resource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.atm.service.AtmServiceImpl;

@WebMvcTest(AtmResource.class)
public class AtmResourceTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AtmServiceImpl atmService;

	@Test
	public void desposit() throws Exception {
		Map<Long, Long> deposit = new HashMap<>();
		deposit.put(10L, 8L);
		deposit.put(5L, 20L);

		ObjectMapper mapper = new ObjectMapper();
		when(atmService.deposit(deposit)).thenReturn("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180");

        this.mockMvc
                .perform(post("/deposit").content(mapper.writeValueAsString(deposit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Balance : 200s=0,50s=0,20s=0,10s=8,5s=20,1s=0,Total =180"));
	}
	
	@Test
	public void withdraw() throws Exception {
		when(atmService.withdraw(75L)).thenReturn("Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259");
		this.mockMvc.perform(get("/withdraw/{withdrawlAmount}", 75)).andExpect(status().isOk()).andExpect(content().string("Dispensed: 20s=3,10s=1,5s=1,\n" + "Balance : 200s=0,50s=0,20s=0,10s=7,5s=37,1s=4,Total =259"));
	}

}
