package run.util;

public class Arrays {
public static int search(int[] ar, int key){
    int index = 0;
    while(index < ar.length && key != ar[index]){
        index++;
    }
    return index == ar.length ? - 1 : index;
}
public static int[] add(int [] ar, int number){
    int [] res = java.util.Arrays.copyOf(ar, ar.length + 1);
    res[ar.length] = number;
    return res;
}
public static int[] insert(int[] ar, int index, int number) {
    int [] arNew = new int[ar.length + 1];
    arNew[index] = number;
    System.arraycopy(ar, 0, arNew, 0, index);
    System.arraycopy(ar, index , arNew, index + 1, ar.length - index);
    return arNew; 
}

public static int[] remove(int[] numbers, int index) {
    int [] numbersNew = new int[numbers.length - 1];
    System.arraycopy(numbers, 0, numbersNew, 0, index);
    System.arraycopy(numbers, index + 1 , numbersNew, index, numbers.length - index -1);
    return numbersNew; 
}
public static boolean pushMaxAtTheEnd(int [] ar, int length) {
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

public static void sort(int [] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while(!flSorted) {
            length--;
            flSorted = pushMaxAtTheEnd(ar, length);
        }
    }

    public static int binarySearch(int [] ar, int key) {
        sort(ar);
        int flIndex = -1;
        int lower = 0;
        int upper = ar.length - 1;
        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            if (ar[middle] == key) {
                upper = lower - 1;
                flIndex = middle;
            }
            else if (ar[middle] < key) {
                lower = middle + 1;
            } 
            else {
                upper = middle - 1;
            } 
        }
        return flIndex;      
    }
    public static int[] insertSorted(int[] arSorted, int number) {
        int flIndex = - 1;
        int lower = 0;
        int upper = arSorted.length - 1;
        while (lower <= upper) {
            int middle = lower + (upper - lower) / 2;
            if (arSorted[middle] == number) {
                upper = lower - 1;
                flIndex = middle + 1;
            }
            else if (arSorted[middle] < number) {
                lower = middle + 1;
                flIndex = middle + 1;
            } 
            
            else {
                upper = middle - 1;
                flIndex = middle;
            } 
        }
        int [] arNew = java.util.Arrays.copyOf(arSorted, arSorted.length + 1);
        System.arraycopy(arSorted,flIndex , arNew, flIndex + 1, arSorted.length - flIndex);
        arNew[flIndex] = number;
        return arNew;
    }
    public static boolean isOneSwap(int [] array) {
        boolean flSorted = false;
        int[] arNonSorted = java.util.Arrays.copyOf(array, array.length);
        sort(array);
        int counter = 0;
        for(int i = 0; i < arNonSorted.length; i++) {
            if(arNonSorted[i] != array[i]) {
                counter++;
            }
        }
        if(counter == 2) {
            flSorted = true;
        }
        return flSorted;
    }
       
}