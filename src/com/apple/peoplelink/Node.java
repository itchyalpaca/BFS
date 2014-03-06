/*
 * Author: Auston Jin
 * Date: February 2, 2014
 *   
 * */

package com.apple.peoplelink;

import java.util.Set;

public interface Node {
	public String name();
    public Set<Node> children();
}
