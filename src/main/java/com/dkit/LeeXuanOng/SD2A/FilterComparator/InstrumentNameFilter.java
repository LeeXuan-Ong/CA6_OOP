package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

public class InstrumentNameFilter implements IFilter {

    private String name;
    public InstrumentNameFilter(String name) {
           this.name = name;
    }

    @Override
    public boolean matches(Object other) {
        Instrument i = (Instrument) other;
        return i.getInsName().equalsIgnoreCase(name);
    }
}
