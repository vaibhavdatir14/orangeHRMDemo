package com.orengeHRM.testcases;

import org.testng.Assert;
import org.testng.annotations.*;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpList;

public class DashboardTest extends BaseClass {

	Login login;
	Dashboard dashboard;
	PIM_EmpList pimEmpList;

//	@Test
	public void quickLaunchIconDisplay() {
		login = new Login();
		dashboard = new Dashboard();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));

		boolean result = dashboard.quickLaunchAvailable();

		Assert.assertTrue(result);

	}

//	@Test
	public void countQuickLaunchOptions() {

		login = new Login();
		dashboard = new Dashboard();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));

		int totalOption = dashboard.quickLaunchCount();
		System.out.println("Total options are:-" + totalOption);

	}

//	@Test
	public void listQuickLaunchOptions() {

		login = new Login();
		dashboard = new Dashboard();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));

		dashboard.quickLaunchList();

	}
	
	@Test
	public void navigateToPIM() {

		login = new Login();
		dashboard = new Dashboard();
		pimEmpList = new PIM_EmpList();
		
		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		pimEmpList = dashboard.navigateToPIM();
		String acturl = dashboard.getCurrentURL();
		String ExpUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";
		
		Assert.assertEquals(acturl, ExpUrl);
	}
}