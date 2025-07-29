package Vois.TestComponants;

import Vois.PageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

     public WebDriver driver;
     public LandingPage LandingPage;

    public WebDriver InitializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+
                "/src/main/java/Vois/Resources/GlobalData.properties");
        prop.load(fileInputStream);

        String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");

        if(BrowserName.contains("chrome"))
        {
             ChromeOptions options = new ChromeOptions();
            if (BrowserName.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1440,900");
                options.addArguments("--remote-allow-origins=*");
            }

            driver = new ChromeDriver(options);

        } else if (BrowserName.equalsIgnoreCase("firefox"))
        {
             driver = new FirefoxDriver();
        }else if (BrowserName.equalsIgnoreCase("edge"))
        {
             driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        if (!BrowserName.contains("headless")) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    public List<HashMap<String, String>> GetJasonDataToMap(String filePath) throws IOException {

        // read json to string
        String JsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        //string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;
    }

    public String getScreenshot(String TestCaseName,WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir")+"//reports//"+ TestCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+"//reports//"+ TestCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage LaunchApplication() throws IOException {
        driver = InitializeDriver();
        LandingPage = new LandingPage(driver);
        LandingPage.goTo();
        return LandingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void TearDown()
    {
        driver.quit();
    }
}
