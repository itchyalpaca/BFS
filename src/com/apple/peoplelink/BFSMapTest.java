package com.apple.peoplelink;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BFSMapTest {
	
	private static Set<Person> graph = new HashSet<Person>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
				//Create a network of people		
				Person sneezy = new Person("Sneezy");//1		
				Person doc = new Person("Doc");//2
				Person grumpy = new Person("Grumpy");//3
				Person happy = new Person("Happy");//4
				Person sleepy = new Person("Sleepy");//5
				Person bashful = new Person("Bashful");//6
				Person dopey = new Person("Dopey");//7
				Person pluto = new Person("Pluto");//8
				Person optimus = new Person("Optimus");//9
				Person donald = new Person("Donald");//10		

				//connections of sneezy/1
				Set<Person> connections_of_sneezy = new HashSet<Person>();
				connections_of_sneezy.add(doc);//2
				connections_of_sneezy.add(donald);//10;
				connections_of_sneezy.add(optimus);//9
				connections_of_sneezy.add(sleepy);//5		
						
				//connections of doc/2
				Set<Person> connections_of_doc = new HashSet<Person>();
				connections_of_doc.add(bashful);//6
				connections_of_doc.add(happy);//4
				connections_of_doc.add(pluto);//8		
				
				//connections of grumpy/3
				Set<Person> connections_of_grumpy = new HashSet<Person>();
				connections_of_grumpy.add(dopey);//7
				connections_of_grumpy.add(doc);//2
				connections_of_grumpy.add(donald);//10				
				
				//connections of happy/4
				Set<Person> connections_of_happy = new HashSet<Person>();
				connections_of_happy.add(pluto);//8
				connections_of_happy.add(sneezy);//1
				connections_of_happy.add(optimus);//9
				
				//connections of sleepy/5
				Set<Person> connections_of_sleepy = new HashSet<Person>();
				connections_of_sleepy.add(bashful);//6
				connections_of_sleepy.add(donald);//10
				connections_of_sleepy.add(doc);//2
				connections_of_sleepy.add(grumpy);//3
				
				//connections of bashful/6
				Set<Person> connections_of_bashful = new HashSet<Person>();
				connections_of_bashful.add(donald);//10
				connections_of_bashful.add(optimus);//9
				connections_of_bashful.add(sleepy);//5				
				
				//connections of dopey/7
				Set<Person> connections_of_dopey = new HashSet<Person>();
				connections_of_dopey.add(pluto);//8
				connections_of_dopey.add(grumpy);//3
				connections_of_dopey.add(doc);//2
				connections_of_dopey.add(sneezy);//1
				connections_of_dopey.add(optimus);//9
				
				//connections of pluto/8
				Set<Person> connections_of_pluto = new HashSet<Person>();
				connections_of_pluto.add(happy);//4
				connections_of_pluto.add(bashful);//6
				connections_of_pluto.add(optimus);//9
				
				//connections of optimus/9
				Set<Person> connections_of_optimus = new HashSet<Person>();
				connections_of_optimus.add(grumpy);//3
				connections_of_optimus.add(sneezy);//1
				connections_of_optimus.add(donald);//10
				
				//connections of donald/10
				Set<Person> connections_of_donald = new HashSet<Person>();
				connections_of_donald.add(happy);//4
				connections_of_donald.add(sneezy);//7
				connections_of_donald.add(doc);//2
				connections_of_donald.add(sleepy);//5			
				
				//add connections 
				sneezy.connections.addAll(connections_of_sneezy);
				doc.connections.addAll(connections_of_doc);
				grumpy.connections.addAll(connections_of_grumpy);
				happy.connections.addAll(connections_of_happy);
				sleepy.connections.addAll(connections_of_sleepy);
				bashful.connections.addAll(connections_of_bashful);
				dopey.connections.addAll(connections_of_dopey);
				pluto.connections.addAll(connections_of_pluto);
				optimus.connections.addAll(connections_of_optimus);
				donald.connections.addAll(connections_of_donald);				
				
				graph.add(sneezy);
				graph.add(doc);
				graph.add(grumpy);
				graph.add(happy);
				graph.add(sleepy);
				graph.add(bashful);
				graph.add(dopey);
				graph.add(pluto);
				graph.add(optimus);
				graph.add(donald);				
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		graph.clear();
	}

	@Before
	public void setUp() throws Exception {		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGraphSize() throws Exception {			
		assertTrue(graph.size() >= 10);//No need to worry about repeated items since graph is of a Set interface
	}
	
	@Test
	public void testFirstConnections() throws Exception {
		for (Person graphIter : graph) {
			assertTrue(graphIter.children().size() >= 3);
		}
	}
	
	@Test
	public void testCyclicity() throws Exception {
		
		Queue<Node> nodeQ = new LinkedList<Node>();
		Set<Node> visited = new HashSet<Node>();	
		int cycleCount = 0;
		boolean matchFound = false;			
				
		//iterate through all persons in the graph
		for (Node nodeIter : graph) {		
			
			nodeQ.clear();	
			visited.clear();
			nodeQ.add(nodeIter);
			visited.add(nodeIter);
			while (!nodeQ.isEmpty()) {
								
				for (Node childIter : nodeQ.poll().children()) {					
					for (Node visitedIter : visited) {
						if (visitedIter == childIter) {//the node has already been visited
							cycleCount++;
							matchFound = true;							
						} 
					}	
					visited.add(childIter);//visited is a set, duplicate entries is not a problem
					if (!matchFound) {
						nodeQ.add(childIter);						
					}
				}
			}
		}		
		assertTrue(cycleCount > 1);//must be 2 or more cycles. No cycle is acyclic, 1 cycle is uni-cyclic.
	}	

	@Test
	public void testFindConnections() {
		
		Set<Node> theoreticalResult = new HashSet<Node>();
		for (Node nodeIter : graph) {
			if (nodeIter.name().equals("Sneezy")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Donald")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Happy")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Sleepy")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Bashful")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Dopey")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Pluto")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Optimus")) {
				theoreticalResult.add(nodeIter);
			} else if (nodeIter.name().equals("Grumpy")) {
				theoreticalResult.add(nodeIter);
			}			
		}	
		
		Set<Node> actualResult = new HashSet<Node>();
		Network myNetwork = new Network();
		
		for (Node rootIter : graph) {
			if (rootIter.name().equals("Doc")) {
				actualResult.addAll(myNetwork.findConnections(rootIter,5).get(rootIter));
			}
		}		
		
		assertTrue(actualResult.equals(theoreticalResult));
	}
	
	@Test
	public void testConnected() {
		
		Network myNetwork = new Network();
		List<Node> graphList = new LinkedList<Node>();
		int node1Index = 0;
		int node2Index = 0;
		
		graphList.addAll(graph);		
		
		for (int i=0; i<graphList.size(); i++) {			
			if (graphList.get(i).name().equals("Doc")) {
				node1Index = i;
			} else if (graphList.get(i).name().equals("Optimus")) {
				node2Index = i;
			}
		}
		
		assertTrue(myNetwork.connected(graphList.get(node1Index), graphList.get(node2Index)) == 2);
	}
}
