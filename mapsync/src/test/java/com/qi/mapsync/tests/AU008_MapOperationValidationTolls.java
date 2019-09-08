package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.common.utilities.TestReporter;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.MapTollDetailPopup;

@Listeners(TestReporter.class)
public class AU008_MapOperationValidationTolls extends TestBase {
	@Test
	public void testMapTolls() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Toll");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Toll count doesn't match with Icon");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link does not appear");
			String tollDesc = map.clickAndCaptureFirstPopupLink();
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
			map.switch2TollFrame();
			
			MapTollDetailPopup mapToll = PageFactory.initElements(driver, MapTollDetailPopup.class);
			cAssert.assertTrue(mapToll.validateIfTaxTableDisplayed(),"Tax Table not shown up");
			cAssert.assertTrue(mapToll.validateTollLocation(tollDesc),"Toll Location does not match");
						
			map.switch2DefaultFrame();
			int pos2=map.getZoomDraggerPosition();			
			map.closeMapPopUp();			
			
			cAssert.assertTrue(pos>pos2,"Auto Zoom did not happen");
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Toll");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
		map.closeMapPopUp();
	}
}
