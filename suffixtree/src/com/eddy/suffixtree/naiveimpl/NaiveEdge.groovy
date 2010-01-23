package com.eddy.suffixtree.naiveimpl;

import java.util.List;

import com.eddy.suffixtree.Edge;
import com.eddy.suffixtree.Node;

class NaiveEdge<Element,ElementPath> implements Edge<Element,ElementPath> {
	NaiveSuffixTree<Element,ElementPath> owningTree
	
	int startIndex, endIndex
	NaiveNode<Element,ElementPath> startNode, endNode
	
	ElementPath getElementPath() {
		owningTree.handler.getElementPath(startIndex, endIndex)
	}
	
	Node<Element,ElementPath> getStartNode() {
		startNode
	}
	
	Node<Element,ElementPath> getEndNode() {
		endNode
	}
	
	int getWidth() {
		endIndex - startIndex + 1
	}
	
	String toString() {
		"{ --$elementPath--> $endNode }"
	}
}
