package manager;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.lang.reflect.Method;

public class ApplicationManager
{
    @Getter
    //private WebDriver driver;
    private WebDriver webDriver;
    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    @BeforeMethod
    public void setUp(Method method)
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WDListener listener = new WDListener();
        webDriver = new EventFiringDecorator<>(listener).decorate(driver);
    }

    @AfterMethod
    public void tearDown(Method method)
    {
        //logger.info("Stop testing-->" + method.getName());
//   if(driver != null)
//      driver.quit();
    }
}
