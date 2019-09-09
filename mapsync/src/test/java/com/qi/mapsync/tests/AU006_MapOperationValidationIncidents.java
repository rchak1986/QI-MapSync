package com.qi.mapsync.tests;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.common.utilities.TestReporter;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;

@Listeners(TestReporter.class)
public class AU006_MapOperationValidationIncidents extends TestBase {
	@Test
	public void testMapIncident() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Incident count validation for multi icons");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link validation on Popup");
			String incDesc = map.clickAndCaptureFirstPopupLink();
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup with incident detail validation");
			cAssert.assertTrue(map.validateMapPopUpDesc(incDesc),"Incident detail validation w.r.t multi incident popup icon");
			int pos2=map.getZoomDraggerPosition();
			map.closeMapPopUp();			
			
			cAssert.assertTrue(pos>pos2,"Auto Zoom validation");
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Incidents");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup with incident details validation");
		map.closeMapPopUp();
	}
}
