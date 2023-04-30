package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.Comparator;

public class InstrumentsPriceComparator implements Comparator<Instrument> {
    public int compare(Instrument i1, Instrument i2) {
        return Double.compare(i1.getInsPrice(), i2.getInsPrice());

    }
}