package resources;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BasePage {

	public WebDriver driver;
	public Properties prop;

	public WebDriver initializeBrowser() throws IOException {

		String userProfile = "C:\\Users\\SM5034533\\AppData\\Local\\Google\\Chrome\\User Data";
		System.setProperty("webdriver.chrome.driver", "D:\\Shirisha Workspace\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--user-data-dir=" + userProfile);
		options.addArguments("--profile-directory=Default");
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://fedexcompatible.my.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("Login")).click();

		return driver;
	}
}
