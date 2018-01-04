package barcodeproduct.service.BarCodeProduct.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ProductLayer {

    private static final String REST_SERVICE_URI = "https://api.productlayer.com/0.5/" ;
    private static final String API_KEY = "6f44e61e-6f02-488f-b731-25c0ce9e6b43" ;


    public JsonObject getProductWithGtin(String gtin) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-KEY", API_KEY);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> respEntity = restTemplate.exchange(REST_SERVICE_URI + "/product/" + gtin, HttpMethod.GET, entity, String.class);
            JsonObject jsonObject = new JsonParser().parse(respEntity.getBody()).getAsJsonObject();
            return jsonObject;
        }
        catch (final HttpClientErrorException e) {
            System.out.println(e.getStatusCode());
            System.out.println(e.getResponseBodyAsString());
        }
        return null;
    }

}
