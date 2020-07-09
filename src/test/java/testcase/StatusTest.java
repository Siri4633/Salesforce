package testcase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import resources.BasePage;

public class StatusTest extends BasePage {
	public static Logger log = LogManager.getLogger(BasePage.class.getName());
	public WebDriver driver;

	@BeforeTest
	public void initializer() throws IOException {
		driver = initializeBrowser();
		log.info("Driver is initialized");
	}

	@Test
	public void getData() throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\SM5034533\\Desktop\\Excel\\PIW.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheetCount = wb.getNumberOfSheets();
		FileOutputStream fos;

		for (int i = 0; i < sheetCount; i++) {
			if (wb.getSheetName(i).equalsIgnoreCase("PIW tracker")) {
				XSSFSheet sh = wb.getSheetAt(i);
				Iterator<Row> rows = sh.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();

				int column = 0;
				int column1 = 0;
				int column2 = 0;

				int k = 0;
				int x = 0;
				int y = 0;
				while (cells.hasNext()) {
					Cell value = cells.next();
					if (value.getStringCellValue().equalsIgnoreCase("PIW Cert Number")) {
						column = k;
					}

					if (value.getStringCellValue().equalsIgnoreCase("Current Status of PIW")) {

						column1 = x;
					}
					if (value.getStringCellValue().equalsIgnoreCase("PIW Id")) {

						column2 = y;
					}
					k++;
					x++;
					y++;
				}
				System.out.println("PIW Cert Number column is:" + column);
				System.out.println("Current Status of PIW column is:" + column1);
				System.out.println("PIW Id column is:" + column2);

				while (rows.hasNext()) {
					Row r = rows.next();
					String certNum;
					Cell row1 = r.getCell(column1);// status col
					Cell row2 = r.getCell(column2);// piw id
					Cell temp = r.getCell(column);// piw certt col
					if (temp != null) {

						if (temp.getStringCellValue().contains("Cert")) {

							certNum = temp.getStringCellValue();
							// System.out.println(certNum);

							driver.findElement(By.xpath("//div[@class=\"searchBoxClearContainer\"]/input"))
									.sendKeys(certNum);
							driver.findElement(By.id("searchButtonContainer")).click();
							driver.findElement(By.xpath("//table[@class=\"list\"]/tbody/tr[2]/th/a")).click();
							driver.findElement(By.xpath("//div[@class=\"mainContent\"]//ul/li[2]/a")).click();
							driver.findElement(By.xpath(
									"//div[@class=\"listHoverLinks\"]/a/span[text()='Product Information Worksheets']"))
									.click();
							WebElement piwStatus = driver
									.findElement(By.xpath("//table[@class=\"list\"]/tbody/tr[2]/td[3]"));
							WebElement piwid = driver.findElement(
									By.xpath("//div[@class=\"listRelatedObject Custom21Block\"]//tbody/tr[2]/th[1]/a"));

							String status = piwStatus.getText();
							String id = piwid.getText();

							System.out.println(certNum + " " + status + " " + id);

							row1.setCellValue(status);
							row2.setCellValue(id);

						} else {
							row1.setCellValue("--");
							row2.setCellValue("--");
						}

					}
					// idhar?
				}
			}

		}
		fos = new FileOutputStream("C:\\Users\\SM5034533\\Desktop\\Excel\\PIW.xlsx");
		wb.write(fos);

	}

	@AfterTest
	public void tearDown() {
		driver.close();
		log.info("Driver is closed");

	}

}
