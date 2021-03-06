package com.globantu.automation.yoniffer_mendez.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public abstract class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor jsExecutor;
	
	protected static final By liTest = By.tagName("li");
	protected static final By buttonTest = By.tagName("button");
	protected static final By tdTest = By.tagName("td");
	protected static final By spanTest = By.tagName("span");
	
	public BasePage(WebDriver pDriver) {
		PageFactory.initElements(pDriver, this);
		wait= new WebDriverWait(pDriver,20);
		driver=pDriver;
		jsExecutor = (JavascriptExecutor) driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}
	
	public void dispose() {
		if(driver!=null) driver.quit();
	}
	
	public JavascriptExecutor getJsExecutor() {
		return jsExecutor;
	}
	
	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(30, TimeUnit.SECONDS)
	            .pollingEvery(5, TimeUnit.SECONDS)
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
}
