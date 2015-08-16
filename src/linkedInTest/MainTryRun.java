package linkedInTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MainTryRun {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		
		desiredcapabilitiesWindows("iexplore", "http://10.0.0.49:4444/wd/hub");

	}
	
	public static  void desiredcapabilitiesWindows(String browser, String url)
			throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setBrowserName(browser);
		dc.setPlatform(Platform.WINDOWS);
		RemoteWebDriver driver = new RemoteWebDriver(new URL(url), dc);
		driver.get("https://www.linkedin.com/");
	}


}
