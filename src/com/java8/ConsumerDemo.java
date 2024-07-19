package com.java8;

import java.util.function.Consumer;

public class ConsumerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Consumer<Integer> consume=i->System.out.println("Taking inuput andd perform operation");
		consume.accept(5);
		Consumer<Integer> consume1=i->System.out.println("Taking inuput andd perform operation1");
		consume1.accept(15);
		consume.andThen(consume1).accept(2);

	}

}
