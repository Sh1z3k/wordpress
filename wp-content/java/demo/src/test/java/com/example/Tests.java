package com.example;


import java.util.ArrayList;




import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;


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
    public void PriceCollecting_Needs_Some_Improvements_Because_Doges_Discounts(){

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
    public void PriceCollectingEnhanced(){
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
            Assert.isTrue(false,"Picture of item and picture displayed in item page do not match");
            }
        
     
        
       driver.navigate().back();
        }

        System.out.println("Lista produktow z odpowienio zaimplementowanymi obrazkami: ");
        ArrayListPrint(productIdsArrayList_ok);

        System.out.println("Produkty wymagajace poprawy obrazka na stronie produktu: ");
        ArrayListPrint(productIdsArrayList_not_ok);



        Assert.isTrue(true,"All pictures match the item pages pictures");
    }


    @Test
    public void CheckIfSortedProperlyPriceAsc() throws InterruptedException{
        ArrayList<Double> pricesArrayList = new ArrayList<>();
        double price;
        String xpathPart1 = "//*[@id=\"main\"]/div/ul/li[", xpathPart2 = "]/div[2]/span[2]/span/bdi", xpathPart2ForDiscounted = "]/div[2]/span[2]/ins/span/bdi";


        //selecting the sorting type
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/form/select/option[5]")).click();

         synchronized (driver){
            driver.wait(2000);
        }
    

                    for (int i = 1; i <= 9; i++) {

        try{
        price = Double.parseDouble(driver.findElement(By.xpath(xpathPart1+i+xpathPart2ForDiscounted)).getText().replace("$", ""));
        {System.out.println("!!! Cena produktu "+i+". jest obnizona");}
        }
        catch(Exception e){
           price = Double.parseDouble(driver.findElement(By.xpath(xpathPart1+i+xpathPart2)).getText().replace("$", ""));
           System.out.println("!!! Cena produktu "+i+". jest normalna");
        }
        pricesArrayList.add(price);
        
        }


        
        Assert.isTrue(CheckIfArrayListSortedAsc(pricesArrayList),"Items sorting by price ascending error");
    }




    public boolean CheckIfArrayListSortedAsc(ArrayList<Double> al){
        
        for(int i = 0; i < al.size()-1; i++){
            if(al.get(i)<al.get(i+1)){
            System.out.println(al.get(i)+" is lower than "+al.get(i+1));
        }
            else if(al.get(i).equals(al.get(i+1))){
            System.out.println(al.get(i)+" is equal to "+al.get(i+1));
        }
            else if(al.get(i)>al.get(i+1))
            {
                System.out.println("false - CheckIfArrayListSortedAsc");
                return false;
            }
            else System.out.println("DEBUG - unknown error, check the CheckIfArrayListSortedAsc funtion");
        }


        for (Double element : al) {
            System.out.print(element.doubleValue()+" ");
        }

        
        return true;
    }



    public boolean CheckIfArrayListSortedDesc(ArrayList<Double> al){
        
        for(int i = 0; i < al.size()-1; i++){
            if(al.get(i)>al.get(i+1)){
            System.out.println(al.get(i)+" is greater than "+al.get(i+1));
        }
            else if(al.get(i).equals(al.get(i+1))){
            System.out.println(al.get(i)+" is equal to "+al.get(i+1));
        }
            else if(al.get(i)<al.get(i+1))
            {
                System.out.println("false - CheckIfArrayListSortedDesc");
                return false;
            }
            else System.out.println("DEBUG - unknown error, check the CheckIfArrayListSortedDesc funtion");
        }


        for (Double element : al) {
            System.out.print(element.doubleValue()+" ");
        }

        
        return true;
    }

     public void ArrayListPrint(ArrayList al){
        if(al.isEmpty())
        System.out.println("This ArrayList is empty");
        else
        for (int i = 0; i < al.size() ;i++)            
        System.out.println("Position: "+(i+1)+". - Price: "+ al.get(i) );
    }


}
