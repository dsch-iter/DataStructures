package com.albion.common.search.graph;

import java.util.ArrayList;
import java.util.List;

import com.albion.common.graph.Edge;
import com.albion.common.graph.Graph;
import com.albion.common.graph.Vertex;

public class BreathFirstSearch {

	public static Vertex locate(Graph graph, Integer id){
		List<Vertex> queue = new ArrayList<Vertex>();
		Vertex root = graph.getRoot();
		
		queue.add(root);
		
		while(!queue.isEmpty()){
			Vertex v = queue.get(0);
			queue.remove(0);
			
			if(v.getId() == id.intValue()){
				v.setTraveled(true);
				return v;
			}
			
			List<Edge> edgeList = v.getEdgeList();
			for(Edge edge:edgeList){
				Vertex x = graph.getGraph().get(edge.getX());
				Vertex y = graph.getGraph().get(edge.getY());
				if(x.isTraveled() == false){
					x.setTraveled(true);
					queue.add(x);
				}
				if(y.isTraveled() == false){
					y.setTraveled(true);
					queue.add(y);
				}
			}
		}

		return null;
	}

}
