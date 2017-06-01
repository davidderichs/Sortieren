package de.bht.hochschule.algorithmen.s814614.Aufgaben.Sortieren;

/**
 * @class: Sortiert eine Reihe durch Zerlegen in Teilreihen.
 * Created by David Derichs s814614 on 03.11.2016
 */
public class Mergesort {

    /**
     * Sortier-Algorithmus, nach dem MergeSort-Prinzip.
     * Sortiert eine Reihe mittels Zerlegung in Teilprobleme.
     *
     * @param A Unsortierte Reihe
     * @param l Linke Seite der Reihe
     * @param r Rechte Seite der Reihe.
     * @return
     */
    public static void mergeSort(Comparable<Object>[] A, int l, int r){
        if(l<r){
            //Finde Index für Mitte
            int m = (l+r)/2;
            // Linke Seite sortieren
            mergeSort(A, l, m);
            // Rechte Seite sortieren
            mergeSort(A, m+1, r);
            // Mischen bzw. Zusammenführen der sortierten Teilbereiche.
            merge(A, l, m, r);
        }
    }

    private static void p(String s){
        System.out.println(s);
    }

    /**
     * Vorbedingung: Teilbereich linkeSeite und rechteSeite müssen sortiert sein.
     * Nimmt zwei Teilbereiche eines Arrays entgegen und führt sie zusammen.
     * Dabei wird darauf geachtet, dass der Bereich nach Bearbeitung sortiert ist.
     * Der kleinste Wert liegt links. Der höchste Wert liegt reichts im Bereich.
     * @param r
     * @param l
     * @param m
     * @param A
     */
    private static void merge(Comparable<Object>[] A, int l, int m, int r){
        // Merge sortiert A von Index l bis r
        // Teilfeld A1 geht von Index l bis m
        // Teilfeld A2 geht von Index m+1 bis r
        // Precondition: A1 ist sortiert und A2 ist sortiert
        // H sei Hilfsfeld
        // l aktueller Index A1, m+1 aktueller Index A2

        // Groesse des Felds
        int len = A.length;
        // Hilfsfeld H
        Comparable<Object>[] H = new Comparable[len];

        // Index Hilfsfeld
        int iH=0;
        // Index A1
        int iA1=l;
        // Index A2
        int iA2=m+1;

        // Weder Teilfeld A1 (links), noch Teilfeld A2 (rechts) vollständig durchlaufen
        while(iA1<=m && iA2<=r){
            if(A[iA1].compareTo(A[iA2])<0) {
                // kopiere kleineren aktuellen Wert von A1 nach H
                H[iH]=A[iA1];
                // Erhoehe aktuellen Index von Teilfeld A1 mit kleinerem Wert
                iA1=iA1+1;
            } else {
                // kopiere kleineren aktuellen Wert von A2 nach H
                H[iH]=A[iA2];
                // Erhoehe aktuellen Index von Teilfeld A2 mit kleinerem Wert
                iA2=iA2+1;
            }
            // Erhoehe aktuellen Index von H
            iH=iH+1;
        }

        // Uebertrage Werte des noch nicht vollstaendig durchlaufenen Teilfelds nach H
        if (iA1<=m) {
            while (iA1<=m){
                H[iH]=A[iA1];
                iA1=iA1+1;
                iH=iH+1;
            }
        }else {
            while (iA2<=r){
                H[iH]=A[iA2];
                iA2=iA2+1;
                iH=iH+1;
            }
        }

        // Überschreibe alte Werte mit neuen Werten
        iH=0;
        for(int i=l; i<=r; i++, iH++){
            A[i]=H[iH];
        }
    }
}
