package com.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DuplicateInteger {



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Find the duplicate elements in integer listin java using stream function
		List<Integer> list=Arrays.asList(5, 13, 4,21, 13, 27,2, 59, 59, 34);
		List<String> strlist=Arrays.asList("charu","arjun","sushant","arjun");
		Set<Integer> set=new HashSet<>();
		Set<String> strset=new HashSet<>();
		list.stream().filter(x->!set.add(x)).forEach(x->System.out.println(x));
		strlist.stream().filter(x->!strset.add(x)).forEach(x->System.out.println(x));
		Map<String,Long> mapname=strlist.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(mapname);
		Set<String> names=mapname.entrySet().stream().filter(entry->entry.getValue()>1).map(entry->entry.getKey()).collect(Collectors.toSet());
			System.out.println("names"+names);
			Set<String> strnewset= strlist.stream().filter(i->Collections.frequency(strlist, i)>1).collect(Collectors.toSet());
			System.out.println(strnewset);
	}

}
