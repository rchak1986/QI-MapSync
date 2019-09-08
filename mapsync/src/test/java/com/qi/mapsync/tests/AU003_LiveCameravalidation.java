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
		CustomAssertion cAssert = new CustomAssertion(driver);
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		cAssert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		MapArea map = PageFactory.initElements(driver, MapArea.class);
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Cameras cam = PageFactory.initElements(driver, Cameras.class);
		cam.loadCameras();
		cAssert.assertTrue(cam.validateCameraList(),"Camera List is not loaded");
		
		cam.searchCamera("Ang Mo Kio");
		cAssert.assertTrue(cam.validateCameraSearch(true), "Camera list is not populated");
		cam.clearSearch();
		
		cam.searchCamera("abcdxyz");
		cAssert.assertTrue(cam.validateCameraSearch(false), "Camera list is not expected to get populated");
		cam.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String camDetail = cam.selectCameraLocationAndCaptureInfo("Flyover");
		cAssert.assertTrue(map.validateMapPopUp(), "Popup with cam detail is not shown up");
		int pos2=map.getZoomDraggerPosition();
		cAssert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
		
		map.switch2CamFrame();
		MapCameraDetailPopup mapCam = PageFactory.initElements(driver, MapCameraDetailPopup.class);
		
		cAssert.assertTrue(mapCam.validateCameraLocation(camDetail),"Popup details does not match with the selected incident");
		cAssert.assertTrue(mapCam.validateLastUpdateInfo(),"Last Update info is not shown correctly");
		cAssert.assertTrue(mapCam.validateCameraImage(),"Cam image is not shown correctly");
		map.switch2DefaultFrame();
		
		map.clickOnZoomIn(2);
		cAssert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
		map.closeMapPopUp();
	}
}
