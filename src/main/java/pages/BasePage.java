package pages;

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage
{
    @Setter
    static WebDriver driver;

    public boolean isElementContainsText(WebElement element, String text)
    {
        return element.getText().contains(text);
    }
}


