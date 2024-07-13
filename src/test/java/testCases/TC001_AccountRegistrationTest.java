package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups= {"Regression","Master"})  //Step8 groups added
	public void verifyAccountRegistration() {
		logger.info("***** Starting TC001_AccountRegistrationTest  ****"); // log statement-1

		logger.debug("This is a debug log message"); // log statement-2

		try // putting everything in try - catch block to get debug logs
		{
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link.. "); // log statement-3

			hp.clickRegister();
			logger.info("Clicked on Register Link.. "); // log statement-4

			logger.info("Providing customer details..."); // log statement-5
			// If we hadrcode these details, test only pass one time. Next time it will
			// through an error like account alredy registered.
			// So need to pass random data
			AccountRegistrationPage arp = new AccountRegistrationPage(driver);
			arp.setFirstName(randomString().toUpperCase());
			arp.setLastName(randomString().toLowerCase());
			arp.setEmail(randomString() + "@gmail.com");
			arp.setTelephone(randomInteger());

			String password = randomAlphanumeric(); // to make password and confirm password same
			arp.setPassword(password);
			arp.setConfirmPassword(password);

			arp.setPrivacyPolicy();
			arp.setContinue();

			logger.info("Validating expected message.."); // log statement-6

			String confmsg = arp.getConfirmationMsg();
			Assert.assertEquals(confmsg, "Your Account Has Been Created!", "Confirmation message mismatch");

			logger.info("Test passed");

		} catch (Exception e) {
			logger.error("Test failed: " + e.getMessage());   //error log
			Assert.fail("Test failed: " + e.getMessage());    //fail log
		} finally {
			logger.info("***** Finished TC001_AccountRegistrationTest *****");
		}

	}
}
