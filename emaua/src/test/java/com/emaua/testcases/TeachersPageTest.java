package com.emaua.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emaua.base.TestBase;
import com.emaua.pages.HomePage;
import com.emaua.pages.LoginPage;
import com.emaua.pages.TeachersPage;
import com.emaua.util.TestUtil;

public class TeachersPageTest extends TestBase{

	TestUtil testUtil;
	HomePage homePage;
	TeachersPage teachersPage;
	LoginPage loginPage;
	
	String sheetName = "Teachers";
	
	String sheetName1 = "TeachersWarning";
	String sheetName2 = "TeachersAdd";
	String sheetName3 = "TeachersEdit";
	

	public TeachersPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		loginPage = homePage.clickOnLoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));		
		Thread.sleep(4000);
		teachersPage = homePage.clickOnTeachersPage();
		Thread.sleep(4000);
		
	}
	
	@DataProvider(name = "getEmauaTestData1")
	public Object[][] getEmauaTestData1() {
		Object data[][] = TestUtil.getTestData(sheetName1);
		return data;
	}
	
	@DataProvider(name = "getEmauaTestData2")
	public Object[][] getEmauaTestData2() {
		Object data[][] = TestUtil.getTestData(sheetName2);
		return data;
	}
	
	@DataProvider(name = "getEmauaTestData3")
	public Object[][] getEmauaTestData3() {
		Object data[][] = TestUtil.getTestData(sheetName3);
		return data;
	}
	
	@Test(enabled=false) // Verify the Teachers Page Title
		public void teachersPageTitleTest() {
			String title = teachersPage.validateLoginPageTitle();
			Assert.assertEquals(title, "Teachers :: Emaua");
		}
	
	@Test(priority=1)  // Verify the teachers Page Header
	public void verifyTeachersPageHeading() {
		String teachersPageHeader = teachersPage.verifyTeachersPageHeading();
		Assert.assertEquals(teachersPageHeader, "Teachers", "page heading does not match");
	}
	
	
	// Test Cases for Add Teacher Functionality
	
	@Test()  //Verify the Header of the Add Teacher Pop-up Window
	public void verifyAddTeacherModalTitle() throws InterruptedException {
		teachersPage.clickAddTeacherButton();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		Assert.assertEquals(teachersPage.verifyAddTeacherModalTitle(), "Add Institution", "Add Institution Pop-up Window Header does not match");
	}
	
	
	@Test() // Verify that the save button is disabled
	public void verifySaveButtonDisabled() throws InterruptedException {
		teachersPage.clickAddTeacherButton();
		driver.switchTo().defaultContent();
		Thread.sleep(4000);
		boolean buttonDisabled=!teachersPage.isSaveButtonEnabled();
		Assert.assertEquals(buttonDisabled, true);
	}
	
	@Test(priority=1, dataProvider="getEmauaTestData1")		//Verify that SuperAdmin can add a new Institution  -- COMPLETED
	public void verifyAddTeacherButtonTest(String name, String title, String institution,  String description, String image, String warning) throws InterruptedException {
		teachersPage.clickAddTeacherButton();
		driver.switchTo().defaultContent();
		teachersPage.addNewTeacher(name, title, institution, description, image, warning);
		Thread.sleep(4000);
		Assert.assertEquals(teachersPage.getWarningMessage(), warning, "Warning message does not match");
		teachersPage.clickSaveButton();
	}
	
	@Test(priority=1, dataProvider="getEmauaTestData2")		//Verify that SuperAdmin can add a new Institution  -- COMPLETED
	public void verifyAddTeacher(String name, String title, String institution,  String description, String image, String warning) throws InterruptedException {
		teachersPage.clickAddTeacherButton();
		driver.switchTo().defaultContent();
		teachersPage.addNewTeacher(name, title, institution, description, image, warning);
		Thread.sleep(4000);
		Assert.assertTrue(teachersPage.isSaveButtonEnabled(), "Not all mandatory fields are completed");
		teachersPage.clickSaveButton();
	}
	
	
	@Test ()	// Verify that SuperAdmin can edit Institution
	public void verifyEditInstitutionTest() throws InterruptedException {
		teachersPage.clickEditButton();
		driver.switchTo().defaultContent();
		teachersPage.editInstitution();
		Thread.sleep(4000);
		teachersPage.clickSaveButton();
				
	}
	
	@Test ()	// Verify that SuperAdmin can delete Institution -- COMPLETED
	public void verifyDeleteInstitutionTest() {
		teachersPage.clickDeleteButton();
		driver.switchTo().defaultContent();
		teachersPage.clickYesButton();
		
	}
	
	@Test ()	// Verify that SuperAdmin can click "No" on the delete confirmation page -- COMPLETED
	public void verifyCancelDeleteInstitutionTest() {
		teachersPage.clickDeleteButton();
		driver.switchTo().defaultContent();
		teachersPage.clickNoButton();
		
	}
	
	@Test()  //Verify that pagination is working if available -- COMPLETED
	public void verifyPagination() {
		teachersPage.checkPaginationLink();
	}
	
	@Test()  //Verify that Institutions Details pop-up page is displayed  -- COMPLETED
	public void verifyInstitutionDetailsPopUpPageDisplayed() {
		teachersPage.moveMouseOverInstitutionTitle();
		driver.switchTo().defaultContent();
		Assert.assertEquals(teachersPage.verifyCheckCoursesButton(), true, "Institution Details Popup page is not displayed.");		
	
	}
	

	@Test()  //Verify that Courses can be viewed by clicking the "Check Courses" button -- COMPLETED
	public void verifyInstitutionDetailsPopUpPageCheckCoursesButton() {
		teachersPage.moveMouseOverInstitutionTitle();
		driver.switchTo().defaultContent();
		teachersPage.clickCheckCourseButton();
		
	}
	
	@Test() //verify the mandatory fields
	public void verifyMandatoryNameField() {
		teachersPage.clickAddTeacherButton();
		driver.switchTo().defaultContent();
		teachersPage.verifyMandatoryNameField();
		
	}
	
	@Test()  //Verify the broken links
	public void verifyBrokenLinks() throws MalformedURLException, IOException {
		teachersPage.verifyBrokenLinks();
	}
	
	@Test()  // verift the BreadCrumb -- COMPLETED
	public void verifyBreadCrumb() {
		teachersPage.verifybreadCrumb();
		Assert.assertEquals(teachersPage.verifybreadCrumb(), "Home/Institutions", "BreadCrumb does not match");
	}
	

	
	//----------------------------------------------
	@Test(enabled=false)
		public void clickOnCheckCoursesButton() {
			teachersPage.clickOnCheckCoursesButton();
	}
	
	@Test()
	public void verifyAddTeacher() {
		loginPage = teachersPage.clickOnLoginPage();
	//	teachersPage = teachersPage.login(prop.getProperty("username"), prop.getProperty("password"));
		teachersPage = homePage.clickOnTeachersPage();
		//NgWebDriver ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		//ngWebDriver.waitForAngularRequestsToFinish();
		teachersPage.clickAddTeacherButton();
		//ngWebDriver.waitForAngularRequestsToFinish();
		teachersPage.addTeacher("Gokhan", "Mr.");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
