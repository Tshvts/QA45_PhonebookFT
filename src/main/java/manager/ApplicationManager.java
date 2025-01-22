package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilits.WDListener;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class ApplicationManager
{
    private EventFiringWebDriver driver;
    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    static String browser;
    public ApplicationManager()
    {
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method)
    {
        if (browser.equals(BrowserType.CHROME))
        {
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Use Chrome");
        }

        else if (browser.equals(BrowserType.FIREFOX))
        {
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Use Firefox");
        }

        else
        {
            driver = new EventFiringWebDriver(new ChromeDriver());
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.register(new WDListener());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method)
    {
        if(driver!=null)
            driver.quit();
    }
}
