package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage
{
    static WebDriver driver;

    public static void setDriver(WebDriver wd)
    {
        driver = wd;
    }

    public void pause(int time)
    {
        try
        {
            Thread.sleep(time*1000L);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementContainsText(WebElement element, String text)
    {
        return element.getText().contains(text);
    }
}


