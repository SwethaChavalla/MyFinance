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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class BookingFacilityHelper {
	//WebDriver driver = new FirefoxDriver();
	int year = 2016;
	int month = 9;
	int day = 4;
	
	

	/*public void managefacility() throws InterruptedException {
		logindetails ldr = new logindetails();
		ldr.adminlogin(driver, "DEMO_9", "DEMO_9");
		driver.navigate().to("https://www.itsmyaccount.com/Facility");
		Thread.sleep(3000);
	}*/
	public void Addfacility(WebDriver driver) throws InterruptedException, BiffException, IOException {
	
	File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
	Workbook ws = Workbook.getWorkbook(fs);
	Sheet s = ws.getSheet("AddFacility");
	int rows = s.getRows();
	int columns = s.getColumns();
	String inputdata[][] = new String[rows-1][columns];
	for (int i = 1; i < rows; i++) {
		for (int j = 0; j < columns; j++) {
			Cell cl = s.getCell(j, i);
			inputdata[i-1][j] = cl.getContents();
		}
	}
	
	for (int i = 0; i <rows-1; i++) {
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
			//driver.findElement(By.id("DueDate")).sendKeys(days1);
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='Facility']/div/div[3]/button[1]")).click();
			Thread.sleep(2000);
			WebElement verification = driver.findElement(By.xpath("html/body/div[1]/div/div/div[1]/div[1]/h3"));
			String value = verification.getText();
			Assert.assertEquals(value, "Manage Facilities");
			Thread.sleep(2000);

		}

}
	public void DueDate(WebDriver driver, String days){
		System.out.println("Due date has not given");
	}
	

  /* public void dropdown() throws InterruptedException{ 
	   WebElement select = driver.findElement(By.id("rows")); 
	   Thread.sleep(2000);
	   List<WebElement>  options = select.findElements(By.tagName("option"));
	   for (WebElement option : options){ 
		   if ("40".equals(option.getText())){
			   option.click();
	  Thread.sleep(3000);
	  } } }
	 */
	public String BookFacility(WebDriver driver,String URL, String facilityname, String fromtime, String totime, String blockno,
			String apartmentno, String description) throws InterruptedException {
		Thread.sleep(2000);
		driver.navigate().to(URL);
		Thread.sleep(4000);
		driver.findElement(By.id("searchgrid")).click(); // search
		Thread.sleep(4000);
		driver.findElement(By.xpath("//option[contains(.,'Facility Name')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("jqg1")).sendKeys(facilityname);
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
		String bookeddate = driver.findElement(By.id("FromDate")).getText();
		System.out.println(bookeddate);
		driver.findElement(By.id("FromTime")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("FromTime")).sendKeys(fromtime);
		Thread.sleep(2000);
		((JavascriptExecutor) driver).executeScript("document.getElementById('ToDate').removeAttribute('readonly',0);");
		WebElement todate = driver.findElement(By.id("ToDate"));
		todate.clear();
		todate.sendKeys(df.format(datespecified)); // Enter this date details
													// with valid date format
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@id='FacilityBooking']/div/div[2]/div/div/div/div/div[2]")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("ToTime")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("ToTime")).sendKeys(totime);
		driver.findElement(By.id("auto_BlockID")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("auto_BlockID")).sendKeys(blockno);
		Thread.sleep(2000);
		driver.findElement(By.id("auto_ApartmentID")).sendKeys(apartmentno);
		Thread.sleep(3000);
		driver.findElement(By.id("Description")).sendKeys(description);
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
		System.out.println("Facility booked successfully Invoice no. generated");
		return bookeddate;
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
		System.out.println("Generated Facility Booked Voucherno : " +FIGvoucherno);
		String duedate = driver.findElement(By.xpath(".//*[@id='1']/td[11]")).getText();
		System.out.println("Duedate  " + duedate);
		String clearedvouchers= driver.findElement(By.xpath(".//*[@id='1']/td[12]")).getText();
		System.out.println("Facility booked adjusted with advance vouchers " + clearedvouchers);
		driver.navigate().refresh();
		return FIGvoucherno;
	}
//For cancel the booked facility use below method	
	/*public void VerifyFacilityBookedForGivenDateandtoCancel() throws InterruptedException{
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
	}*/
	/*public void VerifyFacilityBookedForGivenDate(WebDriver driver) throws InterruptedException {
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
	}*/
	public void Addfacility(WebDriver driver, String facilityname, String contactperson, String contactnumber,
			String hour, String amount, String days) throws InterruptedException {
		driver.navigate().to("https://test-itsmyaccount.azurewebsites.net/Facility");
		driver.findElement(By.id("Add")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("FacilityName")).sendKeys(facilityname);
		Thread.sleep(2000);
		driver.findElement(By.id("ContactPerson")).sendKeys(contactperson);
		Thread.sleep(2000);
		driver.findElement(By.id("ContactNumber")).sendKeys(contactnumber);
		Thread.sleep(2000);
		driver.findElement(By.id("IsBookingAllowed")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("IsPaid")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("M1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("M1")).sendKeys(hour);
		Thread.sleep(2000);
		driver.findElement(By.id("Amt1")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("Amt1")).sendKeys(amount);
		Thread.sleep(2000);
		Select duedate =new Select(driver.findElement(By.id("TypeOfDueDate")));
		duedate.selectByIndex(2);
		driver.findElement(By.id("DueDate")).sendKeys(days);
		driver.findElement(By.xpath(".//*[@id='Facility']/div/div[3]/button[1]")).click();
		Thread.sleep(2000);

	}


	@DataProvider(name = "testdata")
	public Object[][] readexcel() throws IOException, BiffException {
		// TODO Auto-generated method stub
		File fs = new File("C:/Users/Swetha/Desktop/variabledata.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(1);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i][j] = cl.getContents();
				System.out.println(inputdata[i][j]);
			}
		}
		return inputdata;
	}

	@DataProvider(name = "testdata1")
	public Object[][] readexcel1() throws IOException, BiffException {
		// TODO Auto-generated method stub
		File fs = new File("C:/Users/Swetha/Desktop/variabledata.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(2);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata2[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata2[i][j] = cl.getContents();
				System.out.println(inputdata2[i][j]);
			}
		}
		return inputdata2;
	}
	
	@DataProvider(name = "testdata2")
	public Object[][] readexcel2() throws IOException, BiffException {
		// TODO Auto-generated method stub
		File fs = new File("C:/Users/Swetha/Desktop/Variable Data For Scenario6.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet(2);
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata2[][] = new String[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata2[i][j] = cl.getContents();
				System.out.println(inputdata2[i][j]);
			}
		}
		return inputdata2;
	}
}
