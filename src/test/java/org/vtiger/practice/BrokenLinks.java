package org.vtiger.practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://demo.automationtesting.in/Register.html");
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		List<String> list=new ArrayList<>();
		for(WebElement link:allLinks)
		{  
			String linkString=link.getAttribute("href");
			list.add(linkString);
			list.remove(null);
		}


		for(String url:list) {
			System.out.println(url);
			URL u=new URL(url);
			HttpURLConnection response=(HttpURLConnection) u.openConnection();
			int statusCode=response.getResponseCode();
			if(statusCode!=200) {
				System.out.println(url+" ==>  "+statusCode+" ==> "+response.getResponseMessage());
			}
		}

	}
}

