package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;
import testBase.Baseclass;

public class Tc002_Logintest  extends Baseclass {
    @Test(groups ={ "Sanity", "Master"})
    public  void verify_login(){
        HomePage hp=new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on My Account link");
        hp.clickLogin();
        logger.info("Clicked on Login link");
        Loginpage lp=new Loginpage(driver);
        lp.setEmail(p.getProperty("email"));
        logger.info("Provided email");
        lp.setPassword(p.getProperty("password"));

        logger.info("Provided password");
        lp.clickLogin();
        logger.info("Clicked on Login button");

        //my account page
        MyAccountpage myacc=new MyAccountpage(driver);
        boolean targetpage=myacc.isMyAccountPageExists();
        Assert.assertTrue(targetpage);//
        logger.info("*******finish tc002_Logintest Success*******");





    }
}
