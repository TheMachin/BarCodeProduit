package barcodeproduct.service.BarCodeProduct.model.serializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import barcodeproduct.service.BarCodeProduct.model.Compagny;

import java.io.IOException;

public class CompagnySerializer extends JsonSerializer<Compagny> {
        @Override
        public void serialize(Compagny value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeObject(value);
        }
}
