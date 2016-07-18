package com.albion.common.graph;

import java.util.List;

public class Vertex{
	
	final private int id;
	private List<Edge> edgeList;
	private boolean traveled;
	
	
	public Vertex(int aId, List<Edge> aList){
		id = aId;
		setEdgeList(aList);
		traveled = false;
	}
	public Vertex(int aId){
		id = aId;
		traveled = false;
	}
	
	public int getId() {
		return id;
	}

	public List<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(List<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	public boolean isTraveled() {
		return traveled;
	}
	public void setTraveled(boolean traveled) {
		this.traveled = traveled;
	}

	public String toString(){
		StringBuffer s = new StringBuffer();
		s.append("[Vertex ID]-["+id+"]\n");
		s.append("[EDGES]\n");
		for(Edge edge:edgeList){
			s.append(edge.toString());
		}
		return s.toString();
	}
	
	
}
