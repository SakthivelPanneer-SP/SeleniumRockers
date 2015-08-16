package linkedInTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class LinkedInTestcaseTwo
{
	WrapperMain wrapper= new WrapperMain();
	List<String> excelValue=new ArrayList<String>();
  @Test
  public void linkedInTestcaseTwo()
  {
	  	wrapper.enterValueById("login-email",excelValue.get(0));
		wrapper.enterValueById("login-password",excelValue.get(1));
		wrapper.clickByName("submit");
		wrapper.verifyTextByXpath("//*[@id='identity']/section/div/div/h3/a", excelValue.get(2));
		
		wrapper.clickByLinkText("Advanced");
		wrapper.enterValueById("advs-keywords", "mumbai");
		wrapper.sendEnterKeysbyName("submit");
		String count = wrapper.getLinkName("//*[@id='results_count']/div/p/strong[1]");
		String cValue = wrapper.convertStringToInt(count);
		System.out.println(cValue);
		
		//System.out.println("Count = "+count);
		String linkName = wrapper.getLinkName("//*[@id='results']/li[1]/div/h3/a");
		System.out.println("Link name = "+linkName);
		String text = wrapper.getLinkName("//*[@id='results']/li[1]/div/h3/span/span/abbr");
		if(text.contains("2"))
		{
			wrapper.clickByXpath("//*[@id='results']/li[2]/div/div[3]/a");
		}
		else
		{
			System.out.println("Connect button / 2nd connection not exists");
		}
		wrapper.browserClose();
  }
  @BeforeMethod
  public void beforeMethod()
  {
	  //Read the Data from Excel
	  
	 excelValue=wrapper.readExcel("C:\\SeleniumRockers\\data\\Project_Locators_mithun.xlsx", "Sheet2");
	  		
  }

  @AfterMethod
  public void afterMethod()
  {
	  System.out.println("I am not loggedOut");
  }

  @BeforeClass
  public void beforeClass()
  {
	  //Launching the Browser
	 // wrapper.launchBrowser("https://www.linkedin.com/", "chrome");
	  wrapper.desiredcapabilitiesWindows("firefox",
				"http://192.168.1.2:4444/wd/hub", "https://www.linkedin.com/");
  }

  @AfterClass
  public void afterClass()
  {
	  // Close the Browser
	 wrapper.browserClose();
  }

 

}
