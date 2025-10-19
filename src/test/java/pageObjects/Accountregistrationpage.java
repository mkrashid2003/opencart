package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Accountregistrationpage extends BasePage {
    public Accountregistrationpage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name="firstname")
    WebElement txtfirstname;
    @FindBy(name="lastname")
    WebElement txtlastname;
    @FindBy(name="email")
    WebElement txtemail;
    @FindBy(name="telephone")
    WebElement txttelephone;
    @FindBy(name="password")
    WebElement txtpassword;
    @FindBy(name="confirm")
    WebElement txtconfirmpassword;
    @FindBy(name="agree")
    WebElement chkdpolicy;
    @FindBy(xpath="//input[@value='Continue']")
    WebElement btncontinue;
    @FindBy(xpath="//h1[text()='Your Account Has Been Created!']")
    WebElement msgconfirmation;

    public void setFirstname(String fname) {
        txtfirstname.sendKeys(fname);
    }
    public void setLastname(String lname) {
        txtlastname.sendKeys(lname);
    }
    public void setEmail(String email) {
        txtemail.sendKeys(email);
    }
    public void setTelephone(String telephone) {
        txttelephone.sendKeys(telephone);
    }
    public void setPassword(String password) {
        txtpassword.sendKeys(password);
    }
    public void setConfirmPassword(String confirmpassword) {
        txtconfirmpassword.sendKeys(confirmpassword);
    }
    public void setPrivacyPolicy() {
        chkdpolicy.click();
    }
    public void clickContinue() {
        btncontinue.click();
    }
    public String getConfirmationMsg() {
        try {
            return msgconfirmation.getText();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
