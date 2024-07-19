package com.java8;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

public static String reverseString(String s) {
	//Reverse of the String
			String reverseStr = Stream.of(s)
					.map(string -> new StringBuilder(string).reverse())
					.collect(Collectors.joining());
			return reverseStr;

}
public static void fetchduplicate(List<Integer> i)
{
	//Elements which are duplicate
	Set<Integer> set=new HashSet<>();
	 System.out.println("Elements which are duplicate");

	 //Elements which are duplicate
	 i.stream().filter(x->!set.add(x)).forEach(x->System.out.println(x));
}


	public static void fetchSecondSmallest(int[] arr)
	{
		int secondsmallelement=Arrays.stream(arr).sorted().skip(1).findFirst().orElseThrow(()->new IllegalArgumentException("Array does't have second smallest elemnt"));

		System.out.println("secondsmallelement"+secondsmallelement);

	}
	public static void fetchSecondSmallestduplist(int[] arr)
	{
		int secondsmallelementdup=Arrays.stream(arr).distinct().skip(1).findFirst().orElseThrow(()->new IllegalArgumentException("Array does't have second smallest elemnt"));
		System.out.println("secondsmallelement with duplicate elemnts"+secondsmallelementdup);
	}

	public static void fetchcommonelemnt(int[] arr1,int[] arr2)
	{

		List<Integer> commonelements=Arrays.stream(arr1).filter(number->Arrays.stream(arr2).anyMatch(arrnum->arrnum == number)).boxed().collect(Collectors.toList());
		System.out.println(commonelements);
	}
	public static void fetchreversenumber(int[] arr)
	{
		IntStream.range(0,arr.length/2)	.forEach(i->{
			int temp=arr[i];
			arr[i]=arr[arr.length-i-1];
			arr[arr.length-i-1]=temp;
		});
		 System.out.println("Reversed Array = " + Arrays.toString(arr));
	}
	public static void fetchlengthoflongeststring(String[] arr)
	{
		int maxlength=Arrays.stream(arr).mapToInt(string->string.length()).peek(System.out::println).max().orElse(0);
		System.out.println("Maximum length in  Array = "+maxlength);
	}
	public static void removeduplicateString(String[] arr)
	{
		Arrays.stream(arr).distinct().forEach(x->System.out.println(x));

	}
	public static void productArray(int[] arr)
	{
		int[] ints = Arrays.stream(arr)
                .map(s -> Arrays.stream(arr).filter(x -> x != s).reduce(1, (x, y) -> x * y))
                .toArray();
		System.out.println("product"+Arrays.toString(ints));

	}
		public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String s = "Charu";
	 List<Integer> i=Arrays.asList(1,1,2,2,3,5,5);
	 int[] numbers= {5,2,8,3,1};
	 int[] dupnumbers= {5,2,8,3,1,1};
	 int[] array1= {1,2,3,4,5};
	 int[] array2= {4,5,6,7,8};
	 int[] numbers1= {1,2,3,4,5};
	 String[] strings= {"Apple","Bananna","Apple","Avocado","Apricot","Grapes"};
	 int[] number2= {1,2,3,4};//o/p:[24,12,8,6]
	 System.out.println("Reversed String = " +  reverseString(s));
	 fetchduplicate(i);
	 fetchSecondSmallest(numbers);
	 fetchSecondSmallestduplist(dupnumbers);
	 fetchcommonelemnt(array1,array2);
	 fetchreversenumber(numbers1);
	 fetchlengthoflongeststring(strings);
	 removeduplicateString(strings);
	 productArray(number2);
	}

}
