package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups= {"Sanity","Master"})                   //Step8 groups added
	public void verifyLogin()
	{
		logger.info("*********TC002_LoginTest started**********");
		try
		{
			
		//Home Page
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Cicked on My Account page");
		
		hp.clickLogin();
		logger.info("Cicked on My Login link under my account page");
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		logger.info("Cicked on Login button");
		
		//MyAccount Page
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage = map.ismyAccountPageExixts();     //storing boolean value into a variable
		Assert.assertEquals(targetpage, true, "Login Passed");   //comparing with true and printing message
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*********TC002_LoginTest finished**********");
		
	}
	
}
