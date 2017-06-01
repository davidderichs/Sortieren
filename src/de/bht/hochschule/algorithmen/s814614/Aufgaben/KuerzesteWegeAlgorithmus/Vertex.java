package de.bht.hochschule.algorithmen.s814614.Aufgaben.KuerzesteWegeAlgorithmus;

/** Eine Klasse, die Knoten eines Graphen repräsentiert
 * 
 * @author ripphausen
 * @version 1.0
 */
public class Vertex {
	private int id;

	public Vertex(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString() {
		return new Integer(id).toString();
	}
}