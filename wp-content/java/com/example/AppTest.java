package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WordPressMainPageTest {

    public static void main(String[] args) {
        // Utwórz nowy obiekt przeglądarki Chrome.
        WebDriver driver = new ChromeDriver();

        // Otwórz stronę główną WordPressa.
        driver.get("https://example.com/");

        // Sprawdź, czy tytuł strony jest poprawny.
        String title = driver.getTitle();
        if (!title.equals("WordPress")) {
            throw new AssertionError("Tytuł strony nie jest poprawny.");
        }

        // Sprawdź, czy logo WordPressa jest wyświetlane.
        WebElement logo = driver.findElement(By.xpath("//*[@id=\"site-title\"]"));
        if (logo == null) {
            throw new AssertionError("Logo WordPressa nie jest wyświetlane.");
        }

        // Zamknij przeglądarkę.
        driver.quit();
    }
}