package installing1;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Classinstalling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.setProperty("java.net.preferIPv4Stack" , "true");
		WebDriver driver= new FirefoxDriver();
		System.out.println("Hello Google...");  
	      driver.get("http://test-itsmyaccount.azurewebsites.net/Login"); 
		driver.close();
		

	}

}
