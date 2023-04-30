package com.dkit.LeeXuanOng.SD2A.DTOs;

import com.dkit.LeeXuanOng.SD2A.DTOs.Instrument;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TimeZone;
public class DeserializationInstrument implements JsonDeserializer<Instrument> {

    public Instrument deserialize(JsonElement json, Type typeof, JsonDeserializationContext context ) throws JsonParseException{

        JsonObject object =json.getAsJsonObject();

        int instrumentId = object.get("id").getAsInt();
        String insName = object.get("insName").getAsString();
        String insDesc = object.get("insDesc").getAsString();
        int insStock = object.get("insStock").getAsInt();
        double insPrice = object.get("insPrice").getAsDouble();
        String insCategory = object.get("insCategory").getAsString();

        Instrument instrument = new Instrument(instrumentId,insName,insDesc,insStock,insPrice,insCategory);
        return instrument;
    }
}
