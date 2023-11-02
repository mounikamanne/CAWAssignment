package cawassignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CAWAssignment {

	static WebDriver driver;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\kokkonda.mounika\\Documents\\FSWeb_WS\\CAWAssignment\\lib\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);

		try {
			driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

			WebElement tableDataButton = driver.findElement(By.xpath("//summary[contains(text(),'Table Data')]"));
			tableDataButton.click();

			String jsonData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, "
					+ "{\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, "
					+ "{\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, "
					+ "{\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, "
					+ "{\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
			WebElement dataInput = driver.findElement(By.xpath("//input[@id='caption']"));
			dataInput.clear();
			dataInput.sendKeys(jsonData);

			WebElement refreshTableButton = driver.findElement(By.xpath("//button[@id='refreshtable']"));
			refreshTableButton.click();

			String tableContent = driver.findElement(By.tagName("caption")).getText();
			if (tableContent.equals(jsonData)) {
				System.out.println("Data matches the UI table.");
			} else {
				System.out.println("Data does not match the UI table.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
