package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;

import com.qi.mapsync.common.utilities.*;

@Listeners(TestReporter.class)
public class AU003_LiveCameravalidation extends TestBase {
	@Test
	public void validateCameraList() throws Exception{
		CustomAssertion cAssert = new CustomAssertion(driver,test);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page Load Validation");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Cameras cam = PageFactory.initElements(driver, Cameras.class);
		cam.loadCameras();
		cAssert.assertTrue(cam.validateCameraList(),"Camera List Validation");
		
		cam.searchCamera("Ang Mo Kio");
		cAssert.assertTrue(cam.validateCameraSearch(true), "Camera list with valid search");
		cam.clearSearch();
		
		cam.searchCamera("abcdxyz");
		cAssert.assertTrue(cam.validateCameraSearch(false), "Camera list with invalid search");
		cam.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String camDetail = cam.selectCameraLocationAndCaptureInfo("Flyover");
		cAssert.assertTrue(map.validateMapPopUp(), "Popup with cam detail validation");
		int pos2=map.getZoomDraggerPosition();
		cAssert.assertTrue(pos>pos2,"Automatic Zoom ");
		
		map.switch2CamFrame();
		MapCameraDetailPopup mapCam = PageFactory.initElements(driver, MapCameraDetailPopup.class);
		
		cAssert.assertTrue(mapCam.validateCameraLocation(camDetail),"Popup details validation w.r.t listing");
		cAssert.assertTrue(mapCam.validateLastUpdateInfo(),"Last Update info validation");
		cAssert.assertTrue(mapCam.validateCameraImage(),"Cam image validation");
		map.switch2DefaultFrame();
		
		map.clickOnZoomIn(2);
		cAssert.assertTrue(map.validateMapPopUp(), "Camera Detail popup validation");		
		map.closeMapPopUp();
	}
}
