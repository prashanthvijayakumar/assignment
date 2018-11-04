package com.rbc.gam.scenarios;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rbc.gam.pages.CheckOutPage;
import com.rbc.gam.pages.HomePage;
import com.rbc.gam.pages.ProductCategoryPage;
import com.rbc.gam.utilis.TestData;

public class PlaceOrderTest extends InitTest {

	@Test (priority = 0)
	public void place_order() throws Exception {
		
		try {
			HomePage hp = new HomePage(init_elements);
			ProductCategoryPage pc = new ProductCategoryPage(init_elements);
			CheckOutPage cp = new CheckOutPage(init_elements);
			
			// get the data pool values
			
			String json_text = new String(Files.readAllBytes(Paths.get(".\\datapool\\TestData.json")), StandardCharsets.UTF_8);
			
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting(); 
			  
			Gson gson = builder.create(); 
			TestData test_data = gson.fromJson(json_text, TestData.class); 
			logger.info("TCName: "+test_data.getTCName());
				    
			hp.navigate_accessories_tab();
			
			pc.addItemToCart(test_data.getData().get("item"));
			pc.verify_result(pc.get_alert_message(), "Item has been added to your cart!");
			
			hp.click_checkout_cart();
			
			cp.update_quantity(test_data.getData().get("quantity_test"));
			cp.verify_result(cp.get_quantity(), test_data.getData().get("quantity_test"));
			
			cp.update_quantity(test_data.getData().get("quantity"));
			cp.verify_result(cp.get_quantity(), test_data.getData().get("quantity"));
			
			cp.enter_shipment_details_and_purchase(test_data);
			cp.verify_result(cp.get_result_message(), "Thank you, your purchase is pending.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("Place order test failed."+ e.getMessage());
			throw new Exception("Place order test failed."+ e.getMessage());
		}
	}
}
