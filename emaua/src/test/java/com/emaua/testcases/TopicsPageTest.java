package com.emaua.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.emaua.base.TestBase;
import com.emaua.pages.HomePage;
import com.emaua.pages.TopicsPage;

public class TopicsPageTest extends TestBase{
	
	HomePage homePage;
	TopicsPage topicsPage;
	
	
	public TopicsPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
		topicsPage = new TopicsPage();
	}
	
	@Test(enabled=false)
	public void loginPageTitleTest() {
		String title = topicsPage.validateTopicsPageTitle();
		Assert.assertEquals(title, "About :: Emaua");
	}
	
	@Test(enabled=false)
	public void emauaLogoTest() {
		boolean flag = topicsPage.validateEmauaLogo();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	

}
