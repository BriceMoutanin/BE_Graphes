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
	
	private static Graph graph1, graph2, graph3 ;
	private static ShortestPathData data_gr1_faisable, data_gr1_non_connexe, data_gr1_identique;
	private static ShortestPathData data_gr2_faisable, data_gr2_non_connexe, data_gr2_identique;
	private static ShortestPathData data_gr3_faisable, data_gr3_identique;
	
	private static DijkstraAlgorithm solD_data_gr1_faisable, solD_data_gr1_non_connexe, solD_data_gr1_identique;
	private static DijkstraAlgorithm solD_data_gr2_faisable, solD_data_gr2_non_connexe, solD_data_gr2_identique;
	private static DijkstraAlgorithm solD_data_gr3_faisable, solD_data_gr3_identique;
	
	private static BellmanFordAlgorithm solB_data_gr1_faisable, solB_data_gr1_non_connexe, solB_data_gr1_identique;
	private static BellmanFordAlgorithm solB_data_gr2_faisable, solB_data_gr2_non_connexe, solB_data_gr2_identique;
	private static BellmanFordAlgorithm solB_data_gr3_faisable, solB_data_gr3_identique;
	
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
    graph1 = reader1.read();
    
    // Read the second graph.
    graph2 = reader2.read();
    
    // Read the third graph.
    graph3 = reader3.read();
    
    data_gr1_faisable = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(530112) , arc_insp);
    data_gr1_non_connexe = new ShortestPathData(graph1, graph1.getNodes().get(530112), graph1.getNodes().get(383921) , arc_insp);
    data_gr1_identique = new ShortestPathData(graph1, graph1.getNodes().get(502922), graph1.getNodes().get(502922) , arc_insp);
    
    data_gr2_faisable = new ShortestPathData(graph2, graph2.getNodes().get(62659), graph2.getNodes().get(797990) , arc_insp);
    data_gr2_non_connexe = new ShortestPathData(graph2, graph2.getNodes().get(62659), graph2.getNodes().get(706992) , arc_insp);
    data_gr2_identique = new ShortestPathData(graph2, graph2.getNodes().get(62659), graph2.getNodes().get(62659) , arc_insp);
    
    data_gr3_faisable = new ShortestPathData(graph3, graph3.getNodes().get(822642), graph3.getNodes().get(323463) , arc_insp);
    data_gr3_identique = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(323463) , arc_insp);
    
    /** Dijkstra solutions definitions */
    
    solD_data_gr1_faisable = new DijkstraAlgorithm(data_gr1_faisable) ;
    solD_data_gr1_non_connexe = new DijkstraAlgorithm(data_gr1_non_connexe) ;
    solD_data_gr1_identique = new DijkstraAlgorithm(data_gr1_identique) ;
    
    solD_data_gr2_faisable = new DijkstraAlgorithm(data_gr2_faisable) ;
    solD_data_gr2_non_connexe = new DijkstraAlgorithm(data_gr2_non_connexe) ;
    solD_data_gr2_identique = new DijkstraAlgorithm(data_gr2_identique) ;
    
    solD_data_gr3_faisable = new DijkstraAlgorithm(data_gr3_faisable) ;
    solD_data_gr3_identique = new DijkstraAlgorithm(data_gr3_identique) ;
    
    /** Bellman-Ford solutions definitions */
    
    solB_data_gr1_faisable = new BellmanFordAlgorithm(data_gr1_faisable) ;
    solB_data_gr1_non_connexe = new BellmanFordAlgorithm(data_gr1_non_connexe) ;
    solB_data_gr1_identique = new BellmanFordAlgorithm(data_gr1_identique) ;
    
    solB_data_gr2_faisable = new BellmanFordAlgorithm(data_gr2_faisable) ;
    solB_data_gr2_non_connexe = new BellmanFordAlgorithm(data_gr2_non_connexe) ;
    solB_data_gr2_identique = new BellmanFordAlgorithm(data_gr2_identique) ;
    
    solB_data_gr3_faisable = new BellmanFordAlgorithm(data_gr3_faisable) ;
    solB_data_gr3_identique = new BellmanFordAlgorithm(data_gr3_identique) ;
   
    
    
	}
	
    @Test
    public void testDijkstraGraph() {
        assertEquals(solD_data_gr1_faisable.doRun().getPath().getTravelTime(36), solB_data_gr1_faisable.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr1_non_connexe.doRun().getPath().getTravelTime(36), solB_data_gr1_non_connexe.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr1_identique.doRun().getPath().getTravelTime(36), solB_data_gr1_identique.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr2_faisable.doRun().getPath().getTravelTime(36), solB_data_gr2_faisable.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr2_non_connexe.doRun().getPath().getTravelTime(36), solB_data_gr2_non_connexe.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr2_identique.doRun().getPath().getTravelTime(36), solB_data_gr2_identique.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr3_faisable.doRun().getPath().getTravelTime(36), solB_data_gr3_faisable.doRun().getPath().getTravelTime(36),1e-6);
        assertEquals(solD_data_gr3_identique.doRun().getPath().getTravelTime(36), solB_data_gr3_identique.doRun().getPath().getTravelTime(36),1e-6);
        
        assertEquals(solD_data_gr1_faisable.doRun().getPath().getLength(), solB_data_gr1_faisable.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr1_non_connexe.doRun().getPath().getLength(), solB_data_gr1_non_connexe.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr1_identique.doRun().getPath().getLength(), solB_data_gr1_identique.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr2_faisable.doRun().getPath().getLength(), solB_data_gr2_faisable.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr2_non_connexe.doRun().getPath().getLength(), solB_data_gr2_non_connexe.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr2_identique.doRun().getPath().getLength(), solB_data_gr2_identique.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr3_faisable.doRun().getPath().getLength(), solB_data_gr3_faisable.doRun().getPath().getLength(),1e-6);
        assertEquals(solD_data_gr3_identique.doRun().getPath().getLength(), solB_data_gr3_identique.doRun().getPath().getLength(),1e-6);
        
    }

    
}
