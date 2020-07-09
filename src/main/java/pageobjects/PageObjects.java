package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjects {
	public WebDriver driver;

	By search = By.xpath("//div[@class=\"searchBoxClearContainer\"]/input");
	By searchButton = By.id("searchButtonContainer");
	By certNum = By.xpath("//table[@class=\"list\"]/tbody/tr[2]/th/a");
	By detailsButton = By.xpath("//div[@class=\"mainContent\"]//ul/li[2]/a");
	By piwHover = By.xpath("//div[@class=\"listHoverLinks\"]/a/span[text()='Product Information Worksheets']");
	By piwStatus = By.xpath("//table[@class=\"list\"]/tbody/tr[2]/td[3]");

	public PageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement search() {
		return driver.findElement(search);
	}

	public WebElement searchButton() {
		return driver.findElement(searchButton);
	}

	public WebElement certNum() {
		return driver.findElement(certNum);
	}

	public WebElement detailsButton() {
		return driver.findElement(detailsButton);
	}

	public WebElement piwHover() {
		return driver.findElement(piwHover);
	}

	public WebElement piwStatus() {
		return driver.findElement(piwStatus);
	}

}
