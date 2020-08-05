package utility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils
{
	public WebDriver driver;
	
	public Utils(WebDriver webdriver)
	{
		driver = webdriver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method is used to wail until all Javascripts are executed successfully
	 * on page
	 */
	public void waitUntilPageIsLoaded()
	{
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>()
		{
			public Boolean apply(WebDriver driver)
			{
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 90);
		wait.until(pageLoadCondition);
	}
	
	/**
	 * This method is used to wait until element is visible on page
	 * 
	 * @param element
	 */
	public void waitForElementToBeVisible(WebElement element)
	{
		try
		{
			waitUntilPageIsLoaded();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e)
		{
			Log.error("Element is not visible on screen" + e);
		}
	}
	
	/**
	 * This method is used to wait until element is clickable
	 * 
	 * @param element
	 */
	public void waitForElementToBeClickable(WebElement element)
	{
		try
		{
			waitUntilPageIsLoaded();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e)
		{
			Log.error("Element is not clickable" + e);
		}
	}
	
	/**
	 * This method is used to enter Text in TextBox
	 * 
	 * @param element
	 * @param value
	 * @param elementName
	 * @return boolean
	 */
	public boolean enterText(WebElement element, String value, String elementName)
	{
		try
		{
			waitUntilPageIsLoaded();
			waitForElementToBeVisible(element);
			element.sendKeys(value);
			Log.info(elementName + " : " + value + " entered successfully");
			return true;
		} catch (Exception e)
		{
			Log.error("Unable to enter text in : " + elementName + " : " + e);
			return false;
		}
	}
	
	/**
	 * This method is used to click At Element
	 * 
	 * @param element
	 * @param value
	 * @param elementName
	 * @return boolean
	 */
	public boolean clickAtElement(WebElement element, String elementName)
	{
		try
		{
			waitUntilPageIsLoaded();
			waitForElementToBeClickable(element);
			element.click();
			waitUntilPageIsLoaded();
			Log.info(elementName + " clicked successfully");
			return true;
		} catch (Exception e)
		{
			Log.error("Unable to click At Element : " + elementName + " : " + e);
			return false;
		}
	}
	
	/**
	 * This method is used to scroll untill element is visible on UI
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} 
		catch (Exception e)
		{
			Log.error("Unable to scroll into view : " + e);
		}
	}
}
