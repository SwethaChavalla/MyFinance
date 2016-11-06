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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BookingFacilityHelper {
	private static final String FIGvoucherno = null;
	// WebDriver driver = new FirefoxDriver();
	logindetails ldr = new logindetails();
	String fromdate = "FromDate";
	String todate = "ToDate";

	public void Addfacility(WebDriver driver,String URL, String facilityname1,String contactperson1,String contactnumber1, String hour1,String amount1,String days1) throws InterruptedException, BiffException, IOException {
			driver.navigate().to(URL);
			Thread.sleep(2000);
			driver.findElement(By.id("Add")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("FacilityName")).sendKeys(facilityname1);
			Thread.sleep(2000);
			driver.findElement(By.id("ContactPerson")).sendKeys(contactperson1);
			Thread.sleep(2000);
			driver.findElement(By.id("ContactNumber")).sendKeys(contactnumber1);
			Thread.sleep(2000);
			driver.findElement(By.id("IsBookingAllowed")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("IsPaid")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("M1")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("M1")).sendKeys(hour1);
			Thread.sleep(2000);
			driver.findElement(By.id("Amt1")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("Amt1")).sendKeys(amount1);
			Thread.sleep(2000);
			DueDate(driver, days1);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='Facility']/div/div[3]/button[1]")).click();
			Thread.sleep(2000);
			WebElement verification = driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/div[1]/h3"));
			String value = verification.getText();
			Assert.assertEquals(value, "Manage Facilities");
			Thread.sleep(2000);

		}

	

	public void DueDate(WebDriver driver, String days) {
		System.out.println("Due date has not given");
	}

	public void dropdown(WebDriver driver) throws InterruptedException {
		WebElement select = driver.findElement(By.id("rows"));
		Thread.sleep(2000);
		List<WebElement> options = select.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if ("40".equals(option.getText())) {
				option.click();
				Thread.sleep(3000);
			}
		}
	}

	public void BookFacility(WebDriver driver,String url, String Facilityname, String Fromdateid, String FromTime, String Todateid,
			String ToTime, String Block, String FlatNo, String Description) throws InterruptedException, BiffException, IOException {
			driver.navigate().to(url);
			Thread.sleep(4000);
			driver.findElement(By.id("searchgrid")).click(); // search
			Thread.sleep(4000);
			driver.findElement(By.xpath("//option[contains(.,'Facility Name')]")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("jqg1")).sendKeys(Facilityname);
			Thread.sleep(4000);
			driver.findElement(By.id("fbox_Grid_search")).click();// find button
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='searchhdfbox_Grid']/a/span")).click(); // close
																							// button
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='1']/td[12]/a/button")).click();
			Thread.sleep(4000);
			driver.findElement(By.id("Add")).click();
			Thread.sleep(4000);
			setDate(driver,Fromdateid,2016,10,7);
			Thread.sleep(2000);
			driver.findElement(By.id("FromTime")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("FromTime")).sendKeys(FromTime);
			Thread.sleep(2000);
			setDate(driver,Todateid,2016,10,7);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='FacilityBooking']/div/div[2]/div/div/div/div/div[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("ToTime")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("ToTime")).sendKeys(ToTime);
			Thread.sleep(2000);
			driver.findElement(By.id("auto_BlockID")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("auto_BlockID")).sendKeys(Block);
			Thread.sleep(2000);
			driver.findElement(By.id("auto_ApartmentID")).sendKeys(FlatNo);
			Thread.sleep(3000);
			driver.findElement(By.id("Description")).sendKeys(Description);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='FacilityBooking']/div/div[3]/button[2]")).submit();
			WebDriverWait wait = new WebDriverWait(driver,50);
			wait.until(ExpectedConditions.alertIsPresent());
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

	public String ToVerifyGeneratedVoucherno(WebDriver driver, String URL) throws InterruptedException, BiffException, IOException {
		driver.navigate().to(URL);
		Thread.sleep(4000);
		driver.findElement(
				By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i"))
				.click();// voucher type
		Thread.sleep(4000);
		driver.findElement(By.linkText("Facility Booking")).click();
		Thread.sleep(4000);
		String FIGvoucherno = driver.findElement(By.xpath(".//*[@id='1']/td[6]")).getText();
		System.out.println("Generated Facility Booked Voucherno : " + FIGvoucherno);
		String duedate = driver.findElement(By.xpath(".//*[@id='1']/td[11]")).getText();
		System.out.println("Duedate  " + duedate);
		driver.navigate().refresh();
		return FIGvoucherno;
	}

	public String ToVerifyGeneratedVouchernoforscenario4(WebDriver driver) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Voucher");
		Thread.sleep(4000);
		driver.findElement(
				By.xpath("html/body/div[1]/div/div/div[1]/div[2]/div[1]/div/div/div/table/tbody/tr/td[3]/div/a/i"))
				.click();// voucher type
		Thread.sleep(4000);
		driver.findElement(By.linkText("Facility Booking")).click();
		Thread.sleep(4000);
		String FIGvoucherno = driver.findElement(By.xpath(".//*[@id='1']/td[6]")).getText();
		System.out.println("Generated Facility Booked Voucherno : " + FIGvoucherno);
		String duedate = driver.findElement(By.xpath(".//*[@id='1']/td[11]")).getText();
		System.out.println("Duedate  " + duedate);
		String clearedvouchers = driver.findElement(By.xpath(".//*[@id='1']/td[12]")).getText();
		System.out.println("Facility booked adjusted with advance vouchers " + clearedvouchers);
		driver.navigate().refresh();
		return FIGvoucherno;
	}

	public void VerifyFacilityBookedForGivenDateandtoCancel(WebDriver driver, int day) throws InterruptedException {
		WebElement calendertable = driver.findElement(By.xpath(".//*[@id='calendar']/div[2]"));
		Thread.sleep(4000);
		List<WebElement> cellrows = calendertable.findElements(By.cssSelector(".cal-row-fluid.cal-before-eventlist"));
		Thread.sleep(4000);
		for (int x = 1; x < 5; x++) {
			Thread.sleep(4000);
			List<WebElement> columns = cellrows.get(x).findElements(By.tagName("div"));
			Thread.sleep(4000);
			for (int i = 0; i <= 12; i++) {
				String givendate = columns.get(i).getText();
				Thread.sleep(4000);
				String number = Integer.toString(day);
				if (number.equals(givendate)) {
					columns.get(i).click();
					Thread.sleep(2000);
					break;
				}
			}
		}
	}

	public void VerifyFacilityBookedForGivenDate(WebDriver driver, int day) throws InterruptedException {
		WebElement calendertable = driver.findElement(By.xpath(".//*[@id='calendar']/div[2]"));
		Thread.sleep(4000);
		List<WebElement> cellrows = calendertable.findElements(By.cssSelector(".cal-row-fluid.cal-before-eventlist"));
		Thread.sleep(4000);
		for (int x = 1; x < 5; x++) {
			Thread.sleep(4000);
			List<WebElement> columns = cellrows.get(x).findElements(By.tagName("div"));
			Thread.sleep(4000);
			for (int i = 0; i <= 12; i++) {
				String givendate = columns.get(i).getText();
				Thread.sleep(4000);
				String number = Integer.toString(day);
				if (number.equals(givendate)) {
					columns.get(i).click();
					Thread.sleep(2000);
					break;
				}
			}
		}
	}

	
	
}
