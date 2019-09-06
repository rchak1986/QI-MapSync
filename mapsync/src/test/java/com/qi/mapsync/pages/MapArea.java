package com.qi.mapsync.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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
    @FindBy(how=How.ID, using = "QiPanZoomBar_2_zoomin_innerImage") WebElement btnZoomIn;
    @FindBy(how=How.ID, using="popup_close") WebElement welMapPopUpClose;
    @FindBy(how=How.ID, using="QiPanZoomBar_2_OpenLayers.Map_5") WebElement welMapZoomDragger;
    
    @FindBy(how=How.ID, using="ifCam") WebElement frmCamFrame;
    @FindBy(how=How.ID, using="myDropdownList") WebElement frmTollFrame;
    
    public void switch2CamFrame(){
    	driver.switchTo().frame(frmCamFrame);
    }
    
    public void switch2TollFrame(){
    	driver.switchTo().frame(frmTollFrame);
    }
    
    public void switch2DefaultFrame(){
    	driver.switchTo().defaultContent();
    }
    
    public boolean validateMapPopUp(){
    	if (welMapIncidentPopUp.isDisplayed()) return true;
    	else return false;
    }
    
    public void closeMapPopUp() throws InterruptedException{
    	welMapPopUpClose.click();
    	Thread.sleep(1000);
    }
    
    public boolean validateMapPopUpTimeAndDesc(String incidentType,String timeAndDesc){
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
    }
    
    public void clickOnZoomIn(int numberOfTimes){
    	for (int i=0;i<numberOfTimes; i++) btnZoomIn.click();
    }
    public void waitUntilBannerDisplayed() {
    	try {
			driverUtil.waitUntilObjClickable(driver, btnCollapseGalactioAd);
			Thread.sleep(2000);
		} catch (Exception e) {
		}    	
    }
    public void closeAdBanner() throws InterruptedException{
    	if (btnCollapseGalactioAd.getAttribute("class").contains("collapse")){
    		btnCollapseGalactioAd.click();
    		Thread.sleep(1000);
    	}
    }
    public int getZoomDraggerPosition(){
    	String styleInfo = welMapZoomDragger.getAttribute("style");
    	String positionInfo = styleInfo.split("\\;")[2].trim().split("\\:")[1].trim().replace("px", "");
    	
    	if (positionInfo!=null) return Integer.parseInt(positionInfo);
    	else return 0;
    }
    
}
