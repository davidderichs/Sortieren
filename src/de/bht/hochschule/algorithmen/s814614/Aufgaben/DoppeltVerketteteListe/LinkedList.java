package de.bht.hochschule.algorithmen.s814614.Aufgaben.DoppeltVerketteteListe;

import java.util.*;

/**
 * Klasse, die eine doppelt verkettete Liste beschreibt.
 * Eine Leere Liste enthaelt bereits leere Listen-Elemente Tail und Head.
 * Implementiert die Schnittstelle List<T>
 * Fuer die Implementierung der Schnittstelle wurde die Java-API 1.8 genutzt.
 * @param <T> Objekt-Typ, den die Listen-Elemente haben sollen.
 */
public class LinkedList<T> implements List<T> {

    // Beschreibt das erste Element der Liste
    private ListElement<T> head;
    // Beschreibt das letzte Element der Liste
    private ListElement<T> tail;
    // Laenge der Liste, ohne Head und Tail zu zaehlen.
    private int length;

    /**
     * Erstellt eine leere Doppelt verkettete Liste
     */
    LinkedList(){
        // Erzeuge Dummy-Elemente für Tail und Head
        this.head=new ListElement(null);
        this.tail=new ListElement(null);
        // Setze Referenzen von Head auf Tail
        head.next=tail;
        // Setze Referenz von Tail auf Head
        tail.previous=head;
        // Setze Laenge der Liste auf 0;
        length=0;
    }

    /**
     * Klasse, welche die Elemente der Liste beschreibt.     *
     * @param <T> Generisches Klassen-Attribut, welches beim Deklarieren der doppelt verketteten Liste angegeben wird.
     */
    private class ListElement<T>{

        private T value;
        private ListElement<T> next;
        private ListElement<T> previous;

        ListElement(T value){
            this.value= value;
        }
    }

    /**
     * Fügt das uebergebenen Wert in die Liste ein.
     * @param t Einzufuegender Wert.
     * @return Wahr, wenn Object erfolgreich eingefuegt wurde.     *
     */
    public boolean add(T t){
        // Test if value is null
        if (t==null) return false;
        // Erstelle neues Element mit gegebenem Wert.
        ListElement<T> newListElement = new ListElement<T>(t);
        // Lege neues Element als Nachfolger von ehemals letztem Element fest.
        tail.previous.next=newListElement;
        // Lege ehemals letztes Element als Vorgaenger von neuem Element fest.
        newListElement.previous=tail.previous;
        // Lege neues Element als Vorgaenger von Tail fest.
        tail.previous=newListElement;
        // Lege Tail als Nachfolger von neuem Element fest.
        newListElement.next=tail;
        // Erhoehe Laenge der Liste
        length++;
        // Teile erfolgreiches Einfuegen mit.
        return this.contains(newListElement);
    }

    /**
     * Liefert die Menge der, in der Liste enthaltenen Elemente.
     * @return Anzahl der Elemente. Maximalwert ist Integer.MAX_VALUE
     */
    @Override
    public int size() {
        return length;
    }

    /**
     * Liefert true, wenn die Liste leer ist (also nur Head und Tail enthaelt).
     * @return true, wenn Liste leer.
     */
    @Override
    public boolean isEmpty() {
        return head.next.equals(tail);
    }

    /**
     * Prüft, ob ein Element o in der Liste enthalten ist.
     * @param o Zu suchendes Object
     * @return True, wenn Objekt in der Liste enthalten ist.
     */
    @Override
    public boolean contains(Object o) {
        boolean contained = false;
        ListIterator<T> it = this.iterator();
        // Iteriere durch Objekte der Liste und pruefe, ob Objekt in Liste
        while (it.hasNext()){
            // Wenn Objekt gefunden, setze Rueckgabewert auf true.
            contained=o.equals(it.next());
            if (contained==true) break;
        }
        // Teile mit, ob Objekt gefunden oder nicht.
        return contained;
    }


    /**
     * Implementiert die Schnittstelle Iterator.
     * Kann zum Durchlaufen der LinkedList genutzt werden.
     */
    public class ObjectIterator implements ListIterator<T>{

        public ListElement<T> currentNext;
        public ListElement<T> currentPrev;

        public ObjectIterator(){
            currentNext = LinkedList.this.head;
            currentPrev = LinkedList.this.tail;
        }

        /**
         * Liefert true, wenn außer Head und Tail mindestens ein anderes Listenelement existiert.
         * @return
         */
        @Override
        public boolean hasNext() {
            return !(currentNext.next==tail);
        }

        /**
         * Liefert das im naechsten ListenElement enthaltene Objekt.
         * @return Value des naechsten ListElements (Objekt vom Typ T)
         */
        @Override
        public T next() {
            currentNext = currentNext.next;
            return currentNext.value;
        }

        /**
         * Liefert das im vorherigen ListenElement enthaltene Objekt.
         * @return Value des vorherigen Elements (Typ T)
         */
        @Override
        public boolean hasPrevious() {

            return !(currentPrev.previous==head);
        }

        @Override
        public T previous() {
            currentPrev = currentPrev.previous;
            return currentPrev.value;
        }

        @Override
        public int nextIndex() {
            return LinkedList.this.indexOf(currentNext.next);
        }

        @Override
        public int previousIndex() {
            return LinkedList.this.indexOf(currentNext.previous);
        }

        //++++++++++++++++++ OPTIONALE METHODEN von LISTITERATOR +++++++++++++++++++++++++++
        /**
         * OPTIONAL OPERATION remove() IS NOT SUPPORTED
         */
        @Override
        public void remove() {

        }

        /**
         * OPTIONAL OPERATION set() IS NOT SUPPORTED
         */
        @Override
        public void set(T t) {

        }

        /**
         * OPTIONAL OPERATION add() IS NOT SUPPORTED
         */
        @Override
        public void add(T t) {
        //++++++++++++++++++ ENDE OPTIONALE METHODEN von LISTITERATOR +++++++++++++++++++++++
        }
    }

    /**
     * Liefert ein Iterator Object vom Typ LinkedListIterator, welches zum Durchlaufen der Liste genutzt werden kann.
     * @return Iterator Objekt
     */
    @Override
    public ListIterator<T> iterator() {
        return new ObjectIterator();
    }

    /**
     * Liefert die Liste als Array aus.
     * @return
     */
    @Override
    public Object[] toArray() {
        // Erstelle leeres Array, welches dieselbe Groesse wie die Liste hat.
        Object[] objects = new Object[length];
        // Durchlaufe die Liste.
        ListIterator<T> it = this.iterator();
        int i=0;
        // Fuege alle Elemente in das Array ein
        while(it.hasNext()) objects[i]=it.next();
        // Liefere das Array aus.
        return objects;
    }

    /**
     * Löscht das Übergebene Object, falls vorhanden aus der Liste heraus.
     * @param o Zu löschendes Object.
     * @return wahr, wenn Object geslöscht wurde.
     */
    @Override
    public boolean remove(Object o) {
        // finde oldElement, welches o enthaelt.
        ListElement<T> oldElement=this.getListElement(LinkedList.this.indexOf(o));
        // Setze Referenz von Vorgaenger auf Nachfolger (next)
        oldElement.previous.next=oldElement.next;
        // Setze Referenz von Nachfolger auf Vorgaenger (previous)
        oldElement.next.previous=oldElement.previous;
        // Lösche altes Objekt
        oldElement.value=null;
        // lösche altes Element
        oldElement=null;
        this.length--;
        return true;
    }

    /**
     * Liefert das ListElement, an der Stelle position aus
     * @param position Position des ListElements
     * @return gesuchtes listElement
     */
    private ListElement<T> getListElement(int position){
        // Setze listElement auf Anfang der Liste (Erstes Element nach Head)
        ListElement<T> listElement=this.head;
        // Laufe zu Position, an dem sich das Element befindet.
        int i = 0;
        while (i<=position){
            listElement=listElement.next;
            i++;
        }
        // Liefere listElement aus
        return listElement;
    }

    /**
     * Fügt eine Sammlung von Objekten zur Liste hinzu
     * PRECONDITION: Die zuzufügende Sammlung muss von Typ Collection<T> sein.
     * @param c Hinzuzufuegende Sammlung
     * @return wahr, wenn Sammlung hinzugefuegt wurde.
     */
    @Override
    public boolean addAll(Collection c) {
        for(Object o : c) this.add((T) o);
        return true;
    }

    /**
     * Löscht den Inhalt der Liste durch Setzen der Zeiger von Head auf Tail und umgekehrt.
     */
    @Override
    public void clear() {
        head.next=tail;
        tail.previous=head;
        length=0;
    }

    /**
     * Liefert das Objekt an der Stelle "index" aus der Liste.
     * @param index Index, an dem das geforderte Objekt liegt.
     * @return Objekt an der Stelle "index", oder null, wenn Objekt nicht vorhanden oder Index-Out-Of-Range
     */
    @Override
    public T get(int index) {
        if (index>this.length-1 || index<0) throw new IndexOutOfBoundsException();
        // Gibt den Wert (value) des ListElements an der Stelle "index" aus.
        return this.getListElement(index).value;
    }

    /**
     * Fügt alle Objekte ab der Stelle "index" aus einer Sammlung zur Liste hinzu.
     * @param index Index, an dem das erste Objekt in der Sammlung liegt.
     * @param c Sammlung von Objekten.
     * @return Wahr, wenn alle Objekt hinzugefügt wurden.
     */
    @Override
    public boolean addAll(int index, Collection c) {
        // Iteriere durch c und fuege alle Element ab der Stelle "index" zur Liste hinzu.
        // Erhoehe die Laenge der Liste nach jedem Hinzufuegen im 1;
        Iterator it = c.iterator();
        while(index>0){
            it.next();
            index--;
        }
        while(it.hasNext()){
            this.add((T) it.next());
        }
        // Pruefe, ob alle Objekte der Collection c wirklich in der Liste sind.
        // Gib ggf. true aus.
        return this.containsAll(c);
    }

    /**
     * Ersetzt das Objekt an der Stelle "index" in der Liste mit dem uebergebenen Element.
     * @param index Stelle, an dem das Objekt ersetzt werden soll.
     * @param element Neues Element.
     * @return Das ersetzte Objekt an der Stelle "index"
     */
    @Override
    public T set(int index, Object element) {
        T oldValue = this.getListElement(index).value;
        this.getListElement(index).value=(T)element;
        return oldValue;
    }

    /**
     * Fügt das übergebene Objekt in die Liste an der uebergebenen Position ein.
     * Objekte an dieser Stelle oder rechts davon werden nach rechts "verschoben".
     * @param index Index, an dem das neue Element hinzugefügt werden soll.
     * @param element Neues Element.
     */
    @Override
    public void add(int index, Object element) {

        if (index>=length) return;
        if (element==null) return;

        // Setze nextListElement auf ListElement an der Stelle "index"
        ListElement<T> nextListElement = this.getListElement(index);
        // Setze prevListElement auf dessen Vorgaenger
        ListElement<T> prevListElement = nextListElement.previous;

        // Erstelle neues ListElement für das uebergebene Objekt
        ListElement<T> newlistElement = new ListElement<T>((T)element);

        // Setze Referenz "next" von prevListElement auf newListElement
        prevListElement.next=newlistElement;
        // setze Referenz "prev" von neuem Element auf prevListElement
        newlistElement.previous=prevListElement;

        // Setze Referenz von neuem Element "next" auf nextListElement
        newlistElement.next=nextListElement;
        // Setze Referenz nextListElement auf neues Element
        nextListElement.previous=newlistElement;

        // Erhoehe Laenge der Liste um 1
        this.length++;
    }

    /**
     * Löscht das Element an der Stelle "index" in der Liste.
     * @param index
     * @return Wert des geloeschten Elements.
     */
    @Override
    public T remove(int index) {
        ListElement<T> delElement = this.getListElement(index);
        // Nachfolger des zu loeschenden Elements
        ListElement<T> nextElement = delElement.next;
        // Vorgaenger des zu loeschenden Elements
        ListElement<T> prevElement = delElement.previous;
        // Setze Referenzen wischen Vorgaenger und Nachfolger.
        prevElement.next=nextElement;
        nextElement.previous=prevElement;
        // Passe Laenge der Liste an.
        this.length--;
        return delElement.value;
    }

    /**
     * Liefert den ersten Index eines übergebenen Objekts, falls sich dieses in der Liste befindet.
     * @param o Zu suchendes Objekt.
     * @return Index des gesuchten Objekts, oder -1, wenn Objekt nicht gefunden.
     */
    @Override
    public int indexOf(Object o) {
        ListIterator<T> it = this.iterator();
        int pos=0;
        while(it.hasNext()){
            T curObj=it.next();
            if(curObj.equals(o)){
                return pos;
            }
            pos++;
        }
        return -1;
    }

    /**
     * Liefert den letzten Index eines zu übergebenen Objekts, falls sich dieses in der Liste befindet.
     * @param o Gesuchtes Objekt.
     * @return Index des gesuchten Objekts oder -1, wenn Objekt nicht gefunden.
     */
    @Override
    public int lastIndexOf(Object o) {
        ListIterator<T> it = this.iterator();
        int pos=length;
        while(it.hasPrevious()){
            T curObj=it.previous();
            pos--;
            if(curObj.equals(o)){
                return pos;
            }
        }
        return -1;
    }

    /**
     * Liefert ein ListIterator Objekt, zum Durchlaufen der Liste.
     * @return ListIterator Objekt.
     */
    @Override
    public ListIterator<T> listIterator() {
        return this.iterator();
    }

    /**
     * Liefert ein ListIterator Objekt, zum Durchlaufen der Liste.
     * Der Iterator startet am angegebenen Index.
     * @param index Startpunkt des Iterators.
     * @return ListIterator Objekt.
     */
    @Override
    public ListIterator listIterator(int index) {
        ListIterator it = this.iterator();
        while(index>=0)it.next();
        return it;
    }

    /**
     * Liefert einen Teil der Liste zwischen fromIndex und toIndex.
     * @param fromIndex Start des Teilbereichs.
     * @param toIndex Ende des Teilbereichs.
     * @return Listen-Objekt, dass alle Elemente des Teilbereichs enthaelt.
     */
    @Override
    public List subList(int fromIndex, int toIndex) {
        // Erstelle neue Liste list
        LinkedList<T> list = new LinkedList<T>();
        // Fuege alle Elemente zwischen fromIndex und toIndex zu list hinzu
        while (fromIndex<=toIndex){
            list.add(this.get(fromIndex));
            fromIndex++;
        }
        // Gib neue Liste aus.
        return list;
    }

    /**
     * Löscht alle Elemente aus der Liste, mit Ausnahme der Objekte,
     * welche mit den in der uebergebenen Sammlung enthaltenen Objekte übereinstimmen.
     * @param c Sammlung mit zu behaltenen Objekten
     * @return wahr, wenn Löschung und Speicherung betroffener Objekte erfolgreich.
     */
    @Override
    public boolean retainAll(Collection c) {
        // Loesche alle ListElemente, deren Inhalt keinem Objekt der Sammlung c entspricht.
        ListIterator<T> it = this.iterator();
        while(it.hasNext()){
            T currObj = it.next();
            if(!(c.contains(currObj))) this.remove(currObj);
        }
        // Pruefe, ob falsche Elemente in der Liste sind
        it = this.iterator();
        while(it.hasNext()){
            if (!(c.contains(it.next()))) return false;
        }
        // Pruefe, ob Elemente der Sammlung in der Liste enhalten sind.
        return (this.containsAll(c));
    }

    /**
     * Löscht alle Objekte aus der Liste,
     * welche mit den in der übergebenen Sammlung enthaltenen Objekte übereinstimmen.
     * @param c Sammlung von zu löschenden Objekten.
     * @return wahr, wenn Löschung erfolgreich.
     */
    @Override
    public boolean removeAll(Collection c) {
        // Loesche alle ListElemente, deren Inhalt Objekten der Sammlung c entsprichten.
        ListIterator<T> it = this.iterator();
        while(it.hasNext()){
            T currObj = it.next();
            if((c.contains(currObj))) this.remove(currObj);
        }
        // Pruefe, ob falsche Elemente in der Liste sind
        it = this.iterator();
        while(it.hasNext()){
            if ((c.contains(it.next()))) return false;
        }
        // Pruefe, ob Elemente der Sammlung in der Liste enhalten sind.
        // Gib ggf. false aus.
        return (!(this.containsAll(c)));
    }

    /**
     * Liefert "true", wenn alle Elemente der in der Sammlung enthaltenen Objekte in der Liste uebereinstimmen.
     * @param c Sammlung von zu vergleichenden Objekten.
     * @return warh, wenn alle Objekte der Sammlung in der Liste "enthalten" (uebereinstimmen)
     */
    @Override
    public boolean containsAll(Collection c) {
        // Durchlaufe alle Elemente der Sammlung c und pruefe, ob Element in Liste enthalten.
        // Falls nicht, gib false zurueck.
        Iterator it = c.iterator();
        while(it.hasNext()){
            if (!(this.contains(it.next()))) return false;
        }
        return true;
    }

    /**
     * Liefert das Ergebnis von toArray() mit Pruefung der Laufzeit-Typisierung.
     * @param
     * @param <T1>
     * @return
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return (T1[]) this.toArray();
    }

}
