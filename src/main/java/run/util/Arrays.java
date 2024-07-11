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
}