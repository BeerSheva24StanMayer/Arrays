package run.util;

import java.security.Key;
import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays {
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

    public static int[] insert(int[] ar, int index, int number) {
        int[] arNew = new int[ar.length + 1];
        arNew[index] = number;
        System.arraycopy(ar, 0, arNew, 0, index);
        System.arraycopy(ar, index, arNew, index + 1, ar.length - index);
        return arNew;
    }

    public static int[] remove(int[] numbers, int index) {
        int[] numbersNew = new int[numbers.length - 1];
        System.arraycopy(numbers, 0, numbersNew, 0, index);
        System.arraycopy(numbers, index + 1, numbersNew, index, numbers.length - index - 1);
        return numbersNew;
    }

    public static boolean pushMaxAtTheEnd(int[] ar, int length) {
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
            flSorted = pushMaxAtTheEnd(ar, length);
        }
    }

    public static int binarySearch(int[] ar, int key) {
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
        int flIndex = binarySearch(arSorted, number);
        if (-flIndex == arSorted.length) {
            flIndex = -flIndex;
        } else if (flIndex < 0) {
            flIndex = -flIndex - 1;
        } else {
            flIndex += 1;
        }
        int[] arNew = java.util.Arrays.copyOf(arSorted, arSorted.length + 1);
        System.arraycopy(arSorted, flIndex, arNew, flIndex + 1, arSorted.length - flIndex);
        arNew[flIndex] = number;
        return arNew;
    }

    public static boolean isOneSwap(int[] array) {
        int j = -1;
        int k = -1;
        int i = 0;
        boolean flOut = true;
        while ((i < array.length - 1) && (j < 0 || k < 0)) {

            if (array[i] > array[i + 1]) {
                if (j < 0) {
                    j = i;
                } else {
                    k = i + 1;
                }
            }
            i++;
        }
        if (j >= 0) {
            if (k < 0) {
                k = j + 1;
            }
        } else if (j < 0) {
            flOut = false;
        }
        if (flOut == true) {
            int[] testArray = java.util.Arrays.copyOf(array, array.length);
            swap(testArray, j, k);
            for (int b = 0; b < testArray.length - 2; b++) {
                if (testArray[b] > testArray[b + 1]) {
                    flOut = false;
                    break;
                }
            }
        }
        return flOut;
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
        int indexH = array.length - 1;
        int indexL = 0;
        int indexM = (indexH + indexL) / 2;
        int comRes = 0;
        while (indexL <= indexH && (comRes = comp.compare(array[indexM], key)) != 0) {
            if (comRes > 0) {
                indexH = indexM - 1;
            } else {
                indexL = indexM + 1;
            }
            indexM = (indexH + indexL) / 2;
        }
        return indexL > indexH ? -(indexL + 1) : indexM;
    }

    public static <T> int binarySearch(T[] array, T key) {
        // TODO
        // The code should be based on binarySearch
        int indexH = array.length - 1;
        int indexL = 0;
        int indexM = (indexH + indexL) / 2;
        int comRes = 0;
        while (indexL <= indexH && (comRes = -(((Comparable) key).compareTo(array[indexM]))) != 0) { // I know it is not right, still on my way to figure out to get the right solution
            if (comRes > 0) {
                indexH = indexM - 1;
            } else {
                indexL = indexM + 1;
            }
            indexM = (indexH + indexL) / 2;
        }
        return indexL > indexH ? -(indexL + 1) : indexM;
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
}
