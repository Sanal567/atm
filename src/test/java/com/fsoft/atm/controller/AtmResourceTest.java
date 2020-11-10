package com.fsoft.atm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsoft.atm.service.AtmServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AtmResource.class)
public class AtmResourceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AtmServiceImpl atmService;

    @Test
    public void desposit() throws Exception {
        Map<Long, Long> deposit = new HashMap<>();
        deposit.put(1L, 1L);
        deposit.put(10L, -1L);

        ObjectMapper mapper = new ObjectMapper();

        when(atmService.deposit(Mockito.any(HashMap.class))).thenReturn("Incorrect deposit amount");

        this.mockMvc
                .perform(post("/deposit").content(mapper.writeValueAsString(deposit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("Incorrect deposit amount"));

    }





}
