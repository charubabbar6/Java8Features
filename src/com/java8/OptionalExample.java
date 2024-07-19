package com.java8;

import java.util.Optional;

public class OptionalExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String[] str = new String[10];
		// String[] str = null;
	        str[5] = "JAVA OPTIONAL CLASS EXAMPLE";  // Setting value for 5th index
	        Optional<String> checkNull = Optional.ofNullable(str[5]);
	        if(!checkNull.isEmpty()) {
		        checkNull.ifPresent(System.out::println);   // printing value by using method reference
		        System.out.println(checkNull.get());    // printing value by using get method
		        System.out.println(str[5].toLowerCase());
		        }
		else
		{
			System.out.println("string is empty");
		}
	}

}
