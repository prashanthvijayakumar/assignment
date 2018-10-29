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
		
		pc.select_item_from_page("Magic Mouse");
		pc.add_to_cart_button();
		pc.verify_result(pc.get_alert_message(), "Item has been added to your cart!");
		
		hp.click_checkout_cart();
		
		cp.enter_quantity("5");
		cp.click_update_btn();
		cp.verify_result(cp.get_quantity(), "5");
		
		cp.enter_quantity("1");
		cp.click_update_btn();
		cp.verify_result(cp.get_quantity(), "1");
		
		cp.click_continue_btn();
		cp.enter_email_address(test_data.getData().get("email"));
		cp.enter_first_name(test_data.getData().get("first_name"));
		cp.enter_last_name(test_data.getData().get("last_name"));
		cp.enter_address(test_data.getData().get("address"));
		cp.enter_city(test_data.getData().get("city"));
		cp.enter_province(test_data.getData().get("province"));
		cp.select_country(test_data.getData().get("country"));
		cp.enter_postal_code(test_data.getData().get("postal_code"));
		cp.enter_phone_number(test_data.getData().get("phone_number"));
		cp.select_same_as_billing_address_checkbox();
		
		cp.click_purchase_button();
		
		cp.verify_result(cp.get_result_message(), "Thank you, your purchase is pending.");
		
		Thread.sleep(5000);
	}
}
