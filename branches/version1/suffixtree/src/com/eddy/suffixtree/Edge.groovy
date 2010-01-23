package com.eddy.suffixtree;

import java.util.List;

interface Edge<Element,ElementPath> {
	ElementPath getElementPath()
	int getWidth()
	int getStartIndex()
	int getEndIndex()
	Node<Element,ElementPath> getStartNode()
	Node<Element,ElementPath> getEndNode()
}
