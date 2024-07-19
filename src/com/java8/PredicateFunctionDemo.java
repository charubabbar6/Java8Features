package com.java8;

import java.util.function.Predicate;

public class PredicateFunctionDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Predicate<String> checklen=s->s.length()>=5;
		System.out.println(checklen.test("Java is Functional programminvg"));
		Predicate<String> checkevenlen=s->s.length()%2==0;
		System.out.println(checkevenlen.test("Java is Functional programminvg"));
		System.out.println("Merging with and"+checklen.and(checkevenlen).test("Java is Functional programminvg"));
		System.out.println("Merging with or"+checklen.or(checkevenlen).test("Java is Functional programminvg"));
		System.out.println("Merging with negate"+checklen.negate().test("Java is Functional programminvg"));
	}

}
