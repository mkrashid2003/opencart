package testcases;

import org.testng.Assert;
import org.testng.IDataProviderMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;
import testBase.Baseclass;
import utilities.DataProviders;

public class Tc004_LoginDDT extends Baseclass {
    @Test(dataProvider="LoginData",dataProviderClass= DataProviders.class,groups = "Datadriven")//getting data from diffrent DataProviders class
    public void verify_loginDDT(String email,String password,String exp) throws InterruptedException {//three parameters
        logger.info("*******start tc004_LoginDDT*******");
try{
// home page
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
        logger.info("Clicked on My Account link");
        hp.clickLogin();
        logger.info("Clicked on Login link");
//  login page
        Loginpage lp = new Loginpage(driver);
        lp.setEmail(email);
        logger.info("Provided email");
        lp.setPassword(password);
        logger.info("Provided password");
        lp.clickLogin();

        logger.info("Clicked on Login button");

        //my account page
        MyAccountpage myacc = new MyAccountpage(driver);
        boolean targetpage = myacc.isMyAccountPageExists();
        Assert.assertTrue(targetpage);//
        if(exp.equalsIgnoreCase("valid"))
        {
            if(targetpage==true){
                myacc.clickLogout();
                Assert.assertTrue(true);
                logger.info("Clicked on Logout button");
            }
            else{
                Assert.assertTrue(false);
            }
        }
        if(exp.equalsIgnoreCase("invalid"))
        {
            if(targetpage==true){
                myacc.clickLogout();
                Assert.assertTrue(false);


            }
            else{
                Assert.assertTrue(true);
            }
        }
}
catch(Exception e){
    Assert.fail();
}
Thread.sleep(4000);
        logger.info("*******finish tc004_LogintestDDT Success*******");


    }
}