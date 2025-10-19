package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage {
    public Loginpage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="input-email")
    WebElement txtemail;
    @FindBy(id="input-password")
    WebElement txtpassword;
    @FindBy(xpath="//input[@value='Login']")
    WebElement btnlogin;
    public void setEmail(String email) {
        txtemail.sendKeys(email);
    }
    public void setPassword(String password) {
        txtpassword.sendKeys(password);
    }
    public void clickLogin() {
        btnlogin.click();
    }

}

