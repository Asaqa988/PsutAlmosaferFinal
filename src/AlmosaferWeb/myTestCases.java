package AlmosaferWeb;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class myTestCases {

	String TheWebSite = "https://global.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();
	Random rand = new Random();

	String[] arabicCitiesNames = { "دبي", "جدة" };
	String[] englishCitiesNames = { "Jeddah", "riyadh", "dubai" };
	int randomArabic = rand.nextInt(arabicCitiesNames.length);
	int randomEnglish = rand.nextInt(englishCitiesNames.length);

	@BeforeTest
	public void Setup() {
		driver.manage().window().maximize();
		driver.get(TheWebSite);
		WebElement WelcomeScreen = driver
				.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		WelcomeScreen.click();
	}

	@Test(enabled = false)
	public void checkTheLanguage() {
		String ActualLnag = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLang = "en";
		softassert.assertEquals(ActualLnag, ExpectedLang, "this is to check the langauge");
	}

	@Test(enabled = false)
	public void CheckTheCurrency() {

		String ExpectedCurrency = "SAR";
		WebElement CurrencyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String ActualCurrency = CurrencyElement.getText();
		softassert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(enabled = false)
	public void CheckContactNumber() {

		String expectedContactNumber = "+966554400000";

		WebElement ContactNumberelement = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualContactNumber = ContactNumberelement.getText();
		softassert.assertEquals(ActualContactNumber, expectedContactNumber);
	}

	@Test(enabled = false)
	public void CheckQitaffLogo() {
		WebElement QitafLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"));

		boolean isQitafLogoDisplayed = QitafLogo.isDisplayed();
		boolean expectedQitafLogoIsDisplayed = true;

		softassert.assertEquals(isQitafLogoDisplayed, expectedQitafLogoIsDisplayed);
	}

	@Test(enabled = false)
	public void HotelTabIsNotSelectedTest() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualSelectAreaValue = HotelTab.getAttribute("aria-selected");
		String ExpectedSelectAreaValue = "false";

		Assert.assertEquals(ActualSelectAreaValue, ExpectedSelectAreaValue);
	}

	@Test(enabled = false)
	public void CheckTheDepatureAndArrivalDate() {

		// we got two elements from the website 1- for the depature date 2- for the
		// return date
		WebElement ActualDepatureDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement ActualReturnDate = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));

		// from the previous two elements we extracted the text (getText())
		// then we convert the text to int why ? because we need the value of the text
		// as a number , so we can test it's value with the date.day value
		// Integer.parseInt(اسم السترينغ الي بدك تحوله لرقم)

		int ActualDeptureDateValue = Integer.parseInt(ActualDepatureDate.getText());

		int ActualReturnDateValue = Integer.parseInt(ActualReturnDate.getText());

		// we need the date of today so if you try to print the &&today&&
		// the result will be like this 2023-12-06 we don't need the whole date
		// instead we need only the day of the date which is for example 06
		// then we extract only the value of the day via
		// today.plusDays(1).getDayOfMonth();
		// the requirment of the website is to check the date on the website is equals
		// totoday's day + 1 we do this via today.plusDays(1) this is will add one day
		// to today's date

		LocalDate today = LocalDate.now();

		int ExpectedDepatureDateValue = today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDateValue = today.plusDays(2).getDayOfMonth();

		// then we do the assertion process

		Assert.assertEquals(ActualDeptureDateValue, ExpectedDepatureDateValue);
		Assert.assertEquals(ActualReturnDateValue, ExpectedReturnDateValue);

		//// part two to check the day of the week
		String dayElementOnTheWebsite = driver
				.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"))
				.getText().toUpperCase();
		Assert.assertEquals(dayElementOnTheWebsite, today.plusDays(1).getDayOfWeek().toString());

		// another way to print the day of the week as for examle Thursday.
		String helooooooooooooo = today.plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRANCE)
				.toString();

		System.out.println(today.getMonth());
	}

	@Test(invocationCount = 1)

	public void changeTheLanguageOfTheWebsiteRandomly() throws InterruptedException {

		// access the website ar or en randomly

		String[] myUrls = { "https://global.almosafer.com/ar", "https://global.almosafer.com/en" };
		int randomIndex = rand.nextInt(myUrls.length);
		driver.get(myUrls[randomIndex]);

		// this is to click on the hotel tab

		WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		hotelTab.click();

		Thread.sleep(2000);

		// inside the searchhotel tab we will send keys either arabic cities or english
		// cities based on the website lanaguage

		WebElement SearchHotelInput = driver.findElement(By.className("phbroq-2"));

		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		if (driver.getCurrentUrl().contains("ar")) {

			Assert.assertEquals(ActualLanguage, "ar");
			SearchHotelInput.sendKeys(arabicCitiesNames[randomArabic]);
			Thread.sleep(2000);

			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();

			//mySelector.selectByVisibleText("1 غرفة، 1 بالغ، 0 أطفال");

		} else {

			Assert.assertEquals(ActualLanguage, "en");
			SearchHotelInput.sendKeys(englishCitiesNames[randomEnglish]);
			Thread.sleep(2000);
			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();
			WebElement vistorinput = driver.findElement(By.className("tln3e3-1"));
			//mySelector.selectByVisibleText("1 Room, 1 Adult, 0 Children");

		}
		WebElement vistorinput = driver.findElement(By.className("tln3e3-1"));
		Select mySelector = new Select(vistorinput);
		
		mySelector.selectByIndex(1);

	}

	@AfterTest
	public void myAfterTest() {
		softassert.assertAll();
	}

}
