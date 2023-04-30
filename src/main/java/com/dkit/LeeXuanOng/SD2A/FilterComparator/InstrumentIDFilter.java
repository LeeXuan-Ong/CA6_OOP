package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

public class InstrumentIDFilter implements IFilter{

    private int id;
    public InstrumentIDFilter(int id) {
           this.id = id;
    }

    @Override
    public boolean matches(Object other) {
        Instrument i = (Instrument) other;
        return i.getId() == id;
    }
}
