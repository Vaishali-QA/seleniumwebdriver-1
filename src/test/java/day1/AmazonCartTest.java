package day1;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCartTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {

            driver.manage().window().maximize();
            driver.get("https://www.amazon.com");

            // Search product
            WebElement searchBox = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

            searchBox.sendKeys("Samsung S24");
            searchBox.submit()
;            //driver.findElement(By.id("nav-search-submit-button")).click();

            // Open first product
            WebElement firstProduct = wait.until(
                    ExpectedConditions.elementToBeClickable(
                    		By.xpath("(//span[normalize-space()=\"Galaxy S26, Unlocked Android Smartphone, 256GB, Sky Blue\"]")));

            firstProduct.click();

            // Store product name
            WebElement productTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));

            String productName = productTitle.getText().trim();

            System.out.println("Product Added : " + productName);

            // Add to Cart
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));

            addToCart.click();

            // Verify cart count
            WebElement cartCount = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart-count")));

            String count = cartCount.getText();

            if (!count.equals("0")) {
                System.out.println("Cart Count Verification PASSED");
                System.out.println("Cart Count : " + count);
            } else {
                System.out.println("Cart Count Verification FAILED");
            }

            // Open Cart
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart"))).click();

            // Verify product exists in cart
            WebElement cartProduct = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(@class,'a-truncate-cut')]")));

            String cartProductName = cartProduct.getText().trim();

            System.out.println("Product in Cart : " + cartProductName);

            if (productName.contains(cartProductName)
                    || cartProductName.contains(productName)) {

                System.out.println("Product Verification PASSED");
            } else {
                System.out.println("Product Verification FAILED");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}