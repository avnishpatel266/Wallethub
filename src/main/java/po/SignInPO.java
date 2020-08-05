package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.Log;
import utility.Utils;

public class SignInPO extends Utils
{
	
	@FindBy(xpath="//input[@name='em']")
	WebElement emailText;
	
	@FindBy(xpath="//input[@name='pw']")
	WebElement passwordText;
	
	@FindBy(xpath="//button[contains(@class,'center')]")
	WebElement logInBtn;
	
	@FindBy(xpath="//span[contains(text(),'Address')]")
	WebElement addressLabel;
	
	public SignInPO(WebDriver driver)
	{
		super(driver);
	}
	
	/**
	 * This method is used to enter User-name
	 * @param username
	 * @return boolean
	 */
	public boolean enterUserName(String username)
	{
		try
		{
			waitUntilPageIsLoaded();
			if(!enterText(emailText, username, "Username"))
				return false;
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to enter username : " + e);
			return false;
		}
	}
	
	/**
	 * This method is used to enter password
	 * @param password
	 * @return boolean
	 */
	public boolean enterPassword(String password)
	{
		try
		{
			if(!enterText(passwordText, password, "Password"))
				return false;
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to enter Password : " + e);
			return false;
		}
	}
	
	/**
	 * This method is used to click on login button
	 * @return boolean
	 */
	public boolean clickOnLoginButton()
	{
		try
		{
			if(!clickAtElement(logInBtn, "Log In"))
				return false;
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(addressLabel);
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to click on login button : " + e);
			return false;
		}
	}
}
