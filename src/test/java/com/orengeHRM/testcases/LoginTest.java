package com.orengeHRM.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.dataprovider.DataProviders;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.ResetPassword;
import com.orengeHRM.utility.Log;

public class LoginTest extends BaseClass {

	Login login; // created object of Login pageObject

	// Reference to read dataprovider from DataProvider class

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verifyLogin(String uname, String pswd) throws InterruptedException {

		Log.startTestCase("verifyLogin");

		login = new Login();
		Dashboard dashboard = new Dashboard();

		Thread.sleep(3000);

		Log.info("User is entering login credentials");
//		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		dashboard = login.verifyLogin(uname, pswd);
		Log.info("System get current URL");
		String acturl = dashboard.getCurrentURL();
		String expurl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String invmsg = "Invalid credentials";
		String blankfield = "Required";

		try {
			Log.info("System compare expected and actual URL");
//			Assert.assertEquals(acturl, expurl);

			if (acturl.equals(expurl)) {

				System.out.println("Test Passed: Login Success");
			}

			else if (login.invalidLogin().contains(invmsg)) {
				System.out.println("Test Passed: Login Failed with Invalid credentials");
			}

			else if (login.blankCredentials().contains(blankfield)) {
				System.out.println("Test Passed: Enter data in field Login Failed");
			}

		} catch (AssertionError e) {
			Log.error("Error occured in execution" + e);
			System.out.println("Test Failed: " + e.getMessage());
		}
		Log.endTestCase("verifyLogin");
	}

	
//	@Test
	public void forgotPassword() throws InterruptedException {

		Log.startTestCase("forgotPassword");

		login = new Login();
		Thread.sleep(5000);

		Log.info("Clicking on Forgot Password Link");
		boolean forgotLink = login.forgotPassAvailable();
		Log.info("Verify forgot Link available or not");
		Assert.assertTrue(forgotLink);

		Log.endTestCase("forgotPassword");
	}

//	@Test
	public void verifyResetPassword() throws InterruptedException {

		Log.startTestCase("verifyResetPassword");
		login = new Login();

		ResetPassword resetpass = new ResetPassword();
		Thread.sleep(5000);

		Log.info("Clicking on Forgot password link");
		resetpass = login.resetPassword();

		Log.info("Verify reset password page open");
		boolean title = resetpass.verifyResetPassPage();
		Assert.assertTrue(title);
		Log.endTestCase("verifyResetPassword");
	}

}
