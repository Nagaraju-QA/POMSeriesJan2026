package com.qa.Trading.constants;

import java.util.List;

public class AppConstants {

	public static final int DEFAULT_TIME = 5;
	public static final int MED_DEFAULT_TIME = 10;
	public static final int LONG_DEFAULT_TIME = 15;

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";

	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_FRACTION_URL = "route=account/account";

	public static List<String> expHeadersList = List.of("My Account", "My Orders", "My Affiliate Account", "Newsletter");
	
	public static final String SEARCH_RESULTS_PAGE_FRACTION_TITLE = "Search";
	public static final String SEARCH_RESULTS_PAGE_FRACTION_URL = "route=product/search";
	
	public static final String REGISTER_ACCOUNT_PAGE_TITLE = "Register Account";
	
	public static final String FORGOTTEN_PASSWORD_PAGE_TITLE = "Forgot Your Password?";
	
	public static final String EXPECTED_FRACTION_SUCCESS_MSG = "Success: You have added";
	
	public static final String REGISTER_SUCCESS_MSG = "Your Account Has Been Created!";
}
