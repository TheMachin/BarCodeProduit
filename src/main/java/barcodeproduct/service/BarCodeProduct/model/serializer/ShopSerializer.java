package barcodeproduct.service.BarCodeProduct.model.serializer;

import barcodeproduct.service.BarCodeProduct.model.Shop;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ShopSerializer extends JsonSerializer<Shop> {
    @Override
    public void serialize(Shop value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeObject(value);
    }
}
