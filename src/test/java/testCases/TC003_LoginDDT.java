package testCases;

import org.testng.Assert;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/*
Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass {
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups= "DataProvider") // DataProviders is in another
																					// utilities package
	public void verifyLogin(String email, String password, String exp) //exp is expected result from XL sheet-- Valid or Invalid 
	{
		logger.info("**** Starting TC_003_LoginDDT *****");
		try {
			// Home Page
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin(); // Login link under MyAccount

			// Login page
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			// MyAccount Page
			MyAccountPage map = new MyAccountPage(driver);
			boolean targetpage = map.ismyAccountPageExixts(); // storing boolean value into a variable
			
			/*
			Data is valid  - login success - test pass  - logout
			Data is valid -- login failed - test fail

			Data is invalid - login success - test fail  - logout
			Data is invalid -- login failed - test pass
			*/

			if (exp.equalsIgnoreCase("Valid")) // will ignore case sensitivity
			{
				if (targetpage == true) 
				{
					map.clcikLogout();       //it should logout from the application after login
					Assert.assertTrue(true);   //Valid data and login success -- test pass
				} 
				else {
					Assert.assertFalse(false);  //Valid data and login fail -- test fail
				}

			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (targetpage == true) 
				{
					map.clcikLogout();          //it should logout from the application after login
					Assert.assertTrue(false);   //Invalid data and login success -- test fail
				} 
				else 
				{
					Assert.assertTrue(true);     //Invalid data and login fail -- test pass
				}
			}

		} catch (Exception e) {
			Assert.fail("Exception occured" + e.getMessage());
		}
		
		logger.info("**** Finished TC_003_LoginDDT *****");

	}
}
