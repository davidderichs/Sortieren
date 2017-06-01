package de.bht.hochschule.algorithmen.s814614.Aufgaben.DoppeltVerketteteListe;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.ListIterator;

/**
 * Die Klasse Test nutzt Junit Version 3.
 * Sie testet die Klasse LinkedList.
 * Created by dave_digger on 17.12.2016.
 */

public class TestLinkedList extends TestCase{

    /**
     * Erstellt eine TestSuite ts1 und fuegt dieser Test-Methoden hinzu.
     * @return TestSuite ts1
     */
    static public Test suite(){
        p("++++++++++++++ Methods included in this Test: +++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                        " contains(), addAll(Collection c), add(int index, T value), add(T value), contains(T value)\n" +
                        " indexOf(T value), remove(T value), remove(int index), get(int index), size(), clear() \n" +
                        " iterator(), next(), hasNext(), previous(), hasPrevious()\n"+
                        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
        );
        TestSuite ts1 = new TestSuite("LinkedList");
        ts1.addTest(new TestLinkedList("testAddNull"));
        ts1.addTest(new TestLinkedList("testAddNullCollection"));
        ts1.addTest(new TestLinkedList("testAddSingleValueToEmptyList"));
        ts1.addTest(new TestLinkedList("testAddCollection"));
        ts1.addTest(new TestLinkedList("testInsertValueAtFalsePosition"));
        ts1.addTest(new TestLinkedList("testInsertValueAtCorrectPosition"));
        ts1.addTest(new TestLinkedList("testInsertSubCollection"));
        ts1.addTest(new TestLinkedList("testRemoveSingleValue"));
        ts1.addTest(new TestLinkedList("testGetSingleValue"));
        ts1.addTest(new TestLinkedList("testGetSingleValueAtFalsePosition"));
        ts1.addTest(new TestLinkedList("testIterator"));
        return ts1;
    }

    /**
     * Erzeugt ein Objekt der Klasse TestLinkedList.
     * Dabei wird der Standard-Konstruktor der Oberklasse verwendet.
     * @param name
     */
    public TestLinkedList(String name) {super(name);}

    //++++++++++++++++++++++++++ Test-Werte +++++++++++++++++++++++++++++++++++++++++++++++++++++

    // Test-Werte, die von den Test-Methoden verwendet werden.
    static private Object[] testValues = new Object[]{
            "test1", "test2", "test3", "test4", "test5", "test6", null, "test7", null
    };
    // Test-Werte, die von den Test-Methoden verwendet werden.
    static private Object[] testValuesInsert = new Object[]{
            "abc", "def", "xyz", "uio", null
    };

    // Test-Werte, die von den Test-Methoden verwendet werden.
    static private Object[] testValuesIterator = new Object[]{
            1, 2, 3, 4, 5, 6, 7
    };

    // Objekt der Klasse LinkedList, welches getestet wird.
    static private LinkedList<String> testList = new LinkedList<String>();

    //++++++++++++++++++++++++++ Ende Test-Werte ++++++++++++++++++++++++++++++++++++++++++++++++

    //++++++++++++++++++++++++++ TestMethoden +++++++++++++++++++++++++++++++++++++++++++++++++++

    public void testInsertValueAtFalsePosition(){
        // # Test add single Value to false Position #
        p("# Test add single Value to false Position #");
        testList.add(5, testValuesInsert[1]);
        assertFalse("Wert ist in der Liste, obwohl dies nicht erwartet wurde", testList.contains(testValuesInsert[1]));
        // Test List size
        testLength(0);
        // Reset List
        resetList();
    }

    public void testInsertValueAtCorrectPosition(){
        // ######### Test add single Value to correct position #################
        p("# Test add single Value to correct position #");
        // Add Collection with initial Test-Values first
        testList.addAll(Arrays.asList(testValues));
        // Add Value at correct Position
        testList.add(3, testValuesInsert[2]);
        // Test, if List contains new Value
        assertTrue("Objekt nicht in Liste", testList.contains(testValuesInsert[2]));
        // Test, if Position is right
        assertEquals("Position des Objekts ist falsch", 3, testList.indexOf(testValuesInsert[2]));
        // Test, if List-Length is right
        testLength(8);
        // Reset List
        resetList();
    }

    public void testInsertSubCollection() {
        // # Test insert Sub-Collection #
        p("# Test insert Sub-Collection #");
        // Add Collection first to fill List
        testList.addAll(Arrays.asList(testValues));
        // Adding Collection but to specified Position 4
        testList.addAll(2, Arrays.asList(testValuesInsert));
        // Test, if Objects are in list
        assertTrue(testList.containsAll(Arrays.asList(Arrays.copyOfRange(testValuesInsert, 2, 3))));
        // Test Lenght of List
        testLength(9);
        // Reset List
        resetList();

    }

    public void testAddNullCollection(){
        // # Test add empty Collection #
        p("# Test add empty Collection #");
        // Testing adding empty StringList
        testList.addAll(Arrays.asList(new String[0]));
        // Test, if List does not contain null objects
        assertFalse("Null-Objekt gefunden", testList.contains(null));
        // Test length
        testLength(0);
        // reset List
        resetList();
    }

    public void testAddNull() {
        // # Test add null Object #
        p("# Test add null Object #");
        // Test adding null value
        testList.add(null);
        // Test, if List contains null object
        assertFalse("Null-Objekt gefunden", testList.contains(null));
        // Test length after adding null value
        testLength(0);
    }

    public void testAddCollection(){
        // # Test add complete collection #
        p("# Test add complete collection #");
        // Test adding multiple values
        testList.addAll(Arrays.asList(testValues));
        // Testing Length:
        // Length should be 7 because of null-Values are not supported
        testLength(testValues.length - 2);
        // Reset List
        resetList();
    }

    public void testAddSingleValueToEmptyList(){
        // # Test adding single Value to empty List #
        p("# Test adding single Value to empty List #");
        // Test adding single value
        testList.add((String) testValues[0]);
        // Test if single value is in the list
        assertTrue("Objekt nicht in Liste!", testList.contains(testValues[0]));
        // Test length
        testLength(1);
        // Reset List
        resetList();
    }

    public void testRemoveSingleValue() {
        // # Test remove Value #
        p("# Test remove Value #");
        // Add Collection first to fill List
        testList.addAll(Arrays.asList(testValues));
        // Remove first Value of Test-Objects
        testList.remove(testValues[0]);
        // Test, if List does really not contain Element anymore
        assertFalse("Objekt immer noch in Liste", testList.contains(testValues[0]));
        // Test, if List-Length is right
        testLength(6);
        // reset TestList
        resetList();
    }

    public void testRemoveSingleValueAtSpecifiedPosition(){
        // # Test remove Value at specified Position #
        p("# Test remove Value at specified Position #");
        // Add Collection first to fill List
        testList.addAll(Arrays.asList(testValues));
        // Remove first Value of Test-Objects
        testList.remove(testList.indexOf(testValues[0]));
        // Test, if List does really not contain Element anymore
        assertFalse("Objekt immer noch in Liste", testList.contains(testValues[0]));
        // Test, if List-Length is right
        testLength(6);
        // Reset TestList
        resetList();
    }

    public void testGetSingleValue(){
        // # Test get Value at specified  correct position #
        p("# Test get Value at specified correct position #");
        // Add Collection with initial Test-Values first
        testList.addAll(Arrays.asList(testValues));
        // Try to get a specified value
        assertEquals("Value not equal", testValues[2], testList.get(2));
        // Reset List
        resetList();
    }

    public void testGetSingleValueAtFalsePosition(){
        // # Test get Value at specified false position #
        p("# Test get Value at specified false position #");
        // Add Collection with initial Test-Values first
        testList.addAll(Arrays.asList(testValues));
        // Try to get a specified value
        try {
            testList.get(80);
            fail("get() hat keine Ausnahme geworfen");
        } catch (IndexOutOfBoundsException ex) {
            // Alles o.k., next hat die richtige Art von Ausnahme geworfen
        } catch (NullPointerException ex) {
            fail("get() hat falsche Ausnahme geworfen!");
        }
        // Reset List
        resetList();
    }

    public void testIterator(){
        // # Test Iterator of LinkedList #
        p("# Test Iterator of LinkedList #");
        ListIterator it = testList.iterator();
        assertTrue(it instanceof ListIterator);
        // Fill TestList with values
        testList.addAll(Arrays.asList(testValuesIterator));
        // Expected Number of List-Elements is 7
        // Loop through List with Iterator both forwards and backwards
        int i=0;
        while(i<7){
            assertEquals("Objekt hat falschen Wert", testValuesIterator[i], it.next());
            i++;
        }
        i=6;
        it = testList.iterator();
        while (i>=0){
            assertEquals("Objekt hat falschen Wert", testValuesIterator[i], it.previous());
            i--;
        }
        // Reset testList
        resetList();
    }

    //++++++++++++++++++++++++++ Ende TestMethoden ++++++++++++++++++++++++++++++++++++++++++++++

    // ++++++++++++++++++++++++++ Hilfsmethoden +++++++++++++++++++++++++++++++++++++++++++++++++

    private void resetList() {
        testList.clear();
    }

    private void testLength(int prefSize){

        assertEquals("Laenge ist leider falsch!", prefSize, testList.size());
    }

    /**
     * Methode, um die Ausgabe auf der Konsole zu vereinfachen.
     * @param a Zeichen oder Zeichenkette welche(s) auf der Konsole ausgegeben wird.
     */
    static private void p(String a){

        System.out.println(a);
    }

    //++++++++++++++++++++++++++ Ende Hifsmethoden ++++++++++++++++++++++++++++++++++++++++++++++

    /**
     * Fuehrt den Test mittels JUnit 3 aus.
     * Nutzt dazu die awtui (GUI), um die Testergebnisse grafisch darzustellen.
     * @param args
     */
    public static void main (String[] args){
        junit.awtui.TestRunner.run(TestLinkedList.class);
    }
}
