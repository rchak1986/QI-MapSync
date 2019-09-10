package com.qi.mapsync.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class MapArea {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public MapArea(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.XPATH, using="//*[@id='ad_toggle'][@class='sprite ad_bar_toggle ad_bar_collapse']") WebElement btnCollapseGalactioAd;
    @FindBy(how=How.ID, using="popup_contentDiv") WebElement welMapIncidentPopUp;
    @FindBy(how=How.XPATH, using="//*[@id='popup_contentDiv']/div") WebElement welIncidentType;    
    @FindBy(how=How.XPATH, using="//*[@id='popup_contentDiv']/div/b") WebElement welPopupTitle;
    @FindBy(how=How.ID, using = "QiPanZoomBar_2_zoomin_innerImage") WebElement btnZoomIn;
    @FindBy(how=How.ID, using="popup_close") WebElement welMapPopUpClose;
    @FindBy(how=How.ID, using="QiPanZoomBar_2_OpenLayers.Map_5") WebElement welMapZoomDragger;
    @FindBy(how=How.XPATH, using="//*[@id='popup_contentDiv']/table/tbody/tr/td/b") WebElement welParkingPopup;
    @FindBy(how=How.XPATH, using="//*[@class='speedLegendPanel olControlNoSelect']") WebElement welTrafficLegend;
    
    @FindBy(how=How.ID, using="ifCam") WebElement frmCamFrame;
    @FindBy(how=How.ID, using="myDropdownList") WebElement frmTollFrame;
    
    @FindBy(how=How.XPATH, using="//*[@class='buttonPanel olControlNoSelect']/div") List<WebElement> tabMapTabs;
    @FindBy(how=How.XPATH, using="//*[@class='buttonPanel olControlNoSelect']/div[contains(@title,'Incidents')]") WebElement tabIncidents;
    @FindBy(how=How.XPATH, using="//*[contains(@id,'OpenLayers.Geometry.Point_')]") List<WebElement> welMapIcons;
    @FindBy(how=How.XPATH, using="//*[contains(@id,'OpenLayers.Feature.Vector_')][contains(@id,'tspan')]") List<WebElement> welMultiIcons;
    @FindBy(how=How.XPATH, using="//*[@id='popup_contentDiv']/a") List<WebElement> lnkPopupLinks;
    
    public boolean validateIfTrafficSpeedLegendDisplayed(){
    	try{
    		return welTrafficLegend.isDisplayed();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public boolean validateParkingArea(String expectedName){
    	try{
	    	boolean flag=false;
	    	if (welParkingPopup.getText().toLowerCase().contains(expectedName.toLowerCase())) flag=true;
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateParkingLotCounts(String expectedCount){
    	try{
	    	boolean flag=false;
	    	if (welPopupTitle.getText().contains(expectedCount)) flag=true;
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void clickOnAnyMapIcon()  {
    	try{
	    	if (welMapIcons.size()>0){
	    		welMapIcons.get(0).click();
	    		Thread.sleep(1000);
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void clickOnMapTabs(String tabName){
    	try{
	    	tabIncidents.click();
	    	Thread.sleep(3000);
	    	
	    	for(WebElement elem:tabMapTabs){
	    		if(elem.getAttribute("title").trim().toLowerCase().contains(tabName.toLowerCase())){
	    			elem.click();
	    			Thread.sleep(5000);
	    			driverUtil.waitUntilObjDisplayed(driver, welMapIcons.get(0));
	    			break;
	    		}
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean isTabEnabled(String tabName){
    	try{
	    	boolean flag=false;
	    	for(WebElement elem:tabMapTabs){
	    		if(elem.getAttribute("title").trim().toLowerCase().contains(tabName.toLowerCase()) && 
	    				!elem.getAttribute("class").toLowerCase().contains("inactive")){
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
     
    public String selectAndCaptureCountMultiIcons(){
    	try{
	    	String count=null;
	    	if (welMultiIcons.size()>0){
	    		count = welMultiIcons.get(0).getText();
	    		int textCordinate = Integer.parseInt(welMultiIcons.get(0).getAttribute("id").split("\\_")[1]);
	    		
	    		for(WebElement elem:welMapIcons){
	    			if (elem.getAttribute("href").contains("clus")){
	    				if (getMultiSelectCoordinate(elem)==textCordinate){
		    				elem.click();
		    				Thread.sleep(1000);
		    				break;
	    				}
	    			}
	    		}
	    	}
	    	return count;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    public String selectAndCaptureCountMultiIconsParking(){
    	try{
	    	String count=null;
	    	int textCordinate=0;
	    	if (welMultiIcons.size()>0){
	    		for(WebElement e:welMultiIcons){
	    			if (Integer.parseInt(e.getText().trim())<=5){
	    				count = e.getText();
	    	    		textCordinate = Integer.parseInt(e.getAttribute("id").split("\\_")[1]);
	    	    		break;
	    			}
	    		}
	    		
	    		for(WebElement elem:welMapIcons){
	    			if (elem.getAttribute("href").contains("clus")){
	    				if (getMultiSelectCoordinate(elem)==textCordinate){
		    				elem.click();
		    				Thread.sleep(1000);
		    				break;
	    				}
	    			}
	    		}
	    	}
	    	return count;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    private int getMultiSelectCoordinate(WebElement elem){
    	try{
	    	String cood = elem.getAttribute("id").split("\\_")[1];
	    	return (Integer.parseInt(cood)+1);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return 0;
    	}
    }
    
    public boolean validatePopupLinkCount(int expectedCount){
    	try{
	    	boolean flag=false;
	    	if (expectedCount==lnkPopupLinks.size())flag=true;
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateZoomInLink(){
    	try{
	    	boolean flag=false;
	    	String lnkName = "Zoom in";
	    	for(WebElement elem:lnkPopupLinks){
	    		if (elem.getText().trim().toLowerCase().equals(lnkName.toLowerCase())){
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
    
    public String clickAndCaptureFirstPopupLink(){
    	try{
	    	String lnkName = "Zoom in";
	    	String lnk=null;
	    	for(WebElement elem:lnkPopupLinks){
	    		if (!elem.getText().trim().toLowerCase().equals(lnkName.toLowerCase())){
	    			lnk=elem.getText().trim().toLowerCase();
	    			elem.click();
	    			break;
	    		}
	    	}
	    	return lnk;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return null;
    	}
    }
    
    public void switch2CamFrame(){
    	try{
    		driver.switchTo().frame(frmCamFrame);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void switch2TollFrame(){
    	try{
    		driver.switchTo().frame(frmTollFrame);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public void switch2DefaultFrame(){
    	try{
    		driver.switchTo().defaultContent();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateMapPopUp(){
    	try{
	    	if (welMapIncidentPopUp.isDisplayed()) return true;
	    	else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void closeMapPopUp(){
    	try{
	    	welMapPopUpClose.click();
	    	Thread.sleep(1000);
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
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
    
    public boolean validateMapPopUpTimeAndDesc(String incidentType,String timeAndDesc){
    	try{
	    	boolean flag=false;
	    	if (timeAndDesc!=null && incidentType!=null){
	    		if (welIncidentType.getText().trim().toLowerCase().equals(incidentType.toLowerCase())){
	    			if (welMapIncidentPopUp.getText().trim().contains(timeAndDesc.split("\\|")[0])){
	    				if (welMapIncidentPopUp.getText().trim().contains(timeAndDesc.split("\\|")[1])){
	    					flag=true;
	    				}
	    			}
	    		}
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public boolean validateMapPopUpDesc(String desc){
    	try{
	    	boolean flag=false;
	    	if (desc!=null){
	  			if (welMapIncidentPopUp.getText().trim().toLowerCase().contains(desc)){
	    			flag=true;
	    		}
	    	}
	    	return flag;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    
    public void clickOnZoomIn(int numberOfTimes){
    	try{
    		for (int i=0;i<numberOfTimes; i++) btnZoomIn.click();
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    public void waitUntilBannerDisplayed() {
    	try {
			driverUtil.waitUntilObjClickable(driver, btnCollapseGalactioAd);
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}    	
    }
    public void closeAdBanner(){
    	try{
	    	if (btnCollapseGalactioAd.getAttribute("class").contains("collapse")){
	    		btnCollapseGalactioAd.click();
	    		Thread.sleep(1000);
	    	}
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    	}
    }
    
    public boolean validateAdBanner(){
    	try{
	    	if (btnCollapseGalactioAd.getAttribute("class").contains("collapse")){
	    		return true;
	    	}else return false;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return false;
    	}
    }
    public int getZoomDraggerPosition(){
    	try{
	    	String styleInfo = welMapZoomDragger.getAttribute("style");
	    	String positionInfo = styleInfo.split("\\;")[2].trim().split("\\:")[1].trim().replace("px", "");
	    	
	    	if (positionInfo!=null) return Integer.parseInt(positionInfo);
	    	else return 0;
    	}catch(Exception e){
    		Reporter.log(e.getMessage());
    		return 0;
    	}
    }
    
}
