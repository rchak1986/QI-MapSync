package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.common.utilities.TestReporter;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;

@Listeners(TestReporter.class)
public class AU009_MapOperationValidationParking extends TestBase {
	@Test
	public void testMapParking() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Parking");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validateParkingLotCounts(multiCount),"Parking count validation for multi icons");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link validation");
			cAssert.assertTrue(map.validateMapPopUp(),"Popup with parking availability");
			map.closeMapPopUp();
			
			map.pageRefresh();
			map.clickOnMapTabs("Parking");
			multiCount=map.selectAndCaptureCountMultiIconsParking();
			expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Parking count (individual links) validation with multiicons.");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link validation");
			String tollDesc = map.clickAndCaptureFirstPopupLink();
			cAssert.assertTrue(map.validateParkingArea(tollDesc),"Parking Area name validation");
			int pos2=map.getZoomDraggerPosition();	
			cAssert.assertTrue(pos>pos2,"Auto Zoom validation");
								
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup with parking details validation");
			map.closeMapPopUp();			
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Parking");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup with parking details validation");
		map.closeMapPopUp();
	}
}
