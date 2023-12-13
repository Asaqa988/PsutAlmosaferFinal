package AlmosaferWeb;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class ParametersClass {
	
	
	String TheWebSite = "https://global.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();
	Random rand = new Random();
	String[] arabicCitiesNames = { "دبي", "جدة" };
	String[] englishCitiesNames = { "Jeddah", "riyadh", "dubai" };
	int randomArabic = rand.nextInt(arabicCitiesNames.length);
	int randomEnglish = rand.nextInt(englishCitiesNames.length);



public void thebeginingofTheWebSite() {
	driver.manage().window().maximize();
	driver.get(TheWebSite);
	WebElement WelcomeScreen = driver
			.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
	WelcomeScreen.click();
}

public void checkTheLanguageFunction (String theLanaugeINeedtocheck) {
	
	String ActualLnag = driver.findElement(By.tagName("html")).getAttribute("lang");
	String ExpectedLang = theLanaugeINeedtocheck;
	softassert.assertEquals(ActualLnag, ExpectedLang, "this is to check the langauge");
}

public void checkTheCurrecnyFunction (String ExpectedCurrency) {
	
	String theCurrencyINeedToCheck = ExpectedCurrency;
	WebElement CurrencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
	String ActualCurrency = CurrencyElement.getText();
	softassert.assertEquals(ActualCurrency, theCurrencyINeedToCheck);
}

public void checkTheContactNumberFunction(String ContactNumber) {

	String expectedContactNumber = ContactNumber;

	WebElement ContactNumberelement = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
	String ActualContactNumber = ContactNumberelement.getText();
	softassert.assertEquals(ActualContactNumber, expectedContactNumber);
}

public void CheckLogoFunction(WebElement theLogo) {
	WebElement expectedLogoToCheck = theLogo; 

	boolean isLogoDisplayed = expectedLogoToCheck.isDisplayed();
	boolean expectedLogoIsDisplayed = true;

	softassert.assertEquals(isLogoDisplayed, expectedLogoIsDisplayed);
}
}