/*
 * Author: Auston Jin
 * Date: February 2, 2014 
 * */

package com.apple.peoplelink;

import java.util.HashSet;
import java.util.Set;

public class Person implements Node{
	
	public String name;
	public Set<Node> connections = new HashSet<Node>();
	public boolean visited;
	
	public Person(String name) {
		this.name = name;		
		this.visited = false;
	}	

	@Override
	public String name() {		
		return this.name;
	}

	@Override
	public Set<Node> children() {		
		return this.connections;
	}
}
