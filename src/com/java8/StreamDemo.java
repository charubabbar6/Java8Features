package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> arlist=new ArrayList<>();
		arlist.add(10);
		arlist.add(90);
		arlist.add(450);
		arlist.add(15);
		arlist.add(18);
		arlist.add(90);
		//collect //foreach
		 List<Integer> newarlist=new ArrayList<>();
		 newarlist=arlist.stream().filter(x->x>15).collect(Collectors.toList());
		 newarlist.stream().forEach(x->System.out.println(x));
		 //Set
		 System.out.println("collect into set");
		 Set<Integer> newset=new HashSet<>();
		 newset=arlist.stream().filter(x->x>15).collect(Collectors.toSet());
		 newset.stream().forEach(x->System.out.println(x));
		 //count
		 long count=arlist.stream().filter(x->x>15).count();
		 System.out.println("count"+count);
		 //sorted
		 System.out.println("sorted by deafult in ascending order");
		 arlist.stream().sorted().forEach(x->System.out.println(x));
		 System.out.println("sorted in descending order");
		 arlist.stream().sorted((i1,i2)->i2.compareTo(i1)).forEach(x->System.out.println(x));
		 //minmax

		 System.out.println("minmax");
		Integer min= arlist.stream().min((i1,i2)->i1.compareTo(i2)).get();
		System.out.println("min"+min);
		Integer max= arlist.stream().max((i1,i2)->i1.compareTo(i2)).get();
		System.out.println("max"+max);
		//toArray
		System.out.println("New array using toArray");
		Object[] obj=arlist.stream().toArray();
		for(Object newarry :obj) {
			System.out.println(newarry);
		}
		//peek
		System.out.println("Peek");
		arlist.stream().filter(x->x>15).peek(System.out::println).map(x->x*2).collect(Collectors.toSet()).forEach(x->System.out.println(x));
		/*
		 * Stream s=arlist.stream().filter(x->x>15);
		 * s.forEach(x->System.out.println(x));
		 */
		System.out.println("Filtering using stream");
		arlist.stream().filter(x->x>15).forEach(x->System.out.println(x));
		System.out.println("Mapping using stream");
		arlist.stream().map(x->x*x).forEach(x->System.out.println(x));
		//reduce
		System.out.println("Reduce");
		List<Integer> numlist=Arrays.asList(3, 5, 7, 9, 11);
		//both lines are doing same work adding all numbers
		System.out.println(numlist.stream().reduce((a,b)->a+b).get());
		System.out.println(numlist.stream().reduce(Integer::sum).get());
		//limit
		System.out.println("limit");

		numlist.stream().limit(2).collect(Collectors.toList()).forEach(x->System.out.println(x));
		//find first
		System.out.println("findfirst");
		// Using Stream findFirst()
        Optional<Integer> answer = numlist.stream().findFirst();

        // if the stream is empty, an empty
        // Optional is returned.
        if (answer.isPresent()) {
            System.out.println(answer.get());
        }
        else {
            System.out.println("no value");
        }
        //findAny

      		System.out.println("findAny");
      		// Using Stream findFirst()
              Optional<Integer> answer1 = numlist.stream().findAny();

              // if the stream is empty, an empty
              // Optional is returned.
              if (answer1.isPresent()) {
                  System.out.println(answer1.get());
              }
              else {
                  System.out.println("no value");
              }
              System.out.println("findAny Integer using parallel");
              IntStream stream = IntStream.of(4, 5, 8, 10, 12, 16).parallel();

          // Using Stream findAny().
          // Executing the source code multiple times
          // may not return the same result.
          // Every time you may get a different
          // Integer which is divisible by 4.
          stream = stream.filter(i -> i % 4 == 0);

          OptionalInt answer2 = stream.findAny();
          if (answer2.isPresent()) {
              System.out.println(answer2.getAsInt());
          }
          //anyMatch
          System.out.println("anyMatch");
          // Creating a list of Integers
          List<Integer> list = Arrays.asList(3, 4, 6, 12, 20);

          // Stream anyMatch(Predicate predicate)
          boolean answer3 = list.stream().anyMatch(n
                           -> (n * (n + 1)) / 4 == 5);

          // Displaying the result
          System.out.println(answer3);
          System.out.println("anyMatch");
       // Creating a Stream of Strings
          Stream<String> stream1 = Stream.of("geeks", "for",
                                            "geeksd", "geeksh");

          // Check if Character at 1st index is
          // UpperCase in any string or not using
          // Stream anyMatch(Predicate predicate)
          boolean answer4 = stream1.anyMatch(str -> Character.isUpperCase(str.charAt(1)));

          // Displaying the result
          System.out.println(answer4);
          System.out.println("allMatch");
       // Check if all elements of stream
          // are divisible by 3 or not using
          // Stream allMatch(Predicate predicate)
          boolean answer5 = list.stream().allMatch(n-> n % 3 ==0);

          // Displaying the result
          System.out.println(answer5);
          System.out.println("allMatch");
       // Creating a Stream of Strings
          Stream<String> stream2 = Stream.of("Geeks", "for",
                             "GeeksQuiz", "GeeksforGeeks");
       // Check if all elements of stream
          // have length greater than 2 using
          // Stream allMatch(Predicate predicate)
          boolean answer6 = stream2.allMatch(str -> str.length() > 2);

          // Displaying the result
          System.out.println(answer6);

          // To check that there is no string of length 4.
       // Java Program to Illustrate noneMatch() method
       // of Stream class to check whether
       // no elements of this stream match the
       // provided predicate (Predicate predicate)
          System.out.println("noneMatch");
          Stream<String> stream3  = Stream.of("CSE", "C++", "Java", "DS");

      // Now using Stream noneMatch(Predicate predicate)
      // and later storing the boolean answer as
      boolean answer7
          = stream3.noneMatch(str -> (str.length()== 4));

      // Printing the boolean value on the console
      System.out.println(answer7);
      System.out.println("noneMatch");
      //To check that there is no element less than 0.
   // Creating a list of Integers using asList() of
      // Arrays class by declaring object of List
      List<Integer> list1 = Arrays.asList(4, 0, 6, 2);

      // Using Stream noneMatch(Predicate predicate) and
      // storing the boolean value
      boolean answer8
          = list1.stream().noneMatch(num -> num < 0);

      // Printing and displaying the above boolean value
      System.out.println(answer8);
      // To check that there is no element with required characters at required position.
      System.out.println("noneMatch");
   // Creating a Stream of Strings using of() method
      // by creating object of Stream of string type
      Stream<String> stream4
          = Stream.of("Geeks", "fOr", "GEEKSQUIZ",
                      "GeeksforGeeks", "CSe");

      // Using Stream noneMatch(Predicate predicate)
      // and storing the boolean value
      boolean answer9 = stream4.noneMatch(
          str
          -> Character.isUpperCase(str.charAt(1))
                 && Character.isLowerCase(str.charAt(2))
                 && str.charAt(0) == 'f');

      // Printing the above boolean value on console
      System.out.println(answer9);
	}

}
