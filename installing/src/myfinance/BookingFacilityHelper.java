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

	public void Addfacility(WebDriver driver) throws InterruptedException, BiffException, IOException {

		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("AddFacility");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}

		for (int i = 0; i < rows - 1; i++) {
			String URL = inputdata[i][0];
			String facilityname1 = inputdata[i][1];
			String contactperson1 = inputdata[i][2];
			String contactnumber1 = inputdata[i][3];
			String hour1 = inputdata[i][4];
			String amount1 = inputdata[i][5];
			String days1 = inputdata[i][6];

			driver.navigate().to(URL);
			driver.findElement(By.id("Add")).click();
			Thread.sleep(3000);
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

	public void BookFacility(WebDriver driver) throws InterruptedException, BiffException, IOException {
		Thread.sleep(2000);
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("BookFacility");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}

		for (int i = 0; i < rows - 1; i++) {
			String URL1 = inputdata[i][0];
			String facilityname1 = inputdata[i][1];
			String fromdateid = inputdata[i][2];
			String fromtime1 = inputdata[i][3];
			String todateid = inputdata[i][4];
			String totime1 = inputdata[i][5];
			String blockno1 = inputdata[i][6];
			String apartmentno1 = inputdata[i][7];
			String description1 = inputdata[i][8];
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
			Thread.sleep(4000);
			setDate(driver,fromdateid,2016,10,7);
			Thread.sleep(2000);
			driver.findElement(By.id("FromTime")).clear();
			Thread.sleep(2000);
			driver.findElement(By.id("FromTime")).sendKeys(fromtime1);
			Thread.sleep(2000);
			setDate(driver,todateid,2016,10,7);
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
			Thread.sleep(4000);
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

	public String ToVerifyGeneratedVoucherno(WebDriver driver) throws InterruptedException, BiffException, IOException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("UrlForFIGno");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}

		for (int i = 0; i < rows - 1; i++) {
			String url = inputdata[i][0];
		driver.navigate().to(url);
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
		}
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
