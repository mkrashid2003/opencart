package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class Baseclass {
    public static WebDriver driver;
    public Logger logger; // for logging
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(@Optional("Windows") String os, @Optional("chrome") String br) throws IOException {
        // load config properties
        InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
        p = new Properties();
        p.load(input);
        System.out.println("baseURL: " + p.getProperty("baseURL")); // debug print
        logger = LogManager.getLogger(this.getClass()); // log4j2

        if (p.getProperty("execution_env") == null) {
            throw new IllegalStateException("Missing 'execution_env' property in config.properties");
        }

        if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities cap = new DesiredCapabilities();
            // os
            if (os.equalsIgnoreCase("Windows")) {
                cap.setPlatform(Platform.WIN10);
            } else if (os.equalsIgnoreCase("Mac")) {
                cap.setPlatform(Platform.MAC);
            } else {
                System.out.println("Please provide correct os");
            }
            // browser
            switch (br.toLowerCase()) {
                case "chrome":
                    cap.setBrowserName("chrome");
                    break;
                case "edge":
                    cap.setBrowserName("edge");
                    break;
                case "firefox":
                    cap.setBrowserName("firefox");
                    break;
                default:
                    System.out.println("No Matching browser");
            }
            driver = new RemoteWebDriver(new URL("http://192.168.0.112:4444/wd/hub"), cap);
        } else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Please provide valid browser");
                    break;
            }
        } else {
            throw new IllegalStateException("Invalid 'execution_env' property value: " + p.getProperty("execution_env"));
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(p.getProperty("baseURL")); // get url from properties file
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void tearDown() {
        driver.quit();
    }

    public String randomestring() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomnum() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String randomalphanum() {
        String gstr1 = RandomStringUtils.randomAlphabetic(5);
        String gstr2 = RandomStringUtils.randomNumeric(10);
        return (gstr1 + "@" + gstr2);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
        TakesScreenshot ts = (TakesScreenshot) driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilepath);
        sourceFile.renameTo(targetFile);
        return targetFilepath;
    }
}
