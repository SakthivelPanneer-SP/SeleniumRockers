package linkedInTest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class LinkedInTestcaseOne {
	WrapperMain wrapper = new WrapperMain();
	List<String> excelValue = new ArrayList<String>();

	@Test
	public void linkedInTestcaseOne() throws IOException {
		wrapper.enterValueById("login-email", excelValue.get(0));
		wrapper.enterValueById("login-password", excelValue.get(1));
		//wrapper.enterValueById("login-email", "karim_94123@yahoo.co.in");
		//wrapper.enterValueById("login-password", "Test@123");
		wrapper.clickByName("submit");
		wrapper.verifyTextByXpath("//*[@id='identity']/section/div/div/h3/a",
		excelValue.get(2));
		//wrapper.verifyTextByXpath("//*[@id='identity']/section/div/div/h3/a","Karim");
	}

	@BeforeMethod
	public void beforeMethod() {
		// Read the Data from Excel

		excelValue = wrapper.readExcel(
	"C:\\SeleniumRockers\\data\\Project_Locators_mithun.xlsx",
		"Sheet2");

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("I am not loggedOut");
	}

	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		// Launching the Browser
		// wrapper.launchBrowser("https://www.linkedin.com/", "chrome");
		wrapper.desiredcapabilitiesWindows("chrome",
				"http://192.168.1.2:4444/wd/hub", "https://www.linkedin.com/");
	}

	@AfterClass
	public void afterClass() {
		// Close the Browser
		wrapper.browserClose();
	}

}
