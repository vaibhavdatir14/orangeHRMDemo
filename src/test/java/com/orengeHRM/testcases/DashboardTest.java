package com.orengeHRM.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpList;
import com.orengeHRM.utility.Log;

public class DashboardTest extends BaseClass {

	Login login;
	Dashboard dashboard;
	PIM_EmpList pimEmpList;

	@Test(groups = {"All", "Regression"})
	public void quickLaunchIconDisplay() {
		
		Log.startTestCase("quickLaunchIconDisplay");
		login = new Login();
		dashboard = new Dashboard();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));

		Log.info("Verifying Quick Launch Vailable or not");
		boolean result = dashboard.quickLaunchAvailable();

		Assert.assertTrue(result);

		Log.endTestCase("quickLaunchIconDisplay");
	}

	@Test(groups = {"All", "Regression"})
	public void countQuickLaunchOptions() {

		Log.startTestCase("countQuickLaunchOptions");
		login = new Login();
		dashboard = new Dashboard();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));

		Log.info("Counting Quick Launch");
		int totalOption = dashboard.quickLaunchCount();
		System.out.println("Total options are:-" + totalOption);

		Log.endTestCase("countQuickLaunchOptions");
	}

	@Test(groups = "All")
	public void listQuickLaunchOptions() {

		Log.startTestCase("listQuickLaunchOptions");
		login = new Login();
		dashboard = new Dashboard();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));

		Log.info("Showing Quick Launch List");
		dashboard.quickLaunchList();

		Log.endTestCase("listQuickLaunchOptions");
	}
	
	@Test(groups = {"All", "Smoke"})
	public void navigateToPIM() {

		Log.startTestCase("navigateToPIM");
		login = new Login();
		dashboard = new Dashboard();
		pimEmpList = new PIM_EmpList();
		
		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("User Navigating on PIM page");
		pimEmpList = dashboard.navigateToPIM();
		String acturl = dashboard.getCurrentURL();
		String ExpUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
		
		Log.info("Verify systen navigate on Employee List page");
		Assert.assertEquals(acturl, ExpUrl);
		
		Log.endTestCase("navigateToPIM");
	}
}