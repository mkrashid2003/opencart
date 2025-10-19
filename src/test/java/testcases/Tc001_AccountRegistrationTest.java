package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Accountregistrationpage;
import pageObjects.HomePage;
import testBase.Baseclass;

public class Tc001_AccountRegistrationTest extends Baseclass {
    @Test(groups = {"Regression", "Master"})
    public void verify_Account_Registration() {
        logger.info("Starting TC001_AccountRegistrationTest started");
        try {


            HomePage hp = new HomePage(driver);

            hp.clickMyAccount();
            logger.info("Clicked on My Account link");
            hp.clickRegister();
            logger.info("Clicked on Register link");

            Accountregistrationpage regpage = new Accountregistrationpage(driver);
            logger.info("Providing customer data");
            regpage.setFirstname(randomestring().toUpperCase());
            regpage.setLastname(randomestring().toUpperCase());
            regpage.setEmail(randomestring() + "@gmail.com");

            regpage.setTelephone(randomnum());


            String password = randomalphanum();
            regpage.setPassword(password);
            regpage.setConfirmPassword(password);

            regpage.setPrivacyPolicy();
            regpage.clickContinue();
            logger.info("validation expected mssg....");
            String confmsg = regpage.getConfirmationMsg();
            if(confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
                logger.error("Account Registration Failed");

            }
            Assert.assertEquals(confmsg, "Your Account Has Been Created!");
        } catch (Exception e) {


            Assert.fail();

        }
        logger.info("Finished TC001_AccountRegistrationTest");
    }

}
