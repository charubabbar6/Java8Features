package com.java8;

import java.util.function.Function;

public class FunctionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Function<Integer,Integer> square=i->2*i;
		System.out.println(square.apply(2));
		Function<Integer,Integer> cube=i->i*i*i;
		System.out.println(cube.apply(2));
		System.out.println("anthen"+square.andThen(cube).apply(2));
		System.out.println("compose"+square.compose(cube).apply(2));
	}

}
