package com.java8.GroupingBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectStreamItemsToMap {

    public static void main(String[] args) {

        // 1 A single map key mapped to a single value
        Stream<Item> stream = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3")
        );

        Map<Integer, Item> map = stream
                .collect(Collectors.toMap(Item::getItemid, Function.identity()));

        System.out.println(map);

        stream = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3")
        );

        Map<Integer, String> mapWithValue = stream
                .collect(Collectors.toMap(Item::getItemid, Item::getItemname));

        System.out.println(mapWithValue);

        stream = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3")
        );

        LinkedHashMap<Integer, String> mapWithValueInInsertionOrder = stream
                .collect(Collectors.toMap(Item::getItemid, Item::getItemname, (o, n) -> n, LinkedHashMap::new));

        System.out.println(mapWithValueInInsertionOrder);

        // 2 A single map key mapped to multiple values

        Stream<Item> streamWithDuplicates = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3-1"),
                new Item(3, "Item3-2"),
                new Item(3, "Item3-3")
        );

        Map<Integer, Item> mapWithGrouping = streamWithDuplicates
                .collect(Collectors.toMap(Item::getItemid, Function.identity(), (o, n) -> n));

        System.out.println(mapWithGrouping);

        streamWithDuplicates = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3-1"),
                new Item(3, "Item3-2"),
                new Item(3, "Item3-3")
        );

        Map<Integer, List<Item>> mapWithGroupingNew = streamWithDuplicates
                .collect(Collectors.groupingBy(Item::getItemid));

        System.out.println(mapWithGroupingNew);

        streamWithDuplicates = Stream.of(
                new Item(1, "Item1"),
                new Item(2, "Item2"),
                new Item(3, "Item3-1"),
                new Item(3, "Item3-2"),
                new Item(3, "Item3-3")
        );

        Map<Integer, List<String>> mapWithGroupedValues = streamWithDuplicates
                .collect(
                        Collectors.groupingBy(Item::getItemid, Collectors.mapping(Item::getItemname, Collectors.toList())));

        System.out.println(mapWithGroupedValues);
    }
}
