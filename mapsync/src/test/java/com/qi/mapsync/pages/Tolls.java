package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    	tabLive.click();
    	tabTolls.click();
    	driverUtil.waitForLoad(driver);
    }
    
    public boolean validateTollList(){
    	if (lstTollsList.size()>0) return true;
    	else return false;
    }
    
    public String selectTollLocationAndCaptureInfo(String tollNameOrLocation) throws Exception{
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
    }
    
    public void searchToll(String tollNameOrLocation) throws InterruptedException{
    	txtSearchTollLocation.sendKeys(tollNameOrLocation);
    	Thread.sleep(5000);
    }
    
    public void clearSearch() throws InterruptedException{
    	txtSearchTollLocation.clear();
    	Thread.sleep(3000);
    }
    
    public boolean validateTollSearch(boolean validSearch){
    	if (validSearch){
    		if (lstTollsList.size()>0 && !welNoTollFound.isDisplayed()) return true;
    		else return false;
    	}else{
    		if (welNoTollFound.isDisplayed()) return true;
    		else return false;
    	}
    }
}
