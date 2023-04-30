package com.dkit.LeeXuanOng.SD2A.FilterComparator;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;

public class InstrumentPriceFilter implements IFilter{

        private double max;
        private double min;
        public InstrumentPriceFilter(double min, double max) {
               this.max = max;
               this.min = min;
        }

        @Override
        public boolean matches(Object other) {
            Instrument i = (Instrument) other;
            return i.getInsPrice() >= min && i.getInsPrice() <= max;
        }
}
