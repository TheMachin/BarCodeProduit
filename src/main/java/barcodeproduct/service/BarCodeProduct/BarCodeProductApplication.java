package barcodeproduct.service.BarCodeProduct;

import barcodeproduct.service.BarCodeProduct.api.ProductLayer;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarCodeProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarCodeProductApplication.class, args);


		/*ProductLayer productLayer = new ProductLayer();
		JsonObject jsonObject = productLayer.getProductWithGtin("3760259930332");
		System.out.println(jsonObject.toString());*/

	}
}
