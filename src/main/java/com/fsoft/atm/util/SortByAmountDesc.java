package com.fsoft.atm.util;

import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component
public class SortByAmountDesc implements Comparator<Long> {

    @Override
    public int compare(Long o1, Long o2) {
        if (o1 > o2)
            return -1;
        else if (o1 < o2)
            return 1;
        else
            return 0;
    }

}
