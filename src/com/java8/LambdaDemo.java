package com.java8;
import java.util.Comparator;
import java.util.function.BiConsumer;

public class LambdaDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BiConsumer<Integer, Integer> biconsumer=(a,b)->System.out.println(a+b);
		biconsumer.accept(10, 20);
		Comparator<String> c=(s1,s2)->s1.compareTo(s2);
		int comp=c.compare("charu", "charu");
		System.out.println("comp"+comp);

	}

}
