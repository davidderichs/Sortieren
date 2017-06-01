package de.bht.hochschule.algorithmen.s814614.Aufgaben.Sortieren;

/**
 * Implements the HeapSort Algorithm
 * Created by David on 03.11.2016.
 */
public class Heapsort {

    // lengthA legt die Groesse des zu sortierenden Bereichs im Array A an.
    // Waehrend des Sortiervorgangs wird dieser Bereich sukzessive verkleinert.
    static int lengthA;

    /**
     * Sortiert das Array A mittels des HeapSort-Algorithmus
     * @param A zu sortierendes Array
     */
    public static void heapSort(Comparable[] A){
        // Lege aktuelle "Groesse" des Arrays fest.
        // eigentliche Groesse wird nicht veraendert, dient aber zum Verkleinern des zu sortierenden Bereichs.
        lengthA = A.length;
        // Baue Heap auf
        buildHeap(A);

        // Sortiere den Heap
        for (int i=A.length-1; i>0; i-- ){
            // Tausche größtes Element mit Element ganz rechts im Baum.
            tausche(A, 0, i);
            // Reduziere Länge von A um 1
            lengthA=lengthA-1;
            // Stelle Heap-Eigenschaften für jetzt kleineren Baum wieder her.
            heapify(A, 0);
        }
    }

    /**
     * Ordnet die Werte von A zu einem Heap
     * k sei der kleinste Index eines Blattes
     */
    private static void buildHeap(Comparable[] A){
        int k= kleinstesBlatt(A);
        // Führt Heapify für alle Knoten der Blätter aus.
        for (int i=k-1; i>=0; i--){
            heapify(A, i);
        }
    }

    /**
     * Tauscht zwei Werte a und b im Array A
     * @param A Zu behandelndes Array
     * @param a Wert, der an Stelle b muss
     * @param b Wert, der an Stelle a muss
     */
    private static void tausche(Comparable[] A, int a, int b){
        Comparable tausch=A[a];
        A[a]=A[b];
        A[b]=tausch;
    }

    /**
     * Stellt die Heap-Eigenschaft fuer einen Teilbaum mit Index i her.
     * Precondition: linker und rechter Teilbaum von Index i haben die Heap-Eigenschaft
     * @param A Zu reparierendes Array (Heap)
     * @param i Aktueller Index
     */
    private static void heapify (Comparable[] A, int i){
        // Wenn keine Nachfolger vorhanden, tue nichts.
        if (istBlatt(A, i)) return;

        // Finde groessten Nachfolger
        int nachfolger= groessterNachfolger(A, i);

        Comparable wurzel=A[i];

        // Wenn Nachfolger groesser als Wurzel
        if (A[nachfolger].compareTo(A[i])>0) {
            // Tausche Wurzel und Nachfolger.
            A[i] = A[nachfolger];
            A[nachfolger] = wurzel;
        }
        // Fuehre Heapify fuer den Nachfolger aus
        heapify(A, nachfolger);
    }

    /**
     * Berechnet den linken Nachfolger des Elements an der Stelle i in einem Heap
     * @param i Aktueller Index
     * @return Index des linken Nachfolgers
     */
    private static int indexNachfolgerLinks(int i){
        return 2*(i+1)-1;
    }

    /**
     * Berechnet den rechten Nachfolger des Elements an der Stelle i in einem Heap
     * @param i Aktueller Index
     * @return Index des rechten Nachfolgers
     */
    private static int indexNachfolgerRechts(int i){
        return 2*(i+1);
    }

    /**
     * Berechnet den Vorgaenger des Elements an der Stelle i in einem Heap
     * @param i Aktueller Index
     * @return Index des Vorgaengers
     */
    private static int indexVorgaenger(int i){
        return (i-1)/2;
    }

    /**
     * Prüft, ob das aktuelle Element eines Heaps ein Blatt ist.
     * @param A Zu prüfendes Array (Heap)
     * @param i Aktueller Index
     * @return true, wenn Blatt.
     */
    private static boolean istBlatt(Comparable[] A, int i){
        // Fage an Frau Ripphausen: Pruefung auf linken Nachfolger muesste reichen oder?
        // Andernfalls wäre die Heap-Eigenschaft verletzt.
        if(hatLinkenNachfolger(A, i)) return false;
        return true;
    }

    /**
     * Prueft, ob das aktuelle Element eines Heaps einen rechten Nachfolger hat.
     * @param A Zu prüfendes Array (Heap)
     * @param i Aktueller Index
     * @return true, Nachfolger existiert.
     */
    private static boolean hatRechtenNachfolger(Comparable[] A, int i){
        if(indexNachfolgerRechts(i)>=lengthA) return false;
        return true;
    }

    /**
     * Prueft, ob das aktuelle Element eines Heaps einen linken Nachfolger hat.
     * @param A Zu prüfendes Array (Heap)
     * @param i Aktueller Index
     * @return true, Nachfolger existiert.
     */
    private static boolean hatLinkenNachfolger(Comparable[] A, int i){
        if(indexNachfolgerLinks(i)>=lengthA) return false;
        return true;
    }

    /**
     * Ermittelt den groessten Nachfolger des aktuellen Elements in einem Heap
     * Precondition: A[i] ist kein Blatt.
     * @param A Zu prüfendes Array (Heap)
     * @param i Aktueller Index
     * @return groesster Nachfolger
     */
    private static int groessterNachfolger(Comparable[] A, int i){
        if (hatRechtenNachfolger(A,i)){
            if(A[indexNachfolgerLinks(i)].compareTo(A[indexNachfolgerRechts(i)]) > 0) return indexNachfolgerLinks(i);
            return indexNachfolgerRechts(i);
        }
        return indexNachfolgerLinks(i);
    };

    /**
     * Ermittelt den Index des kleinsten Blattes des Heaps A
     * @param A zu analysierendes Array (heap)
     * @return Index des kleinsten Blattes
     */
    private static int kleinstesBlatt(Comparable[] A){
        // Formel:          (n-1)         -      ( (n-1/2)+1 )
        //           letzer Index von A         Anzahl Blätter
        // Im Heap liegen die Blätter auf der "rechten" Seite des Arrays
        int i= (lengthA-1) - (lengthA-1)/2 + 1;
        return i;

    }

}
