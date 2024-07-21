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
    sort(array);
    for(int i = 0; i < limit; i++) {
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
@Test
void binSearchTest() {
    assertEquals(3, binarySearch(numbers, 10));
    assertEquals(5, binarySearch(numbers, 13));
    assertEquals(1, binarySearch(numbers, 3));
    assertEquals(0, binarySearch(numbers, -4));
    assertEquals(6, binarySearch(numbers, 14));
    assertEquals(-1, binarySearch(numbers, -100));
    assertEquals(-2, binarySearch(numbers, 0));
    assertEquals(-5, binarySearch(numbers, 11));  
}
@Test
void insertSortedTest() {
int [] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
sort(testNumbers);
int number1 = -10;
int [] expected1 = {number1, -4, 3, 7, 10, 12, 13, 14};
// assertEquals(0, insertSorted(testNumbers, number1));
assertArrayEquals(expected1, insertSorted(testNumbers, number1));
int number2 = 4;
int [] expected2 = {-4, 3, number2, 7, 10, 12, 13, 14};
assertArrayEquals(expected2, insertSorted(testNumbers, number2));
int number3 = 10;
int [] expected3 = {-4, 3, 7, 10, number3, 12, 13, 14};
assertArrayEquals(expected3, insertSorted(testNumbers, number3));
int number4 = 11;
int [] expected4 = {-4, 3, 7, 10, number4, 12, 13, 14};
assertArrayEquals(expected4, insertSorted(testNumbers, number4));
int number5 = 14;
int [] expected5 = {-4, 3, 7, 10, 12, 13, 14, number5};
assertArrayEquals(expected5, insertSorted(testNumbers, number5));
int number6 = 20;
int [] expected6 = {-4, 3, 7, 10, 12, 13, 14, number6};
assertArrayEquals(expected6, insertSorted(testNumbers, number6));
}
@Test
void isOneSwapTest() {
    int[] arSwap1 = {-10, 20, 30, 9, 15, 18, 45, 400};
    assertEquals(false, isOneSwap(arSwap1));
    int[] arSwap2 = {-10, 9, 15, 18, 20, 30, 45, 400};
    assertEquals(false, isOneSwap(arSwap2));
    int[] arSwap3 = {9, -10, 15, 18, 20, 30, 45, 400};
    assertEquals(true, isOneSwap(arSwap3));
    int[] arSwap4 = {-10, 9, 15, 18, 20, 30, 400, 45};
    assertEquals(true, isOneSwap(arSwap4));
    int[] arSwap5 = {-10, 9, 18, 15, 20, 30, 45, 400};
    assertEquals(true, isOneSwap(arSwap5));
    int[] arSwap6 = {-10, 45, 15, 18, 20, 30, 9, 400};
    assertEquals(true, isOneSwap(arSwap6));
    int[] arSwap7 = {18, 15, -10, 9, 45, 30, 20, 400};
    assertEquals(false, isOneSwap(arSwap7));
}
@Test
void sortAnyTypeTest() {
    String [] strings = {"lmn", "cfta", "w", "aa"};
    String [] expectedASCII = {"aa", "cfta", "lmn", "w"};
    String [] expectedLength = {"w", "aa", "lmn", "cfta"};
    sort(strings, new ComparatorASCII());
    assertArrayEquals(expectedASCII, strings);
    sort(strings, new ComparatorLength());
    assertArrayEquals(expectedLength, strings);
}
@Test
void searchAnyTypeTest() {
    String [] strings = {"lmn", "cfta", "w", "aa"};
    Integer[] numbers = {10, 50, 20, 40, 30};
    sort(strings, new ComparatorASCII());
    assertEquals(0, binarySearch(strings, "aa", new ComparatorASCII()));
    assertEquals(1, binarySearch(strings, "cfta", new ComparatorASCII()));
    assertEquals(3, binarySearch(strings, "w", new ComparatorASCII()));
    assertEquals(-1, binarySearch(strings, "a", new ComparatorASCII()));
    assertEquals(-2, binarySearch(strings, "cft", new ComparatorASCII()));
    assertEquals(-4, binarySearch(strings, "v", new ComparatorASCII()));
    assertEquals(-5, binarySearch(strings, "y", new ComparatorASCII()));
    sort(numbers, new ComparatorInteger());
    assertEquals(0, binarySearch(numbers, 10, new ComparatorInteger()));
    assertEquals(2, binarySearch(numbers, 30, new ComparatorInteger()));
    assertEquals(4, binarySearch(numbers, 50, new ComparatorInteger()));
    assertEquals(-1, binarySearch(numbers, -10, new ComparatorInteger()));
    assertEquals(-2, binarySearch(numbers, 15, new ComparatorInteger()));
    assertEquals(-5, binarySearch(numbers, 45, new ComparatorInteger()));
    assertEquals(-6, binarySearch(numbers, 60, new ComparatorInteger()));
}
@Test
void binarySearchNoComparatorTest() {
    String [] strings = {"aa", "cfta", "lmn", "w"};
    Person prs1 = new Person(10, "Vasya");
    Person prs2 = new Person(20, "Itay");
    Person prs3 = new Person(30, "Sara");
    Person [] persons = {prs1, prs2, prs3};
    // assertEquals(1, java.util.Arrays.binarySearch(strings, "cfta"));
    // assertEquals(0, java.util.Arrays.binarySearch(persons, prs1));
    assertEquals(-1, binarySearch(persons, new Person(5, "Sara")));
    assertEquals(-4, binarySearch(persons, new Person(40, "Serg")));
}

@Test
void evenOddSorting() {
    Integer[] array = {7, -8, 10, -100, 13, -10, 99};
    Integer[] expected = {-100, -10, -8, 10, 99, 13, 7}; //even numbers in ascending order first, odd numbers in descending order after that
    sort(array, new EvenOddComparator());
    assertArrayEquals(expected, array);
}
@Test
void removeIfTest() {
    Integer[] array = {7, -8, 10, -100, 13, -10, 99};
    Integer [] expected = {-8, 10, -100, -10};
    assertArrayEquals(expected, removeIf(array, new OddNumbersPredicate()));
    Integer [] expected1 = {7, 13, 99};
    assertArrayEquals(expected1, removeIf(array, new EvenNumbersPredicate()));
}
@Test
void findTest() {
    Integer[] array = {7, -8, 10, -100, 13, -10, 99};
    Integer [] expected = {7, 13, 99};
    assertArrayEquals(expected, find(array, new OddNumbersPredicate()));
}
}

