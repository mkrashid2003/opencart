package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountpage extends BasePage{
    public MyAccountpage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement msgmyAccount;
    @FindBy(linkText = "Logout")
    WebElement lnkLogout;
    public boolean isMyAccountPageExists() {
        try {
            return msgmyAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickLogout() {
        lnkLogout.click();
    }


}
