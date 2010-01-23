package com.eddy.suffixtree;

import java.util.List;

class SuffixTreeUtils<Element,ElementPath> {
	static List<ElementPath> longRepeatedElementPaths(SuffixTree<Element,ElementPath> tree, int minSize) {
		def reps = [:]
		extractLongRepeatedElementPaths(tree.root, 0, minSize, reps)
		return (reps.values() as List)
	}
	
	private static extractLongRepeatedElementPaths(node, curSize, minSize, reps) {
		def extracted = false
		def pathWidths = [:]
		if(node.edges.values().any { !it.endNode.edges } && curSize >= minSize) {
			//println "add first, $curSize, $node.pathElements, $minSize"
			insertRepeated(reps, node, pathWidths)
			extracted = true
		}
		
		node.edges.values().each { edge ->
			if(edge.endNode.edges) {
				def otherExtracted = extractLongRepeatedElementPaths(edge.endNode, curSize + edge.width, minSize, reps)
				extracted = extracted || otherExtracted
			}
		}
		
		if(node.edges && curSize >= minSize && !extracted) {
			insertRepeated(reps, node, pathWidths)
			//println 'add second'
			extracted = true
		}
		
		return extracted
	}
	
	private static insertRepeated(reps, node, pathWidths) {
		//println "*****: ${node.pathElements}"
		//for(other in reps) {
		//   if(other.fromEdge.endIndex == node.fromEdge.endIndex) {
		//      return
		//   }
		//}
		def oldnode = reps[node.fromEdge.endIndex]
		if(oldnode) {
			if(oldnode.width >= node.width) {
				return
			}
		} 
		//println node.pathElements
		reps[node.fromEdge.endIndex] = node
	}
}
