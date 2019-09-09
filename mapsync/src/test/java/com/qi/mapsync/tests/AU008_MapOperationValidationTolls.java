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
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Toll");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Toll count validation for multi icons");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link validation on popup");
			String tollDesc = map.clickAndCaptureFirstPopupLink();
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup with Toll Details validation");
			map.switch2TollFrame();
			
			MapTollDetailPopup mapToll = PageFactory.initElements(driver, MapTollDetailPopup.class);
			cAssert.assertTrue(mapToll.validateIfTaxTableDisplayed(),"Tax Table validation");
			cAssert.assertTrue(mapToll.validateTollLocation(tollDesc),"Toll Location validation w.r.t map icon");
						
			map.switch2DefaultFrame();
			int pos2=map.getZoomDraggerPosition();			
			map.closeMapPopUp();			
			
			cAssert.assertTrue(pos>pos2,"Auto Zoom validation");
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Toll");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup with Toll detail validation");
		map.closeMapPopUp();
	}
}
