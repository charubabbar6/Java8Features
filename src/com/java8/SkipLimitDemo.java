package com.java8;

import java.util.Arrays;
import java.util.List;

public class SkipLimitDemo {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list=Arrays.asList(5, 13, 4,21, 13, 27,2, 59, 59, 34);
		System.out.println("Limit");
		list.stream().limit(3).forEach(x->System.out.println(x));
		System.out.println("skip");
		list.stream().skip(2).forEach(x->System.out.println(x));
	}

}
