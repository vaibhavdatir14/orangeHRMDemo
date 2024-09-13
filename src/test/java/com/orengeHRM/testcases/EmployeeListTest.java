package com.orengeHRM.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.orengeHRM.base.BaseClass;
import com.orengeHRM.pageobject.Dashboard;
import com.orengeHRM.pageobject.Login;
import com.orengeHRM.pageobject.PIM_EmpAdd;
import com.orengeHRM.pageobject.PIM_EmpList;

public class EmployeeListTest extends BaseClass {

	Login login;
	Dashboard dashboard;
	PIM_EmpList emplist;
	PIM_EmpAdd empadd;
	

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
	public void verifyTableMsgSearchByName() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.enterDataInName("anik54");
//		Thread.sleep(3000);
		String actval = emplist.getDataFromName();
		String expresult = "Records Found";
		String expresult1 = "No Records Found";

		if (actval != null && !actval.isEmpty()) {
			System.out.println("Value present in Name field");

			emplist.searchButton();
			String message = emplist.tableSearchRecordMessage();

			// Toaster is disappear quickly thats why it store here in variable
			String toast = emplist.toasterSearchRecordMessage(true);

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

	}

//	@Test
	public void verifyTableMsgSearchByID() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.enterDataInID("vai");

		emplist.searchButton();

		String toast = emplist.toasterSearchRecordMessage(true);
		String tabmessage = emplist.tableSearchRecordMessage();

		String exprslt = "Records Found";
		String eres = "Record Found";
//		String exprslt1 = "No Records Found";

		if (tabmessage != null && (tabmessage.contains(exprslt) || tabmessage.contains(eres))) {
			System.out.println("Value present in ID field");

			System.out.println("Table Result " + tabmessage);

		}

		else {

			System.out.println(toast);
			// emplist.toasterSearchRecordMessage(true);
			System.out.println("No Result " + tabmessage);
		}

	}

	@Test
	public void empSearchByStatus() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		Thread.sleep(2000);
		emplist.selectFromStatus(3);
	
		emplist.searchButton();
		String tabmessage = emplist.tableSearchRecordMessage();
		String toast = emplist.toasterSearchRecordMessage(true);
		
		String expreslt = "Records Found";
		String expreslt1 = "No Records Found";

		if (tabmessage != null && (tabmessage.contains(expreslt) || tabmessage.contains(expreslt1))) {
			System.out.println("Value selected in status dropdown");

			System.out.println("Table Result " + tabmessage);

		}

		else {

			System.out.println(toast);
			// emplist.toasterSearchRecordMessage(true);
			System.out.println("No. of records :-  " + tabmessage);
		}

	}

//	@Test
	public void verifyNoOfRowsEqualsTabMessage() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.enterDataInName("charl");

		emplist.searchButton();
		Thread.sleep(2000);
		String message = emplist.tableSearchRecordMessage();
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
	}

//	@Test
	public void verifyNameInTable() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
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

	}

//	@Test
	public void verifyEmployeeDelete() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
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

	}

	@Test
	public void editEmployee() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
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

	}

	@Test
	public void addEmployee() throws InterruptedException {

		login = new Login();
		dashboard = new Dashboard();
		emplist = new PIM_EmpList();
		empadd = new PIM_EmpAdd();

		dashboard = login.validLogin(prop.getProperty("username"), prop.getProperty("password"));
		emplist = dashboard.navigateToPIM();

		emplist.employeeAddButton();
		
		String title = empadd.addEmployeePageTitle();
		String expTitle = "Add Employee";

		assertEquals(expTitle, title, "Add Employee Page not Open");

	}

}