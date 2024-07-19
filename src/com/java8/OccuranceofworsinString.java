package com.java8;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OccuranceofworsinString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s="Welcome to my home and my home Welcome you Welcome";
		Map<String,Integer> mp=new TreeMap<>();
		String[] snew=s.split(" ");
		List<String> list=Arrays.asList(snew);
		 for (String element : snew) {

		 if(mp.containsKey(element))
		 {
			 System.out.println(element+"The Value is: " + mp.get(element)+1);
			 mp.put(element, mp.get(element)+1);
		 }
		 else
		 {
			 System.out.println(element+"The Value is: " + 1);
			 mp.put(element,1);
		 }
		}
		 for(Map.Entry<String,Integer> entry:
             mp.entrySet())
		 {
			 System.out.println(entry.getKey()+
	                   " - "+entry.getValue());
		 }
//new
		 Map<String,Long> map=list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		 System.out.print(map);
			/*
			 * Function<String,String> f=Function.identity();
			 * System.out.println(f.apply("Hi"));
			 */
	}
}

