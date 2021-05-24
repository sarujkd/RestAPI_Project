package RentalCarsMocking;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;

import org.testng.annotations.BeforeTest;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;


import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RentalCarsApiTests extends wiremock_mapping   {
	Response res;

	@BeforeTest
	public void get_RentalCarsResponse() {
		
		//String Url="http://localhost:3000";
		String Url="http://localhost:8000";
		RestAssured.baseURI=Url;
		res=RestAssured.given().when().contentType("application/json").get("/rentalCars");
		
		//Getting all cars response and statusCode
		System.out.println("Get Cars response \n"+res.asString());
		System.out.println("Status code : "+res.statusCode());
		Assert.assertTrue(res.statusCode()== 200);
	}
	
	@Test(priority=1,enabled=true)
	public void test_01_get_BlueTeslaCarsWithNotes() {
		
		logger=report.startTest("test_01_get_BlueTeslaCarsWithNotes Test");
		logger.log(LogStatus.INFO, "test_01_get_BlueTeslaCarsWithNotes Test is started");
		System.out.println("test_01_get_BlueTeslaCarsWithNotes Test is started");
		//Storing all cars make
		List<String> blueTeslaCars=res.jsonPath().get("Car.make");
		System.out.println("There are "+ blueTeslaCars.size()+" cars \n"+blueTeslaCars);
		logger.log(LogStatus.INFO, "Got all cars make");
		
		//Checking Tesla cars with blue color
		String carName="Tesla";
		String carColor="Blue";
		int index=0;
		String blueColor="";
		
		System.out.println("\nBlue Tesla cars with notes");
		logger.log(LogStatus.INFO, "Got all Tesla cars with blue color and notes");
		for( int i=0;i<blueTeslaCars.size();i++) {
		
			if(blueTeslaCars.get(i).equalsIgnoreCase(carName)) {
				 blueColor=res.jsonPath().getString("Car["+i+"].metadata.Color");
				
				if(blueColor.equalsIgnoreCase(carColor)) {
				index=i;
				}
				System.out.println("*"+res.jsonPath().getString("Car["+index+"].metadata.Notes"));
			}		
			}	
		
		Assert.assertTrue(res.asString().contains("Tesla"));
		logger.log(LogStatus.PASS, "test_01_get_BlueTeslaCarsWithNotes is passed");
		
		System.out.println("TestCase1 is passed\n*****************************************\n");	
	}
	
	@Test(priority=2,enabled=true)
	
	public void test_02_get_LowestCarPriceAndLowestDiscount() {
		
		logger=report.startTest("test_02_get_LowestCarPriceAndLowestDiscount test");
		
		System.out.println(" test_02_get_LowestCarPriceAndLowestDiscount test is started");
		logger.log(LogStatus.INFO, "test_02_get_LowestCarPriceAndLowestDiscount test is started");
		
		
		List<String> perdayrent =res.jsonPath().getList("Car.perdayrent");
		int perdayrentSize=perdayrent.size();
		
		System.out.println("All cars price :\n"+perdayrent);
		logger.log(LogStatus.INFO, "All cars perdayrent is listed");
		
		List<perdayrentPrice> perdayrent_Price = new ArrayList<perdayrentPrice>();
		List<priceAfterDiscount> perdayrent_Discount = new ArrayList<priceAfterDiscount>();
		
		for(int i=0;i<perdayrentSize;i++) {
			
			String vin=res.jsonPath().get("Car["+i+"].vin");
			float price=res.jsonPath().get("Car["+i+"].perdayrent.Price");
			float Discount=res.jsonPath().get("Car["+i+"].perdayrent.Discount");
			
			//Caclulating price after discount
			float priceAfterDiscount=price-(price*Discount/100);
		
			//Add price and Discount in their list with vin number
			
			perdayrent_Price.add(new perdayrentPrice(vin,price));
			perdayrent_Discount.add(new priceAfterDiscount(vin,priceAfterDiscount));
		}
		
		
		Collections.sort(perdayrent_Price);
		logger.log(LogStatus.INFO, "All cars price is listed in ascending order");

		Collections.sort( perdayrent_Discount);
		logger.log(LogStatus.INFO, "Cars price after discount is listed in ascending order");
		
		//Printing of all cars having lowest Price
		System.out.println("\n*************Printing all cars having lowest Price***********");
		logger.log(LogStatus.INFO, "Printed all cars having lowest Price");
		Iterator<perdayrentPrice> price_it=perdayrent_Price.iterator();
		while( price_it.hasNext()) {
			perdayrentPrice priceObj=(perdayrentPrice)price_it.next();
			System.out.println("Vin: "+priceObj.vin+" ----- And -----Price: "+priceObj.Price);
		}
		
		//Printing of all cars having lowest price after discount
				System.out.println("\n*************Printing all cars having lowest Discount***********");
				logger.log(LogStatus.INFO, "Printed all cars having lowest Discount");
				
				Iterator<priceAfterDiscount> discount_it=perdayrent_Discount.iterator();
				while( discount_it.hasNext()) {
					priceAfterDiscount discountObj=(priceAfterDiscount)discount_it.next();
					System.out.println("Vin: "+discountObj.vin+"------And--------Discount: "+discountObj.priceAfter_Discount);
				}
				
				
				
		System.out.println("Testcase2 is passed\n ***************************\n");
		
		logger.log(LogStatus.PASS, "test_02_get_LowestCarPriceAndLowestDiscount test is passed");
		
}

	@Test(priority=2,enabled=true)
	
	public void test_03_printAllCarsWith_HighestRevenue() {
		
		logger=report.startTest("test_03_printAllCarsWith_HighestRevenue Test");
		
		System.out.println("test_03_printAllCarsWith_HighestRevenue Test is started");
		logger.log(LogStatus.INFO, "test_03_printAllCarsWith_HighestRevenue Test is started");
		
		
		List<String> metrics =res.jsonPath().getList("Car.metrics");
		int metricsSize=metrics.size();
		
		System.out.println("All cars matrics :\n"+metrics);
		logger.log(LogStatus.INFO, "All cars matrics is listed");
		
		ArrayList<carRevenue>car_Revenue = new ArrayList<carRevenue>();
		
		
		for(int i=0;i< metricsSize;i++) {
			
			String vin=res.jsonPath().get("Car["+i+"].vin");
			float price=res.jsonPath().get("Car["+i+"].perdayrent.Price");
			float Discount=res.jsonPath().get("Car["+i+"].perdayrent.Discount");
			
			float yoymaintenancecost =res.jsonPath().get("Car["+i+"].metrics.yoymaintenancecost");
			float depreciation=res.jsonPath().get("Car["+i+"].metrics.depreciation");
			int yeartodate=res.jsonPath().get("Car["+i+"].metrics.rentalcount.yeartodate");
			
			//Caclulating price after discount
			float priceAfterDiscount=price-(price*Discount/100);
		
			float carRevenue=(priceAfterDiscount*yeartodate)-(yoymaintenancecost+ depreciation);
			
			//Adding carRevenue in the list
			
			car_Revenue.add(new carRevenue(vin,carRevenue));
			
		}
		
		
		Collections.sort(car_Revenue);
		logger.log(LogStatus.INFO, "All cars Revenue is listed in descending order");

		
		//Printing of all cars having highest Revenue
		System.out.println("\n*************Printing all cars having highest Revenue***********");
		logger.log(LogStatus.INFO, "Printed all cars having highest Revenue");
		
		Iterator<carRevenue> revenue_it=car_Revenue.iterator();
		
		while(revenue_it.hasNext()) {
			carRevenue revenueObj=(carRevenue)revenue_it.next();
			System.out.println("Vin: "+revenueObj.vin+" ------ And -----Revenue: "+revenueObj.carRevenue);
		}
				
		System.out.println("\ntest_03_printAllCarsWith_HighestRevenue Test is passed"
				+ "\n***********************************************\n");
		
		logger.log(LogStatus.PASS, "test_03_printAllCarsWith_HighestRevenue Test is passed");
		
}


}