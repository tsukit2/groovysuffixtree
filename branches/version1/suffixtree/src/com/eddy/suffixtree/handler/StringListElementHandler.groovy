package com.eddy.suffixtree.handler;

import com.eddy.suffixtree.ElementHandler;

class StringListElementHandler implements ElementHandler<String,List<String>>{
	List<String> list
	boolean ignoreCase
	
	StringListElementHandler(List<String> list, boolean ignoreCase=false) {
	   this.list = list
		this.ignoreCase = ignoreCase
	}
	
	int getElementCount() {
	   list.size()
	}
	
	String getElementKeyAt(int index) {
	   def str = list[index]
	   return ignoreCase ? str.toLowerCase() : str
	}
	
	String getElementAt(int index) {
	   list[index]
	}
	
	boolean elementsAreEqual(int index1, int index2) {
	   getElementKeyAt(index1) == getElementKeyAt(index2)
	}
	
	List<String> getElementPath(int index1, int index2) {
	   return index2 >= index1 ? list[index1..index2] : []
	}
	
	List<String> appendPathTo(List<String> src, List<String> newStuff) {
		src + newStuff
	}
}
