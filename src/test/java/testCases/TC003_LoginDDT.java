package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven") //getting data provider from different class
	public void verify_loginDTT(String email, String pwd, String exp)
	{
	
		logger.info("***********Starting TC003_LoginDDT*************");
		try
		{
	        //HomePage
			HomePage hp= new HomePage(driver);
			hp.clickMyAccount();
			hp.clicklogin();
			
			//login
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPassword(pwd);
			lp.clicklogin(); 
			
			//MyAccount
			MyAccountPage map=new MyAccountPage(driver);
			boolean targetPage=map.isMyAccountPageExist();
			
			//data is valid- login success- test pass-logout
			               //login failed-test fail
			
			if(exp.equalsIgnoreCase("valid"))
			{
				if (targetPage==true)
				{
					map.clicklogout();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			//data is invalid- login success- test fail-logout
                              //login failed-test pass
			
			if(exp.equalsIgnoreCase("invalid"))
			{
				if (targetPage==true)
				{
					map.clicklogout();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("***********Starting TC003_LoginDDT*************");
        
}
}
