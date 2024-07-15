package run.util;

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
        sort(ar);
        int flIndex = -1;
        int lower = 0;
        int upper = ar.length - 1;
        while (lower <= upper) {
            int middle = lower + ((upper - lower) / 2);
            if (ar[middle] == key) {
                upper = lower - 1;
                flIndex = middle;
            } else if (ar[middle] < key && middle == 0 && upper == lower) {
                upper = middle - 1;
                flIndex = -middle - 2;
            } else if (ar[middle] < key && middle == ar.length - 1 && upper == lower) {
                lower = middle + 1;
                flIndex = -middle - 2;
            } else if (ar[middle] < key) {
                lower = middle + 1;
                flIndex = -middle - 2;
            } else if (ar[middle] > key) {
                upper = middle - 1;
                flIndex = -middle - 1;
            }
        }
        return flIndex;
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
                }
            }
        }
        return flOut;
    }
}
