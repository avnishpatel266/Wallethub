package wallethub;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.FBLoginPO;
import utility.BaseTest;

public class FacebookPost extends BaseTest
{
	WebDriver driver;
	FBLoginPO fbLogin;
	
	@BeforeClass
	public void init()
	{
		driver = getWebDriver(getPropertiesValue("browser"));
		fbLogin = new FBLoginPO(driver);
		driver.get(getPropertiesValue("fb.url"));
		driver.manage().window().maximize();
		fbLogin.waitUntilPageIsLoaded();
	}
	
	@Test
	public void doLogin()
	{
		try
		{
			Assert.assertTrue(fbLogin.enterUserName(getPropertiesValue("fb.username")));
			
			Assert.assertTrue(fbLogin.enterPassword(getPropertiesValue("fb.password")));
			
			Assert.assertTrue(fbLogin.clickOnLoginButton());
			
			Assert.assertTrue(fbLogin.postAStatusMessage("Hello World"));
		} 
		catch (Exception e)
		{
			Reporter.log("Unabel to take login in Facebook ", true);
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
