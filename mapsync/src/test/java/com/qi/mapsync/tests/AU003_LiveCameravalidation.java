package com.qi.mapsync.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qi.mapsync.pages.*;


public class AU003_LiveCameravalidation extends TestBase {
	@Test
	public void validateCameraList() throws Exception{
		HomePage hPage = PageFactory.initElements(driver, HomePage.class);
		Assert.assertTrue(hPage.validatePageLoad(),"Home Page is not loaded successfully");
		
		Map map = PageFactory.initElements(driver, Map.class);
		map.waitUntilBannerDisplayed();
		map.closeAdBanner();
		
		Cameras cam = PageFactory.initElements(driver, Cameras.class);
		cam.loadCameras();
		Assert.assertTrue(cam.validateCameraList(),"Camera List is not loaded");
		
		cam.searchCamera("Ang Mo Kio");
		Assert.assertTrue(cam.validateCameraSearch(true), "Camera list is not populated");
		cam.clearSearch();
		
		cam.searchCamera("abcdxyz");
		Assert.assertTrue(cam.validateCameraSearch(false), "Camera list is not expected to get populated");
		cam.clearSearch();
		
		int pos = map.getZoomDraggerPosition();
		String camDetail = cam.selectCameraLocationAndCaptureInfo("Flyover");
		Assert.assertTrue(map.validateMapPopUp(), "Popup with cam detail is not shown up");
		int pos2=map.getZoomDraggerPosition();
		Assert.assertTrue(pos>pos2,"Automatic Zoo did not happen");
		
		map.switch2CamFrame();
		MapCameraDetailPopup mapCam = PageFactory.initElements(driver, MapCameraDetailPopup.class);
		
		Assert.assertTrue(mapCam.validateCameraLocation(camDetail),"Popup details does not match with the selected incident");
		Assert.assertTrue(mapCam.validateLastUpdateInfo(),"Last Update info is not shown correctly");
		Assert.assertTrue(mapCam.validateCameraImage(),"Cam image is not shown correctly");
		map.switch2DefaultFrame();
		
		map.clickOnZoomIn(2);
		Assert.assertTrue(map.validateMapPopUp(), "Popup with incident detail is not shown up after zoom");		
		map.closeMapPopUp();
	}
}
