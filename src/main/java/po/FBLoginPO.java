package po;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.Log;
import utility.Utils;

public class FBLoginPO extends Utils
{
	@FindBy(id="email")
	WebElement emailText;
	
	@FindBy(id="pass")
	WebElement passwordText;
	
	@FindBy(xpath="//label[@id='loginbutton']/input")
	WebElement logInBtn;
	
	//@FindBy(xpath="//div[@role='presentation'][contains(@class,'navigationFocus')]")
	@FindBy(xpath="//textarea[contains(@title,'Write something here')]")
	WebElement writeSometingHereLabel;
	
	@FindBy(xpath="//div[@id='stream_pagelet']//div[@role='textbox']")
	WebElement statusText;
	
	@FindBy(xpath="//div[@class='_1j2v']//button[@type='submit']")
	WebElement postBtn;
	
	@FindBy(xpath="//p[text()='Hello World']")
	WebElement helloWorldPostLabel;
	
	public FBLoginPO(WebDriver driver)
	{
		super(driver);
	}
	
	/**
	 * This method is used to enter Username
	 * @param username
	 * @return boolean
	 */
	public boolean enterUserName(String username)
	{
		try
		{
			if(!enterText(emailText, username, "Email or Phone"))
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
			return true;
		}
		catch(Exception e)
		{
			Log.error("Unable to click on login button : " + e);
			return false;
		}
	}
	
	public boolean postAStatusMessage(String message)
	{
		try
		{
			waitUntilPageIsLoaded();
			if(!clickAtElement(writeSometingHereLabel, "Write Something here"))
				return false;
			waitForElementToBeVisible(statusText);
			if(!enterText(statusText, message, "Status"))
				return false;
			waitForElementToBeVisible(postBtn);
			if(!clickAtElement(postBtn, "Post"))
				return false;
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(helloWorldPostLabel);
			
			//This is not needed but just to see the post... This can be removed
			Thread.sleep(2000);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
