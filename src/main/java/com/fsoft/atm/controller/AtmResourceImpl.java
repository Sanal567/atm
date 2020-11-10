package com.fsoft.atm.controller;

import java.util.HashMap;
import java.util.Map;

import com.fsoft.atm.service.AtmServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmResourceImpl implements AtmResource {

    @Autowired
    private AtmServiceImpl atmService;


    @Override
    @PostMapping("/deposit")
    public String deposit(@RequestBody Map<Long, Long> deposit) {
        return atmService.deposit(deposit);
    }

    @Override
    @GetMapping("/withdraw/{withdrawlAmount}")
    public String withdraw(@PathVariable Long withdrawlAmount) {
        return atmService.withdraw(withdrawlAmount);
    }

    @GetMapping("/")
    public Map<Long, Long> testMapInput() {
        Map<Long, Long> map = new HashMap<>();
        map.put(1L, 1L);
        map.put(2L, 2L);
        return map;
    }

}
