package run.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import static run.util.Arrays.*;

import java.util.Random;


public class ArraysTest {
private static final int N_ELEMENTS = 1_000;
int [] numbers = {10, 7, 12, -4, 13, 3, 14};
@Test
void searchTest() {
    assertEquals(0,  search(numbers, 10));
    assertEquals(6,  search(numbers, 14));
    assertEquals(3,  search(numbers, -4));
    assertEquals(-1,  search(numbers, 100));
}
@Test
void addTest() {
    int newNumbers = 100;
    int [] expected = {10, 7, 12, -4, -13, 3, 14, 100};
    assertArrayEquals(expected, add(numbers, newNumbers));
}
@Test
void insertTest() {
    int newNumber = 50;
    int newIndex1 = 0;   
    int [] expected1 = {50, 10, 7, 12, -4, -13, 3, 14};
    assertArrayEquals(expected1, insert(numbers, newIndex1, newNumber));
    int newIndex2 = 4;   
    int [] expected2 = {10, 7, 12, -4, 50, -13, 3, 14};
    assertArrayEquals(expected2, insert(numbers, newIndex2, newNumber));
    int newIndex3 = 6;   
    int [] expected3 = {10, 7, 12, -4, -13, 3, 50, 14};
    assertArrayEquals(expected3, insert(numbers, newIndex3, newNumber));
    int newIndex4 = 7;   
    int [] expected4 = {10, 7, 12, -4, -13, 3, 14, 50};
    assertArrayEquals(expected4, insert(numbers, newIndex4, newNumber));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> insert(numbers, 20, newNumber));
}
@Test
void removeTest() {
    int [] expected1 = {7, 12, -4, -13, 3, 14};
    assertArrayEquals(expected1, remove(numbers, 0));
    int [] expected2 = {10, 7, 12, -4, 3, 14};
    assertArrayEquals(expected2, remove(numbers, 4));
    int [] expected3 = {10, 7, 12, -4, -13, 3};
    assertArrayEquals(expected3, remove(numbers, 6));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> remove(numbers, 10));
}
@Test
void sortTest() {
    int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
    sort(testNumbers);
    int [] expected = {-4, 3, 7, 10, 12, 13, 14};
    assertArrayEquals(expected, testNumbers);
}
@Test
void sortTestRandomArray() {
    int[] array = getRandomArray(N_ELEMENTS);
    int limit = array.length - 1;
    for(int i = 0; 1 < limit; i++){
        assertTrue(array[i] <= array[i + 1]);
    }
}
private int[] getRandomArray(int nElements) {
    int[] res = new int[nElements];
    Random random = new Random();
    for(int i = 0; i < nElements; i++) {
        res[i] = random.nextInt();
    }
    return res;
}
}
