package myfinance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Scenario5 {
	WebDriver driver = new FirefoxDriver();
	BookingFacilityHelper help1 = new BookingFacilityHelper();
	String facilityname = "theater7";
	String contactperson = "swetha";
	String contactnumber = "88765432";
	String hour = "1";
	String amount = "1000";
	String URL = "https://test-itsmyaccount.azurewebsites.net/Facility";
	String fromtime = "04:00 PM ";
	String totime = "05:00 PM";
	String blockno = "Yuva";
	String apartmentno = "Y/2";
	String description = "Booking facility";
	String voucherno;
	String editamount = "400";
	String editamount2 = "600";
	

	@Test(priority = 1)
	public void Login() throws InterruptedException {
		logindetails ldr = new logindetails();
		ldr.adminlogin(driver, "DEMO_12", "DEMO_12");
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(3000);
	}
@Test (priority = 2)
public void GenerateFixedInvoice() throws InterruptedException {
	driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Invoice");
	Thread.sleep(4000);
	driver.findElement(By.id("Fixed")).click();
	Thread.sleep(4000);
	driver.findElement(
			By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
			.click();
	Thread.sleep(4000);
	driver.findElement(By.linkText("Yuva")).click();
	Thread.sleep(2000);
	driver.findElement(
			By.id("auto_ApartmentID")).sendKeys("Y/2");
	Thread.sleep(2000);
	driver.findElement(By.id("BA1")).clear();
	Thread.sleep(2000);
	driver.findElement(By.id("BA1")).sendKeys("600");// basic
														// amount
	Thread.sleep(2000);
	driver.findElement(By.id("x1")).sendKeys("Generting Invoice");// narration
	Thread.sleep(2000);
	driver.findElement(By.id("Gen")).click(); // Generate button
	Thread.sleep(5000);
	driver.findElement(By.id("Generate")).click();// yes button
	Thread.sleep(2000);
	driver.findElement(By.id("GenInv")).click(); // generate invoice
													// button
	Thread.sleep(3000);
	Alert alert = driver.switchTo().alert();
	Thread.sleep(2000);
	String message = alert.getText();
	System.out.println(message);
	alert.accept();
	String splitmessage = message.split(" ")[2];
	System.out.println("Maintanance fixed invoice no." + splitmessage);
	Thread.sleep(2000);
	String fixedvoucherno = splitmessage;
	Thread.sleep(2000);
	help1.Addfacility(driver, facilityname, contactperson, contactnumber, hour, amount);
	help1.BookFacility(driver, URL, facilityname, fromtime, totime, blockno, apartmentno, description);
    String voucher = help1.ToVerifyGeneratedVoucherno(driver);
    voucherno = voucher;
    driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
	Thread.sleep(4000);
	driver.findElement(By.id("Payment")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
	
	Thread.sleep(4000);
	driver.findElement(By.linkText("Yuva")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i")).click();
	//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i
	Thread.sleep(4000);
	driver.findElement(By.linkText("Y/2")).click();
	Thread.sleep(4000);
	driver.findElement(By.id("Go")).click();
	Thread.sleep(4000);
	WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]"));
	List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
	int rowscount = rows.size();
	System.out.println(rowscount);
	for (rowscount = 1; rowscount < rows.size(); rowscount++) {
		List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
		String rowvalue = columns.get(1).getText();
		Thread.sleep(4000);
		if (rowvalue.equals(fixedvoucherno)) {
			System.out.println(fixedvoucherno);
			Thread.sleep(2000);
			columns.get(0).findElement(By.tagName("input")).click();
			Thread.sleep(2000);
		}
		if (rowvalue.equals(voucherno)){
			columns.get(0).findElement(By.tagName("input")).click();
		List <WebElement> vlues =columns.get(5).findElements(By.tagName("input"));
		Thread.sleep(2000);
		vlues.get(2).clear();
		Thread.sleep(2000);
		vlues.get(2).sendKeys(editamount);	
		}
	}
	Thread.sleep(4000);
	driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
	Thread.sleep(4000);
	driver.findElement(By.id("btnpay")).click();
	Thread.sleep(4000);
	Alert alert2 = driver.switchTo().alert();
	Thread.sleep(4000);
	String message3 = alert2.getText();
	Thread.sleep(4000);
	System.out.println(message3);
	String splitmessage2 = message3.split(" ")[1];
	Thread.sleep(4000);
	System.out.println(splitmessage);
	alert.accept();
	Thread.sleep(2000);
	System.out.println(splitmessage2);
	// Generting nother invoice
	driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Invoice");
	Thread.sleep(4000);
	driver.findElement(By.id("Fixed")).click();
	Thread.sleep(4000);
	driver.findElement(
			By.xpath(".//*[@id='Invoice']/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div/a/i"))
			.click();
	Thread.sleep(4000);
	driver.findElement(By.linkText("Yuva")).click();
	Thread.sleep(2000);
	driver.findElement(
			By.id("auto_ApartmentID")).sendKeys("Y/2");
	Thread.sleep(2000);
	driver.findElement(By.id("BA1")).clear();
	Thread.sleep(2000);
	driver.findElement(By.id("BA1")).sendKeys("1500");// basic
														// amount
	Thread.sleep(2000);
	driver.findElement(By.id("x1")).sendKeys("Generting Invoice");// narration
	Thread.sleep(2000);
	driver.findElement(By.id("Gen")).click(); // Generate button
	Thread.sleep(5000);
	driver.findElement(By.id("Generate")).click();// yes button
	Thread.sleep(2000);
	driver.findElement(By.id("GenInv")).click(); // generate invoice
													// button
	Thread.sleep(2000);
	Alert alert3 = driver.switchTo().alert();
	Thread.sleep(2000);
	String message4 = alert3.getText();
	System.out.println(message4);
	alert.accept();
	String splitmessage3 = message4.split(" ")[2];
	System.out.println("Maintanance fixed invoice no." + splitmessage3);
	Thread.sleep(2000);
	String fixedvoucherno2 = splitmessage3;
	Thread.sleep(2000);

	// pyment
	driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
	Thread.sleep(4000);
	driver.findElement(By.id("Payment")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
	
	Thread.sleep(4000);
	driver.findElement(By.linkText("Yuva")).click();
	Thread.sleep(4000);
	driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i")).click();
	//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i
	Thread.sleep(4000);
	driver.findElement(By.linkText("Y/2")).click();
	Thread.sleep(4000);
	driver.findElement(By.id("Go")).click();
	Thread.sleep(4000);
	WebElement paymenttable2 = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]"));
	List<WebElement> rows2 = paymenttable2.findElements(By.tagName("tr"));
	int rowscount2 = rows2.size();
	
	for (rowscount2 = 1; rowscount2 < rows2.size(); rowscount2++) {
		List<WebElement> columns2 = rows2.get(rowscount2).findElements(By.tagName("td"));
		String rowvalue2 = columns2.get(1).getText();
		Thread.sleep(4000);
		if (rowvalue2.equals(fixedvoucherno2)) {
			System.out.println(fixedvoucherno2);
			Thread.sleep(2000);
			columns2.get(0).findElement(By.tagName("input")).click();
			Thread.sleep(2000);
		}
		if (rowvalue2.equals(voucherno)){
			columns2.get(0).findElement(By.tagName("input")).click();
		List <WebElement> vlues =columns2.get(5).findElements(By.tagName("input"));
		Thread.sleep(2000);
		vlues.get(2).clear();
		Thread.sleep(2000);
		vlues.get(2).sendKeys(editamount2);	
		}
	}
	Thread.sleep(4000);
	driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
	Thread.sleep(4000);
	driver.findElement(By.id("btnpay")).click();
	Thread.sleep(4000);
	Alert alert4 = driver.switchTo().alert();
	Thread.sleep(4000);
	String message5 = alert4.getText();
	Thread.sleep(4000);
	System.out.println(message3);
	String splitmessage4 = message5.split(" ")[1];
	Thread.sleep(4000);
	System.out.println(splitmessage4);
	alert.accept();


	

}
}
