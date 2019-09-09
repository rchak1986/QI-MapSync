package com.qi.mapsync.common.utilities;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Report {
	public ExtentTest test;
	public ExtentReports report;
	public ExtentHtmlReporter htmlReport;

	public ExtentReports initiateReport(String reportName)
	{
		if (reportName==null) reportName="Report_";
		Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/CustomReport/"+reportName+"_"+formater.format(calendar.getTime())+".html");
        report = new ExtentReports();
		report.attachReporter(htmlReport);
		return report;
	}
	
	public ExtentTest startTest(ExtentReports report,String testName){
		test = report.createTest(testName);
		return test;
	}
	
	public void closeReport(ExtentReports report){
		report.flush();
	}
}