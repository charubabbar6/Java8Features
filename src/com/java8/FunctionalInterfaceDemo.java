package com.java8;

@FunctionalInterface
public interface FunctionalInterfaceDemo {
	void singleAbstractmethod();
	default void print() {
		System.out.println("1st Default method of functional Interface");
	}
	default void print1() {
		System.out.println("2nd Default method of functional Interface");
	}
}
