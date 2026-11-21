package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.Status;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testCases.BaseClass;

public class ExtendReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; //UI of report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test;   //creating test case entries in the report and update status of the test method
	String repName;
	
	
	public void onStart(ITestContext TestContext) {
	
	String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
    repName = "Test-Report-" + timestamp + ".html";
    sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName);
    
    sparkReporter.config().setDocumentTitle("opencart Automation Report");//title of report
    sparkReporter.config().setReportName("opencart Functional Testing");//name of report
    sparkReporter.config().setTheme(Theme.DARK);
    
    extent = new ExtentReports();
    extent.attachReporter(sparkReporter);
    extent.setSystemInfo("Application", "opencart");
    extent.setSystemInfo("Module", "Admin");
    extent.setSystemInfo("Sub Module", "Customers");
    extent.setSystemInfo("User Name", System.getProperty("user.name"));
    extent.setSystemInfo("Environment", "QA");
    
    String os = TestContext.getCurrentXmlTest().getParameter("os");
    extent.setSystemInfo("Operating System", os);
	
    String browser = TestContext.getCurrentXmlTest().getParameter("browser");
    extent.setSystemInfo("Browser", browser);
    
    List<String> includeGroups = TestContext.getCurrentXmlTest().getIncludedGroups();
    if(!includeGroups.isEmpty())
    {
    	extent.setSystemInfo("Groups", includeGroups.toString());
    	}
}
	
	
	
	public void onTestSuccess(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());      //display group in report
		test.log(Status.PASS,result.getName()+"got successfully executed");
	}
	
	
	public void onTestFailure(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());//create new entry in report
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath = ((BaseClass)result.getInstance()).captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
	public void onTestSkipped(ITestResult result) {
		test =extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());

	}
	
	
	public void onFinish(ITestContext TestContext) {
		
	extent.flush();
	
	String pathofExtentReport= System.getProperty("user.dir") + "\\reports\\" + repName;
	File extentReports= new File(pathofExtentReport);
	
	try {
		Desktop.getDesktop().browse(extentReports.toURI());
	}
	catch(IOException e1) {
		e1.printStackTrace();
	}

	}
		
}

