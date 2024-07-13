package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)  //name of the constructor should be same as class name
	{
		this.driver=driver;   //refers to the current object in the constructor
		PageFactory.initElements(driver, this);
	}

}
