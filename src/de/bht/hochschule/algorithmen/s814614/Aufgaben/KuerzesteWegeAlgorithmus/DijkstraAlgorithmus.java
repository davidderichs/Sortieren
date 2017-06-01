package de.bht.hochschule.algorithmen.s814614.Aufgaben.KuerzesteWegeAlgorithmus;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by dave_digger on 22.01.2017.
 */
public class DijkstraAlgorithmus {

    public void findShortestPath(Graph graph, Vertex startVertex) throws Exception {

        // Nutze einen Comparator fuer die Priority-Queue
        Comparator<Vertex> comparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.getId()-v2.getId();
            }
        };

        // Erstelle Priority-Queue, welche alle Knoten enthaelt.
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>(graph.getNumberVertices(), comparator);

        // Pruefe auf moegliches negatives Kantengewicht
        checkWeight(graph);

        // SingleSource Initialisierung mit StartKnoten startVertex
        Graph singleSource = new Graph();
        singleSource.addVertex(startVertex);

        // Lege Set an, welche alle Knoten enthaelt, welche ueber s erreicht werden koennen.
        Set<Vertex> s = null;

        // Solange Queue noch Knoten enthaelt.
        while ( !(pQueue.isEmpty()) ){
            Vertex u = pQueue.poll();
            s.add(u);
            Collection<Vertex> neighborsOfU=graph.getNeighbours(u);

            for(Object v : neighborsOfU){
                relax(u, v);
            }
        }
    }

    private void relax(Vertex u, Object v) {

    }

    private void checkWeight(Graph graph)throws Exception{
        Collection<Edge> edges = graph.getEdges();
        for (Edge e : edges){
            if (e.getWeight()<0) throw new Exception("Negative Kantenwerte sind nicht erlaubt!");
        }
    }
}
