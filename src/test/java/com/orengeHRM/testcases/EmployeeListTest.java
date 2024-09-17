package com.orengeHRM.testcases;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpAdd;
import com.orengeHRM.pageobject.PIM_EmpList;
import com.orengeHRM.utility.Log;

public class EmployeeListTest extends BaseClass {

	Login login;
	Dashboard dashboard;
	PIM_EmpList emplist;
	PIM_EmpAdd empadd;
	

//	@Test
	public void verifyResetAll() throws InterruptedException {

		Log.startTestCase("verifyResetAll");
		
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		Log.info("User Name Enter");
		emplist.enterDataInName("Vaibhav");
		Thread.sleep(3000);
		String actval = emplist.getDataFromName();

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Some value in field");

			Log.info("Clicking on Reset button");
			emplist.resetSearch();
			String actvalafterreset = emplist.getDataFromName();

			System.out.println("Value in field after reset: \"" + actvalafterreset + "\"");

			Log.info("Verify Name field is clear");
			if (actvalafterreset == null || actvalafterreset.isEmpty()) {
				System.out.println("No value in field - Test case pass");
			} else {
				System.out.println("Test case failed: Value still present in field after reset");
			}
		} else {
			System.out.println("No text is inserted");
		}
		Log.endTestCase("verifyResetAll");
	}

//	@Test
	public void verifyTableMsgSearchByName() throws InterruptedException {

		Log.startTestCase("verifyTableMsgSearchByName");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		Log.info("Enter data in Name field");
		emplist.enterDataInName("anik54");
//		Thread.sleep(3000);
		String actval = emplist.getDataFromName();
		String expresult = "Records Found";
		String expresult1 = "No Records Found";

		Log.info("Validate value present in Name field");
		if (actval != null && !actval.isEmpty()) {
			System.out.println("Value present in Name field");

			emplist.searchButton();
			String message = emplist.tableSearchRecordMessage();

			// Toaster is disappear quickly thats why it store here in variable
			String toast = emplist.toasterSearchRecordMessage(true);

			Log.info("Validating Table message");
			if (message.contains(expresult)) {

				System.out.println("Table Result " + message);
			}

			else if (message.contains(expresult1)) {

				System.out.println("Table Result " + message);
				System.out.println(toast);
				// emplist.toasterSearchRecordMessage(true);
			}

		}

		else {
			emplist.toasterSearchRecordMessage(false);
			System.out.println("Toaster not visible");
		}
		Log.endTestCase("verifyTableMsgSearchByName");
	}

//	@Test
	public void verifyTableMsgSearchByID() throws InterruptedException {

		Log.startTestCase("verifyTableMsgSearchByID");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		Log.info("Entering Employee ID");
		emplist.enterDataInID("vai");

		emplist.searchButton();

		String toast = emplist.toasterSearchRecordMessage(true);
		String tabmessage = emplist.tableSearchRecordMessage();

		String exprslt = "Records Found";
		String eres = "Record Found";
//		String exprslt1 = "No Records Found";

		Log.info("Validating Entered value present in field");
		if (tabmessage != null && (tabmessage.contains(exprslt) || tabmessage.contains(eres))) {
			System.out.println("Value present in ID field");

			System.out.println("Table Result " + tabmessage);

		}

		else {

			Log.info("Toaster message");
			System.out.println(toast);
			// emplist.toasterSearchRecordMessage(true);
			System.out.println("No Result " + tabmessage);
		}
		Log.endTestCase("verifyTableMsgSearchByID");
	}

	@Test
	public void empSearchByStatus() throws InterruptedException {

		Log.startTestCase("empSearchByStatus");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		Thread.sleep(2000);
		emplist.selectFromStatus(3);
	
		emplist.searchButton();
		String tabmessage = emplist.tableSearchRecordMessage();
		String toast = emplist.toasterSearchRecordMessage(true);
		
		String expreslt = "Records Found";
		String expreslt1 = "No Records Found";

		Log.info("Validating Entered selected option");
		if (tabmessage != null && (tabmessage.contains(expreslt) || tabmessage.contains(expreslt1))) {
			System.out.println("Value selected in status dropdown");

			System.out.println("Table Result " + tabmessage);

		}

		else {

			Log.info("Toaster message");
			System.out.println(toast);
			// emplist.toasterSearchRecordMessage(true);
			System.out.println("No. of records :-  " + tabmessage);
		}
		Log.endTestCase("empSearchByStatus");
	}

//	@Test
	public void verifyNoOfRowsEqualsTabMessage() throws InterruptedException {

		Log.startTestCase("verifyNoOfRowsEqualsTabMessage");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		Log.info("Entering Employee Name");
		emplist.enterDataInName("charl");

		emplist.searchButton();
		Thread.sleep(2000);
		String message = emplist.tableSearchRecordMessage();
		
		Log.info("Extracting number from string");
		String tabmsgstr = message.replaceAll("[^0-9]", "");
		System.out.println(message);

		// if (message.contains("Records Found") || message.contains("Record Found")) {

		if (!message.equals("No Records Found")) {

			// countTableData() return row size
			String noOfRecords = emplist.countTableData();
			System.out.println(noOfRecords);

			noOfRecords.replaceAll("[^0-9]", "");

			if (tabmsgstr.equals(noOfRecords)) {
				System.out.println("The number of records matches the number of rows.");
			} else {
				System.out.println("The number of records does not match the number of rows.");
			}
		}
		
		Log.endTestCase("verifyNoOfRowsEqualsTabMessage");
	}

//	@Test
	public void verifyNameInTable() throws InterruptedException {

		Log.startTestCase("verifyNameInTable");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		String employeeName = "charl";
		emplist.enterDataInName(employeeName);

		emplist.searchButton();
		Thread.sleep(2000);
		String message = emplist.tableSearchRecordMessage();
		System.out.println(message);

		if (!message.equals("No Records Found")) {

			// countTableData() return row size
			String noOfRecords = emplist.getNameFromTable(employeeName);
			System.out.println(noOfRecords);

			if (noOfRecords.contains(employeeName)) {
				System.out.println("The entered Name " + employeeName + " is available in data");
			} else {
				System.out.println("Record available but entered Name not found");
			}

		}

		else if (message.contains("No Records Found")) {
			System.out.println("No data to show, Table message is match");
		}
		Log.endTestCase("verifyNameInTable");
	}

//	@Test
	public void verifyEmployeeDelete() throws InterruptedException {

		Log.startTestCase("verifyEmployeeDelete");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		String employeeName = "bala";
		emplist.enterDataInName(employeeName);

		emplist.searchButton();
		Thread.sleep(2000);
		String message = emplist.tableSearchRecordMessage();
		System.out.println(message);

		if (!message.equals("No Records Found")) {

			// countTableData() return row size
			String noOfRecords = emplist.getNameFromTable(employeeName);
			System.out.println(noOfRecords);

			if (noOfRecords.equalsIgnoreCase(employeeName)) {
				System.out.println("The entered Name " + employeeName + " is available in data");

				Thread.sleep(3000);
				emplist.deleteRecord();

			} else {
				System.out.println("Record available but entered Name not found");
			}

		}

		else if (message.contains("No Records Found")) {
			System.out.println("No data to show, Table message is match");
		}
		Log.endTestCase("verifyEmployeeDelete");
	}

	@Test
	public void editEmployee() throws InterruptedException {

		Log.startTestCase("editEmployee");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		String employeeName = "amelia";
		emplist.enterDataInName(employeeName);

		emplist.searchButton();
		Thread.sleep(2000);
		String message = emplist.tableSearchRecordMessage();
		System.out.println(message);

		if (!message.equals("No Records Found")) {

			// countTableData() return row size
			String noOfRecords = emplist.getNameFromTable(employeeName);
			System.out.println(noOfRecords);

			if (noOfRecords.equalsIgnoreCase(employeeName)) {
				System.out.println("The entered Name " + employeeName + " is available in data");
				String title = emplist.editEmployeeFormMeassage();
		
				String expTitle = "Personal Details";

				assertEquals(expTitle, title, "Edit Page not Open");

			} else {
				System.out.println("Record available but entered Name not found");
			}

		}

		else if (message.contains("No Records Found")) {
			System.out.println("No data to show, Table message is match");
		}
		Log.startTestCase("editEmployee");
	}

	@Test
	public void addEmployee() throws InterruptedException {
		
		Log.startTestCase("addEmployee");
		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();

		Log.info("User entering credentials to login");
		dashboard = login.verifyLogin(prop.getProperty("username"), prop.getProperty("password"));
		
		Log.info("Navigate on PIM page");
		emplist = dashboard.navigateToPIM();

		emplist.employeeAddButton();
		
		String title = empadd.addEmployeePageTitle();
		String expTitle = "Add Employee";

		assertEquals(expTitle, title, "Add Employee Page not Open");
		Log.endTestCase("addEmployee");
	}
		
}