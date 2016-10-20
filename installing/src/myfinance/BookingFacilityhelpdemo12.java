package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BookingFacilityhelpdemo12 extends BookingFacilityHelper{
	WebDriver driver = new FirefoxDriver();
	int year = 2016;
	int month = 9;
	int day = 10;
	
	
	public void DueDate(WebDriver driver, String days){
		Select duedate =new Select(driver.findElement(By.id("TypeOfDueDate")));
		duedate.selectByIndex(2);
		driver.findElement(By.id("DueDate")).sendKeys(days);
	
	}
	
	
	public void BookFacility(WebDriver driver) throws InterruptedException, BiffException, IOException {
		Thread.sleep(2000);
		File fs = new File("C:/Users/Swetha/Desktop/Financial Scenarios Test data for DEMO12/Copy of Generate Fixed Invoice.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(3);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
			}
		}
		//String[][] bookeddate = new String[1][1];
		for (int i = 0; i < rows; i++) {
			String URL1 = inputdata[i][0];
			String facilityname1= inputdata[i][1];
			String fromtime1 =inputdata[i][2];
			String totime1 =inputdata[i][3];
			String blockno1 =inputdata[i][4];
			String apartmentno1 = inputdata[i][5];
			String description1 =inputdata[i][6];
		driver.navigate().to(URL1);
		Thread.sleep(4000);
		driver.findElement(By.id("searchgrid")).click(); // search
		Thread.sleep(4000);
		driver.findElement(By.xpath("//option[contains(.,'Facility Name')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg1")).sendKeys(facilityname1);
		Thread.sleep(4000);
		driver.findElement(By.id("fbox_Grid_search")).click();// find button
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																						// button
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='1']/td[12]/a/button")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("Add")).click();
		Thread.sleep(5000);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date datespecified = c.getTime();
		driver.findElement(By.id("FromDate")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("FromDate")).sendKeys(df.format(datespecified));
		Thread.sleep(2000);
		//String bookeddate1 = driver.findElement(By.id("FromDate")).getText();
		//bookeddate [0][0]=bookeddate1;
		driver.findElement(By.id("FromTime")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("FromTime")).sendKeys(fromtime1);
		Thread.sleep(2000);
		driver.findElement(By.id("ToDate")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("ToDate")).sendKeys(df.format(datespecified));
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='FacilityBooking']/div/div[2]/div/div/div/div/div[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("ToTime")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("ToTime")).sendKeys(totime1);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_BlockID")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("auto_BlockID")).sendKeys(blockno1);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_ApartmentID")).sendKeys(apartmentno1);
		Thread.sleep(3000);
		driver.findElement(By.id("Description")).sendKeys(description1);
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='FacilityBooking']/div/div[3]/button[2]")).submit();
		Thread.sleep(2000);
		Alert alert2 = driver.switchTo().alert();
		Thread.sleep(2000);
		String message3 = alert2.getText();
		System.out.println(message3);
		Thread.sleep(4000);
		alert2.accept();
		Thread.sleep(2000);
		WebElement header = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[1]/h3"));
		String value = header.getText();
		Assert.assertEquals(value, "Facility Availability");
		Thread.sleep(2000);
		System.out.println("Facility booked successfully Invoice no. generated");
		}
		
		
		
	}
	public String ToVerifyGeneratedVoucherno(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(4000);
		driver.findElement(
				By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i"))
				.click();// voucher type
		Thread.sleep(4000);
		driver.findElement(By.linkText("Facility Booking")).click();
		Thread.sleep(4000);
		String FIGvoucherno = driver.findElement(By.xpath(".//*[@id='1']/td[6]")).getText();
		System.out.println("Generated Facility Booked Voucherno : " +FIGvoucherno);
		String duedate = driver.findElement(By.xpath(".//*[@id='1']/td[11]")).getText();
		System.out.println("Duedate to pay amount  " + duedate);
		String Voucherno = driver.findElement(By.xpath(".//*[@id='1']/td[3]")).getText();
		System.out.println("Voucher No: " + Voucherno );
		driver.navigate().refresh();
		return FIGvoucherno;
	}
	

}
