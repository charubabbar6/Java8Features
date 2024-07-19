package com.java8;

public class MethodReferenceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 FunctionalInterfaceDemo fd=Test::testImplementation;
		 fd.singleAbstractmethod();


		//If no testImplementation is available in existing codebase then use following code for lambda expression
		/*
		 * FunctionalInterfaceDemo
		 * f=()->System.out.println("Implementation of single abstract method");
		 * f.singleAbstractmethod();
		 */
	}

}
