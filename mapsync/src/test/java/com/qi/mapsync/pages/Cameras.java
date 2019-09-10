package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class Cameras {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public Cameras(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.ID, using="txtSearchCamerasingapore") WebElement txtSearchCameraLocation;
    @FindBy(how=How.XPATH, using="//*[@id='info_panel']//a[contains(@onclick,'Live')]") WebElement tabLive;
    @FindBy(how=How.XPATH, using="//*[@id='rdIncidents']/following-sibling::label[contains(@for,'Cameras')]") WebElement tabCameras;
    @FindBy(how=How.XPATH, using="//*[@id='camera_location_singapore']/li//a") List<WebElement> lstCameraList;
    @FindBy(how=How.XPATH, using="//*[@id='singaporeCamWrapper']//div[@class='no_result_txtSearchCamerasingapore']") WebElement welNoCameraFound;
    @FindBy(how=How.ID, using="popup_contentDiv") WebElement welMapCamPopUp;
    
    public void loadCameras(){
    	try{
	    	tabLive.click();
	    	tabCameras.click();
	    	driverUtil.waitForLoad(driver);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateCameraList(){
    	try{
    		if (lstCameraList.size()>0) return true;
    		else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public String selectCameraLocationAndCaptureInfo(String camNameOrLocation) throws Exception{
    	String camInfo= null;
    	try{
	    	for(WebElement elem:lstCameraList){
	    		if(elem.getText().trim().toLowerCase().contains(camNameOrLocation.toLowerCase())){
	    			camInfo = elem.getText().trim();
	    			elem.click();
	    			driverUtil.waitUntilObjDisplayed(driver, welMapCamPopUp);
	    			break;
	    		}
	    	}
	    	return camInfo;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    public void searchCamera(String camNameOrLocation){
    	try{
	    	txtSearchCameraLocation.sendKeys(camNameOrLocation);
	    	Thread.sleep(5000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clearSearch() {
    	try{
	    	txtSearchCameraLocation.clear();
	    	Thread.sleep(3000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateCameraSearch(boolean validSearch){
    	try{
	    	if (validSearch){
	    		if (lstCameraList.size()>0 && !welNoCameraFound.isDisplayed()) return true;
	    		else return false;
	    	}else{
	    		if (welNoCameraFound.isDisplayed()) return true;
	    		else return false;
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
}
