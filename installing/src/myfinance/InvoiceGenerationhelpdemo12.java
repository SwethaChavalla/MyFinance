package myfinance;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class InvoiceGenerationhelpdemo12 extends InvoiceGenerationHelper {
	//WebDriver driver = new FirefoxDriver();

	public void SelectTaxInProfile(WebDriver driver) throws InterruptedException, BiffException, IOException{
		driver.findElement(By.xpath(".//*[@id='bs-example-navbar-collapse-1']/ul/li[4]/span/a")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("ServiceTax")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("ServiceTaxPercent")).sendKeys("10");
		Thread.sleep(2000);
		driver.findElement(By.id("btnSave")).click();
		ldr.logout(driver);
		}
		
	


public void setDate(WebDriver driver,String id,int year,int month,int day) throws InterruptedException{
	//System.out.println("setDate");
	Calendar c = Calendar.getInstance();
	c.set(Calendar.YEAR, year);
	c.set(Calendar.MONTH, month);
	c.set(Calendar.DAY_OF_MONTH, day);
	DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	Date datespecified = c.getTime();
	driver.findElement(By.id(id)).clear();
	Thread.sleep(2000);
	driver.findElement(By.id(id)).sendKeys(df.format(datespecified));
	Thread.sleep(2000);
}

}
