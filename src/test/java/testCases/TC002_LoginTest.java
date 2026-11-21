package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("************Starting TC002_LoginTest**************");
		
	try
	{
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clicklogin();
		
		//login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clicklogin(); 
		
		//MyAccount
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExist();
		
		//Assert.assertEquals(targetPage, true,"Login Failed");  
		Assert.assertTrue(targetPage);   //we can write any assertion, both are ture
	}
	catch (Exception e)
	{
		Assert.fail();
	}
		logger.info("************Finished TC002_LoginTest**************");
		
		
		
	}


}
