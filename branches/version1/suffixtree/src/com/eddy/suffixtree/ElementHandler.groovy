package com.eddy.suffixtree;

interface ElementHandler<Element,ElementPath> {
	int getElementCount()
	Element getElementKeyAt(int index)
   Element getElementAt(int index)
   boolean elementsAreEqual(int index1, int index2)
	ElementPath getElementPath(int index1, int index2)
	ElementPath appendPathTo(ElementPath src, ElementPath newStuff)
}
