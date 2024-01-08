package com.example;


import java.util.ArrayList;

import javax.swing.text.html.parser.Element;
import javax.xml.xpath.XPath;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Tests {

     WebDriver driver;

    @BeforeEach
    public void BeforEachTest(){

        driver = new ChromeDriver();
        driver.navigate().to("http://localhost/wordpress/shop/");
        driver.manage().window().setSize(new Dimension(600, 600));

    }

    @AfterEach
    public void AfteEachTest(){
        driver.close();
    }



    @Test
    public void ZbieranieCenTest_Do_Poprawy_Bo_Omija_Wyprzedaz(){

        ArrayList<Double> priceArrayList = new ArrayList<Double>();
        double price;


        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[1]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price);
        priceArrayList.add(price);
        

        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[2]/div[2]/span[2]/ins/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);


        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[3]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price);       
        priceArrayList.add(price);

        
        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[4]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);

        
        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[5]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);


        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[6]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);

        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[7]/div[2]/span[2]/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);

        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[8]/div[2]/span[2]/ins/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);

        price = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li[9]/div[2]/span[2]/ins/span/bdi")).getText().replace("$", ""));
        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price); 
        priceArrayList.add(price);



        ArrayListPrint(priceArrayList);
    }

    @Test
    public void ZbieranieCenEnhanced(){
        ArrayList<Double> priceArrayList = new ArrayList<Double>();
        double price;
        String xpathPart1 = "//*[@id=\"main\"]/div/ul/li[", xpathPart2 = "]/div[2]/span[2]/span/bdi", xpathPart2ForDiscounted = "]/div[2]/span[2]/ins/span/bdi";

        for(int i=1;i<=9;i++){

        try{
        price = Double.parseDouble(driver.findElement(By.xpath(xpathPart1+i+xpathPart2ForDiscounted)).getText().replace("$", ""));
        System.out.println("!!! Cena produktu "+i+". jest obnizona");
        }
        catch(Exception e){
           price = Double.parseDouble(driver.findElement(By.xpath(xpathPart1+i+xpathPart2)).getText().replace("$", ""));
        }

        System.out.println("Cena "+ (priceArrayList.size()+1) +". produktu to: "+ price);
        priceArrayList.add(price);
        }


        ArrayListPrint(priceArrayList);
    }

    @Test
    public void IsProperPicDisplayedInItemPage() throws InterruptedException{
        
        ArrayList<String> productIdsArrayList_ok = new ArrayList<>();
        ArrayList<String> productIdsArrayList_not_ok = new ArrayList<>();

        String picturePath, productId;
        int product_name_start_in_string = 0;


        for (int i = 1; i <= 9; i++) {
        picturePath = driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li["+i+"]/div[1]/a/img")).getAttribute("src");

        product_name_start_in_string = driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li["+i+"]")).getAttribute("class").indexOf("post-");
        productId = driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li["+i+"]")).getAttribute("class").substring(product_name_start_in_string+5, product_name_start_in_string+9); // 5 and 9 because we want to extract only productId from string "post-xxxx" where xxxx is the productId

        driver.findElement(By.xpath("//*[@id=\"main\"]/div/ul/li["+i+"]/div[1]/a/img")).click();

        if(picturePath.equals(driver.findElement(By.xpath("//*[@id=\"product-"+productId+"\"]/div[1]/div/div/img")).getAttribute("src"))){
            System.out.println("Picture of product-"+productId+" is implemented correctly in product page");
            productIdsArrayList_ok.add(productId);
        }
            else{
            System.out.println("!!! Picture of product-"+ productId + "IS NOT IMPLEMENTED CORRECTLY !!!");
            productIdsArrayList_not_ok.add(productId);
            }
        
     
        
       driver.navigate().back();
        }

        System.out.println("Lista produktow z odpowienio zaimplementowanymi obrazkami: ");
        ArrayListPrint(productIdsArrayList_ok);

        System.out.println("Produkty wymagajace poprawy obrazka na stronie produktu: ");
        ArrayListPrint(productIdsArrayList_not_ok);

    }













     public void ArrayListPrint(ArrayList al){
        if(al.isEmpty())
        System.out.println("Ta lista jest pusta");
        else
        for (int i = 0; i < al.size() ;i++)            
        System.out.println("Na "+ (i+1) + ". pozycji listy znajduje sie cena: "+ al.get(i) );

        
    }


}
