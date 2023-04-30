package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.Comparator;

public class InstrumentsNameComparator implements Comparator<Instrument> {

    public int compare(Instrument i1, Instrument i2) {
        return i1.getInsName().compareTo(i2.getInsName());
    }

}
