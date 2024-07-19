package com.java8;

import java.util.Optional;

public class OptionalExampleWithCondition {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating an Optional with a non-null value
        Optional<String> nonNullOptional = Optional.ofNullable("Hello, World!");

        // Using orElse to provide a default value if Optional is empty
        String result = nonNullOptional.orElse("Default Value");
        System.out.println(result); // Output: Hello, World!

        // Creating an Optional with a null value
        Optional<String> nullOptional = Optional.ofNullable(null);

        // Using orElse to provide a default value if Optional is empty
        String resultWithDefault = nullOptional.orElse("Default Value");
        System.out.println(resultWithDefault); // Output: Default Value
	}

}
