package de.bht.hochschule.algorithmen.s814614.Aufgaben.Sortieren;

/**
 * Created by David on 03.11.2016.
 */
public class Quicksort {

    /**
     * Sortiert das Array A mittels des Quicksort Algorithmus
     * @param A zu sortierendes Array
     * @param l Index links
     * @param r Index rechts
     */
    public static void quicksort(Comparable[] A, int l, int r){
        if (l < r){
            // Partitioniere A durch Aufteilung in linken und Rechten Teil anhand Pivot-Element q.
            int q = partition(A, l, r);
            // Sortiere die Teilbereiche neben Pivot-Element q
            // linker Bereich
            quicksort(A, l, q-1);
            // rechter Bereich
            quicksort(A, q+1, r);
        }
    }

    /**
     * Zerlegt den Bereich zwischen l und r des Arrays A in zwei Teilbereiche.
     * Der Linke Teilbereich enthaelt nur Elemente kleiner gleich dem Pivot-Element p.
     * Der rechte Teilbereich enthaelt nur Elemente groesser gleich dem Pivot-Element p.
     * @param A Zu sortierendes Array
     * @param l linke Index-Grenze
     * @param r rechte Index-Grenze
     * @return
     */
    private static int partition(Comparable[] A, int l, int r) {
        // Setze j auf das Pivotelement, also auf A.length � 1.
        // Pivot-Element
        Comparable p=A[r];

        // Setze Index i auf den Index -1 links des zu sortierenden Bereichs.
        int i = l-1;
        // Setze Index j auf den Index rechts des zu sortierenden Bereichs.
        int j = r;


        while(true){
            // Durchlaufe Feld mit Index i in Richtung des rechten
            // Randes. Stoppe, wenn A[i] >= p oder i >= j.
            while(true){
                ++i;
                if (p.compareTo(A[i]) <= 0 || i>=j) break;
            }
            // Durchlaufe Feld mit Index j in Richtung des linken Randes.
            // Stoppe, wenn A[j] <= p oder i >= j.
            while(true){
                --j;
                if (p.compareTo(A[j]) >= 0 || i>=j) break;
            }
            // Wenn Bedingung i >= j noch nicht erf�llt, tausche A[i]
            // und A[j] aus
            if(i>=j) break;
            tausche (A, i, j);
        }

        // Bedingung i>=j ist erfuellt.
        // Tausche Pivotelement am Index r mit aktuellem Element A[i]
        tausche(A, i, r);
        r=i;
        return r;
    }

    /**
     * Tauscht die Elemente A[i] und A[j]
     * @param A zu behandelndes Array
     * @param i Index des ersten Elements
     * @param j Indes des zweiten Elements
     */
    private static void tausche(Comparable[] A, int i, int j) {
        Comparable tausch=A[i];
        A[i]=A[j];
        A[j]=tausch;
    }

}
