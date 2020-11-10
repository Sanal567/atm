package com.fsoft.atm.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AtmResource {

    public String deposit(@RequestBody Map<Long, Long> deposit);

    public String withdraw(@PathVariable Long withdrwalAmount);

}
