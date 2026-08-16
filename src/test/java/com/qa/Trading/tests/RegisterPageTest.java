package com.qa.Trading.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.Trading.Base.BaseTest;
import static com.qa.Trading.constants.AppConstants.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.qa.Trading.pages.LoginPage;
import com.qa.Trading.utils.CSVUtil;
import com.qa.Trading.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void pageSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object getUserRegTestData() {
		return new Object[][] {
			{"virat", "kohli",  "1818181818", "raju@1234","No"},
			{"Ms", "Dhoni", "0707070707", "dhoni@123" ,"Yes"},
			{"Rohit", "Sharma", "4545454545", "rohit@123" ,"No"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		Object regData[][] = ExcelUtil.getTestData("Sheet1");
		return regData;
	}
	
//	@DataProvider
//	public Object[][] getCSVData(){
//		return CSVUtil.csvData("product");
//	}
	
	@DataProvider
	public Object[][] getCSVData() throws IOException{
		List<Object[]> data = new ArrayList<Object[]>();
		try(BufferedReader br= new BufferedReader(new FileReader("./src/test/resources/TestData/product.csv"))){
			String line;
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				for(int i=0;i<values.length;i++) {
					values[i] = values[i].replace("\"", "").trim();
				}
				data.add(values);
			}
		}
		return data.toArray(new Object[0][]);
	}
	
	@Test(dataProvider = "getCSVData")
	public void userRegistrationTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		boolean isRegister = registerPage.userRegistration(firstName, lastName, telephone, password, subscribe);
		Assert.assertTrue(isRegister);
	}
}
