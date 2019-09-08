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
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Parking");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validateParkingLotCounts(multiCount),"Parking count does not match");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link does not appear");
			cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
			map.closeMapPopUp();
			
			map.pageRefresh();
			map.clickOnMapTabs("Parking");
			multiCount=map.selectAndCaptureCountMultiIconsParking();
			expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Parking count doesn't match with Icon");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link does not appear");
			String tollDesc = map.clickAndCaptureFirstPopupLink();
			cAssert.assertTrue(map.validateParkingArea(tollDesc),"Parking Area name is not correct.");
			int pos2=map.getZoomDraggerPosition();	
			cAssert.assertTrue(pos>pos2,"Auto Zoom did not happen");
								
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
			map.closeMapPopUp();			
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Parking");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
		map.closeMapPopUp();
	}
}
