package com.qi.mapsync.tests;

import java.util.HashMap;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.MapTollDetailPopup;
import com.qi.mapsync.pages.Tolls;
import com.qi.mapsync.common.utilities.*;

@Listeners(TestReporter.class)
public class AU004_LiveTollsvalidation extends TestBase {
	@Test
	public void validateTollList() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Tolls toll = PageFactory.initElements(driver, Tolls.class);
		toll.loadTollList();
		cAssert.assertTrue(toll.validateTollList(),"Toll List is not loaded");
		
		toll.searchToll("CTE");
		cAssert.assertTrue(toll.validateTollSearch(true), "Toll list is not populated");
		toll.clearSearch();
		
		toll.searchToll("abcdxyz");
		cAssert.assertTrue(toll.validateTollSearch(false), "Toll list is not expected to get populated");
		toll.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String tollDetail = toll.selectTollLocationAndCaptureInfo("ECP TO CITY");
		cAssert.assertTrue(map.validateMapPopUp(), "Popup with cam detail is not shown up");
		int pos2=map.getZoomDraggerPosition();
		cAssert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
		
		map.switch2TollFrame();
		MapTollDetailPopup mapToll = PageFactory.initElements(driver, MapTollDetailPopup.class);
		
		cAssert.assertTrue(mapToll.validateTollLocation(tollDetail),"Popup details does not match with the selected toll");
		cAssert.assertTrue(mapToll.validateIfTaxTableDisplayed(),"Tax Table info is not shown correctly");
		cAssert.assertTrue(mapToll.validateVehicleTypeIfPresent("Motorcycle (Weekdays)"),"Vehicle Type is not shown correctly");
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
		
		cAssert.assertTrue(mapToll.validateTaxTable(taxMap),"Tax Rate Table is not showing correct data");
		
		map.switch2DefaultFrame();
		
		map.clickOnZoomIn(2);
		cAssert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
		map.closeMapPopUp();
	}
}
