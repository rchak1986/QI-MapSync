package com.qi.mapsync.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;
public class HomePage {
	
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public HomePage(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }

    @FindBy(how=How.ID, using="txtGlobalSearch") WebElement txtGlobalSearch;
    @FindBy(how=How.XPATH, using="//*[@id='formPublicSearch']/span") WebElement btnGlobalSearch;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Sign in')]") WebElement lnkSignIn;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Register')]") WebElement lnkRegister;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Mobile App')]") WebElement lnkMobileApp;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Galactio')]") WebElement lnkGalactio;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'SG GPS Navigation')]") WebElement lnkGPSNavigation;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Legend')]") WebElement lnkLegend;
    @FindBy(how=How.XPATH, using="//*[@id='div_header']//a[contains(text(),'Calendar')]") WebElement lnkCalendar;
    @FindBy(how=How.XPATH, using="//*[@id='info_panel']//a[contains(@class,'tab_button')]") List<WebElement> tabLeftTabs;
    
    public void clickOnRegister(){
    	try{
    		lnkRegister.click();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public void clickOnSignin(){
    	try{
    		lnkSignIn.click();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public boolean validateAndClickLeftTab(String tabText) {
    	try{
	    	boolean flag=false;
	    	switch(tabText.toLowerCase()){
	    		case "directions":
	    			for (WebElement elem: tabLeftTabs){
	    				if (elem.getAttribute("class").contains("directions")){
	    					flag=true;
	    					elem.click();
	    					Thread.sleep(1000);
	    					break;
	    				}
	    			}
	    			break;
	    		case "personal":
	    			for (WebElement elem: tabLeftTabs){
	    				if (elem.getAttribute("class").contains("me_tab")){
	    					flag=true;
	    					elem.click();
	    					Thread.sleep(1000);
	    					break;
	    				}
	    			}
	    			break;
	    		case "live":
	    			for (WebElement elem: tabLeftTabs){
	    				if (elem.getAttribute("class").contains("live_tab")){
	    					flag=true;
	    					elem.click();
	    					Thread.sleep(1000);
	    					break;
	    				}
	    			}
	    			break;
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validatePageLoad() {
    	try{
	    	driverUtil.waitForLoad(driver);
	    	return driverUtil.waitUntilObjEnabled(driver, btnGlobalSearch);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateGlobalSearchPresense(){
    	try{
    		return txtGlobalSearch.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateGlobalSearchButtonPresense(){
    	try{
    		return btnGlobalSearch.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateSignInLinkPresense(){
    	try{
    		return lnkSignIn.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateRegisterLinkPresense(){
    	try{
    		return lnkRegister.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateMobileAppLinkPresense(){
    	try{
    		return lnkMobileApp.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateGalactioLinkPresense(){
    	try{
    		return lnkGalactio.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateGPSNavigationLinkPresense(){
    	try{
    		return lnkGPSNavigation.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateLegendLinkPresense(){
    	try{
    		return lnkLegend.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateCalendarLinkPresense(){
    	try{
    		return lnkCalendar.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    //to do
    //add validation for other links here
    
    
}
