package run.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {
    public static final String DELIMETER = ";";

    public static int search(int[] ar, int key) {
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] ar, int number) {
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = number;
        return res;
    }

    /**
     * 
     * @param ar
     * @param index
     * @param number
     * @return reference to a new array containing @param number at @param index
     */
    public static int[] insert(int[] ar, int index, int number) {
        // creates new array with all elements from the given "ar" and
        // the given "number" at the given index
        // to apply System.arraycopy method
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        System.arraycopy(ar, index, res, index + 1, ar.length - index);
        res[index] = number;
        return res;
    }

    /**
     * 
     * @param numbers
     * @param index
     * @return new array with no removed from @param numbers number at @param index
     */
    public static int[] remove(int[] numbers, int index) {
        // creates new array with no element in "numbers" at "index"
        // to apply System.arraycopy method
        int[] res = java.util.Arrays.copyOf(numbers, numbers.length - 1);
        System.arraycopy(numbers, index + 1, res, index, res.length - index);
        return res;

    }

    private static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = true;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = false;
                swap(ar, i, i + 1);
            }
        }
        return res;
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = pushMaxAtEnd(ar, length);
        }
    }

    /**
     * 
     * @param ar  - sorted array
     * @param key - being searched number
     * @return see comments definition
     */
    public static int binarySearch(int[] ar, int key) {
        // index of the search key, if it is contained in the array;
        // otherwise, (-(insertion point) - 1).
        // The insertion point is defined as the point at which the key would be
        // inserted into
        // the array: the index of the first element greater than the key, or a.length
        // if all elements in the array are less than the specified key. Note that this
        // guarantees that the return value will be >= 0 if and only if the key is
        // found.
        int left = 0;
        int right = ar.length - 1;
        int middle = (left + right) / 2;
        while (left <= right && ar[middle] != key) {
            if (key < ar[middle]) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) / 2;
        }
        return left > right ? -(left + 1) : middle;
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        // arSorted is sorted array
        // to insert number at index to keep the array sorted
        // additional sorting is disallowed
        int index = binarySearch(arSorted, number);
        if (index < 0) {
            index = -index - 1;
        }
        return insert(arSorted, index, number);
    }

    public static boolean isOneSwap(int[] array) {
        // return true if a given array has exactly one swap to get sorted array
        // the swaped array's elements may or may not be neighbors
        boolean res = false;
        int index1 = -1;
        int index2 = 0;
        index1 = getFirstIndex(array);
        if (index1 > -1) {
            index2 = getSecondIndex(array, index1);
            res = isOneSwapCheck(array, index1, index2);
        }
        return res;

    }

    private static boolean isOneSwapCheck(int[] array, int index1, int index2) {
        swap(array, index1, index2);
        boolean res = isArraySorted(array);
        swap(array, index2, index1); // array restored after swap
        return res;
    }

    private static boolean isArraySorted(int[] array) {
        int index = 1;
        while (index < array.length && array[index] >= array[index - 1]) {
            index++;
        }
        return index == array.length;
    }

    private static int getSecondIndex(int[] array, int index1) {
        int index = array.length - 1;
        int lowBorder = index1 + 1;
        while (index > lowBorder && array[index] >= array[index1]) {
            index--;
        }

        return index;

    }

    private static int getFirstIndex(int[] array) {
        int index = 0;
        int limit = array.length - 1;
        while (index < limit && array[index] <= array[index + 1]) {
            index++;
        }
        return index == limit ? -1 : index;
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        boolean flSort = false;
        do {
            length--;
            flSort = true;
            for (int i = 0; i < length; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    flSort = false;
                }
            }
        } while (!flSort);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;

    }

    public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
        int left = 0;
        int right = array.length - 1;
        int middle = (left + right) / 2;
        int compRes = 0;
        while (left <= right && (compRes = comp.compare(key, array[middle])) != 0) {
            if (compRes < 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) / 2;
        }
        return left > right ? -(left + 1) : middle;

    }

    @SuppressWarnings("unchecked")
    public static <T> int binarySearch(T[] array, T key) {
        // The code should be based on binarySearch with comparator
        return binarySearch(array, key, (Comparator<T>) Comparator.naturalOrder());
    }

    public static <T> T[] insert(T[] array, int index, T item) {
        T[] res = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, res, index + 1, array.length - index);
        res[index] = item;
        return res;
    }

    public static <T> T[] find(T[] array, Predicate<T> predicate) {
        T[] result = java.util.Arrays.copyOf(array, 0);
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i])) {
                result = insert(result, result.length, array[i]);
            }
        }
        return result;
    }

    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {

        return find(array, predicate.negate());
    }

    /**
     * 
     * @param chars         - array of char primitives
     * @param mustBeRules   - array of rules that must be true
     * @param mustNotBeRule array of rules that must be false
     * @return empty error message if array of chars matches all rules otherwise
     *         specific error message saying what rules don't match
     */
    public static String matchesRules(char[] chars,
            CharacterRule[] mustBeRules, CharacterRule[] mustNotBeRules) {
                clearRules(mustBeRules);
                clearRules(mustNotBeRules);
        for (int i = 0; i < chars.length; i++) {
            updateRules(chars[i], mustBeRules);
            updateRules(chars[i], mustNotBeRules);
        }
        String errorMessagesMustBe = getErrorMessages(mustBeRules, true);
        String errorMessagesMustNotBe = getErrorMessages(mustNotBeRules, false);
        return errorMessagesMustBe + (errorMessagesMustBe.isEmpty() || errorMessagesMustNotBe.isEmpty() ? "" : DELIMETER) + errorMessagesMustNotBe;
    }

    private static void clearRules(CharacterRule[] rules) {
        for(int i = 0; i < rules.length; i++) {
            rules[i].flag = false;
        }
    }

    private static String getErrorMessages(CharacterRule[] rules, boolean flag) {
        CharacterRule[] errorRules = find(rules, r -> r.flag != flag);
        String res = "";
        if (errorRules.length != 0) {
            res = errorRules[0].errorMessage;
            for (int i = 1; i < errorRules.length; i++) {
                res += DELIMETER + errorRules[i].errorMessage;
            }
        }
        return res;
    }

    private static void updateRules(char c, CharacterRule[] rules) {
        for (int i = 0; i < rules.length; i++) {
            if(rules[i].predicate.test(c)) {
                rules[i].flag = true;
            }
        }
    }
}