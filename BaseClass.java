package Com.Crm.BaseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Pom.Dwslogin;
import javaUtility.CommonDatas; 

public class BaseClass {

    public static WebDriver driver;
    public static Properties properties;

    CommonDatas common = new CommonDatas();
   
    @BeforeClass 
    @Parameters("browser")
    public void crossBrowser(String browser) throws IOException {
    	String data = common.data("browser");
    	String url = c ommon.data("url");

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("internetexplorer")) {
            System.setProperty("webdriver.ie.driver", "path/to/IEDriverServer");
            driver = new InternetExplorerDriver();
        } else {
            System.out.println("Unsupported browser. Defaulting to Chrome.");
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
            driver = new ChromeDriver();
        }

       
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    
    }
    
    @BeforeMethod
    public void login() {
        driver.get(properties.getProperty("url"));

        Dwslogin dwslog = new Dwslogin(driver);
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

    }
    
   
    @AfterMethod
    public void logout() {
    	
    }

    
    
    }

