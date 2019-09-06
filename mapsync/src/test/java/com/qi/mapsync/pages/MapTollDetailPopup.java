package com.qi.mapsync.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import com.qi.mapsync.common.utilities.CommonDriverUtilities;
import com.qi.mapsync.common.utilities.Utilities;

public class MapTollDetailPopup {
	WebDriver driver;
	Utilities util;
	CommonDriverUtilities driverUtil;
    public MapTollDetailPopup(WebDriver driver){ 
             this.driver=driver; 
             util= new Utilities();
             driverUtil = new CommonDriverUtilities(driver);
    }
    
    @FindBy(how=How.XPATH, using="/html/body/div/label/b") WebElement welLocationInfo;
    @FindBy(how=How.ID, using="select_price_chart") WebElement ddlVahicleTypes;
    @FindBy(how=How.XPATH, using="//*[@id='div_erp_rate']/table") List<WebElement> tblTaxRates;
    
    public boolean validateTollLocation(String expectedLocation){
    	boolean flag=false;
    	if (welLocationInfo.getText().trim().contains(expectedLocation)) flag=true;
    	return flag;
    }
    
    public boolean validateVehicleTypesWRTItemCount(int itemCount){
    	Select sel = new Select(ddlVahicleTypes);
    	List<WebElement> list = sel.getOptions();
    	if (itemCount==list.size())return true;
    	else return false;
    }

    public boolean validateVehicleTypeIfPresent(String vType){
    	boolean flag=false;
    	Select sel = new Select(ddlVahicleTypes);
    	List<WebElement> list = sel.getOptions();
    	for (WebElement e : list){
    		if (e.getText().trim().contains(vType)){
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }
    
    public boolean selectVehicleTypeIfPresent(String vType){
    	boolean flag=false;
    	Select sel = new Select(ddlVahicleTypes);
    	List<WebElement> list = sel.getOptions();
    	for (WebElement e : list){
    		if (e.getText().trim().contains(vType)){
    			sel.selectByValue(e.getText());
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }
    
    public WebElement getTargetTaxTable(){
    	WebElement tElem=null;
    	for (WebElement elem: tblTaxRates){
    		if (elem.getAttribute("style").contains("display:block;")){
    			tElem=elem;
    			break;
    		}
    	}
    	return tElem;
    }
    
    public boolean validateTaxTable(HashMap<String,String> taxSet) throws Exception{
    	boolean flag=true;
    	for (String key : taxSet.keySet()) {
    		String time = key;
			String cost = taxSet.get(key);
			
			WebElement targetTable = getTargetTaxTable();
			int targetRow = driverUtil.getRowWithCellText(targetTable, time);
			if (targetRow>0){
				String targetCost = driverUtil.getCellData(targetTable, targetRow, 2);
				if (!targetCost.equals(cost)){
					flag=false;
					break;
				}
			}			
		}
    	return flag;
    }
    
    public boolean validateIfTaxTableDisplayed(){
    	WebElement tbl = getTargetTaxTable();
    	return tbl.isDisplayed();
    }
}
