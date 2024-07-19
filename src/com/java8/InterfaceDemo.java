package com.java8;


public interface InterfaceDemo {
	//void printnameABS();
	default void printName() {
		System.out.println("Default method of Interface");
	}
	//Note : A default method cannot override a method from java.lang.Object
	/*
	 * default int hashCode() { return 2*6; }
	 */
}
