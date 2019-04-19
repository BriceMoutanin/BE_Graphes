package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class Dijkstra_Bellman_comparison {
	
	
	
	public static void initAll() throws IOException {
		
	// Call the right Arc Inspector
	ArcInspector arc_insp = ArcInspectorFactory.getAllFilters().get(0);
	
    // Visit these directory to see the list of available files on Commetud.
		
    String mapName1 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/midi-pyrenees.mapgr";
    String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    String mapName3 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/fractal-spiral.mapgr";

    // Create a graph reader.
    GraphReader reader1 = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName1))));
    
    // Create a second graph reader.
    GraphReader reader2 = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName2))));
    
    // Create a third graph reader.
    GraphReader reader3 = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName3))));

    // Read the first graph.
    Graph graph1 = reader1.read();
    
    // Read the second graph.
    Graph graph2 = reader2.read();
    
    // Read the third graph.
    Graph graph3 = reader3.read();
    
    ShortestPathData data_gr1_faisable = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(502922) , arc_insp);
    ShortestPathData data_gr1_non_connexe = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(502922) , arc_insp);
    ShortestPathData data_gr1_impossible = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(502922) , arc_insp);
    ShortestPathData data_gr1_identique = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(502922) , arc_insp);
	}

    
}
