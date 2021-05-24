package parsing_studentResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class testBase {
	public static ExtentReports report;
	public static ExtentTest logger;
	
	
	@BeforeClass
	public void createExtentReport() {
		
		String fileName = new SimpleDateFormat("'students_Report_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "student_Report/" + fileName;
		report = new ExtentReports(path);
	}
		
	
	@AfterClass
	public void close_ExtentReport() {
		report.endTest(logger);
		report.flush();	
		
	}
	
	
	}


