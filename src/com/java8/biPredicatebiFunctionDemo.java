package com.java8;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class biPredicatebiFunctionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BiPredicate<Integer,Integer>  checksumoftwo= (a,b)->a+b >= 5;
		System.out.println(checksumoftwo.test(1, 3));
		BiFunction<Integer,Integer,Integer> multiplyboth=(a,b)->a*b;
		System.out.println(multiplyboth.apply(10, 2));
	}

}
