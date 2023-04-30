package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

import java.util.Comparator;

public class InstrumentsIDComparator implements Comparator<Instrument> {

    public int compare(Instrument i1, Instrument i2) {
        if(i1.getId() > i2.getId())
        {
            return 1;
        }
        else if(i1.getId() < i2.getId())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}
