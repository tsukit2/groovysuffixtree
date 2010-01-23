package com.eddy.suffixtree.handler;

import com.eddy.suffixtree.ElementHandler;

class StringElementHandler implements ElementHandler<Character,String>{
	String str
	boolean ignoreCase
	
	StringElementHandler(String str, boolean ignoreCase=false) {
	   this.str = str
		this.ignoreCase = ignoreCase
	}
	
	int getElementCount() {
	   str.size()
	}
	
	Character getElementKeyAt(int index) {
	   def ch = str[index]
	   return ignoreCase ? ch.toLowerCase() : ch
	}
	
	Character getElementAt(int index) {
	   str[index]
	}
	
	boolean elementsAreEqual(int index1, int index2) {
	   getElementKeyAt(index1) == getElementKeyAt(index2)
	}
	
	String getElementPath(int index1, int index2) {
	   return index2 >= index1 ? str[index1..index2] : ''
	}
	
	String appendPathTo(String src, String newStuff) {
		src + newStuff
	}
}
