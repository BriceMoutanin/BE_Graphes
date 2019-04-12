package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.List;

import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Label;
import org.insa.graph.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        int nbNodes = graph.size();
        
        /* Binary Heap creation */
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ;
        
        /* Get the start node */
        Node origin_node = data.getOrigin();
        
        /* Label tab creation to stock the created labels
         * Insert the start node label in the Label list */
        Label[] label_tab = new Label[nbNodes];
        label_tab[origin_node.getId()]=new Label(origin_node.getId(),0);
        
        /* Fill the label_tab with infinite costs for all nodes except the origin one */
        for(int i=0;i<nbNodes;i++) {
        	if(i != origin_node.getId()) {
        		label_tab[i]=new Label(i,Float.POSITIVE_INFINITY);
        	}
        }
        
        /* Insert the start node label in the Binary Heap */
        tas.insert(label_tab[0]);
        
        while(!tas.isEmpty()){
        	Label current_label = tas.deleteMin();
        	float current_length = current_label.getCost();
        	current_label.setMarque(true);
        	for(Arc arc: graph.getNodes().get(current_label.getCurrent_node()).getSuccessors()) {
        		Node successor = arc.getDestination();
        		label_tab[successor.getId()].setFather(arc);
        		if(Float.compare(current_length+arc.getLength(),label_tab[successor.getId()].getCost()) < 0) { // If the new cost is lower than the old one
        			label_tab[successor.getId()].setCost(current_length+arc.getLength());
        			int label_index_heap = tas.array
        			if ()
        		}
        	}
        }
        
        
        
        
        
        
        
        
        
        
        
        
        return solution;
    }

}
