/*
 * Author: Auston Jin 
 * Date: February 2th, 2014
 * Revised February 5th, 2014
 * 
 * This is a helper class for the findConnections() and connected() method in class "Network"
 * */

package com.apple.peoplelink;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BFSMap {	

	public BFSMap() {
		
	}	
		
	public static Map<Node, Integer> map (Node node) {
		
		//BFS queue
		Queue<Node> nodeQ = new LinkedList<Node>();
		nodeQ.add(node);		
		
		//list of visited nodes
		Set<Node> visited = new HashSet<Node>();		
		visited.add(node);	
		
		//map of connections with their associated depths. HashMap is used since map ordering is not required 
		Map<Node, Integer> connectionsMap = new HashMap<Node, Integer>(); 		
				
		//a queue to keep the depth of all connections from the passed in node
		Queue<Integer> depthQ = new LinkedList<Integer>();
		depthQ.add(0);		
		
		int currDepth = 0;			
		boolean matchFound = false;//true if a children of currNode has already been visited
		
		while (!nodeQ.isEmpty()) {
			
			currDepth = depthQ.poll();					
			for (Node childIter : nodeQ.poll().children()) {				
				for (Node visitedIter : visited) {					
					if (childIter == visitedIter) {
						matchFound = true;
					}
				}				
				if (!matchFound) {
					nodeQ.add(childIter);					
					visited.add(childIter);
					depthQ.add(currDepth+1);
					connectionsMap.put(childIter, currDepth+1);					
				}
				matchFound = false;				
			}				
		}
		
		return connectionsMap;
	}	
}
