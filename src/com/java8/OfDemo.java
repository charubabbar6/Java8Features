package com.java8;

import java.util.stream.Stream;

public class OfDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream.of(1,11,111,1111,11111).forEach(x->System.out.println(x));
		String[] name= {"charu","Arjun","Sushant"};
		Stream.of(name).filter(x->x.length()==5).forEach(x->System.out.println(x));
	}

}
