package com.qi.mapsync.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils; 

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qi.mapsync.common.utilities.*;

@Listeners(TestReporter.class)
public class TestBase extends Report {	
	public WebDriver driver;
	private Utilities util=null;
	public ExtentReports reports;
	public ExtentTest test;
	public ExtentHtmlReporter htmlReport;
	
	public WebDriver getDriver() {
        return driver;
	}
	
	public ExtentTest getETest(){
		return test;
	}
	
	public ExtentReports getEReport(){
		return reports;
	}
	
	@BeforeMethod
	public void beforeTestMethod(Method testMethod){   
		test = startTest(reports,testMethod.getName());
	}
	@BeforeSuite
	public void cleanReportDirectory() throws IOException{
		try{
			FileUtils.cleanDirectory(new File(System.getProperty("user.dir")+"/test-output/CustomReport/"));
		}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
	}
	
	@AfterMethod
	public void afterMethod(){
		if(reports==null)reports=getEReport();
	}
	
	@SuppressWarnings("deprecation")
	@BeforeClass
	@Parameters({ "browser" })
	public void initialize(@Optional String browser) throws IOException{
		reports = initiateReport(this.getClass().getName().split("\\.")[4]);
		
		util = new Utilities();
		String brw = util.getPropertyValue("Browser");
		if (brw==null) brw="chrome";
		if (browser!=null && !browser.equals("")) brw= browser.toLowerCase();
		
		switch(brw.trim().toLowerCase()){
		default:
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\Resources\\drivers\\chromedriver.exe");
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			String[] switches = {"--ignore-certificate-errors", "--disable-popup-blocking", "--disable-translate"};
			dc.setCapability("chrome.switches", Arrays.asList(switches));
			driver = new ChromeDriver(dc);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\Resources\\drivers\\geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("pdfjs.disabled", true);  // disable the built-in PDF viewer
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);

			FirefoxOptions optionsFF = new FirefoxOptions();
			optionsFF.setProfile(profile);
			if (util.checkFilePresent("C:/Program Files (x86)/Mozilla Firefox/firefox.exe")) {
				optionsFF.setBinary("C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
				driver = new FirefoxDriver(optionsFF);
			} else if (util.checkFilePresent("C:/Program Files/Mozilla Firefox/firefox.exe")) {
				optionsFF.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
				driver = new FirefoxDriver(optionsFF);
			}

			break;
		case "internet explorer":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\Resources\\drivers\\IEDriverServer.exe");
			DesiredCapabilities dcIE = DesiredCapabilities.internetExplorer();
			dcIE.setCapability("ignoreZoomSetting", true);
			dcIE.setCapability("ignoreProtectedModeSettings", true);
			dcIE.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			//Delete Browser Cache since IE does not open a clean profile unlike Chrome & FireFox
			try {
				Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			} catch (IOException e) {
				e.printStackTrace();
			}
			driver = new InternetExplorerDriver(dcIE);
			break;
		case "microsoftedge":
			try {
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/drivers/MicrosoftWebDriver.exe");
                EdgeOptions edgeOpt = new EdgeOptions();//`new EdgeOptions()` is preferred to `DesiredCapabilities.edge()`
                edgeOpt.setCapability(CapabilityType.ACCEPT_SSL_CERTS,true);
                edgeOpt.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT,true);
                driver = new EdgeDriver(edgeOpt);
			} catch (Exception err) {				
			}
			break;
		case "android":
			//add mobile driver steps
			//add mobile driver capabilities
			break;
		case "ios":
			//add mobile driver steps
			//add mobile driver capabilities
			break;
		}
		
		driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
      	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      	
      	String url = util.getPropertyValue("URL");
		if (url==null) url="http://www.mapsynq.com";
      	driver.get(url);		
	}
	
	@AfterClass
	//Test cleanup
	public void TeardownTest()
    {
        driver.quit();
        closeReport(reports);
    }

//	@AfterSuite
//	public void flushReport(){
////		driver.quit();
//		closeReport(reports);
//	}
}
