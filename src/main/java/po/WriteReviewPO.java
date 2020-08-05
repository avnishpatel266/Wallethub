package po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import utility.Log;
import utility.Utils;

public class WriteReviewPO extends Utils
{
	@FindBy(xpath="//span[text()='Login']")
	WebElement loginLink;
	
	@FindBy(xpath="//div[contains(@class,'review-action')]//div[@class='rating-box-wrapper']")
	WebElement whatIsYourRatingLabel;
	
	@FindBy(xpath="//div[contains(@class,'review-action')]//div[@class='rating-box-wrapper']//*[contains(@stroke,'#4ae0e1')]")
	List<WebElement> starLitUp;
	
	@FindBy(xpath="//span[@class='dropdown-placeholder'][contains(text(),'Select')]")
	WebElement selectPolicyDropdown;
	
	@FindBy(xpath="//textarea[contains(@placeholder,'Write your review')]")
	WebElement writeYourReviewTextArea;
	
	@FindBy(xpath="//div[text()='Submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//*[contains(text(),'Your review has been posted')]")
	WebElement reviewPostedLabel;
	
	@FindBy(xpath="//div[@class='profilenav']//li[2]//a")
	WebElement activityLink;
	
	@FindBy(xpath="//div[@class='profilenav']//li[3]//a")
	WebElement reviewLink;
	
	@FindBy(xpath="//div[@class='reviews']//p[2]")
	WebElement latestReviewLabel;
	
	String ratingStartXpath = "//div[contains(@class,'review-action')]//div[@class='rating-box-wrapper']//*[";
	String ratingEndXpath ="]";
	
	String selectPolictyStartXpath = "//div[contains(@class,'opened')]//li[@class='dropdown-item'][contains(text(),'";
	String selectPolicyEndXpath = "')]";
	
	
	public WriteReviewPO(WebDriver driver)
	{
		super(driver);
	}
	
	/**
	 * This method is used to click on login button
	 * @return boolean
	 */
	public boolean clickOnLoginLink()
	{
		try
		{
			if(!clickAtElement(loginLink, "Log In"))
				return false;
			waitUntilPageIsLoaded();
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to click on login link : " + e);
			return false;
		}
	}
	
	/**
	 * This method is used to rate star while review
	 * @param numberOfStar
	 * @return
	 */
	public boolean rateStar(int numberOfStar)
	{
		try
		{
			waitUntilPageIsLoaded();
			scrollIntoView(whatIsYourRatingLabel);
			String xpath = ratingStartXpath + numberOfStar + ratingEndXpath;
			WebElement star = driver.findElement(By.xpath(xpath));
			Actions act = new Actions(driver);
			act.moveToElement(star).build().perform();
			if(starLitUp.size() == numberOfStar)
			{
				Log.info(numberOfStar + " are lit up");
			}
			else
			{
				Log.error(numberOfStar + " are not lit up");
				return false;
			}
			star = driver.findElement(By.xpath(xpath));
			if(!clickAtElement(star, "Star"))
				return false;
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to rate star");
			return false;
		}
	}
	
	/**
	 * This method is used to select policy and write review
	 * @param policy
	 * @param review
	 * @return
	 */
	public boolean selectPolicyAndWriteReview(String policy, String review)
	{
		try
		{
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(selectPolicyDropdown);
			if(!clickAtElement(selectPolicyDropdown, "Policy Dropdown"))
				return false;
			String xpath = selectPolictyStartXpath + policy + selectPolicyEndXpath;
			WebElement policyElement = driver.findElement(By.xpath(xpath));
			waitForElementToBeVisible(policyElement);
			if(!clickAtElement(policyElement, "Policy"))
				return false;
			waitUntilPageIsLoaded();
			if(!clickAtElement(writeYourReviewTextArea, "Write Your Review"))
				return false;
			if(!enterText(writeYourReviewTextArea, review , "Write Your Review"))
				return false;
			if(!clickAtElement(submitBtn, "Submit"))
				return false;
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(reviewPostedLabel);
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to select policy");
			return false;
		}
	}
	
	/**
	 * This method is used to verify posted review
	 * @param review
	 * @return boolean
	 */
	public boolean verifyReview(String review)
	{
		try
		{
			clickAtElement(activityLink, "Activity");
			waitUntilPageIsLoaded();
			clickAtElement(reviewLink, "Review");
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(latestReviewLabel);
			if(latestReviewLabel.getText().equalsIgnoreCase(review))
				return true;
			else
			{
				Log.error("Review are not matched");
				return false;
			}
		}
		catch(Exception e)
		{
			Log.error("Unable to verify review");
			return false;
		}
	}
}
