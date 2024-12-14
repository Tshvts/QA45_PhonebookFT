package pages;

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage
{
    @Setter
    static WebDriver driver;

    public boolean isElementContainsText(WebElement element, String text)
    {
        return element.getText().contains(text);
    }

    public boolean validateUrl(String url, int time)
    {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.urlContains(url));
        }
        catch (org.openqa.selenium.TimeoutException e)
        {
            e.printStackTrace();
            System.out.println("Created exception");
            return false;
        }

    }
}


