package com.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Statisticsoperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list=Arrays.asList(5, 13, 4,21, 13, 27,2, 59, 59, 34);
		IntSummaryStatistics result=list.stream().mapToInt(x->x).summaryStatistics();
		System.out.println(result);
		System.out.println(result.getMin());
		
	}

}
