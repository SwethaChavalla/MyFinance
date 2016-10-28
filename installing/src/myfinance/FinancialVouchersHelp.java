package myfinance;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FinancialVouchersHelp {

	// WebDriver driver = new FirefoxDriver();
	String voucherno;
	String advanceamount;

	String editamount1 = "400";
	String editamount2 = "600";

	/*
	 * public void login() throws InterruptedException { logindetails logd = new
	 * logindetails(); logd.adminlogin(driver, "DEMO_9", "DEMO_9"); }
	 */
	public Object[][] Payment(WebDriver driver, String voucherno)
			throws InterruptedException, BiffException, IOException {
		
		String splitmessage[][] = new String[1][1];
		Thread.sleep(4000);
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("Payment");
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
			String dateid = inputdata[i][1];
			String Block = inputdata[i][2];
			String Flatno = inputdata[i][3];
			String naration = inputdata[i][4];
			driver.navigate().to(url);
			Thread.sleep(4000);
			driver.findElement(By.id("Payment")).click();
			Thread.sleep(4000);
			setDate(driver,dateid,2016,9,26);
			driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText(Block)).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i")).click();
			// *[@id='Payment']/div/div/div[2]/div[2]/div[2]/div/a/i
			Thread.sleep(4000);
			driver.findElement(By.linkText(Flatno)).click();
			Thread.sleep(4000);
			driver.findElement(By.id("Go")).click();
			Thread.sleep(4000);
			WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[4]"));
			// *[@id='Payment']/div/div/div[2]/div[4]/table/tbody
			// *[@id='Payment']/div/div/div[2]/div[3]/table/tbody
			List<WebElement> rows1 = paymenttable.findElements(By.tagName("tr"));
			int rowscount = rows1.size();
			for (rowscount = 1; rowscount < rows1.size(); rowscount++) {
				List<WebElement> columns1 = rows1.get(rowscount).findElements(By.tagName("td"));
				String rowvalue = columns1.get(1).getText();
				if (rowvalue.equals(voucherno)) {
					System.out.println("Paying amount for the voucher: " + voucherno);
					columns1.get(0).findElement(By.tagName("input")).click();
					break;
				}
			}
			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys(naration);
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			splitmessage[0][0] = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println("Generated Payment Receipt no: " + splitmessage[0][0]);
			alert.accept();
			Thread.sleep(2000);

		}
		return splitmessage;
	}

	public void setDate(WebDriver driver,String id,int year,int month,int day) throws InterruptedException {
		//System.out.println("Payment Date is not set so taken current date");
	}

	public String AdvancePaymentForFacility(WebDriver driver) throws InterruptedException, BiffException, IOException {
		String advancevoucherno = null;
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("AdvancePaymentData");
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
			String dateid = inputdata[i][1];
			String Block = inputdata[i][2];
			String Flatno = inputdata[i][3];
			String vouchertype = inputdata[i][4];
			String advanceamount = inputdata[i][5];
			String naration = inputdata[i][6];
			String message = inputdata[i][7];
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.findElement(By.id("Payment")).click(); // payment button
			Thread.sleep(4000);
			setDate(driver,dateid,2016,9,26);
			driver.findElement(By.cssSelector("#auto_BlockID")).clear();// Block
																		// ID
																		// dropdown
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#auto_BlockID")).sendKeys(Block);
			Thread.sleep(2000);
			driver.findElement(By.id("auto_ApartmentId")).sendKeys(Flatno); // Apartment
																			// ID
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#auto_InvType")).clear();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#auto_InvType")).sendKeys(vouchertype); // Voucher
																						// type
			Thread.sleep(4000);
			driver.findElement(By.id("Go")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).sendKeys(advanceamount);
			Thread.sleep(2000);
			driver.findElement(By.id("Narration")).sendKeys(naration);
			Thread.sleep(2000);
			driver.findElement(By.id("btnpay")).click(); // pay button
			Thread.sleep(2000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(2000);
			String vouchermessage = alert.getText();
			Thread.sleep(2000);
			alert.accept();
			Thread.sleep(2000);
			String advancevoucherno1 = vouchermessage.split(" ")[1];
			System.out.println(message + advancevoucherno1);
			advancevoucherno = advancevoucherno1;
			
		}
		
		return advancevoucherno;
	}

	public void AdvancePaymentForGeneralAndMaintenance(WebDriver driver)
			throws InterruptedException, BiffException, IOException {

		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("AdvancePaymentDataForG&M");
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
			String dateid = inputdata[i][1];
			String Block = inputdata[i][2];
			String Flatno = inputdata[i][3];
			String vouchertype = inputdata[i][4];
			String advanceamount = inputdata[i][5];
			String naration = inputdata[i][6];
			String message = inputdata[i][7];
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.navigate().to(url);
			Thread.sleep(2000);
			driver.findElement(By.id("Payment")).click(); // payment button
			Thread.sleep(4000);
			setDate(driver,dateid,2016,9,26);
			driver.findElement(By.cssSelector("#auto_BlockID")).clear();// Block
																		// ID
																		// dropdown
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#auto_BlockID")).sendKeys(Block);
			Thread.sleep(2000);
			driver.findElement(By.id("auto_ApartmentId")).sendKeys(Flatno); // Apartment
																			// ID
			Thread.sleep(4000);
			driver.findElement(By.cssSelector("#auto_InvType")).clear();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#auto_InvType")).sendKeys(vouchertype); // Voucher
																						// type
			Thread.sleep(4000);
			driver.findElement(By.id("Go")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='advrow']/td[4]/input")).sendKeys(advanceamount);
			Thread.sleep(2000);
			driver.findElement(By.id("Narration")).sendKeys(naration);
			Thread.sleep(2000);
			driver.findElement(By.id("btnpay")).click(); // pay button
			Alert alert = driver.switchTo().alert();
			String vouchermessage = alert.getText();
			alert.accept();
			String advancevoucherno1 = vouchermessage.split(" ")[1];
			System.out.println(message + advancevoucherno1);
		}

	}

	public void ReverseAdvance(WebDriver driver, String advancevoucherno) throws InterruptedException, BiffException, IOException {
		String reverseadvancevoucherno = null;
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("ReverseAdvanceURL");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		for (int x = 0; x < rows - 1; x++) {
			String url = inputdata[x][0];
		driver.navigate().to(url);
		Thread.sleep(2000);
		driver.findElement(By.id("RevAdv")).click();
		Thread.sleep(4000);
		WebElement reversaltable = driver.findElement(By.xpath(".//*[@id='Grid1']/tbody"));
		Thread.sleep(2000);
		int rowcount = reversaltable.findElements(By.tagName("tr")).size();
		Thread.sleep(2000);
		loop1: for (int i = 1; i < rowcount; i++) {
			String rowID = i + "";
			// List<WebElement> rows =
			// reversaltable.findElement(By.xpath(".//*[@id='Grid1']/tbody")).findElements(By.id(rowID));
			List<WebElement> rows1 = reversaltable.findElements(By.id(rowID));
			Thread.sleep(2000);
			String rowdata = rows1.get(0).findElements(By.tagName("td")).get(6).getText();
			if (rowdata.equals(advancevoucherno)) {
				rows1.get(0).findElements(By.tagName("td")).get(6).click();
				driver.findElement(By.id("Reason")).sendKeys("Reversing advance amount of facility booking");
				driver.findElement(By.id("Generate")).click();
				Alert alert = driver.switchTo().alert();
				String message = alert.getText();
				reverseadvancevoucherno = message.split(" ")[3];
				System.out.println("Reverse advance voucher no: " + reverseadvancevoucherno);
				alert.accept();
				break loop1;

			}
		}
		if (reverseadvancevoucherno == null) {
			driver.findElement(By.xpath(".//*[@id='Popup']/div/div/div[3]/div[3]/button[2]")).click();
			System.out.println("Voucher does not exist in the list:" + advancevoucherno);
		}
		}
	}
	
	public void PaymentMoreThanTotalAmount(WebDriver driver,String advancevoucher) throws InterruptedException, BiffException, IOException {
		File fs = new File("C:/Users/Swetha/Desktop/IMA Testing/All Financial Scenarios Test Data.xls");
		Workbook ws = Workbook.getWorkbook(fs);
		Sheet s = ws.getSheet("PayingMoreAmount");
		int rows = s.getRows();
		int columns = s.getColumns();
		String inputdata[][] = new String[rows - 1][columns];
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cl = s.getCell(j, i);
				inputdata[i - 1][j] = cl.getContents();
			}
		}
		for (int x = 0; x < rows - 1; x++) {
			String URL1 = inputdata[x][0];
			String dateid = inputdata[x][1];
			String Block = inputdata[x][2];
			String Flat = inputdata[x][3];
			String editamount = inputdata[x][4];
			String narration = inputdata[x][5];
		driver.navigate().to(URL1);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		setDate(driver,dateid,2016,9,28);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();//dropdown xpath
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();// dropdown xpath
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]/table/tbody"));
		List<WebElement> rows1 = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows1.size();
		for (rowscount = 0; rowscount < rows1.size(); rowscount++) {
			List<WebElement> columns1 = rows1.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns1.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns1.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns1.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[3]/button[2]")).click();
			}
		}

	}}

	public void Paymentforscenario4forcancel(WebDriver driver, String URL, String Block, String Flat,
			String advancevoucher, String editamount, String narration) throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]/table/tbody"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		for (rowscount = 0; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[3]/button[2]")).click();
			}
		}

	}

	public void Paymentforscenario4forpay(WebDriver driver, String URL, String Block, String Flat,
			String advancevoucher, String editamount, String narration) throws InterruptedException {
		driver.navigate().to(URL);
		Thread.sleep(2000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Block)).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText(Flat)).click();
		Thread.sleep(3000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(3000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]/table/tbody"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		for (rowscount = 0; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			if (rowvalue.equals(advancevoucher)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount);
				Thread.sleep(4000);
				driver.findElement(By.id("Narration")).sendKeys(narration);
				Thread.sleep(4000);
				driver.findElement(By.id("btnpay")).click();
				Thread.sleep(4000);
				Alert alert = driver.switchTo().alert();
				Thread.sleep(3000);
				String message = alert.getText();
				System.out.println(message);
				alert.accept();
				Thread.sleep(3000);
				driver.findElement(By.xpath(".//*[@id='btnpay']")).click();
				Thread.sleep(3000);
			}
		}

	}

	public void Paymentforscenario6invoice1(WebDriver driver, String voucherno, String facvoucherno, String editamount1)
			throws InterruptedException {
		driver.navigate().to("https://www.itsmyaccount.com/Voucher");
		Thread.sleep(4000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Block 3")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("B3/1")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(4000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		System.out.println(rowscount);
		for (rowscount = 1; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			Thread.sleep(4000);
			if (rowvalue.equals(voucherno)) {
				Thread.sleep(4000);
				System.out.println(voucherno);
				Thread.sleep(4000);
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(4000);
				break;
			}
			if (rowvalue.equals(facvoucherno)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount1);
				Thread.sleep(4000);
			}

			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			String splitmessage = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println(splitmessage);
			alert.accept();
			Thread.sleep(2000);

		}
	}

	public void Paymentforscenario6invoice2(WebDriver driver, String voucherno, String facvoucherno, String editamount2)
			throws InterruptedException {
		driver.navigate().to("https://www.itsmyaccount.com/Voucher");
		Thread.sleep(4000);
		driver.findElement(By.id("Payment")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[1]/div[3]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("Block 3")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[2]/div[1]/div/a/i")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("B3/1")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("Go")).click();
		Thread.sleep(4000);
		WebElement paymenttable = driver.findElement(By.xpath(".//*[@id='Payment']/div/div/div[2]/div[3]"));
		List<WebElement> rows = paymenttable.findElements(By.tagName("tr"));
		int rowscount = rows.size();
		System.out.println(rowscount);
		for (rowscount = 1; rowscount < rows.size(); rowscount++) {
			List<WebElement> columns = rows.get(rowscount).findElements(By.tagName("td"));
			String rowvalue = columns.get(1).getText();
			Thread.sleep(4000);
			if (rowvalue.equals(voucherno)) {
				Thread.sleep(4000);
				System.out.println(voucherno);
				Thread.sleep(4000);
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(4000);
				break;
			}
			if (rowvalue.equals(facvoucherno)) {
				columns.get(0).findElement(By.tagName("input")).click();
				Thread.sleep(2000);
				List<WebElement> vlues = columns.get(5).findElements(By.tagName("input"));
				Thread.sleep(2000);
				vlues.get(2).clear();
				vlues.get(2).sendKeys(editamount2);
				Thread.sleep(4000);
			}

			Thread.sleep(4000);
			driver.findElement(By.id("Narration")).sendKeys("Paying amount for voucher");
			Thread.sleep(4000);
			driver.findElement(By.id("btnpay")).click();
			Thread.sleep(4000);
			Alert alert = driver.switchTo().alert();
			Thread.sleep(4000);
			String message3 = alert.getText();
			Thread.sleep(4000);
			System.out.println(message3);
			String splitmessage = message3.split(" ")[1];
			Thread.sleep(4000);
			System.out.println(splitmessage);
			alert.accept();
			Thread.sleep(2000);

		}
	}

}
