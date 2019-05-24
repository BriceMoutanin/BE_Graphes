package org.insa.algo.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.*;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class AStarValidityTest {
	
	private static Graph graph1, graph2, graph3 ;
	
	// All variables for the length tests
	private static ShortestPathData data_gr1_faisable_l, data_gr1_non_connexe_l, data_gr1_identique_l;
	private static ShortestPathData data_gr2_faisable_l, data_gr2_non_connexe_l, data_gr2_identique_l;
	private static ShortestPathData data_gr3_faisable_l, data_gr3_identique_l;
	private static ShortestPathData data_gr1_faisablev2_l;
	private static ShortestPathData data_gr2_faisablev2_l;
	private static ShortestPathData data_gr3_faisablev2_l;
	
	private static AStarAlgorithm solD_data_gr1_faisable_l, solD_data_gr1_non_connexe_l, solD_data_gr1_identique_l;
	private static AStarAlgorithm solD_data_gr2_faisable_l, solD_data_gr2_non_connexe_l, solD_data_gr2_identique_l;
	private static AStarAlgorithm solD_data_gr3_faisable_l, solD_data_gr3_identique_l;
	private static AStarAlgorithm solD_data_gr1_faisablev2_l;
	private static AStarAlgorithm solD_data_gr2_faisablev2_l;
	private static AStarAlgorithm solD_data_gr3_faisablev2_l;
	
	private static DijkstraAlgorithm solB_data_gr1_faisable_l;
	private static DijkstraAlgorithm solB_data_gr2_faisable_l;
	private static DijkstraAlgorithm solB_data_gr3_faisable_l;
	
	private static Path pathD_data_gr1_faisable_l;
	private static Path pathD_data_gr2_faisable_l;
	private static Path pathD_data_gr3_faisable_l;
	
	private static Path pathD_data_gr1_faisablev2_l;
	private static Path pathD_data_gr2_faisablev2_l;
	private static Path pathD_data_gr3_faisablev2_l;
	
	private static Path pathB_data_gr1_faisable_l;
	private static Path pathB_data_gr2_faisable_l;
	private static Path pathB_data_gr3_faisable_l;
	
	// All variables for the time tests
	private static ShortestPathData data_gr1_faisable_t, data_gr1_non_connexe_t, data_gr1_identique_t;
	private static ShortestPathData data_gr2_faisable_t, data_gr2_non_connexe_t, data_gr2_identique_t;
	private static ShortestPathData data_gr3_faisable_t, data_gr3_identique_t;
	private static ShortestPathData data_gr1_faisablev2_t;
	private static ShortestPathData data_gr2_faisablev2_t;
	private static ShortestPathData data_gr3_faisablev2_t;
		
	private static AStarAlgorithm solD_data_gr1_faisable_t, solD_data_gr1_non_connexe_t, solD_data_gr1_identique_t;
	private static AStarAlgorithm solD_data_gr2_faisable_t, solD_data_gr2_non_connexe_t, solD_data_gr2_identique_t;
	private static AStarAlgorithm solD_data_gr3_faisable_t, solD_data_gr3_identique_t;
	private static AStarAlgorithm solD_data_gr1_faisablev2_t;
	private static AStarAlgorithm solD_data_gr2_faisablev2_t;
	private static AStarAlgorithm solD_data_gr3_faisablev2_t;
		
	private static DijkstraAlgorithm solB_data_gr1_faisable_t;
	private static DijkstraAlgorithm solB_data_gr2_faisable_t;
	private static DijkstraAlgorithm solB_data_gr3_faisable_t;
		
	private static Path pathD_data_gr1_faisable_t;
	private static Path pathD_data_gr2_faisable_t;
	private static Path pathD_data_gr3_faisable_t;
	
	private static Path pathD_data_gr1_faisablev2_t;
	private static Path pathD_data_gr2_faisablev2_t;
	private static Path pathD_data_gr3_faisablev2_t;
		
	private static Path pathB_data_gr1_faisable_t;
	private static Path pathB_data_gr2_faisable_t;
	private static Path pathB_data_gr3_faisable_t;
	
	
    @BeforeClass
	public static void initAll() throws IOException {
		
	// Call the right Arc Inspectors
	ArcInspector arc_insp_length = ArcInspectorFactory.getAllFilters().get(0);
	ArcInspector arc_insp_time = ArcInspectorFactory.getAllFilters().get(2);
	
    // Visit these directory to see the list of available files on Commetud.
		
    String mapName1 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/new-zealand.mapgr";
    String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";
    String mapName3 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/fractal-spiral.mapgr";
    
    // The following lines are just supposed to allow us to run our tests on our own computers //
    // String mapName1 = "/Users/bricemoutanin/Downloads/new-zealand.mapgr";
    // String mapName2 = "/Users/bricemoutanin/Downloads//paris.mapgr";
    // String mapName3 = "/Users/bricemoutanin/Downloads/fractal-spiral.mapgr";
	
    //String mapName1 = "/home/babar/Bureau/maps/new-zealand.mapgr";
    //String mapName2 = "/home/babar/Bureau/maps/paris.mapgr";
    //String mapName3 = "/home/babar/Bureau/maps/fractal-spiral.mapgr";
	
    /////////////////////////////////////////////////////////////////////////////////////////////

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
    
    /** Data constructions for the length tests *////////////////////////////////////////////////////////////////////////////////////////
    
    data_gr1_faisable_l = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(243205) , arc_insp_length);
    data_gr1_non_connexe_l = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(229756) , arc_insp_length);
    data_gr1_identique_l = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(327010) , arc_insp_length);
    
    data_gr2_faisable_l = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(38451) , arc_insp_length);
    data_gr2_non_connexe_l = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(29322) , arc_insp_length);
    data_gr2_identique_l = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(5287) , arc_insp_length);
    
    data_gr3_faisable_l = new ShortestPathData(graph3, graph3.getNodes().get(822642), graph3.getNodes().get(323463) , arc_insp_length);
    data_gr3_identique_l = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(323463) , arc_insp_length);
    
    /** Data construction with reversed destination and origin */
    
    data_gr1_faisablev2_l = new ShortestPathData(graph1, graph1.getNodes().get(243205), graph1.getNodes().get(327010) , arc_insp_length);
    data_gr2_faisablev2_l = new ShortestPathData(graph2, graph2.getNodes().get(38451), graph2.getNodes().get(5287) , arc_insp_length);
    data_gr3_faisablev2_l = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(822642) , arc_insp_length);
    
    /** AStar solutions definitions */
    
    solD_data_gr1_faisable_l = new AStarAlgorithm(data_gr1_faisable_l) ;
    solD_data_gr1_non_connexe_l = new AStarAlgorithm(data_gr1_non_connexe_l) ;
    solD_data_gr1_identique_l = new AStarAlgorithm(data_gr1_identique_l) ;
    
    solD_data_gr2_faisable_l = new AStarAlgorithm(data_gr2_faisable_l) ;
    solD_data_gr2_non_connexe_l = new AStarAlgorithm(data_gr2_non_connexe_l) ;
    solD_data_gr2_identique_l = new AStarAlgorithm(data_gr2_identique_l) ;
    
    solD_data_gr3_faisable_l = new AStarAlgorithm(data_gr3_faisable_l) ;
    solD_data_gr3_identique_l = new AStarAlgorithm(data_gr3_identique_l) ;
    
    /* AStar reversed solutions definitions */
    
    solD_data_gr1_faisablev2_l = new AStarAlgorithm(data_gr1_faisablev2_l) ;
    solD_data_gr2_faisablev2_l = new AStarAlgorithm(data_gr2_faisablev2_l) ;
    solD_data_gr3_faisablev2_l = new AStarAlgorithm(data_gr3_faisablev2_l) ;
    
    
    /** Dijkstra solutions definitions */

    solB_data_gr1_faisable_l = new DijkstraAlgorithm(data_gr1_faisable_l) ;
    solB_data_gr2_faisable_l = new DijkstraAlgorithm(data_gr2_faisable_l) ;
    solB_data_gr3_faisable_l = new DijkstraAlgorithm(data_gr3_faisable_l) ;
    
    /** Path definitions */
    
    pathD_data_gr1_faisable_l = solD_data_gr1_faisable_l.doRun().getPath();
	pathD_data_gr2_faisable_l = solD_data_gr2_faisable_l.doRun().getPath();
	pathD_data_gr3_faisable_l = solD_data_gr3_faisable_l.doRun().getPath();
	
	pathB_data_gr1_faisable_l = solB_data_gr1_faisable_l.doRun().getPath();
	pathB_data_gr2_faisable_l = solB_data_gr2_faisable_l.doRun().getPath();
	pathB_data_gr3_faisable_l = solB_data_gr3_faisable_l.doRun().getPath();
	
	/** Reversed Path definitions */
	
    pathD_data_gr1_faisablev2_l = solD_data_gr1_faisablev2_l.doRun().getPath();
	pathD_data_gr2_faisablev2_l = solD_data_gr2_faisablev2_l.doRun().getPath();
	pathD_data_gr3_faisablev2_l = solD_data_gr3_faisablev2_l.doRun().getPath();
	
	

	/** Data constructions for the time tests *////////////////////////////////////////////////////////////////////////////////////////
    
    data_gr1_faisable_t = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(243205) , arc_insp_time);
    data_gr1_non_connexe_t = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(229756) , arc_insp_time);
    data_gr1_identique_t = new ShortestPathData(graph1, graph1.getNodes().get(327010), graph1.getNodes().get(327010) , arc_insp_time);
    
    data_gr2_faisable_t = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(38451) , arc_insp_time);
    data_gr2_non_connexe_t = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(29322) , arc_insp_time);
    data_gr2_identique_t = new ShortestPathData(graph2, graph2.getNodes().get(5287), graph2.getNodes().get(5287) , arc_insp_time);
    
    data_gr3_faisable_t = new ShortestPathData(graph3, graph3.getNodes().get(822642), graph3.getNodes().get(323463) , arc_insp_time);
    data_gr3_identique_t = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(323463) , arc_insp_time);
    
    
    /** Data construction with reversed destination and origin */
    
    data_gr1_faisablev2_t = new ShortestPathData(graph1, graph1.getNodes().get(243205), graph1.getNodes().get(327010) , arc_insp_time);
    data_gr2_faisablev2_t = new ShortestPathData(graph2, graph2.getNodes().get(38451), graph2.getNodes().get(5287) , arc_insp_time);
    data_gr3_faisablev2_t = new ShortestPathData(graph3, graph3.getNodes().get(323463), graph3.getNodes().get(822642) , arc_insp_time);
    
    /** AStar solutions definitions */
    
    solD_data_gr1_faisable_t = new AStarAlgorithm(data_gr1_faisable_t) ;
    solD_data_gr1_non_connexe_t = new AStarAlgorithm(data_gr1_non_connexe_t) ;
    solD_data_gr1_identique_t = new AStarAlgorithm(data_gr1_identique_t) ;
    
    solD_data_gr2_faisable_t = new AStarAlgorithm(data_gr2_faisable_t) ;
    solD_data_gr2_non_connexe_t = new AStarAlgorithm(data_gr2_non_connexe_t) ;
    solD_data_gr2_identique_t = new AStarAlgorithm(data_gr2_identique_t) ;
    
    solD_data_gr3_faisable_t = new AStarAlgorithm(data_gr3_faisable_t) ;
    solD_data_gr3_identique_t = new AStarAlgorithm(data_gr3_identique_t) ;
    
    /* AStar reversed solutions definitions */
    
    solD_data_gr1_faisablev2_t = new AStarAlgorithm(data_gr1_faisablev2_t) ;
    solD_data_gr2_faisablev2_t = new AStarAlgorithm(data_gr2_faisablev2_t) ;
    solD_data_gr3_faisablev2_t = new AStarAlgorithm(data_gr3_faisablev2_t) ;
    
    
    /** Dijkstra solutions definitions */

    solB_data_gr1_faisable_t = new DijkstraAlgorithm(data_gr1_faisable_t) ;
    solB_data_gr2_faisable_t = new DijkstraAlgorithm(data_gr2_faisable_t) ;
    solB_data_gr3_faisable_t = new DijkstraAlgorithm(data_gr3_faisable_t) ;
    
    /** Path definitions */
    
    pathD_data_gr1_faisable_t = solD_data_gr1_faisable_t.doRun().getPath();
	pathD_data_gr2_faisable_t = solD_data_gr2_faisable_t.doRun().getPath();
	pathD_data_gr3_faisable_t = solD_data_gr3_faisable_t.doRun().getPath();
	
	pathB_data_gr1_faisable_t = solB_data_gr1_faisable_t.doRun().getPath();
	pathB_data_gr2_faisable_t = solB_data_gr2_faisable_t.doRun().getPath();
	pathB_data_gr3_faisable_t = solB_data_gr3_faisable_t.doRun().getPath();
	
	/** Reversed Path definitions */
	
    pathD_data_gr1_faisablev2_t = solD_data_gr1_faisablev2_t.doRun().getPath();
	pathD_data_gr2_faisablev2_t = solD_data_gr2_faisablev2_t.doRun().getPath();
	pathD_data_gr3_faisablev2_t = solD_data_gr3_faisablev2_t.doRun().getPath();
    
	}
    
    @Test
    public void testValid_LengthMode() {
    	assertTrue(pathD_data_gr1_faisable_l.isValid());
    	assertTrue(pathD_data_gr2_faisable_l.isValid());
    	assertTrue(pathD_data_gr3_faisable_l.isValid());
    	assertEquals(solD_data_gr1_non_connexe_l.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_non_connexe_l.doRun().getPath(),null);
    	assertEquals(solD_data_gr1_identique_l.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_identique_l.doRun().getPath(),null);
    	assertEquals(solD_data_gr3_identique_l.doRun().getPath(),null);
    }
    
    @Test
    public void testValid_TimeMode() {
    	assertTrue(pathD_data_gr1_faisable_t.isValid());
    	assertTrue(pathD_data_gr2_faisable_t.isValid());
    	assertTrue(pathD_data_gr3_faisable_t.isValid());
    	assertEquals(solD_data_gr1_non_connexe_t.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_non_connexe_t.doRun().getPath(),null);
    	assertEquals(solD_data_gr1_identique_t.doRun().getPath(),null);
    	assertEquals(solD_data_gr2_identique_t.doRun().getPath(),null);
    	assertEquals(solD_data_gr3_identique_t.doRun().getPath(),null);
    }
    
    @Test
    public void testTravelTime_LengthMode() {
        assertEquals(pathD_data_gr1_faisable_l.getMinimumTravelTime(), pathB_data_gr1_faisable_l.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr2_faisable_l.getMinimumTravelTime(), pathB_data_gr2_faisable_l.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr3_faisable_l.getMinimumTravelTime(), pathB_data_gr3_faisable_l.getMinimumTravelTime(),1e-6);    	
    }
    
    @Test
    public void testTravelTime_TimeMode() {
        assertEquals(pathD_data_gr1_faisable_t.getMinimumTravelTime(), pathB_data_gr1_faisable_t.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr2_faisable_t.getMinimumTravelTime(), pathB_data_gr2_faisable_t.getMinimumTravelTime(),1e-6);
        assertEquals(pathD_data_gr3_faisable_t.getMinimumTravelTime(), pathB_data_gr3_faisable_t.getMinimumTravelTime(),1e-6);    	
    }
    
    @Test
    public void testLength_LengthMode() {
        assertEquals(pathD_data_gr1_faisable_l.getLength(), pathB_data_gr1_faisable_l.getLength(),1e-6);
        assertEquals(pathD_data_gr2_faisable_l.getLength(), pathB_data_gr2_faisable_l.getLength(),1e-6);
        assertEquals(pathD_data_gr3_faisable_l.getLength(), pathB_data_gr3_faisable_l.getLength(),1e-6);    	
    }
    
    @Test
    public void testLength_TimeMode() {
        assertEquals(pathD_data_gr1_faisable_t.getLength(), pathB_data_gr1_faisable_t.getLength(),1e-6);
        assertEquals(pathD_data_gr2_faisable_t.getLength(), pathB_data_gr2_faisable_t.getLength(),1e-6);
        assertEquals(pathD_data_gr3_faisable_t.getLength(), pathB_data_gr3_faisable_t.getLength(),1e-6);    	
    }
    
    @Test
    public void NoOracle_reversedPathLengthEquality_LengthMode() {
        assertEquals(pathD_data_gr1_faisable_l.getLength(), pathD_data_gr1_faisablev2_l.getLength(),1e3);
        assertEquals(pathD_data_gr2_faisable_l.getLength(), pathD_data_gr2_faisablev2_l.getLength(),1e3);
        assertEquals(pathD_data_gr3_faisable_l.getLength(), pathD_data_gr3_faisablev2_l.getLength(),1e3);    	
    }
    
    @Test
    public void NoOracle_reversedPathTimeEquality_TimeMode() {
        assertEquals(pathD_data_gr1_faisable_t.getMinimumTravelTime(), pathD_data_gr1_faisablev2_t.getMinimumTravelTime(),2e2);
        assertEquals(pathD_data_gr2_faisable_t.getMinimumTravelTime(), pathD_data_gr2_faisablev2_t.getMinimumTravelTime(),2e2);
        assertEquals(pathD_data_gr3_faisable_t.getMinimumTravelTime(), pathD_data_gr3_faisablev2_t.getMinimumTravelTime(),2e2);    	
    }
    
    //Vitesse differentes
    //sous chemin
    // inverser origine et destination
    
}
