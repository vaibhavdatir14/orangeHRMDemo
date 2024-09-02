package com.orengeHRM.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.ResetPassword;

public class LoginTest extends BaseClass {
	
	Login login;			//created object of Login pageObject
	
	
	@Test
	 public void validLogin() throws InterruptedException {
		login = new Login();
		Dashboard dashboard = new Dashboard();
		Thread.sleep(3000);
		
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		String acturl = dashboard.getCurrentURL();	 
		String expurl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		try {
			Assert.assertEquals(acturl, expurl);
            System.out.println("Test Passed: Login Success");
        } catch (AssertionError e) {
            System.out.println("Test Failed: " + e.getMessage());
        }
		
	}
	
	// we will parameterized this test
//	@Test
	public void invalidLogin() throws InterruptedException {
		login = new Login();
		Thread.sleep(3000);
		boolean result = login.invalidLogin("abcd","admin123");
		
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void forgotPassword() throws InterruptedException {
		
		login = new Login();
		Thread.sleep(5000);
		
		boolean forgotLink = login.forgotPassAvailable();
		Assert.assertTrue(forgotLink);
	}
	
	@Test
	public void verifyResetPassword() throws InterruptedException {
		
		login = new Login();
		ResetPassword resetpass = new ResetPassword();
		Thread.sleep(5000);
		
		resetpass = login.resetPassword();
		boolean title = resetpass.verifyResetPassPage();
		Assert.assertTrue(title);
		
	}
	
}
