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

public class AStarTest {
	
	private static Graph graph1, graph2, graph3 ;
	private static ShortestPathData data_gr1_faisable, data_gr1_non_connexe, data_gr1_identique;
	private static ShortestPathData data_gr2_faisable, data_gr2_non_connexe, data_gr2_identique;
	private static ShortestPathData data_gr3_faisable, data_gr3_identique;
	
	private static AStarAlgorithm solD_data_gr1_faisable, solD_data_gr1_non_connexe, solD_data_gr1_identique;
	private static AStarAlgorithm solD_data_gr2_faisable, solD_data_gr2_non_connexe, solD_data_gr2_identique;
	private static AStarAlgorithm solD_data_gr3_faisable, solD_data_gr3_identique;
	
	private static DijkstraAlgorithm solB_data_gr1_faisable;
	private static DijkstraAlgorithm solB_data_gr2_faisable;
	private static DijkstraAlgorithm solB_data_gr3_faisable;
	
	private static Path pathD_data_gr1_faisable;
	private static Path pathD_data_gr2_faisable;
	private static Path pathD_data_gr3_faisable;
	
	private static Path pathB_data_gr1_faisable;
	private static Path pathB_data_gr2_faisable;
	private static Path pathB_data_gr3_faisable;
	
	
    @BeforeClass
	public static void initAll() throws IOException {
		
	// Call the right Arc Inspector
	ArcInspector arc_insp = ArcInspectorFactory.getAllFilters().get(0);
	
    // Visit these directory to see the list of available files on Commetud.
		
    String mapName1 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/new-zealand.mapgr";
    String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";
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
    
    data_gr1_faisable = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(243205) , arc_insp);
    data_gr1_non_connexe = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(229756) , arc_insp);
    data_gr1_identique = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(327010) , arc_insp);
    
    data_gr2_faisable = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(38451) , arc_insp);
    data_gr2_non_connexe = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(29322) , arc_insp);
    data_gr2_identique = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(5287) , arc_insp);
    
    data_gr3_faisable = new ShortestPathData(graph3, graph3.getNodes().get(822642), graph3.getNodes().get(323463) , arc_insp);
    data_gr3_identique = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(323463) , arc_insp);
    
    /** Dijkstra solutions definitions */
    
    solD_data_gr1_faisable = new AStarAlgorithm(data_gr1_faisable) ;
    solD_data_gr1_non_connexe = new AStarAlgorithm(data_gr1_non_connexe) ;
    solD_data_gr1_identique = new AStarAlgorithm(data_gr1_identique) ;
    
    solD_data_gr2_faisable = new AStarAlgorithm(data_gr2_faisable) ;
    solD_data_gr2_non_connexe = new AStarAlgorithm(data_gr2_non_connexe) ;
    solD_data_gr2_identique = new AStarAlgorithm(data_gr2_identique) ;
    
    solD_data_gr3_faisable = new AStarAlgorithm(data_gr3_faisable) ;
    solD_data_gr3_identique = new AStarAlgorithm(data_gr3_identique) ;
    
    
    /** Bellman-Ford solutions definitions */

    solB_data_gr1_faisable = new DijkstraAlgorithm(data_gr1_faisable) ;
    solB_data_gr2_faisable = new DijkstraAlgorithm(data_gr2_faisable) ;
    solB_data_gr3_faisable = new DijkstraAlgorithm(data_gr3_faisable) ;
    
    pathD_data_gr1_faisable = solD_data_gr1_faisable.doRun().getPath();
	pathD_data_gr2_faisable = solD_data_gr2_faisable.doRun().getPath();
	pathD_data_gr3_faisable = solD_data_gr3_faisable.doRun().getPath();
	
	pathB_data_gr1_faisable = solB_data_gr1_faisable.doRun().getPath();
	pathB_data_gr2_faisable = solB_data_gr2_faisable.doRun().getPath();
	pathB_data_gr3_faisable = solB_data_gr3_faisable.doRun().getPath();

    
    
	}
    
    @Test
    public void testValid() {
    	assertTrue(solD_data_gr1_faisable.doRun().getPath().isValid());
    	assertTrue(solD_data_gr2_faisable.doRun().getPath().isValid());
    	assertTrue(solD_data_gr3_faisable.doRun().getPath().isValid());
    	assertEquals(solD_data_gr1_non_connexe.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_non_connexe.doRun().getPath(),null);
    	assertEquals(solD_data_gr1_identique.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_identique.doRun().getPath(),null);
    	assertEquals(solD_data_gr3_identique.doRun().getPath(),null);
    }
    
    @Test
    public void testTravelTime() {
        assertEquals(pathD_data_gr1_faisable.getMinimumTravelTime(), pathB_data_gr1_faisable.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr2_faisable.getMinimumTravelTime(), pathB_data_gr2_faisable.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr3_faisable.getMinimumTravelTime(), pathB_data_gr3_faisable.getMinimumTravelTime(),1e-6);   	
    }
    
    
    
    @Test
    public void testLength() {
    	
        assertEquals(pathD_data_gr1_faisable.getLength(), pathB_data_gr1_faisable.getLength(),1e-6);
        assertEquals(pathD_data_gr2_faisable.getLength(), pathB_data_gr2_faisable.getLength(),1e-6);
        assertEquals(pathD_data_gr3_faisable.getLength(), pathB_data_gr3_faisable.getLength(),1e-6);    	
    }
    
    //Vitesse differentes
    //sous chemin
    // inverser origine et destination
    
}