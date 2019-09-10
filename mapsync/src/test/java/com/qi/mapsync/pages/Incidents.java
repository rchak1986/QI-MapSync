package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

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
    	try{
	    	tabLive.click();
	    	tabIncidents.click();
	    	driverUtil.waitForLoad(driver);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateNoIncident(){
    	try{
    		return welNoIncidentData.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void pageRefresh(){
    	try{
	    	driver.navigate().refresh();
	    	driverUtil.waitForLoad(driver);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void selectYesterday(){
    	try{
	    	Select sel = new Select(ddlIncidentDay);
	    	sel.selectByIndex(1);
	    	Thread.sleep(5000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public void selectToday() {
    	try{
	    	Select sel = new Select(ddlIncidentDay);
	    	sel.selectByIndex(0);
	    	Thread.sleep(3000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateIncidentList(){
    	try{
	    	if (lstIncidentList.size()>0) return true;
	    	else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public String getAnyRandomIncident(){
    	try{
	    	int incNum = util.getRandom(0, (lstIncidentList.size()-1));
	    	String separator =  "on";
	    	if (!lstIncidentList.get(incNum).getText().contains("on"))separator="in";
	    	return lstIncidentList.get(incNum).getText().split(separator)[1];
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    public String getAnyRandomIncidentType(){
    	try{
	    	int incNum = util.getRandom(0, (lstIncidentList.size()-1));
	    	String separator =  "on";
	    	if (!lstIncidentList.get(incNum).getText().contains("on"))separator="in";
	    	return lstIncidentList.get(incNum).getText().split(separator)[0].split("\\ ")[1];
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
   
    public boolean selectIncidentByType(String incidentType){
    	try{
	    	boolean flag=false;
	    	for (WebElement elem:lstIncidentList){
	    		if (elem.getText().contains(incidentType)){
	    			elem.click();
	    			flag=true;
	    			break;
	    		}
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public String selectIncidentByTypeAndCaptureTimeAndDesc(String incidentType) throws Exception{
    	try{
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
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
       
    public void searchIncident(String text) {
    	try{
	    	txtSearchIncident.sendKeys(text);
	    	Thread.sleep(5000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clearSearch()  {
    	try{
	    	txtSearchIncident.clear();
	    	Thread.sleep(5000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateSearchIncident(boolean validSearch){
    	try{
	    	if (validSearch){
	    		if (lstIncidentList.size()>0 && !welNoSearchResult.isDisplayed()) return true;
	    		else return false;
	    	}else{
	    		if (welNoSearchResult.isDisplayed()) return true;
	    		else return false;
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
}
