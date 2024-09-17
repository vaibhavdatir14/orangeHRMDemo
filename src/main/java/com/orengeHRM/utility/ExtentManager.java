package com.orengeHRM.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() {
      
    	if (extent == null) {
            // Initialize ExtentSparkReporter
//            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport/" + "MyReport.html");
            
        	ReadConfig readConfig = new ReadConfig();
    		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
    		String reportName = "OrengeHRM-" + timestamp + ".html";
            
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + reportName);
        	
         
            
            // Load configuration from XML
            try {
				sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
				
				// Initialize ExtentReports
	            extent = new ExtentReports();
	            extent.attachReporter(sparkReporter);

	            extent.setSystemInfo("HostName", "Vaibhav");
	            extent.setSystemInfo("ProjectName", "OrengeHRM");
	            extent.setSystemInfo("Tester", "Vaibhav");
	            extent.setSystemInfo("OS", "Win11");
	            extent.setSystemInfo("browser:", readConfig.getBrowser());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
/*            ReadConfig readConfig = new ReadConfig();
    		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
    		String reportName = "OrengeHRM-" + timestamp + ".html";
            
            sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);
            // Configure reporter settings
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Extent Report");
            sparkReporter.config().setReportName("Test Automation Report");

            // Initialize ExtentReports
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("HostName", "Vaibhav");
            extent.setSystemInfo("ProjectName", "OrengeHRM");
            extent.setSystemInfo("Tester", "Vaibhav");
            extent.setSystemInfo("OS", "Win11");
            extent.setSystemInfo("browser:", readConfig.getBrowser());
 */          
        }
    }

    public static void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
