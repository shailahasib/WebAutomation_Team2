package giftFinder;

import common.WebAPI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static giftFinder.GiftFinderElements.*;
import static org.openqa.selenium.Keys.ARROW_DOWN;

public class GiftFinderPage extends WebAPI {

    @FindBy (how = How.CSS, using = burgerMenu)
    public WebElement burger;
    @FindBy (how = How.XPATH, using = giftFinderInDep)
    public WebElement giftFinderButton;
    @FindBy (how = How.XPATH, using = textCheckGiftPage)
    public WebElement getTextGiftPage;

    @FindBy (how = How.CSS, using = giftForWho)
    public WebElement forWho;
    @FindBy (how = How.CSS, using = selectHer)
    public WebElement forHer;
    @FindBy (how = How.CSS, using = selectAgeGroup)
    public WebElement ageGroupBox;
    @FindBy (how = How.CSS, using = selectBaby)
    public WebElement forBaby;
    @FindBy (how = How.CSS, using = selectPriceRange)
    public WebElement priceRangeBox;
    @FindBy (how = How.CSS, using = priceLessThan25)
    public WebElement lessThan25;

    @FindBy (how = How.CSS, using = occasionDropArrow)
    public WebElement dropArrowOccasion;
    @FindBy (how = How.CSS, using = selectBirthdayOption)
    public WebElement selectBirthday;
    @FindBy (how = How.XPATH, using = birthdayImage)
    public WebElement birthdayBanner;

    @FindBy (how = How.CSS, using = copyRightDisplay)
    public WebElement copyRightText;

    @FindBy (how = How.CSS, using = showAllRadioButton)
    public WebElement showAllRadio;

    @FindBy (how = How.CSS, using = topArrowButton)
    public WebElement backToTopArrow;
    @FindBy (how = How.XPATH, using = giftFinderAtTop)
    public WebElement giftFinderAtTopCorner;

    @FindBy (how = How.CSS, using = feedBackButton)
    public WebElement feedBack;
    @FindBy (how = How.CSS, using = stars)
    public WebElement fiveStars;

    @FindBy (how = How.CSS, using = brandBoxScroll)
    public WebElement scrollInBrand;

    @FindBy (how = How.CSS, using = theMandalorian)
    public WebElement mandalorianBrand;

    public void navigateToGiftFinder(){
        burger.click();
        giftFinderButton.click();
    }
    public void validateGiftFinderPage(){
        String expected = textForGiftPage;
        int attempts = 0;
        String actual = "";
        while(attempts < 2) {
            try {
                 actual = getTextGiftPage.getText();//stale element handling
                break;
            } catch(Exception e) {
            }
            attempts++;
        }
        Assert.assertEquals(actual, expected, "Failed: Did not navigate to gift finder page");
    }

    public void selectOccasion() throws InterruptedException {
        int attempts = 0;
        while(attempts < 2) {
            try {
                dropArrowOccasion.click();//stale element handling
                break;
            } catch(Exception e) {
            }
            attempts++;
        }
        selectBirthday.click();
        birthdayBanner.isDisplayed();
    }
    public void validateSelectOccasion(){
        Boolean imageLoaded =
          (Boolean) ((JavascriptExecutor)driver).executeScript(
                        "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",birthdayBanner);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(!imageLoaded,"Failed: Birthday banner did not load");

    }

    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", copyRightText);
    }
    public void validateScrollDown(){
        boolean elemDisplayed = copyRightText.isDisplayed();
        Assert.assertTrue(elemDisplayed,"Failed: Element not displayed");
    }

    public void showAllRadioSelect(){
        showAllRadio.click();
    }
    public void validateShowAllSelect(){
        boolean selected = showAllRadio.isSelected();
        Assert.assertTrue(selected,"Failed :Radio button not selected");
    }

    public void selectBackToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        backToTopArrow.click();
    }
    public void validateBackToTopButton(){
        boolean topOfPageDisplay = giftFinderAtTopCorner.isDisplayed();
        Assert.assertTrue(topOfPageDisplay);
    }

    public void giveFeedBackStars(){
        feedBack.click();
        fiveStars.click();
    }
    public void validateFiveStars(){
        boolean fiveStarsFeedBack= fiveStars.isSelected();
        Assert.assertTrue(fiveStarsFeedBack, "Failed: 5 stars not selected");
    }

    public void scrollInsideBrand(){
        scrollInBrand.sendKeys(ARROW_DOWN);
    }
    public void validateBrandScrollDown(){
        boolean mandalorianDisplayed = mandalorianBrand.isDisplayed();
        Assert.assertTrue(mandalorianDisplayed, "Failed: Did not scroll down to The Mandalorian");
    }



}


