package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }



    @FindBy(xpath="//span[text()='My Account']")
    WebElement lnkmyAccount;
    @FindBy(linkText="Register")
    WebElement lnkregister;
    @FindBy(linkText="Login")
    WebElement lnklogin;



    public void clickMyAccount() {
        lnkmyAccount.click();
    }
    public void clickRegister() {
        lnkregister.click();
    }
    public void clickLogin() {
        lnklogin.click();
    }



}
