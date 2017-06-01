package de.bht.hochschule.algorithmen.s814614.Aufgaben.TestSortieren;

import de.bht.hochschule.algorithmen.s814614.Aufgaben.Sortieren.Mergesort;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by David on 19.11.2016.
 */
public class MergesortTest{

        // testArrays wird mit Beispiel-Werten gefüllt.
        private ArrayList<Comparable[]> testArrays;
        // Ordner, in dem sich die Beispiel-Dateien befinden.
        private File testFolder;
        // Liste aller Beispiel-Dateien
        private File[] listOfFiles;
        // Testergebnis. Wird auf false gesetzt, sobald ein Array nach der Ausführung von quicksort nicht sortiert ist.
        private boolean testResult;

        /**
         * Initialisiert die Umgebungsvariablen für den Test.
         */
        public MergesortTest(){
            this.testFolder = new File("./src/de//bht//hochschule//algorithmen//s814614//Aufgaben//TestSortieren//TestFiles//");
            listOfFiles = testFolder.listFiles();
            testArrays = new ArrayList<Comparable[]>();
            getArraysFromFiles();
            this.testResult=true;
        }

        /**
         * Listet die im Test-Ordner befindlichen Dateien auf und überführt die Liste in testArrays.
         */
    private void getArraysFromFiles(){
        for (int i = 0; i < listOfFiles.length; i++) {
            testArrays.add(FileIntArray.FileToIntArray(listOfFiles[i].toString()));
        }
    }

    /**
     * Führt eine Sortierung mit den Testwerten in testArrays aus.
     * Misst die zeit vor und nach der Ausführung von quicksort.
     * Test-Ergebnisse werden auf der Konsole ausgegeben.
     * @throws Exception
     */
    public void testMergesort() throws Exception {
        int file=0;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./src/de//bht//hochschule//algorithmen//s814614//Aufgaben//TestSortieren///resultsMergesort.txt");
        }
        catch ( Exception e) {
            System.out.println("'dat' konnte nicht geoeffnet werden");

        }
        // Aufzeichnen der Laufzeiten in die Datei "results.txt" und auf der Konsole
        fos.write(new String ("Test und Laufzeiten fuer Mergesort:\n\n").getBytes());
        System.out.println("Test und Laufzeiten fuer Mergesort:\n");
        for(Comparable<Object>[] A : testArrays){
            fos.write(new String ("Testergebnis fuer " + listOfFiles[file].getName() + " :\n").getBytes());
            System.out.println("Testergebnis fuer " + listOfFiles[file].getName() + " :");
            // Messe StartZeit
            long startTime = System.nanoTime();
            Mergesort.mergeSort(A, 0, A.length - 1);
            // Messe Endzeit
            long endTime = System.nanoTime();
            checkResult(A);
            // Umrechnung in Milisekunden
            long time = (endTime-startTime)/1000;
            fos.write(new String ("Erfolgreich: " + this.testResult + "\nZeit: " + time + " ms\n\n").getBytes());
            System.out.println("Erfolgreich: " + this.testResult + "\nZeit: " + time + " ms\n");
            file++;
        }
    }

    /**
     * Prüft, ob das Array A sortiert ist.
     * @param A zu prüfendes Array
     */
    private void checkResult(Comparable[] A){
        for(int i=1; i<A.length; i++) {
            if(A[i-1].compareTo(A[i])>0) this.testResult=false;
        }
    }
}