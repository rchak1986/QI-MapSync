package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class Incidents {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public Incidents(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.XPATH, using="//*[@id='info_panel']//a[contains(@onclick,'Live')]") WebElement tabLive;
    @FindBy(how=How.XPATH, using="//*[@id='rdIncidents']/following-sibling::label[contains(@for,'Incidents')]") WebElement tabIncidents;
    @FindBy(how=How.ID, using="txtSearchIncidentsingapore") WebElement txtSearchIncident;
    @FindBy(how=How.XPATH, using="//*[@id='search_incident_singapore']/li//div[@class='item_content']") List<WebElement> lstIncidentList;
    @FindBy(how=How.XPATH, using="//*[@id='incidentsingapore']/div[@class='no_result_txtSearchIncidentsingapore']") WebElement welNoSearchResult;    
    @FindBy(how=How.ID, using="selIncidentDays")WebElement ddlIncidentDay;
    @FindBy(how=How.ID, using="divIncidentInfo")WebElement welNoIncidentData;
    @FindBy(how=How.ID, using="popup_contentDiv") WebElement welMapIncidentPopUp;
    
    
    public void loadIncidents(){
    	tabLive.click();
    	tabIncidents.click();
    	driverUtil.waitForLoad(driver);
    }
    
    public boolean validateNoIncident(){
    	return welNoIncidentData.isDisplayed();
    }
    
    public void pageRefresh(){
    	driver.navigate().refresh();
    	driverUtil.waitForLoad(driver);
    }
    
    public void selectYesterday() throws InterruptedException{
    	Select sel = new Select(ddlIncidentDay);
    	sel.selectByIndex(1);
    	Thread.sleep(5000);
    }
    public void selectToday() throws InterruptedException{
    	Select sel = new Select(ddlIncidentDay);
    	sel.selectByIndex(0);
    	Thread.sleep(3000);
    }
    
    public boolean validateIncidentList(){
    	if (lstIncidentList.size()>0) return true;
    	else return false;
    }
   
    public boolean selectIncidentByType(String incidentType){
    	boolean flag=false;
    	for (WebElement elem:lstIncidentList){
    		if (elem.getText().contains(incidentType)){
    			elem.click();
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }
    
    public String selectIncidentByTypeAndCaptureTimeAndDesc(String incidentType) throws Exception{
    	String time=null;
    	for (WebElement elem:lstIncidentList){
    		if (elem.getText().contains(incidentType)){
    			elem.click();
    			time = elem.findElement(By.tagName("div")).getText().trim() + "|" + elem.getText().trim();
    			driverUtil.waitUntilObjDisplayed(driver, welMapIncidentPopUp);
    			break;
    		}
    	}
    	return time;
    }
    
    
    
    public void searchIncident(String text) throws InterruptedException{
    	txtSearchIncident.sendKeys(text);
    	Thread.sleep(5000);
    }
    
    public void clearSearch() throws InterruptedException{
    	txtSearchIncident.clear();
    	Thread.sleep(5000);
    }
    
    public boolean validateSearchIncident(boolean validSearch){
    	if (validSearch){
    		if (lstIncidentList.size()>0 && lstIncidentList.get(0).isDisplayed() && !welNoSearchResult.isDisplayed()) return true;
    		else return false;
    	}else{
    		if (welNoSearchResult.isDisplayed()) return true;
    		else return false;
    	}
    }
}
