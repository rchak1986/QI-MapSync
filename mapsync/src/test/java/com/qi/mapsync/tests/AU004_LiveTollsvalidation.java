package com.qi.mapsync.tests;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.MapTollDetailPopup;
import com.qi.mapsync.pages.Tolls;

public class AU004_LiveTollsvalidation extends TestBase {
	@Test
	public void validateTollList() throws Exception{
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		Assert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Tolls toll = PageFactory.initElements(driver, Tolls.class);
		toll.loadTollList();
		Assert.assertTrue(toll.validateTollList(),"Toll List is not loaded");
		
		toll.searchToll("CTE");
		Assert.assertTrue(toll.validateTollSearch(true), "Toll list is not populated");
		toll.clearSearch();
		
		toll.searchToll("abcdxyz");
		Assert.assertTrue(toll.validateTollSearch(false), "Toll list is not expected to get populated");
		toll.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String camDetail = toll.selectTollLocationAndCaptureInfo("ECP TO CITY");
		Assert.assertTrue(map.validateMapPopUp(), "Popup with cam detail is not shown up");
		int pos2=map.getZoomDraggerPosition();
		Assert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
		
		map.switch2TollFrame();
		MapTollDetailPopup mapToll = PageFactory.initElements(driver, MapTollDetailPopup.class);
		
		Assert.assertTrue(mapToll.validateTollLocation(camDetail),"Popup details does not match with the selected toll");
		Assert.assertTrue(mapToll.validateIfTaxTableDisplayed(),"Tax Table info is not shown correctly");
		Assert.assertTrue(mapToll.validateVehicleTypeIfPresent("Motorcycle (Weekdays)"),"Vehicle Type is not shown correctly");
		mapToll.selectVehicleTypeIfPresent("Motorcycle (Weekdays)");
		
		HashMap<String, String> taxMap = new HashMap<>();
		taxMap.put("00:00 - 06:59","$0.00");
		taxMap.put("07:00 - 08:00","$0.00");
		taxMap.put("08:00 - 08:04","$0.25");
		taxMap.put("08:04 - 08:30","$0.50");
		taxMap.put("08:30 - 08:34","$0.75");
		taxMap.put("08:34 - 08:55","$1.00");
		taxMap.put("08:55 - 09:00","$0.50");
		taxMap.put("09:00 - 22:30","$0.00");
		taxMap.put("22:30 - 23:59","$0.00");
		
		Assert.assertTrue(mapToll.validateTaxTable(taxMap),"Tax Rate Table is not showing correct data");
		
		map.switch2DefaultFrame();
		
		map.clickOnZoomIn(2);
		Assert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
		map.closeMapPopUp();
	}
}
