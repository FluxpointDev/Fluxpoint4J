package ch.andre601.fluxpoint4j.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ColorObjectSerializer implements JsonSerializer<ColorObject>{
    @Override
    public JsonElement serialize(ColorObject src, Type typeOfSrc, JsonSerializationContext context){
        return new JsonPrimitive(src.getColor());
    }
}
