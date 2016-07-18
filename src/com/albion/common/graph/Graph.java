package com.albion.common.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.albion.xml.XPathTask;


/*
 * 	private T x; private T y;private W weight;
 * 	private M id; private N data;
 * 
 */
public class Graph{
	private HashMap< Integer, Vertex> graph;
	private Vertex root = null;
	
	public Graph(String filePath){
		setGraph(new HashMap<Integer, Vertex>());
		parseInput(filePath);

	}

	public void parseInput(String filePath){
		XPathTask xpt = null;
		File inputFile = new File(filePath);
		
		try {
			xpt = new XPathTask(inputFile);
			NodeList vertex_list = xpt.processQuery("//vertices/vertex");
			
			for(int i = 0; i < vertex_list.getLength(); i++){
				Node nNode = vertex_list.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) nNode;
					
					int vertex_id = Integer.parseInt(elem.getAttribute("id"));
					Vertex vertex = new Vertex(vertex_id);
					String isRootString = elem.getAttribute("root");
					if(!"".equals(isRootString)&&isRootString.equals("true")){
						if(getRoot()!=null){
							throw new IllegalArgumentException("root was already set once.");
						}
						else{
							setRoot(vertex);
						}
					}
					
					NodeList edge_list = elem.getElementsByTagName("edge");
					addEdgesToAVertex(vertex, edge_list);
					getGraph().put(new Integer(vertex_id), vertex);
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Iterator<Integer> iter = getGraph().keySet().iterator();
		
		while(iter.hasNext()){
			Integer i = iter.next();
			Vertex v = getGraph().get(i);
//			System.out.println("VERTEX ID: "+v.getId());
		}
	}

	public Vertex addEdgesToAVertex(Vertex vertex, NodeList list){
		List<Edge> edgeList = new ArrayList<Edge>();
		for(int j = 0; j < list.getLength(); j++){
			Node mNode = list.item(j);
			if (mNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) mNode;
				String id = elem.getAttribute("id");
				String weight = elem.getAttribute("weight");
				String direction = elem.getAttribute("direction");
				
				Directions way;
				if(direction.equals("A_TO_B")){
					way = Directions.A_TO_B;
				}else if(direction.equals("B_TO_A")){
					way = Directions.B_TO_A;
				}else{
					way = Directions.BOTH;
				}

				Edge edge = new Edge(vertex.getId(), Integer.parseInt(id), way, Integer.parseInt(weight));
				
				edgeList.add(edge);
			}
			
			
		}
		vertex.setEdgeList(edgeList);

		
		return vertex;
		
	}

	public HashMap< Integer, Vertex> getGraph() {
		return graph;
	}

	public void setGraph(HashMap< Integer, Vertex> graph) {
		this.graph = graph;
	}

	public Vertex getRoot() {
		return root;
	}

	public void setRoot(Vertex root) {
		this.root = root;
	}
	
	public String toString(){
		StringBuffer s = new StringBuffer();
		s.append("[Root]-["+root.getId()+"]\n");
		for(Map.Entry<Integer, Vertex> entry:graph.entrySet()){
			Vertex v = entry.getValue();
			s.append(v.toString());
		}
		
		return s.toString();
	}
}
