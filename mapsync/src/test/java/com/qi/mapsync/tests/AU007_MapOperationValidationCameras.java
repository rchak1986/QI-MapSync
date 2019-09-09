package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.common.utilities.CustomAssertion;
import com.qi.mapsync.common.utilities.TestReporter;
import com.qi.mapsync.pages.HomePage;
import com.qi.mapsync.pages.MapArea;
import com.qi.mapsync.pages.MapCameraDetailPopup;

@Listeners(TestReporter.class)
public class AU007_MapOperationValidationCameras extends TestBase {
	@Test
	public void testMapCameras() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Traffic Camera");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Camera count validation for multi icons");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link validation on popup");
			String camDesc = map.clickAndCaptureFirstPopupLink();
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup with camera detail validation");
			map.switch2CamFrame();
			
			MapCameraDetailPopup mapCam = PageFactory.initElements(driver, MapCameraDetailPopup.class);
			cAssert.assertTrue(mapCam.validateCameraImage(),"Cam Image validation");
			cAssert.assertTrue(mapCam.validateCameraLocation(camDesc),"Camera location validation");
			cAssert.assertTrue(mapCam.validateLastUpdateInfo(),"last Update info validation");
			
			map.switch2DefaultFrame();
			int pos2=map.getZoomDraggerPosition();			
			map.closeMapPopUp();			
			
			cAssert.assertTrue(pos>pos2,"Auto Zoom validation");
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Traffic Camera");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup with camera details validation");
		map.closeMapPopUp();
	}
}
