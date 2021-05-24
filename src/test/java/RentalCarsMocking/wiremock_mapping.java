package RentalCarsMocking;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.github.tomakehurst.wiremock.WireMockServer;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class wiremock_mapping {

	public static final int PORT = 8000;
	public static final String HOST = "localhost";
	private static WireMockServer wiremockServer = new WireMockServer(PORT);
	public static ExtentReports report;
	public static ExtentTest logger;

	@BeforeSuite
	public void setUp() {

		wiremockServer.start();
		ResponseDefinitionBuilder mockResponse = new ResponseDefinitionBuilder();
		mockResponse.withStatus(200);

		WireMock.configureFor(HOST, PORT);
		WireMock.stubFor(WireMock.get(urlEqualTo("/rentalCars"))
				.willReturn(aResponse().withStatus(200).withBodyFile("jsonResponse/cars.json")));
	}

	@AfterSuite
	public void tearDown_WiremockServer() {
		if (wiremockServer != null && wiremockServer.isRunning())
			wiremockServer.shutdown();
	}

	@BeforeClass
	public void createExtentReport() {

		String fileName = new SimpleDateFormat("'TeslaCarsReport_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "Report/" + fileName;
		report = new ExtentReports(path);
	}

	@AfterClass
	public void close_ExtentReport() {
		report.endTest(logger);
		report.flush();

	}

}
