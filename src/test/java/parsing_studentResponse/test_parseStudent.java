package parsing_studentResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class test_parseStudent extends testBase {

	JSONObject obj;

	@BeforeTest()
	public void test_parsestudent() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();

		obj = (JSONObject) parser.parse(new FileReader(".\\src\\test\\java\\parsing_studentResponse\\students.json"));

	}

	// Find value of username

	@Test(priority=1)
	public void test_Find_value_of_username() {
		logger = report.startTest("test_Find_value_of_username Test ");
		logger.log(LogStatus.INFO, "test_Find_value_of_username Test is started");
		
		// get username
		String username = (String) obj.get("username");
		Assert.assertEquals("aa@m.com", username);

		System.out.println("username=" + username + " is displayed");
		logger.log(LogStatus.INFO, "username " + username + " is displayed");
		logger.log(LogStatus.PASS, "test_username test is passed");
	}

	// Find values of all sessionid
	 @Test(priority=2)
	public void test_get_All_sessionid_AndLast_sessionId() {

		logger = report.startTest("test_get_All_sessionid_AndLast_sessionId Test ");
		logger.log(LogStatus.INFO, "test_get_All_sessionid_AndLast_sessionId Test is started");

		// printing all session ids
		JSONArray jarr = (JSONArray) obj.get("sessionid");
		int size = jarr.size();
		System.out.println("sessionid Array size : " + size);
		System.out.println("All sessionids are :");
		for (Object ja : jarr) {
			
			 int lastSession=Integer.parseInt(ja.toString());
			 System.out.print(lastSession + " ");
		}
        String lastSessionid= jarr.get(size - 1).toString();
        int lastSession=Integer.parseInt(lastSessionid);
		System.out.println("\nLast sessionid= " + lastSessionid);

		logger.log(LogStatus.INFO, "All sessionids printed");
		logger.log(LogStatus.INFO, "Last sessionid is printed");
		logger.log(LogStatus.PASS, "test_get_All_sessionid_AndLast_sessionId test is passed");

	}

	// Find all marks of second student
	 @Test(priority=3)
	public void test_Find_all_marks_of_second_student() {

		logger = report.startTest("test_Find_all_marks_of_second_student Test ");
		logger.log(LogStatus.INFO, "test_Find_all_marks_of_second_student Test is started");

		// printing all marks of second student

		JSONArray jarr = (JSONArray) obj.get("students");// 20 25 22

		JSONObject Std2Obj = (JSONObject) jarr.get(1);
		JSONArray Std_marks = (JSONArray) Std2Obj.get("marks");
		System.out.println("All marks of second student test is printed");
		for (Object mark : Std_marks) {
			System.out.print(mark.toString() + " ");
		}

		logger.log(LogStatus.INFO, "All marks of second student is printed");
		logger.log(LogStatus.PASS, "test_Find_all_marks_of_second_student Test is passed");
	}

	// Find the second state value of first student

	@Test(priority=4)
	public void test_Find_secondstate_Value_of_First_student() {

		logger = report.startTest("test_Find_secondstate_Value_of_First_student test ");
		logger.log(LogStatus.INFO, "test_Find_secondstate_Value_of_First_student test is started");

		// printing the second state value of first student

		JSONArray jarr = (JSONArray) obj.get("students");// 20 25 22

		JSONObject first_stdObj = (JSONObject) jarr.get(0);
		JSONArray addressArr = (JSONArray) first_stdObj.get("adress");
		JSONObject addr2 = (JSONObject) addressArr.get(1);

		String addr2State = (String) addr2.get("state");
		System.out.println("\nThe second state value of first student:" + addr2State);// ca

		logger.log(LogStatus.INFO, "The second state value of first student:" + addr2State + " is printed");
		logger.log(LogStatus.PASS, "test_Find_secondstate_Value_of_First_student test is passed");
	}

	// Find the second contact value of second student
	 @Test(priority=5)
	public void test_Find_second_contact_value_of_second_student() {

		logger = report.startTest("test_Find_second_contact_value_of_second_student test ");
		logger.log(LogStatus.INFO, "test_Find_second_contact_value_of_second_student test is started");

		// Find the second contact value of second student

		JSONArray jarr = (JSONArray) obj.get("students");// 20 25 22

		JSONObject sec_stdObj = (JSONObject) jarr.get(1);// second student object
		JSONArray contactArr = (JSONArray) sec_stdObj.get("contact");// second student's contacts
		String secContact = (String) contactArr.get(1);// second student's second contact

		System.out.println("\nsecond contact value of second student:" + secContact);// "3456"

		logger.log(LogStatus.INFO, "test_Find_second_contact_value_of_second_student:" + secContact + " is printed");
		logger.log(LogStatus.PASS, "test_Find_second_contact_value_of_second_student is passed");
	}

	// Find all cities of second student
	 @Test(priority=6)
	public void test_Find_all_cities_of_second_student() {

		logger = report.startTest("test_Find_all_cities_of_second_student test ");
		logger.log(LogStatus.INFO, "test_Find_all_cities_of_second_student test is started");

		// Find all cities of second student

		JSONArray jarr = (JSONArray) obj.get("students");

		JSONObject sec_stdObj = (JSONObject) jarr.get(1);// second student's object
		JSONArray addrArr = (JSONArray) sec_stdObj.get("adress");

		System.out.println("\nAll cities of second student");// abc,xyz
		for (int i = 0; i < addrArr.size(); i++) {
			JSONObject cityObj = (JSONObject) addrArr.get(i);
			String city = (String) cityObj.get("city");
			System.out.println("city " + (i + 1) + ":" + city);
		}

		logger.log(LogStatus.INFO, "test_Find_all_cities_of_second_student is printed");
		logger.log(LogStatus.PASS, "test_Find_all_cities_of_second_student is passed");
	}

	// Find contacts of all students
	@Test(priority=7)
	public void test_Find_contacts_of_all_students() {

		logger = report.startTest(" test_Find_contacts_of_all_students test ");
		logger.log(LogStatus.INFO, " test_Find_contacts_of_all_students test is started");

		// Find contacts of all students

		JSONArray st_jarr = (JSONArray) obj.get("students");// students array

		System.out.println("Contacts of all students is :");
		int i = 0;
		for (Object std : st_jarr) {
			i++;
			JSONObject contactsObj = (JSONObject) std;// contact object
			System.out.println("\nContacts of student" + i + " is ");
			JSONArray contactsArr = (JSONArray) contactsObj.get("contact");// contacts array

			for (Object con : contactsArr) {

				String contacts = con.toString();
				System.out.print(contacts + " ");
			}

		}

		logger.log(LogStatus.INFO, "Contacts of all students is printed");
		logger.log(LogStatus.PASS, "test_Find_contacts_of_all_students is passed");
	}
//Find adress of first student

	@Test(priority=8)
	public void test_Find_adress_of_first_student() {

		logger = report.startTest("test_Find_adress_of_first_student Test ");
		logger.log(LogStatus.INFO, "test_Find_adress_of_first_student Test is started");

		// Finding adress of first student

		JSONArray jarr = (JSONArray) obj.get("students");

		JSONObject sec_stdObj = (JSONObject) jarr.get(0);// First student's object
		JSONArray addrArr = (JSONArray) sec_stdObj.get("adress");// first student's address array

		System.out.println("\n \nAdress of first student: ");
		for (int i = 0; i < addrArr.size(); i++) {
			System.out.println(addrArr.get(i));
		}

		logger.log(LogStatus.INFO, "Adress of first student is printed");
		logger.log(LogStatus.PASS, "test_Find_all_cities_of_second_student Test is passed");
	}

}