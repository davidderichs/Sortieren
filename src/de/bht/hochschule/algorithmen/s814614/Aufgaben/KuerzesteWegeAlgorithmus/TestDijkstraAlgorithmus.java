package de.bht.hochschule.algorithmen.s814614.Aufgaben.KuerzesteWegeAlgorithmus;

/**
 * Created by dave_digger on 22.01.2017.
 */
public class TestDijkstraAlgorithmus {

    public static void main (String[] test){
        String path = ".src\\de\\bht\\hochschule\\algorithmen\\s814614\\Aufgaben\\KuerzesteWegeAlgorithmus\\TestFiles\\graphwsu.txt";
        Graph graph = GraphLesen.FileToGraph(path, true);
    }
}
