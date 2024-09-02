package com.orengeHRM.testcases;

import org.testng.annotations.Test;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpList;

public class EmployeePageTest extends BaseClass {

	Login login;
	Dashboard dashboard;
	PIM_EmpList emplist;

//	@Test
	public void verifyResetAll() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.enterDataInName("Vaibhav");
		Thread.sleep(3000);
		String actval = emplist.getDataFromName();

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Some value in field");

			emplist.resetSearch();
			String actvalafterreset = emplist.getDataFromName();

			System.out.println("Value in field after reset: \"" + actvalafterreset + "\"");

			if (actvalafterreset == null || actvalafterreset.isEmpty()) {
				System.out.println("No value in field - Test case pass");
			} else {
				System.out.println("Test case failed: Value still present in field after reset");
			}
		} else {
			System.out.println("No text is inserted");
		}

	}

//	@Test
	public void empSearchByName() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.enterDataInName("anik54");
		Thread.sleep(3000);
		String actval = emplist.getDataFromName();
		String expresult = "Records Found";
		String expresult1 = "No Records Found";

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Value present in Name field");

			emplist.searchButton();
			String message = emplist.tableSearchRecordMessage();

			if (message.contains(expresult)) {

				System.out.println(message);
			}

			else if (message.contains(expresult1)) {

				System.out.println(message);
				emplist.toasterSearchRecordMessage(true);
			}

		}

		else {
			emplist.toasterSearchRecordMessage(false);
			System.out.println("Toaster not visible");
		}

	}

//	@Test
	public void empSearchByIDMessage() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.empSearchByID("001");
		Thread.sleep(3000);
		String actval = emplist.getDataFromID();
		String exprslt = "Records Found";
		String exprslt1 = "No Records Found";

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Value present in ID field");

			emplist.searchButton();
			String tabmessage = emplist.tableSearchRecordMessage();

			if (tabmessage.contains(exprslt)) {

				System.out.println(tabmessage);
			}

			else if (tabmessage.contains(exprslt1)) {
				
				emplist.toasterSearchRecordMessage(true);
				System.out.println(tabmessage);
			}

		}

		else {
			emplist.toasterSearchRecordMessage(false);
			System.out.println("Toaster not visible");
		}

	}

	@Test
	public void empSearchByStatus() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.getDataFromStatus(2);
		emplist.empSearchbyStatus(2);
		
/*		Thread.sleep(3000);
		
		String actval = emplist.getDataFromStatus();
		String expreslt = "Records Found";
		String expreslt1 = "No Records Found";

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Value present in ID field");

			emplist.searchButton();
			String tabmsg = emplist.tableSearchRecordMessage();

			if (tabmsg.contains(expreslt)) {

				System.out.println(tabmsg);
			}

			else if (tabmsg.contains(expreslt1)) {
				
				emplist.toasterSearchRecordMessage(true);
				System.out.println(tabmsg);
			}

		}

		else {
			emplist.toasterSearchRecordMessage(false);
			System.out.println("Toaster not visible");
		}
*/
	}

}
