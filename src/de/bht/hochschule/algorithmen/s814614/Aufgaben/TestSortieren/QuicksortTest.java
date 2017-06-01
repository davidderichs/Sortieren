package de.bht.hochschule.algorithmen.s814614.Aufgaben.TestSortieren;
import de.bht.hochschule.algorithmen.s814614.Aufgaben.Sortieren.Quicksort;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Testet Quicksort und gibt pro getestetem Array eine Zeit an, die für die Sortierung gebraucht wurde.
 */
public class QuicksortTest{

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
    public QuicksortTest(){
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
    public void testQuickSort() throws Exception {

        // Öffne Datei, in welche die Laufzeiten geschrieben wird mit Schreibrechten.
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("./src/de//bht//hochschule//algorithmen//s814614//Aufgaben//TestSortieren///resultsQuickSort.txt");
        }
        catch ( Exception e) {
            System.out.println("'dat' konnte nicht geoeffnet werden");

        }

        // Index für die aktuelle Datei in der listOfFiles
        int i=0;

        // Aufzeichnen der Laufzeiten in die Datei "results.txt" und auf der Konsole
        fos.write(new String ("Test und Laufzeiten fuer Quicksort:\n\n").getBytes());
        System.out.println("Test und Laufzeiten fuer Quicksort:\n");
        for(Comparable[] A : testArrays){

            // Messe StartZeit in nano-sec
            long startTime = System.nanoTime();
            // Sortiere A mittels Quicksort

            Quicksort.quicksort(A, 0, A.length - 1);
            // Messe Endzeit in nano-sec
            long endTime = System.nanoTime();

            // Prüfe, ob Array sortiert
            checkResult(A);

            // Umrechnung in Milisekunden
            long time = (endTime-startTime)/1000;

            // Schreibe ergebnisse in Datei.
            fos.write(new String ("Testergebnis fuer " + listOfFiles[i].getName() + " :\n").getBytes());
            fos.write(new String("Erfolgreich: " + this.testResult + "\nZeit: " + time + " ms\n\n").getBytes());

            // Gib Ergebnisse auf der Konsole aus.
            System.out.println("Testergebnis fuer " + listOfFiles[i].getName() + " :");
            System.out.println("Erfolgreich: " + this.testResult + "\nZeit: " + time + " ms\n");

            // Gehe zu nächster Datei
            i++;
        }
    }

    /**
     * Prüft, ob das Array A sortiert ist.
     * @param A zu prüfendes Array
     */
    private void checkResult(Comparable[] A){
        for(int i=1; i<A.length; i++) {
            if(A[i-1].compareTo(A[i]) > 0) this.testResult=false;
        }
    }
}