package co.selenium.demo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestAutomation {

	WebDriver driver;
	private String orderMyPage;

	public void invokeBrowser() {
		System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		getCommands();
	}

	public void getCommands() {
		try {
			driver.get("http://automationpractice.com/index.php");
			// click on Dresses
			driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[2]/a")).click();
			Thread.sleep(1000);
			// click on In Stock
			driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[1]/form/div/div[7]/ul/li/label/a")).click();
			Thread.sleep(2000);
			getProduct();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getProduct() {
		try {
			Actions action = new Actions(driver);
			WebElement element = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div"));
			action.moveToElement(element).build().perform();
			driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[2]/div/div[2]/div[2]/a[2]")).click();
			Thread.sleep(2000);
			changeQuantity();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void changeQuantity() {
		try {
			driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]")).click();
			Thread.sleep(2000);
			changeSize();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void changeSize() {
		try {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			Actions action = new Actions(driver);
			WebElement els = driver.findElement(By.xpath("//*[@id=\"uniform-group_1\"]/span"));
			action.moveToElement(els).build().perform();
			driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[2]")).click();
			Thread.sleep(2000);
			js.executeScript("window.scrollBy(0,200)");
			Thread.sleep(2000);
			changeColor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	public void changeColor() {
		try {
			driver.findElement(By.xpath("//*[@id=\"color_24\"]")).click();
			Thread.sleep(2000);
			addToCart();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addToCart() {
		
		try {
			driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button/span")).click();
			Thread.sleep(4000);
			proceedToCheckOut();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void proceedToCheckOut() throws AWTException {
		orderMyPage = "Order - My Store";
		try {
			Robot robot = new Robot();
			robot.mouseMove(950,500 );
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		String getTitle = driver.getTitle();
		System.out.println("The Title of the page is " +  getTitle);
		if(orderMyPage.contentEquals(getTitle)) {
			System.out.println( "Test Passed") ;
		}
		else {
		System.out.println( "Test Failed" );
			}
		}

	public static void main(String[] args) {
		TestAutomation myObje = new TestAutomation();
		myObje.invokeBrowser();

	}

}
