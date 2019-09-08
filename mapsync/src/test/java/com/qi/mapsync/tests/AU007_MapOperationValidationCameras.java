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
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);		
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		cAssert.assertTrue(map.isTabEnabled("Incidents"));
		map.clickOnMapTabs("Traffic Camera");
		int pos = map.getZoomDraggerPosition();
		String multiCount = map.selectAndCaptureCountMultiIcons();
		if (multiCount!=null){
			int expectedCount = Integer.parseInt(multiCount) + 1;
			cAssert.assertTrue(map.validatePopupLinkCount(expectedCount),"Camera count doesn't match with Icon");
			cAssert.assertTrue(map.validateZoomInLink(),"Zoom In link does not appear");
			String camDesc = map.clickAndCaptureFirstPopupLink();
			
			cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
			map.switch2CamFrame();
			
			MapCameraDetailPopup mapCam = PageFactory.initElements(driver, MapCameraDetailPopup.class);
			cAssert.assertTrue(mapCam.validateCameraImage(),"Cam Image does not show up");
			cAssert.assertTrue(mapCam.validateCameraLocation(camDesc),"Camera location does not match");
			cAssert.assertTrue(mapCam.validateLastUpdateInfo(),"last Update info does not show up correctly");
			
			map.switch2DefaultFrame();
			int pos2=map.getZoomDraggerPosition();			
			map.closeMapPopUp();			
			
			cAssert.assertTrue(pos>pos2,"Auto Zoom did not happen");
		}
		
		map.pageRefresh();
		map.clickOnMapTabs("Traffic Camera");
		map.clickOnAnyMapIcon();
		cAssert.assertTrue(map.validateMapPopUp(),"Popup does not show up");
		map.closeMapPopUp();
	}
}
