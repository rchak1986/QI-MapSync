package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class Tolls {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public Tolls(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.ID, using="txtSearchERPsingapore") WebElement txtSearchTollLocation;
    @FindBy(how=How.XPATH, using="//*[@id='info_panel']//a[contains(@onclick,'Live')]") WebElement tabLive;
    @FindBy(how=How.XPATH, using="//*[@id='rdIncidents']/following-sibling::label[contains(@for,'Erp')]") WebElement tabTolls;
    @FindBy(how=How.XPATH, using="//*[@id='erp_locationsingapore']/li//a") List<WebElement> lstTollsList;
    @FindBy(how=How.XPATH, using="//*[@id='singaporeerpWrapper']//div[@class='no_result_txtSearchERPsingapore']") WebElement welNoTollFound;
    @FindBy(how=How.ID, using="popup_contentDiv") WebElement welMapTollPopUp;
    
    public void loadTollList(){
    	try{
	    	tabLive.click();
	    	tabTolls.click();
	    	driverUtil.waitForLoad(driver);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateTollList(){
    	try{
	    	if (lstTollsList.size()>0) return true;
	    	else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public String selectTollLocationAndCaptureInfo(String tollNameOrLocation){
    	try{
	    	String camInfo= null;
	    	for(WebElement elem:lstTollsList){
	    		if(elem.getText().trim().toLowerCase().contains(tollNameOrLocation.toLowerCase())){
	    			camInfo = elem.getText().trim();
	    			elem.click();
	    			driverUtil.waitUntilObjDisplayed(driver, welMapTollPopUp);
	    			break;
	    		}
	    	}
	    	return camInfo;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    public void searchToll(String tollNameOrLocation) {
    	try{
	    	txtSearchTollLocation.sendKeys(tollNameOrLocation);
	    	Thread.sleep(5000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clearSearch(){
    	try{
	    	txtSearchTollLocation.clear();
	    	Thread.sleep(3000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateTollSearch(boolean validSearch){
    	try{
	    	if (validSearch){
	    		if (lstTollsList.size()>0 && !welNoTollFound.isDisplayed()) return true;
	    		else return false;
	    	}else{
	    		if (welNoTollFound.isDisplayed()) return true;
	    		else return false;
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
}
