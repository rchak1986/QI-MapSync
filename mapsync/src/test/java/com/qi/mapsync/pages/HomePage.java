package com.qi.mapsync.pages;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    	lnkRegister.click();
    }
    public void clickOnSignin(){
    	lnkSignIn.click();
    }
    public boolean validateAndClickLeftTab(String tabText) throws InterruptedException{
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
    }
    
    public boolean validatePageLoad() throws Exception{
    	driverUtil.waitForLoad(driver);
    	return driverUtil.waitUntilObjEnabled(driver, btnGlobalSearch);
    }
    
    public boolean validateGlobalSearchPresense(){
    	return txtGlobalSearch.isDisplayed();
    }
    public boolean validateGlobalSearchButtonPresense(){
    	return btnGlobalSearch.isDisplayed();
    }
    public boolean validateSignInLinkPresense(){
    	return lnkSignIn.isDisplayed();
    }
    public boolean validateRegisterLinkPresense(){
    	return lnkRegister.isDisplayed();
    }
    public boolean validateMobileAppLinkPresense(){
    	return lnkMobileApp.isDisplayed();
    }
    public boolean validateGalactioLinkPresense(){
    	return lnkGalactio.isDisplayed();
    }
    public boolean validateGPSNavigationLinkPresense(){
    	return lnkGPSNavigation.isDisplayed();
    }
    public boolean validateLegendLinkPresense(){
    	return lnkLegend.isDisplayed();
    }
    public boolean validateCalendarLinkPresense(){
    	return lnkCalendar.isDisplayed();
    }
    //to do
    //add validation for other links here
    
    
}
