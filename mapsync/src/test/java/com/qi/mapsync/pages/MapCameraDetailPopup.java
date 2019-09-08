package com.qi.mapsync.pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class MapCameraDetailPopup {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public MapCameraDetailPopup(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.XPATH, using="/html/body/div/label") WebElement welLastUpdateInfo;
    @FindBy(how=How.XPATH, using="/html/body/div/label/b") WebElement welLocationInfo;
    @FindBy(how=How.XPATH, using="/html/body/div/img") WebElement welCamImageInfo;
    
    public boolean validateCameraLocation(String expectedLocation){
    	boolean flag=false;
    	if (welLocationInfo.getText().trim().toLowerCase().contains(expectedLocation.toLowerCase())) flag=true;
    	return flag;
    }
    
    public boolean validateLastUpdateInfo(){
    	Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-YYYY");  
	    String strDate= formatter.format(date); 
	    
	    if (welLastUpdateInfo.getText().trim().contains(strDate))return true;
	    else return false;
    }
    
    public boolean validateCameraImage(){
    	return welCamImageInfo.isDisplayed();
    }
}
