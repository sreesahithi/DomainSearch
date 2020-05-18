package com.netsol.tests;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.netsol.pages.Cartpage;
import com.netsol.pages.Checkoutpage;
import com.netsol.pages.ContinuePage;
import com.netsol.pages.HomePage;
import com.netsol.pages.LoginPage;
import com.netsol.pages.OrderReceipt;
import com.netsol.pages.PrivateRegistration;
import com.netsol.pages.Processpayment;
import com.netsol.pages.Purchaseaccount;

public class DomainSearchTest extends CommonDriver{
	
	
    
	 
   @Test
	public void verifyvalidlogin() throws InterruptedException, IOException
	{
	   test = extent.createTest("Domain Search ");
	   
	    LoginPage loginpage; 
	    
	   loginpage = new LoginPage(driver);
	   loginpage.typeUserName();
	   loginpage.typePassword();
	   loginpage.clickOnLoginButton();
	   
	   
	  HomePage homepage = new HomePage(driver);
	  homepage.searchDomains();
	  
	    ContinuePage continuepage = new ContinuePage(driver);
	    continuepage.continueButton();
	    
	    
	    PrivateRegistration privateregistration = new PrivateRegistration(driver);
	    privateregistration.noThanks();
	    privateregistration.continuebtn();
	    privateregistration.button();
	    privateregistration.radiobtn();
	    privateregistration.nextbtn();
	    
	    
	    Checkoutpage checkoutpage =new Checkoutpage(driver);
	    checkoutpage.checkoutbtn();
	    
	    Cartpage cartpage = new Cartpage(driver);
	    cartpage.buybtn();
	    cartpage.btn();
		cartpage.navigateToUpp();

	    Purchaseaccount purchaseaccount = new Purchaseaccount(driver);
	    purchaseaccount.continuebtn();
	    purchaseaccount.checkboxclik();
	    
	    
	   Processpayment processpayment = new Processpayment(driver);
	   processpayment.processpaymentbtn();
			   
	  
	   OrderReceipt orderReceipt = new OrderReceipt(driver);
	   orderReceipt.ordernumber();
	   
	   if(!orderReceipt.orderNumber.isEmpty()) {
			
			 test.log(Status.PASS, " Domains Search Flow with Order Number  - 	" +orderReceipt.orderNumber);
			   	}
		
		else {	
		    test.log(Status.FAIL, " Domains Search Flow Failed");

		}  
	    
	    
	} 
	
	}
	
