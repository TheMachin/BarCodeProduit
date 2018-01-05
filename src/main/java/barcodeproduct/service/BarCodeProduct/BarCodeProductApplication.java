package barcodeproduct.service.BarCodeProduct;

import barcodeproduct.service.BarCodeProduct.api.ProductLayer;
import com.google.gson.JsonObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BarCodeProductApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(BarCodeProductApplication.class, args);
	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(BarCodeProductApplication.class);
	}
}
