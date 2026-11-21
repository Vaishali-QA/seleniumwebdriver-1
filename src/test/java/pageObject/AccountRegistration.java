package pageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage{

	public AccountRegistration(WebDriver driver) {
		super(driver);
	}
	 @FindBy(xpath="//input[@id=\"input-firstname\"]")
	 WebElement txtFirstname;
	 
	 @FindBy(xpath="//input[@id=\"input-lastname\"]")
	 WebElement txtlastname;
	 
	 @FindBy(xpath="//input[@id=\"input-email\"]")
	 WebElement txtemail;
	 
	 @FindBy(xpath="//input[@id=\"input-telephone\"]")
	 WebElement txtphone;
	 
	 @FindBy(xpath="//input[@id=\"input-password\"]")
	 WebElement txtpwd;
	 
	 @FindBy(xpath="//input[@id=\"input-confirm\"]")
	 WebElement txtcmfpwd;
	 
	 @FindBy(xpath="//input[@name=\"agree\"]")
	 WebElement txtcmfpolicy;
	 
	 @FindBy(xpath="//input[@value=\"Continue\"]")
	 WebElement btncontnue;
	 
	 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	 WebElement msgconfirmation;
	 
	 
	 
	 public void setFirstName(String fname) 
	 {
		 txtFirstname.sendKeys(fname);
	 }
	 
	 public void setLastName(String lname) 
	 {
		 txtlastname.sendKeys(lname);
	 }
	 
	 public void setemail(String email) 
	 {
		 txtemail.sendKeys(email);
	 }
	 
	 public void setphone(String phone) 
	 {
		 txtphone.sendKeys(phone);
	 }
	 
	 public void setpassword(String pwd) 
	 {
		 txtpwd.sendKeys(pwd);
	 }
	 
	 public void setcmfpassword(String cmfpwd) 
	 {
		 txtcmfpwd.sendKeys(cmfpwd);
	 }
	 
	 public void setPrivacyPolicy() 
	 {
		 txtcmfpolicy.click();
	 }
	 
	 public void clickContinue() 
	 {
		 btncontnue.click();
	 }
	 
	 public String  getConfirmationMsg() {
	 try {
		 return (msgconfirmation.getText());
	 } catch (Exception e) {
		 return (e.getMessage());
	 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

}
