package jenipractice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class LoginFe {
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	@BeforeMethod
	public void inite() {
		if (Objects.isNull(extent)) {

			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("./extentReport/Spark.html");
			ExtentSparkReporter failedSpark = new ExtentSparkReporter("./extentReport/failedSpark.html").filter()
					.statusFilter().as(new Status[] { Status.FAIL }).apply();
			failedSpark.config().setDocumentTitle("failde TCs");
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Passed TCs");
			extent.attachReporter(spark, failedSpark);
			test = extent.createTest("tc");
		}
	}

	@AfterClass
	public void tearDown() throws Exception {
		extent.flush();
		driver.close();

	}

	@Test
	public void login() {
		driver = new ChromeDriver();
		test.info("launching browser");
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		test.info("entered valid credentials");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("build executed");
		System.err.println("BUILD EXE PROCESS");
		Reporter.log("BUILD RUNS", true);
	}

	@Test
	public void name() {
		test.info("second test");
		System.out.println("second test");
		//Assert.fail();

	}

}
