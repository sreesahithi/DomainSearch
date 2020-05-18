package com.netsol.tests;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


 

public class CommonDriver  {

   // public static ChromeDriver driver=null;    
  
    public static FirefoxDriver driver=null;    
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;


   @BeforeSuite
    public void startdriver() throws InterruptedException {
	   
	   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
       Calendar cal = Calendar.getInstance();
      Date dateAndTime = cal.getTime();
      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
       System.out.println(dateFormat.format(cal.getTime()));
       System.out.println(timeStamp);
       htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReports\\ReportResults"+timeStamp+".html");
	    
       extent = new ExtentReports();
       extent.attachReporter(htmlReporter);
        
        
       htmlReporter.config().setChartVisibilityOnOpen(true);
       htmlReporter.config().setDocumentTitle("RCOM Extent Results");
       htmlReporter.config().setReportName("My Own Report");
       htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
       htmlReporter.config().setTheme(Theme.DARK);
   
       // System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");	
     System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");	

        String proxyUrl = "zproxy.qamain.netsol.com";
	    int proxyPort = 8080;
	 //   String httpProxy = proxyUrl+":" +proxyPort;
	   
	    String httpProxy =  "zproxy.qamain.netsol.com:8080";
	    Proxy proxy=new Proxy();
		  // Set HTTP Port to 7777
		  proxy.setHttpProxy(httpProxy).setFtpProxy(httpProxy).setSslProxy(httpProxy).setSslProxy(httpProxy);
		  
		  // Create desired Capability object
		  DesiredCapabilities cap=DesiredCapabilities.firefox();
		  
		 // DesiredCapabilities cap=DesiredCapabilities.chrome();


		  // Pass proxy object p
		  cap.setCapability(CapabilityType.PROXY, proxy);

		  // Open  firefox browser
		//  driver = new ChromeDriver(cap);
		   driver=new FirefoxDriver(cap);
		 
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
Thread.sleep(2000);

driver.get("https://www.networksolutions.com/manage-it/index.jsp");
driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
Thread.sleep(5000);

        }
   
   @AfterMethod
   public void getResult(ITestResult result) throws InterruptedException
   {
       if(result.getStatus() == ITestResult.FAILURE)
       {
           test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
           test.fail(result.getThrowable());
       }
       else if(result.getStatus() == ITestResult.SUCCESS)
       {
           test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
       }
       else
       {
           test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
           test.skip(result.getThrowable());
       }
   }
   
  
   @AfterSuite
   public void TeardownTest() { 
       extent.flush();
   } 
   
   
}
