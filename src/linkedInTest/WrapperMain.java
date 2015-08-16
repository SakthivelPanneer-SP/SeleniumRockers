package linkedInTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author LENOVO
 *
 */
/**
 * @author LENOVO
 *
 */
public class WrapperMain {
	RemoteWebDriver driver;

	/**
	 * @param url
	 * @param browser
	 * 
	 */
	public void launchBrowser(String url, String browser) {

		try {
			if (browser.contains("IE")) {
				System.setProperty("webdriver.ie.driver",
						"C:\\SeleniumRockers\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (browser.contains("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\SeleniumRockers\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else {
				driver = new FirefoxDriver();
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Browser ca");
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param url
	 */
	public void launchUrl(String url) {

		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Browser ca");
		} finally {
			takeSnap();
		}
	}

	/**
	 * close()
	 * 
	 */
	public void browserClose() {
		try {
			driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public List<String> readExcel(String filepath, String nameofsheet) {
		// Create a path
		File file = new File(filepath);

		FileInputStream fis = null;
		XSSFWorkbook workBook = null;
		XSSFSheet sheet = null;
		List<String> excelvalue = new ArrayList<String>();
		try {
			fis = new FileInputStream(file);
			workBook = new XSSFWorkbook(fis);
			sheet = workBook.getSheet(nameofsheet);

			// Navigate to the row

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);

				for (int j = 0; j <= row.getLastCellNum(); j++) {

					// System.out.println(row.getCell(j));
					String a = row.getCell(j).getStringCellValue();
					if (null != a) {
						excelvalue.add(row.getCell(j).getStringCellValue());
					}

				}
			}

		} catch (FileNotFoundException e) {
			System.out
					.println(" FileNotFoundException : The file is not found please check the path : "
							+ e.getLocalizedMessage());
		} catch (IOException e) {
			System.out
					.println("IOException : The file is not found please check the path : "
							+ e.getLocalizedMessage());
		} catch (Exception e) {
			System.out
					.println("Exception : The file is not found please check the path : "
							+ e.getLocalizedMessage());
		}

		return excelvalue;
		// writeExcel("C:\\Users\\PriShan\\workspace\\Java1\\data\\Product.xlsx",
		// "Sheet2", excelvalue);

	}

	public List<WebElement> webElementListByTagName(String tagname) {
		return driver.findElements(By.tagName(tagname));
	}

	/**
	 * quit()
	 * 
	 * @
	 */
	public void browserQuit() {
		try {
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param id
	 * @param data
	 */
	public void enterValueById(String id, String data) {
		try {
			// driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	public void sendEnterKeysbyName(String text) {
		try {
			WebElement webelement = driver.findElement(By.name(text));
			webelement.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param name
	 * @param data
	 *            @
	 */
	public void enterValueByName(String name, String data) {
		try {
			driver.findElement(By.name(name)).clear();
			driver.findElement(By.name(name)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param linktext
	 * @param data
	 */
	public String convertStringToInt(String count) {
		String a = null;

		// count.replaceAll(",","");
		try {
			NumberFormat format = NumberFormat.getIntegerInstance();
			Number number = format.parse(count);
			a = number.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;

	}

	public String removeBracketSymbols(String comma) {
		// (45)
		System.out.println("comma val :: " + comma);
		String temp = null;
		try {
			if (null != comma) {
				temp = comma.replace("(", "");
				temp = temp.replace(")", "");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public void enterValueByLinkText(String linktext, String data) {
		try {
			driver.findElement(By.linkText(linktext)).clear();
			driver.findElement(By.linkText(linktext)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param tagname
	 * @param data
	 */
	public void enterValueByTagName(String tagname, String data) {
		try {
			driver.findElement(By.tagName(tagname)).clear();
			driver.findElement(By.tagName(tagname)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param classname
	 * @param data
	 */
	public void enterValueByClassName(String classname, String data) {
		try {
			driver.findElement(By.className(classname)).clear();
			driver.findElement(By.className(classname)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param linktext
	 * @param data
	 */
	public void enterValueByPartialLinkText(String linktext, String data) {
		try {
			driver.findElement(By.partialLinkText(linktext)).clear();
			driver.findElement(By.partialLinkText(linktext)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param selector
	 * @param data
	 */
	public void enterValueByCssSelector(String selector, String data) {
		try {
			driver.findElement(By.cssSelector(selector)).clear();
			driver.findElement(By.cssSelector(selector)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param xpath
	 * @param data
	 */
	public void enterValueByXpath(String xpath, String data) {
		try {
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param id
	 *            @
	 */
	public void clickById(String id) {
		try {
			driver.findElement(By.id(id)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param name
	 *            @
	 */
	public void clickByName(String name) {
		try {
			driver.findElement(By.name(name)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param linktext
	 *            @
	 */
	public void clickByLinkText(String linktext) {
		try {
			driver.findElement(By.linkText(linktext)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param tagname
	 *            @
	 */
	public void clickByTagName(String tagname) {
		try {
			driver.findElement(By.tagName(tagname)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param classname
	 *            @
	 */
	public void clickByClassName(String classname) {
		try {
			driver.findElement(By.className(classname)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param linktext
	 *            @
	 */
	public void clickByPartialLinkText(String linktext) {
		try {
			driver.findElement(By.partialLinkText(linktext)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param selector
	 *            @
	 */
	public void clickByCssSelector(String selector) {
		try {
			driver.findElement(By.cssSelector(selector)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}
	}

	/**
	 * @param xpath
	 *            @
	 */
	public void clickByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}

	}

	/**
	 * @param urltext
	 *            @
	 */
	public void verifyUrl(String urltext) {
		try {
			String url = driver.getCurrentUrl();
			if (url.contains(urltext)) {
				System.out.println("URL Verfied");
				System.out.println("Current URL: " + url);
			} else {
				System.out.println("URL Not Verfied");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}

	}

	/**
	 * @param titletext
	 */
	public void verifyTitle(String titletext) {
		try {
			String title = driver.getTitle();
			if (title.contains(titletext)) {
				System.out.println("URL Verfied");
				System.out.println("Current URL: " + title);
			} else {
				System.out.println("URL Not Verfied");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}

	}

	/**
	 * @param tagname
	 * @param text
	 */
	public void verifyLinkHref(String tagname, String text) {
		try {
			int count = 0;

			List<WebElement> lnk = driver.findElements(By.tagName(tagname));
			for (WebElement link : lnk) {
				String cnt = link.getAttribute("href");
				if (cnt.contains(text)) {
					System.out.println("Url: " + cnt);
					count++;

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}

	}

	/**
	 * @param tagname
	 * @param text
	 */
	public void verifyLinkText(String tagname, String text) {
		int count = 0;

		List<WebElement> lnk = driver.findElements(By.tagName(tagname));
		for (WebElement link : lnk) {
			String cnt = link.getText();
			if (cnt.contains(text)) {
				System.out.println("Url: " + cnt);
				count++;

			}
		}

	}

	/**
	 * @param id
	 * @param text
	 */
	public void verifyTextById(String id, String text) {

		String msg = driver.findElement(By.id(id)).getText();
		if (msg.contains(text)) {
			System.out.println("Verified Text: " + msg);
		} else {
			System.out
					.println("Text Not Verified and Expected text not available");
		}

	}

	/**
	 * @param name
	 * @param text
	 */
	public void verifyTextByName(String name, String text) {

		String msg = driver.findElement(By.name(name)).getText();
		if (msg.contains(text)) {
			System.out.println("Verified Text: " + msg);
		} else {
			System.out
					.println("Text Not Verified and Expected text not available");
		}

	}

	/**
	 * @param xpath
	 * @param text
	 */
	public void verifyTextByXpath(String xpath, String text) {

		try {
			String msg = driver.findElement(By.xpath(xpath)).getText();
			if (msg.contains(text)) {
				System.out.println("Verified Text: " + msg);
			} else {
				System.out
						.println("Text Not Verified and Expected text not available");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			takeSnap();
		}

	}

	/**
	 * @param className
	 * @param text
	 */
	public void verifyTextByClassName(String className, String text) {

		String msg = driver.findElement(By.className(className)).getText();
		if (msg.contains(text)) {
			System.out.println("Verified Text: " + msg);
		} else {
			System.out
					.println("Text Not Verified and Expected text not available");
		}

	}

	/**
	 * @param tagname
	 * @param text
	 */
	public void verifyTextByTagName(String tagname, String text) {

		String msg = driver.findElement(By.tagName(tagname)).getText();
		if (msg.contains(text)) {
			System.out.println("Verified Text: " + msg);
		} else {
			System.out
					.println("Text Not Verified and Expected text not available");
		}

	}

	/**
	 * @param webElement
	 * @param sExpectedText
	 * @return @
	 */
	public boolean verifyText(WebElement webElement, String sExpectedText) {
		if (isEmptyOrNull(webElement.getText()))
			return false;

		String sActualText = webElement.getText();
		if (sActualText.equalsIgnoreCase(sExpectedText))
			return true;
		else
			return false;
	}

	/**
	 * @param sInputText
	 * @return
	 */
	public boolean isEmptyOrNull(String sInputText) {
		return sInputText.length() == 0 || sInputText == null;
	}

	public void getTitlePrint() {

		System.out.println(driver.getTitle());

	}

	/**
	 * 
	 */
	public void getUrlPrint() {
		System.out.println(driver.getCurrentUrl());

	}

	/**
	 * @param id
	 * @param index
	 */
	public void selectValueByIndex(String id, int index) {

		WebElement drpdown = driver.findElement(By.id(id));
		Select list = new Select(drpdown);
		list.selectByIndex(index);
	}

	/**
	 * @param id
	 * @param value
	 */
	public void selectValueByValue(String id, String value) {

		WebElement drpdown = driver.findElement(By.id(id));
		Select list = new Select(drpdown);
		list.selectByValue(value);
	}

	/**
	 * @param id
	 * @param text
	 */
	public void selectValueByVisibleText(String id, String text) {

		WebElement drpdown = driver.findElement(By.id(id));
		Select list = new Select(drpdown);
		list.selectByVisibleText(text);
	}

	/**
	 * @param name
	 * @param index
	 */
	public void selectIndexByElementName(String name, int index) {

		WebElement drpdown = driver.findElement(By.name(name));
		Select list = new Select(drpdown);
		list.selectByIndex(index);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void selectValueByElementName(String name, String value) {

		WebElement drpdown = driver.findElement(By.name(name));
		Select list = new Select(drpdown);
		list.selectByValue(value);
	}

	/**
	 * @param name
	 * @param text
	 */
	public void selectVisibleTextByElementName(String name, String text) {

		WebElement drpdown = driver.findElement(By.name(name));
		Select list = new Select(drpdown);
		list.selectByVisibleText(text);
	}

	Alert alert;

	public void getAlertText() {
		try {
			alert.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}

	}

	public void printAlertText() {
		try {
			System.out.println("Alert Message: " + alert.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}
	}

	/**
	 * @param data
	 */
	public void sendKeysAlert(String data) {
		try {
			alert.sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}
	}

	public void acceptAlert() {
		try {
			alert.accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}

	}

	public void dismissAlert() {
		try {
			alert.dismiss();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}
	}

	public void switchtoAlertAccept() {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// takeSnap();
		}

	}

	public void switchtoAlertDismiss() {
		driver.switchTo().alert().dismiss();

	}

	/**
	 * @param tagname
	 */
	public void switchToFrameElementByTagName(String tagname) {
		driver.switchTo().frame(driver.findElement(By.tagName(tagname)));

	}

	/**
	 * @param id
	 */
	public void switchToFrameElementByid(String id) {
		driver.switchTo().frame(driver.findElement(By.id(id)));

	}

	/**
	 * 
	 */
	public void switchToFirstFrame() {
		driver.switchTo().frame(0);
	}

	/**
	 * @param id
	 */
	public void switchToFrameById(String id) {
		driver.switchTo().frame(id);
	}

	/**
	 * @param name
	 */
	public void switchToFrameByName(String name) {
		driver.switchTo().frame(name);
	}

	/**
	 * @param name
	 */
	public void switchToFrameElementByName(String name) {
		driver.switchTo().frame(driver.findElement(By.name(name)));

	}

	/**
	 * @param xpath
	 */
	public void switchToFrameElementByXpath(String xpath) {
		driver.switchTo().frame(driver.findElement(By.xpath(xpath)));

	}

	/**
	 * @param name
	 */
	public void findTotalFrameByTagName(String name) {
		List<WebElement> total = driver.findElements(By.tagName(name));
		System.out.println("Total count: " + total.size());

	}

	public void switchToPrimaryWindow() {
		driver.switchTo().defaultContent();
	}

	/**
	 * 
	 */
	public void getCurrentWindowId() {
		String currentwindow = driver.getWindowHandle();
		System.out.println("Current Window ID: " + currentwindow);
	}

	/**
	 * 
	 */
	public void getTotalWindowId() {
		Set<String> total = driver.getWindowHandles();
		System.out.println("Total Windows IDs: " + total);
	}

	/**
	 * 
	 */
	public void getTotalWindowSize() {
		Set<String> total = driver.getWindowHandles();
		System.out.println("Total Windows Count: " + total.size());
	}

	/**
	 * 
	 */
	public void switchToNextWindow() {
		Set<String> total = driver.getWindowHandles();
		for (String handle : total) {
			driver.switchTo().window(handle);

		}

	}

	/**
	 * @throws InterruptedException
	 */
	public void switchToLastWindow() throws InterruptedException {
		String parent = driver.getWindowHandle();
		Set<String> total = driver.getWindowHandles();
		int i = 0;

		for (String string : total) {
			for (i = 1; i < total.size(); i++) {
				if (!string.equals(parent)) {

					driver.switchTo().window(string);

				}
			}
			Thread.sleep(3000);
			i++;
		}

	}

	/**
	 * @throws InterruptedException
	 */
	public void CloseAllSubWindow() throws InterruptedException {
		String parent = driver.getWindowHandle();
		Set<String> total = driver.getWindowHandles();
		int i = 0;

		for (String string : total) {
			for (i = 1; i < total.size(); i++) {
				if (!string.equals(parent)) {

					driver.switchTo().window(string);

				}
			}
			Thread.sleep(3000);
			i++;
			driver.close();
		}

	}

	public void takeSnap() {

		try {
			File snap = (File) driver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(
					snap,
					new File("C:\\SeleniumRockers\\Screenshots\\"
							+ System.currentTimeMillis() + ".jpg"));
		} catch (FileNotFoundException e) {
			System.out
					.println("IOException when reading file" + e.getMessage());
		} catch (IOException e) {
			System.out
					.println("IOException when reading file" + e.getMessage());
		} catch (Exception e) {
			System.out
					.println("IOException when reading file" + e.getMessage());
		}
	}

	public int elementSize(String xpath) {
		int count = driver.findElementsByXPath(xpath).size();

		return count;
	}

	public String getLinkName(String xpath) {
		String linkname = driver.findElementByXPath(xpath).getText();

		return linkname;
	}

	public void verifyCountByXpath(int count, String xpath) {

		if (count > 1) {
			clickByLinkText(xpath);
		} else {
			System.out.println("count is equal to 1");
		}

	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredcapabilitiesWindows(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesMac(String browser, String huburl, String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.MAC);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesAndroid(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.ANDROID);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesXp(String browser, String huburl, String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.XP);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesVista(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.VISTA);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesLinux(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.LINUX);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesUnix(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.UNIX);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesWin8(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WIN8);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesWin81(String browser, String huburl,
			String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.WIN8_1);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}

	/**
	 * @param browser
	 * @param huburl
	 * @param url
	 */
	public void desiredCapabilitiesAny(String browser, String huburl, String url) {

		try {
			DesiredCapabilities dc = new DesiredCapabilities();

			dc.setBrowserName(browser);
			dc.setPlatform(Platform.ANY);
			driver = new RemoteWebDriver(new URL(huburl), dc);

			launchUrl(url);
		} catch (Exception e) {

		}
	}
}
