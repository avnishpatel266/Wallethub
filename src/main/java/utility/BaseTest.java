package utility;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest
{
	public WebDriver driver;
	
	public WebDriver getWebDriver(String driverName)
	{
		switch (driverName)
		{
			case "firefox":
			{
				System.setProperty("webdriver.gecko.driver", "src\\test\\resources\\browsers\\geckodriver.exe");
				driver = new FirefoxDriver();
				return driver;
			}
			case "ie":
			{
				System.setProperty("webdriver.ie.driver", "src\\test\\resources\\browsers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				return driver;
			}
			default:
			{
				System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\browsers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				 options.addArguments("--disable-notifications");
				System.out.println("Disable Notification");
				driver = new ChromeDriver(options);
				return driver;
			}
		}
	}
	
	/**
	 * This method is used to load all properties file
	 * 
	 * @return properties
	 * @throws Exception
	 */
	public Properties loadProperties() throws Exception
	{
		File folder = new File("src\\\\test\\\\resources\\\\properties");
		File[] listOfFiles = folder.listFiles();
		FileReader reader;
		Properties p = new Properties();
		for (File file : listOfFiles)
		{
			if (file.isFile())
			{
				reader = new FileReader(file);
				p.load(reader);
			}
		}
		return p;
	}
	
	/**
	 * This method is used to get property value based on key
	 * 
	 * @param key
	 * @return String value
	 */
	public String getPropertiesValue(String key)
	{
		String value = "";
		try
		{
			Properties p = loadProperties();
			value = p.getProperty(key);
		} catch (Exception e)
		{
			System.out.println("Unable to get Properties Value for key : " + key + " " + e);
		}
		return value;
	}
}
