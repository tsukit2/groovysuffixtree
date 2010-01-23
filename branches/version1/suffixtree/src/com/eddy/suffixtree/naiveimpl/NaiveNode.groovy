package com.eddy.suffixtree.naiveimpl;

import java.util.List;
import java.util.Map;

import com.eddy.suffixtree.Edge;
import com.eddy.suffixtree.Node;

class NaiveNode<Element,ElementPath> implements Node<Element,ElementPath> {
	private Map<Element,NaiveEdge<Element,ElementPath>> edges = [:]
	private NaiveEdge<Element,ElementPath> fromEdge
	NaiveSuffixTree<Element,ElementPath> owningTree
	
	ElementPath getElementPath() {
		if(fromEdge) {
			return owningTree.handler.appendPathTo(fromEdge.startNode.elementPath, fromEdge.elementPath)
		}
		
		return owningTree.handler.getElementPath(0, -1)
	}
	
	Map<Element,Edge<Element,ElementPath>> getEdges() {
		edges
	}
	
	Edge<Element,ElementPath> getFromEdge() {
		fromEdge
	}
	
	String toString() {
		edges.toString()
	}
	
	int getWidth() {
		return fromEdge ? fromEdge.startNode.width + fromEdge.width : 0
	}
}

