package org.insa.algo.shortestpath;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.*;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class PerformanceTest {

	private static int nb_tests = 10;
	
	private static Graph graph1, graph2, graph3 ;
	
	private static ArrayList<ShortestPathData> list_NZ_t = new ArrayList<ShortestPathData>();
	private static ArrayList<ShortestPathData> list_PR_t = new ArrayList<ShortestPathData>();
	private static ArrayList<ShortestPathData> list_FS_t = new ArrayList<ShortestPathData>();
	private static ArrayList<ShortestPathData> list_NZ_l = new ArrayList<ShortestPathData>();
	private static ArrayList<ShortestPathData> list_PR_l = new ArrayList<ShortestPathData>();
	private static ArrayList<ShortestPathData> list_FS_l = new ArrayList<ShortestPathData>();
	
	//private static File outputFile = new File("/home/moutanin/Bureau/BE_Graphes/resultats_de_tests.txt");
	
	public static void initAll() throws IOException {
		// Call the right Arc Inspectors
		ArcInspector arc_insp_length = ArcInspectorFactory.getAllFilters().get(0);
		ArcInspector arc_insp_time = ArcInspectorFactory.getAllFilters().get(2);
		
	    // Visit these directory to see the list of available files on Commetud.
			
	    //String mapName1 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/new-zealand.mapgr";
	    //String mapName2 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";
	    //String mapName3 = "/home/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/fractal-spiral.mapgr";
	    
	    // The following lines are just supposed to allow us to run our tests on our own computers //
	    String mapName1 = "/Users/bricemoutanin/Downloads/new-zealand.mapgr";
	    String mapName2 = "/Users/bricemoutanin/Downloads//paris.mapgr";
	    String mapName3 = "/Users/bricemoutanin/Downloads/fractal-spiral.mapgr";
		
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
	    
	    for (int i=0;i<nb_tests;i++) {
	    	list_NZ_t.add(new ShortestPathData(graph1, graph1.getNodes().get((int)(Math.random()*graph1.getNodes().size())), graph1.getNodes().get((int)(Math.random()*graph1.getNodes().size())) , arc_insp_time));
	    	list_PR_t.add(new ShortestPathData(graph2, graph2.getNodes().get((int)(Math.random()*graph2.getNodes().size())), graph2.getNodes().get((int)(Math.random()*graph2.getNodes().size())) , arc_insp_time));
	    	list_FS_t.add(new ShortestPathData(graph3, graph3.getNodes().get((int)(Math.random()*graph3.getNodes().size())), graph3.getNodes().get((int)(Math.random()*graph3.getNodes().size())) , arc_insp_time));
	    	list_NZ_l.add(new ShortestPathData(graph1, graph1.getNodes().get((int)(Math.random()*graph1.getNodes().size())), graph1.getNodes().get((int)(Math.random()*graph1.getNodes().size())) , arc_insp_length));
	    	list_PR_l.add(new ShortestPathData(graph2, graph2.getNodes().get((int)(Math.random()*graph2.getNodes().size())), graph2.getNodes().get((int)(Math.random()*graph2.getNodes().size())) , arc_insp_length));
	    	list_FS_l.add(new ShortestPathData(graph3, graph3.getNodes().get((int)(Math.random()*graph3.getNodes().size())), graph3.getNodes().get((int)(Math.random()*graph3.getNodes().size())) , arc_insp_length));
	    }
	    
	    
	}
	
	public static void perf_test_length() throws IOException {
		DijkstraAlgorithm res_NZ_D, res_PR_D, res_FS_D;
		AStarAlgorithm res_NZ_A, res_PR_A, res_FS_A;
		ShortestPathSolution sol_NZ_D, sol_PR_D, sol_FS_D, sol_NZ_A, sol_PR_A, sol_FS_A;
		FileWriter out = null;
		
	    try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/bricemoutanin/test_performance_longueur.txt"),"utf-8"))){
	    	w.write("/** TESTS DE PERFORMANCE ENTRE DIJKSTRA ET A STAR **/\n\n");
	    	w.write("Remarque : les temps d'exécution sont donnés en millisecondes\n\n");
	    	w.write("Carte\t\t|\tOrigine\t|\tDestination\t|\tMode\t|\tNoeuds visités (D)\t|\tNoeuds visités (A*)\t|\tTemps execution (D)\t|\tTemps execution (A*)\t|\tTaille max du tas (D)\t|\tTaille max du tas (A*)");	    	w.write(System.lineSeparator());
	    
		//try {
		//	out = new FileWriter(outputFile);
		//	out.write("/** TESTS DE PERFORMANCE ENTRE DIJKSTRA ET A STAR **/\n\n");
		 //   out.write("Carte\t|\tOrigine\t|\tDestination\t|\tMode\t|\tDijkstra (noeuds visités)\t|\tAStar (noeuds visités)");
		    
			for (int i=0;i<nb_tests;i++) {
				res_NZ_D = new DijkstraAlgorithm(list_NZ_l.get(i));
				res_NZ_A = new AStarAlgorithm(list_NZ_l.get(i));
				res_PR_D = new DijkstraAlgorithm(list_PR_l.get(i));
				res_PR_A = new AStarAlgorithm(list_PR_l.get(i));
				res_FS_D = new DijkstraAlgorithm(list_FS_l.get(i));
				res_FS_A = new AStarAlgorithm(list_FS_l.get(i));
				
				sol_NZ_D = res_NZ_D.doRun();
				sol_NZ_A = res_NZ_A.doRun();
				sol_PR_D = res_PR_D.doRun();
				sol_PR_A = res_PR_A.doRun();
				sol_FS_D = res_FS_D.doRun();
				sol_FS_A = res_FS_A.doRun();
				
				if (sol_NZ_D.getPath() != null && sol_NZ_A.getPath() != null) {
					w.write("New-Zealand\t|\t"+list_NZ_l.get(i).getOrigin().getId()+"\t|\t"+list_NZ_l.get(i).getDestination().getId()+"\t\t|\tLENGTH\t|\t"+res_NZ_D.visited_nodes+"\t\t\t|\t"+res_NZ_A.visited_nodes+"\t\t\t|\t"+res_NZ_D.duration+"\t\t\t\t|\t"+res_NZ_A.duration+"\t\t|\t"+res_NZ_D.taille_max_tas+"\t\t\t|\t"+res_NZ_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
				if (sol_PR_D.getPath() != null && sol_PR_A.getPath() != null) {
					w.write("Paris\t\t|\t"+list_PR_l.get(i).getOrigin().getId()+"\t|\t"+list_PR_l.get(i).getDestination().getId()+"\t\t|\tLENGTH\t|\t"+res_PR_D.visited_nodes+"\t\t\t|\t"+res_PR_A.visited_nodes+"\t\t\t|\t"+res_PR_D.duration+"\t\t\t\t|\t"+res_PR_A.duration+"\t\t|\t"+res_PR_D.taille_max_tas+"\t\t\t|\t"+res_PR_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
				if (sol_FS_D.getPath() != null && sol_FS_A.getPath() != null) {
					w.write("Fractal Spiral\t|\t"+list_FS_l.get(i).getOrigin().getId()+"\t|\t"+list_FS_l.get(i).getDestination().getId()+"\t\t|\tLENGTH\t|\t"+res_FS_D.visited_nodes+"\t\t\t|\t"+res_FS_A.visited_nodes+"\t\t\t|\t"+res_FS_D.duration+"\t\t\t\t|\t"+res_FS_A.duration+"\t\t|\t"+res_FS_D.taille_max_tas+"\t\t\t|\t"+res_FS_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
		    }
		} catch(IOException e) {System.out.println("AAAAAAH ERREUR DE FICHIER");}
	}
	
	public static void perf_test_time() throws IOException {
		DijkstraAlgorithm res_NZ_D, res_PR_D, res_FS_D;
		AStarAlgorithm res_NZ_A, res_PR_A, res_FS_A;
		ShortestPathSolution sol_NZ_D, sol_PR_D, sol_FS_D, sol_NZ_A, sol_PR_A, sol_FS_A;
		FileWriter out = null;
		
	    try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/bricemoutanin/test_performance_temps.txt"),"utf-8"))){
	    	w.write("/** TESTS DE PERFORMANCE ENTRE DIJKSTRA ET A STAR **/\n\n");
	    	w.write("Remarque : les temps d'exécution sont donnés en millisecondes\n\n");
	    	w.write("Carte\t\t|\tOrigine\t|\tDestination\t|\tMode\t|\tNoeuds visités (D)\t|\tNoeuds visités (A*)\t|\tTemps execution (D)\t|\tTemps execution (A*)\t|\tTaille max du tas (D)\t|\tTaille max du tas (A*)");
	    	w.write(System.lineSeparator());
	    
		//try {
		//	out = new FileWriter(outputFile);
		//	out.write("/** TESTS DE PERFORMANCE ENTRE DIJKSTRA ET A STAR **/\n\n");
		 //   out.write("Carte\t|\tOrigine\t|\tDestination\t|\tMode\t|\tDijkstra (noeuds visités)\t|\tAStar (noeuds visités)");
		    
			for (int i=0;i<nb_tests;i++) {
				res_NZ_D = new DijkstraAlgorithm(list_NZ_t.get(i));
				res_NZ_A = new AStarAlgorithm(list_NZ_t.get(i));
				res_PR_D = new DijkstraAlgorithm(list_PR_t.get(i));
				res_PR_A = new AStarAlgorithm(list_PR_t.get(i));
				res_FS_D = new DijkstraAlgorithm(list_FS_t.get(i));
				res_FS_A = new AStarAlgorithm(list_FS_t.get(i));
				
				sol_NZ_D = res_NZ_D.doRun();
				sol_NZ_A = res_NZ_A.doRun();
				sol_PR_D = res_PR_D.doRun();
				sol_PR_A = res_PR_A.doRun();
				sol_FS_D = res_FS_D.doRun();
				sol_FS_A = res_FS_A.doRun();
				
				if (sol_NZ_D.getPath() != null && sol_NZ_A.getPath() != null) {
					w.write("New-Zealand\t|\t"+list_NZ_t.get(i).getOrigin().getId()+"\t|\t"+list_NZ_t.get(i).getDestination().getId()+"\t\t|\tTIME\t|\t"+res_NZ_D.visited_nodes+"\t\t\t|\t"+res_NZ_A.visited_nodes+"\t\t\t|\t"+res_NZ_D.duration+"\t\t\t\t|\t"+res_NZ_A.duration+"\t\t|\t"+res_NZ_D.taille_max_tas+"\t\t\t|\t"+res_NZ_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
				if (sol_PR_D.getPath() != null && sol_PR_A.getPath() != null) {
					w.write("Paris\t\t|\t"+list_PR_t.get(i).getOrigin().getId()+"\t|\t"+list_PR_t.get(i).getDestination().getId()+"\t\t|\tTIME\t|\t"+res_PR_D.visited_nodes+"\t\t\t|\t"+res_PR_A.visited_nodes+"\t\t\t|\t"+res_PR_D.duration+"\t\t\t\t|\t"+res_PR_A.duration+"\t\t|\t"+res_PR_D.taille_max_tas+"\t\t\t|\t"+res_PR_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
				if (sol_FS_D.getPath() != null && sol_FS_A.getPath() != null) {
					w.write("Fractal Spiral\t|\t"+list_FS_t.get(i).getOrigin().getId()+"\t|\t"+list_FS_t.get(i).getDestination().getId()+"\t\t|\tTIME\t|\t"+res_FS_D.visited_nodes+"\t\t\t|\t"+res_FS_A.visited_nodes+"\t\t\t|\t"+res_FS_D.duration+"\t\t\t\t|\t"+res_FS_A.duration+"\t\t|\t"+res_FS_D.taille_max_tas+"\t\t\t|\t"+res_FS_A.taille_max_tas);
					w.write(System.lineSeparator());
				}
				
		    }
		} catch(IOException e) {System.out.println("AAAAAAH ERREUR DE FICHIER");}
	}
	
	public static void main (String[] args) {
		try {
			initAll();
			perf_test_length();
			perf_test_time();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
