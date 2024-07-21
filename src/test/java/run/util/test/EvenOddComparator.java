package run.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer arg0, Integer arg1) {
        int compareInt = -1;
        if((arg0 % 2 == 0) && (arg1 % 2 == 0)) {
            compareInt = Integer.compare(arg0, arg1);
        }
        else if((arg0 % 2 != 0) && (arg1 % 2 != 0)) {
            compareInt = Integer.compare(arg1, arg0);
        }
        else if((arg0 % 2 == 0) && (arg1 % 2 != 0)) {
            compareInt = -1;
        }
        else if((arg0 % 2 != 0) && (arg1 % 2 == 0)) {
            compareInt = 1;
        }
        return compareInt;

    }

}