package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistration;
import pageObject.HomePage;



public class TC_001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	
	{
		logger.info("*********Staring TC_001_AccountRegistrationTest***********");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on myaccount Link....");
		
		hp.clickregister();
		logger.info("Clicked on Register Link....");
		
		AccountRegistration regpage=new AccountRegistration(driver);
		logger.info("Providing Customer Details....");
		
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName("David");
		regpage.setemail(randomString()+"@gmail.com");  //randomly generated string
		regpage.setphone(randomNumber());
		regpage.setpassword("xyz5687");
		regpage.setcmfpassword("xyz5687");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");
		
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		}
	 catch(Exception e)
	 {
		 logger.error("Test Failed");
		 logger.debug("Debug log");
		 Assert.fail();
		 
	 }
	 
	 logger.info("*****Finished TC_001_AccountRegistrationTest******");
		
	}
}
	

