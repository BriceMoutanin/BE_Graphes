/**
 * 
 */
package org.insa.graph;

import org.insa.algo.AbstractInputData.Mode;

/**
 * @author bricemoutanin
 *
 */
public class LabelStar extends Label implements Comparable<Label> {

	private Graph gr;
	
	private int destination_node;
	
	private double estimated_cost ;
	
	/**
	 * @param current_node
	 * @param cost
	 * @param father
	 */
	public LabelStar(Graph gr, int current_node, double cost, Arc father, int destination_node, Mode mode) {
		super(current_node, cost, father);
		this.gr = gr;
		this.destination_node = destination_node;
		this.estimated_cost = this.gr.getNodes().get(this.destination_node).getPoint().distanceTo(this.gr.getNodes().get(this.getCurrent_node()).getPoint());
		if (mode == Mode.TIME) {
			this.estimated_cost = (this.estimated_cost/1000) / (double)this.gr.getGraphInformation().getMaximumSpeed();
		}
	}

	/**
	 * @param current_node
	 * @param cost
	 */
	public LabelStar(Graph gr, int current_node, double cost, int destination_node, Mode mode) {
		super(current_node, cost);
		this.gr = gr;
		this.destination_node = destination_node;
		this.estimated_cost = this.gr.getNodes().get(this.destination_node).getPoint().distanceTo(this.gr.getNodes().get(this.getCurrent_node()).getPoint());
		if (mode == Mode.TIME) {
			this.estimated_cost = (this.estimated_cost/1000) / (double)this.gr.getGraphInformation().getMaximumSpeed();
		}
	}
	
	public double getEstimated_Cost() {
		return this.estimated_cost;
	}
	
	@Override
	public double getTotalCost() {
		return (this.getCost() + this.estimated_cost);
	}

}
