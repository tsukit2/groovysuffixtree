package com.eddy.suffixtree;

import java.util.List;
import java.util.Map;

interface Node<Element,ElementPath> {
	Map<String,Edge<Element,ElementPath>> getEdges()
	ElementPath getElementPath()
	int getWidth()
	Edge<Element,ElementPath> getFromEdge()
}