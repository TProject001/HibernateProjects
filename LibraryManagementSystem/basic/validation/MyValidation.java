package com.library.basic.validation;

import java.util.regex.Pattern;

public class MyValidation {

	public Boolean isNameValid(String userName1) {
	//	System.out.println("name : "+userName1.trim()+" ");
		return Pattern.compile("[a-zA-Z ]{3,50}").matcher(userName1.trim()).matches();
		
	}
	public Boolean isAddressValid(String userAddress1) {
		return Pattern.compile("(((\\d)+)?(\\s)+)?([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)").matcher(userAddress1.trim()).matches();
	}
	public Boolean isEmailIdValid(String userEmailId1) {
		return Pattern.compile("[a-zA-Z0-9]+(?:(?:[._-][a-zA-Z0-9]+)*@[A-Za-z-]+\\.[a-z]{2,3})").matcher(userEmailId1.trim()).matches();
	}
	public Boolean isIndianPhoneNumberValid(String userPhoneNumber1) {
		return Pattern.compile("^\\+?(0|91)?\\s?[6-9][0-9]{9}").matcher(userPhoneNumber1.trim()).matches();
	}
	public Boolean isPasswordValid(String userPassword1) {
		return Pattern.compile("^(?=.*[!@#%^&*()_+-=\\[\\]{}|;':\\\",./<>?~`])(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{4,30}$").matcher(userPassword1.trim()).matches();
	}
	public Boolean isIdNumeric(String id) {
		return Pattern.compile("[0-9]+{1,6}").matcher(id.trim()).matches();
	}
	public Boolean isBookValid(String bookName) {
		return (bookName.trim().length()>0);
	}
}