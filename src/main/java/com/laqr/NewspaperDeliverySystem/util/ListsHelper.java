package com.laqr.NewspaperDeliverySystem.util;

import java.util.Collections;
import java.util.List;

public class ListsHelper {

    public static <T extends Comparable<? super T>> boolean compareLists(List<T> first, List<T> second) {
        if (first == null && second == null)
            return true;

        if (first == null || second == null)
            return false;

        Collections.sort(first);
        Collections.sort(second);
        return first.equals(second);
    }
}
