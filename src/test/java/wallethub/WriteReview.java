package wallethub;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import po.SignInPO;
import po.WriteReviewPO;
import utility.BaseTest;

public class WriteReview extends BaseTest
{
	WebDriver driver;
	WriteReviewPO writeReview;
	SignInPO signIn;
	
	@BeforeClass
	public void init()
	{
		driver = getWebDriver(getPropertiesValue("browser"));
		writeReview = new WriteReviewPO(driver);
		signIn = new SignInPO(driver);
		driver.get(getPropertiesValue("wallethub.review.url"));
		driver.manage().window().maximize();
		writeReview.waitUntilPageIsLoaded();
	}
	@Test
	public void writeReview()
	{
		try
		{
			Assert.assertTrue(writeReview.clickOnLoginLink());
			
			Assert.assertTrue(signIn.enterUserName(getPropertiesValue("wallethub.username")));
			
			Assert.assertTrue(signIn.enterPassword(getPropertiesValue("wallethub.password")));
			
			Assert.assertTrue(signIn.clickOnLoginButton());
			
			driver.get(getPropertiesValue("wallethub.review.url"));
			
			Assert.assertTrue(writeReview.rateStar(4));
			
			Assert.assertTrue(writeReview.selectPolicyAndWriteReview("Health Insurance", getPropertiesValue("wallethub.review")));
			
			driver.get(getPropertiesValue("wallethub.review.assert.url"));
			
			Assert.assertTrue(writeReview.verifyReview(getPropertiesValue("wallethub.review")));
		} 
		catch (Exception e)
		{
			Reporter.log("Unable to write a review : " + e, true);
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
