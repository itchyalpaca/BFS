/*
 * Author: Auston Jin
 * Date: February 2, 2014
 * Revised February 5th, 2014
 * 
 * findConnections() and connected() are implemented in this class
 *   
 * */

package com.apple.peoplelink;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Network {		
		
	public Map<Node, List<Node>> findConnections(Node node, int depth) {
		
		Map<Node, Integer> allConnections = new HashMap<Node, Integer>();
		allConnections.putAll(BFSMap.map(node));		
		List<Node> subConnections = new LinkedList<Node>();
		
		for (Node nodeIter : allConnections.keySet()) {
			if (allConnections.get(nodeIter) <= depth) {
				subConnections.add(nodeIter);
			}
		}		
		Map<Node, List<Node>> subConnectionsMap = new HashMap<Node, List<Node>>();
		subConnectionsMap.put(node, subConnections);
		
		return subConnectionsMap;
	}
	
	public int connected(Node node1, Node node2) {
		
		Map<Node, Integer> allConnections = new HashMap<Node, Integer>();
		allConnections.putAll(BFSMap.map(node1));
		
		if (node1 == node2) {
			return 0;
		} else {
			for (Node nodeIter : allConnections.keySet()) {
				if (nodeIter == node2) {
					return allConnections.get(nodeIter);
				}
			}
		}		
		
		return -1;
	}	
	
}
