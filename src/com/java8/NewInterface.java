package com.java8;

public interface NewInterface {

	void singleAbstractmethod();
	default void print() {
		System.out.println("1st Default method of functional Interface");
	}

}
