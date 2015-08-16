package linkedInTest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;



public class LinkedInTestcaseThree
{
	WrapperMain wrapper= new WrapperMain();
	List<String> excelValue=new ArrayList<String>();
  @Test
  public void linkedInTestcaseOne() throws InterruptedException
  {
	    wrapper.enterValueById("login-email",excelValue.get(0));
		wrapper.enterValueById("login-password",excelValue.get(1));
		wrapper.clickByName("submit");
		wrapper.verifyTextByXpath("//*[@id='identity']/section/div/div/h3/a", excelValue.get(2));
		wrapper.clickByLinkText("Advanced");
		wrapper.clickByXpath("//*[@id='advs']/div[1]/button");
		wrapper.clickByXpath("//*[@id='pivot-bar']/ul/li[3]/button");
		Thread.sleep(5000);
		wrapper.clickByXpath("//*[@id='pivot-bar']/ul/li[2]/button");
		Thread.sleep(5000);
		String count = wrapper
				.getLinkName("//*[@id='results_count']/div/p/strong[1]");
		Thread.sleep(5000);
		String cValue = wrapper.convertStringToInt(count);
		System.out.println("cValue   : " + cValue);
		String vercount = wrapper
				.getLinkName("//*[@id='facet-N']/fieldset/div/ol/li[2]/div/span");
		String afterRemove = wrapper.removeBracketSymbols(vercount);
		if (count.equals(afterRemove)) {
			System.out.println("1st connection value matches:" + afterRemove);

		} else {
			System.out.println("1st connection value not matches" + afterRemove);
		}
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
	  //wrapper.launchBrowser("https://www.linkedin.com/", "chrome");
	  wrapper.desiredcapabilitiesWindows("chrome",
				"http://192.168.1.2:4444/wd/hub", "https://www.linkedin.com/");
  }

  @AfterClass
  public void afterClass()
  {
	  // Close the Browser
	 wrapper.browserClose();
  }

 

}
