package AlmosaferWeb;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.bouncycastle.crypto.signers.ISOTrailers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class myTestCases  extends ParametersClass{

	@BeforeTest
	public void Setup() {
		thebeginingofTheWebSite();
	}

//	@Test(description = " this is a happy scenario" ,priority = 1)
//	public void checkTheDefaultLanguageisEnglish() {
//		checkTheLanguageFunction("en");
//		softassert.assertAll();
//
//	}
//	
//	@Test(description = "this is a sad sceanrio ",priority = 2)
//	public void ChekTheDefaultLanaugeIsArabic() {
//		checkTheLanguageFunction("ar");
//		softassert.assertAll();
//
//	}

//	@Test(description = " this is to check that the currency is SAR",priority = 3)
//	public void CheckTheCurrency() {
//
//		checkTheCurrecnyFunction("SAR");
//		softassert.assertAll();
//
//
//	}

//	@Test(enabled = true)
//	public void CheckContactNumber() {
//		checkTheContactNumberFunction("+966554400000");
//		softassert.assertAll();
//	}

//	@Test()
//	public void CheckLogoApple() {
//		CheckLogoFunction(driver.findElement(By.xpath("//img[@alt='apple-pay']")));  
//		softassert.assertAll();
//
//	}

//	@Test(enabled = false)
//	public void HotelTabIsNotSelectedTest() {
//		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
//		String ActualSelectAreaValue = HotelTab.getAttribute("aria-selected");
//		String ExpectedSelectAreaValue = "false";
//
//		Assert.assertEquals(ActualSelectAreaValue, ExpectedSelectAreaValue);
//	}

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

	@Test(enabled = false)

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

		} else {

			Assert.assertEquals(ActualLanguage, "en");
			SearchHotelInput.sendKeys(englishCitiesNames[randomEnglish]);
			Thread.sleep(2000);
			WebElement cityList = driver.findElement(By.className("phbroq-4"));
			List<WebElement> myItems = cityList.findElements(By.tagName("li"));
			myItems.get(1).click();

		}
		WebElement vistorinput = driver.findElement(By.className("tln3e3-1"));

		Select mySelector = new Select(vistorinput);
		int randomIndexForVistor = rand.nextInt(2);

		mySelector.selectByIndex(randomIndexForVistor);
		WebElement SearchButton = driver.findElement(By.className("sc-1vkdpp9-6"));
		SearchButton.click();

		Thread.sleep(20000);

		String HotelSearchResult = driver.findElement(By.className("sc-cClmTo")).getText();

		// THIS IS ONE WAY

//		Assert.assertEquals(HotelSearchResult.contains("وجدنا")||HotelSearchResult.contains("found"), true);

		// THIS IS ANOTHER WAY
		if (driver.getCurrentUrl().contains("ar")) {
			boolean ActualResult = HotelSearchResult.contains("وجدنا");
			boolean ExpectedResult = true;
			Assert.assertEquals(ActualResult, ExpectedResult);
			WebElement LowestPriceFilter = driver.findElement(By.className("kgqEve"));
			LowestPriceFilter.click();

		} else {
			boolean ActualResult = HotelSearchResult.contains("found");
			boolean ExpectedResult = true;
			Assert.assertEquals(ActualResult, ExpectedResult);
			WebElement LowestPriceFilter = driver.findElement(By.className("jurIdk"));
			LowestPriceFilter.click();

		}

		Thread.sleep(5000);

		WebElement PricesSection = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> myPrices = PricesSection.findElements(By.className("Price__Value"));

		int lowestPrice = Integer.parseInt(myPrices.get(0).getText());
		int highestPrice = Integer.parseInt(myPrices.get(myPrices.size() - 1).getText());
		
	
		System.out.println(lowestPrice+ " this is the lowest price " );
		System.out.println(highestPrice+ " this is the highest price " );
		Assert.assertEquals(highestPrice > lowestPrice, true);

	}

	@AfterTest
	public void myAfterTest() {
	}

}
