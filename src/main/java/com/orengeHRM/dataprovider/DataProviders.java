package com.orengeHRM.dataprovider;

import com.orengeHRM.utility.ReadExcelFile;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "LoginData")
	public String[][] LoginDataProvider() {
		// System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";

		// Totals rows count
		int ttlRows = ReadExcelFile.getRowCount(fileName, "Credentials");
		// Total Columns
		int ttlColumns = ReadExcelFile.getColCount(fileName, "Credentials");

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"Credentials", i,j);
			}

		}
		return data;
	}
	
	
		@DataProvider(name = "EnterEmployeeName")
		public String[][] addEmployeeName() {
			// System.out.println(System.getProperty("user.dir"));
			String fileName = System.getProperty("user.dir") + "\\TestData\\TestData.xlsx";

			// Totals rows count
			int ttlRows = ReadExcelFile.getRowCount(fileName, "AddEmployee");
			// Total Columns
			int ttlColumns = ReadExcelFile.getColCount(fileName, "AddEmployee");

			String data[][]=new String[ttlRows-1][ttlColumns];

			for(int i=1;i<ttlRows;i++)//rows =1,2
			{
				for(int j=0;j<ttlColumns;j++)//col=0, 1,2
				{

					data[i-1][j]=ReadExcelFile.getCellValue(fileName,"AddEmployee", i,j);
				}

			}
			return data;
	}

}
