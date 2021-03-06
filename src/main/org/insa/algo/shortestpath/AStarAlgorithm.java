package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Label;
import org.insa.graph.LabelStar;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class AStarAlgorithm extends DijkstraAlgorithm {

	public  int visited_nodes = 0;
	
	public long duration = 0;
	
	public int taille_max_tas = 0;
	
	public int marked_nodes = 0;
	
    public AStarAlgorithm(ShortestPathData data) {
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
        
        // Initialize array of predecessors.
        Arc[] predecessorArcs = new Arc[nbNodes];
        
        /* Get the start node */
        Node origin_node = data.getOrigin();
        
        /* Get the destination node */
        Node destination_node = data.getDestination();
        
        /* Label tab creation to stock the created labels
         * Insert the start node label in the Label list */
        LabelStar[] label_tab = new LabelStar[nbNodes];
        label_tab[origin_node.getId()]=new LabelStar(graph,origin_node.getId(),0,destination_node.getId(),data.getMode());
        
        /* Fill the label_tab with infinite costs for all nodes except the origin one */
        for(int i=0;i<nbNodes;i++) {
        	if(i != origin_node.getId()) {
        		label_tab[i]=new LabelStar(graph,i,Float.POSITIVE_INFINITY,destination_node.getId(),data.getMode());
        	}
        }
        
        /* Insert the start node label in the Binary Heap */
        tas.insert(label_tab[origin_node.getId()]);
        
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        //System.out.println("Départ = " + data.getOrigin().getId() + " | Arrivée = " + data.getDestination().getId());
        
        LabelStar current_label;
        
        long debut = System.currentTimeMillis();
        
        while(!tas.isEmpty()){
        	
        	// Update of the maximum size of the heap
        	if(tas.size() > this.taille_max_tas) {
        		this.taille_max_tas=tas.size();
        	}
        	
        	current_label = (LabelStar) tas.deleteMin();
        	//tas.print();
        	//System.out.println("On enlève : " + current_label.toString());
        	label_tab[current_label.getCurrent_node()].setMarque(true); this.marked_nodes++;
        	if (current_label.getFather() != null) {
        		predecessorArcs[current_label.getFather().getDestination().getId()] = current_label.getFather(); // Add the previous arc to the solution
        	}
        	double current_length = current_label.getCost();
        	current_label.setMarque(true);
        	for(Arc arc: graph.getNodes().get(current_label.getCurrent_node()).getSuccessors()) { // For each successors of the current node
        		// Small test to check allowed roads...
                if (!data.isAllowed(arc)) {
                    continue;
                }
        		Node successor = arc.getDestination();
        		if (!label_tab[successor.getId()].isMarque()) { // Considering only the nodes not marked
        			double olddistance = label_tab[successor.getId()].getCost();
        			double newdistance;
        			if (!Double.isInfinite(current_length)) {
        				newdistance = current_length+data.getCost(arc) ;
        			} else {
        				newdistance = data.getCost(arc) ;
        			}
        			if (Double.isInfinite(olddistance) && Double.isFinite(newdistance)) {
        				notifyNodeReached(arc.getDestination());
        				visited_nodes++;
        			}
        			if(Double.compare(newdistance,olddistance) < 0) { // If the new cost is lower than the old one
        				// Update ///////////////////////////////////////
        				if (Double.isFinite(olddistance)) { // Remove the label first if it already exists
        					tas.remove(label_tab[successor.getId()]);
        				}
        				label_tab[successor.getId()].setCost(newdistance);
        				tas.insert(label_tab[successor.getId()]);
        				label_tab[successor.getId()].setFather(arc);
        				///////////////////////////////////////////////////
        			}
        		}
        	}
        	if (current_label.getCurrent_node() == data.getDestination().getId()) {
        		break;
        	}
        }
        
        this.duration = (System.currentTimeMillis()-debut);
        
        // Destination has no predecessor, the solution is infeasible...
        if (predecessorArcs[data.getDestination().getId()] == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            while (arc != null) {
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            Path pathvalid = new Path(graph, arcs);
            
            if(pathvalid.isValid()) {
            solution = new ShortestPathSolution(data, Status.OPTIMAL, pathvalid);
            }
            else {
            	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
            }
        }	
        
        return solution;
    }

}
