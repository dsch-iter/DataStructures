package com.albion.common.search.graph;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.albion.common.graph.Graph;
import com.albion.common.graph.Vertex;

public class BreathFirstSearchTest {

  @Test
  public void testBFS01() {
	  String filePath2 = "src/com/albion/common/data/graph01.xml";
 
	  Graph g = new Graph(filePath2);
//	  System.out.println("HEY: "+g.toString());
	  Vertex v = BreathFirstSearch.locate(g, 4);
	  Assert.assertNotNull(v);
	  Assert.assertEquals(v.getId(), 4);
	  Vertex v2 = BreathFirstSearch.locate(g, 5);
	  Assert.assertNull(v2);
  }
}
