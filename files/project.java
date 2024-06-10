package week15;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class project {
    public static final Logger logger = LogManager.getLogger(Project1.class);

    // Constructor
    public project() {
        // Initialize Log4j configuration
        Configurator.initialize(new DefaultConfiguration());
        // Set root log level to DEBUG
        Configurator.setRootLevel(Level.INFO);
    }

    WebDriver driver;
    String driverPath = "/usr/local/bin/chromedriver";
    String screenshotPath = "/Users/nati/Desktop/sfbu/cs522/screenshots";

    String url = "http://www.amazon.com/";

    String msg = "not found the price meets my expectation ";
    String testDataFile = "/Users/nati/Documents/sfbu/CS522/submission/project/data.xlsx";

    String item;
    String model;
    String color;
    String price;

    String amazonSearchTextBoxId = "twotabsearchtextbox";
    String amazonSearchButtonId = "nav-search-submit-button";

    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd_HHmmss");

    @BeforeTest
    void setup() throws Exception {
        logger.info("Setting up the test...");
        setExcelFile();
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.titleContains("Amazon.com. Spend less. Smile more."));
        logger.info("Test setup completed.");
    }

    @AfterTest
    void tearDown() {
        logger.info("Tearing down the test...");
        driver.quit();
        logger.info("Test teardown completed.");
    }


    @Test(priority = 1, enabled = true)
    void searchItemFromExcel() throws Exception {
        currentDateTime = LocalDateTime.now();
        String formattedDateTime = currentDateTime.format(formatter);
        logger.info("Executing searchItem...");
        searchItem(formattedDateTime);
        logger.info("Finished Executing searchItem...");
        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "project" + formattedDateTime + ".png");
        logger.info("Finished Taking screenshot...");
        logger.info("Executing selectItem...");
        selectItem(formattedDateTime);
        logger.info("Finished Executing selectItem...");
        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "Test2_" + formattedDateTime + ".png");
        logger.info("Finished Taking screenshot...");
        logger.info("Executing adding to cart");
        addToCart(formattedDateTime);
        logger.info("Finished Executing adding to cart");
        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "Test3_" + formattedDateTime + ".png");
        logger.info("Finished Taking screenshot...");

    }


    void searchItem(String formattedDateTime) throws Exception {
        driver.findElement(By.id(amazonSearchTextBoxId)).clear();
        driver.findElement(By.id(amazonSearchTextBoxId)).sendKeys(item, model);
        driver.findElement(By.id(amazonSearchButtonId)).click();

        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "searchItem" + formattedDateTime + ".png");

        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Flip 6 - Portable Bluetooth Speaker, powerful sound and deep bass, IPX7 waterproof")));

        // Check if any of the prices are present in the search results
        boolean b = driver.getPageSource().contains(item) || driver.getPageSource().contains(price) || driver.getPageSource().contains(color) || driver.getPageSource().contains(model);

        Assert.assertTrue(b, msg + model + " or " + color + " or " + price);

        logger.info("Search completed for item: " + item);

        // Assert that item is found
        Assert.assertTrue(b, msg + item);
    }

    void selectItem(String formattedDateTime) throws Exception {
        driver.findElement(By.partialLinkText("Flip 6 - Portable Bluetooth Speaker, powerful sound and deep bass, IPX7 waterproof")).click();
        Thread.sleep(3000);

        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "selectItem" + formattedDateTime + ".png");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//img[@alt='%s']", color))));

        // select the desired color
        selectColor();

        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "selectItem" + formattedDateTime + ".png");

        // Select '1' from the dropdown
        Select quantitySelect = new Select(driver.findElement(By.id("quantity")));
        quantitySelect.selectByValue("1");
        Thread.sleep(3000);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public void selectColor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("JBL Flip 6 - Portable Bluetooth Speaker, powerful sound and deep bass"));
        String xpathExpression = String.format("//img[@alt='%s']", color);
        WebElement colorButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
        colorButton.click();
        logger.info(String.format("'%s' color selected for the item.", color));
    }

    void addToCart(String formattedDateTime) throws Exception {
        Thread.sleep(3000);
        driver.findElement(By.id("add-to-cart-button")).click();
        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "addToCart" + formattedDateTime + ".png");
        Thread.sleep(3000);
        driver.findElement(By.id("attachSiNoCoverage")).click();
        logger.info("Taking screenshot...");
        takeSnapShot(driver, screenshotPath + "//" + "addToCart" + formattedDateTime + ".png");
        Thread.sleep(6000);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        // Call getScreenshotAs method to create an image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        // Move the image file to a new destination
        File DestFile = new File(fileWithPath);

        // Copy the file to the destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    void setExcelFile() throws Exception {
        try {
            FileInputStream file = new FileInputStream(testDataFile);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            item = sheet.getRow(1).getCell(0).getStringCellValue();
            model = sheet.getRow(1).getCell(1).getStringCellValue();
            color = sheet.getRow(1).getCell(2).getStringCellValue();
            price = sheet.getRow(1).getCell(3).getStringCellValue();

        } catch (Exception e) {
            throw (e);
        }
    }

    // private String getCellValue(XSSFCell cell) {
    //     return cell.getCellType() == CellType.NUMERIC ? Double.toString(cell.getNumericCellValue()) : cell.getStringCellValue();
    // }
}
