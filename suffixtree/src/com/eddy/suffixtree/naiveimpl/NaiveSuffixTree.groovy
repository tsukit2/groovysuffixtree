package com.eddy.suffixtree.naiveimpl

import com.eddy.suffixtree.Edge;
import com.eddy.suffixtree.ElementHandler;
import com.eddy.suffixtree.Node;
import com.eddy.suffixtree.SuffixTree;

class NaiveSuffixTree<Element,ElementPath> implements SuffixTree<Element,ElementPath> {
   private ElementHandler<Element,ElementPath> handler
   private NaiveNode<Element,ElementPath> root = new NaiveNode<Element,ElementPath>(owningTree:this)
   private List<Node<Element,ElementPath>> leafNodes = []

   NaiveSuffixTree(ElementHandler<Element,ElementPath> handler) {
      this.handler = handler
      buildTree()
   }

   private void buildTree() {
      int size = handler.elementCount
      size.times { updateTree(root, it, size-1) }
   }

   private void updateTree(node, startIndex, endIndex) {
      // search if there is already and edge
      def elem = handler.getElementKeyAt(startIndex)
      def edge = node.edges[elem]

      // if not, this is easy because we just create a whole new edge and node
      if(!edge) {
         def newNode = new NaiveNode<Element,ElementPath>(owningTree:this)
         def newEdge = new NaiveEdge<Element,ElementPath>(owningTree:this, 
                                                 startIndex:startIndex, endIndex:endIndex,
                                                 startNode:node, endNode:newNode)
         newNode.fromEdge = newEdge
         node.edges[elem] = newEdge
         leafNodes << newNode
         return
      }

      // otherwise, walk down the tree by first walk through matched edge
      def edgeIndex = edge.startIndex
      def curIndex = startIndex
      while(edgeIndex <= edge.endIndex &&
            curIndex <= endIndex &&
            handler.elementsAreEqual(edgeIndex, curIndex)) {
         ++edgeIndex
         ++curIndex
      }

      // determine the comparison what to do. If criteria doesn't match, we don't do any update
      if(edgeIndex > edge.endIndex && curIndex <= endIndex) {
         // if we exhaust the edge while still haven't exhausted the updates, we need to walk down further the subtree
         updateTree(edge.endNode, curIndex, endIndex)
      } else if(edgeIndex <= edge.endIndex && curIndex <= endIndex) {
         // if we hit difference, need to split the edge
         def splitNode = new NaiveNode<Element,ElementPath>(owningTree:this)
         def newNode = new NaiveNode<Element,ElementPath>(owningTree:this)
         leafNodes << newNode
         def newEdgeToNewNode = new NaiveEdge<Element,ElementPath>(
                     owningTree:this, startIndex:curIndex, endIndex:endIndex,
                     startNode:splitNode, endNode:newNode)
         newNode.fromEdge = newEdgeToNewNode
         def newEdgeToOldNode = new NaiveEdge<Element,ElementPath>(
                     owningTree:this, startIndex:edgeIndex, endIndex:edge.endIndex,
                     startNode:splitNode, endNode:edge.endNode)
         edge.endNode.fromEdge = newEdgeToOldNode
         splitNode.edges[handler.getElementKeyAt(newEdgeToOldNode.startIndex)] = newEdgeToOldNode
         splitNode.edges[handler.getElementKeyAt(newEdgeToNewNode.startIndex)] = newEdgeToNewNode
         splitNode.fromEdge = edge
         edge.endIndex = edgeIndex - 1
         edge.endNode = splitNode
      }
   }

   Node<Element,ElementPath> getRoot() {
      root
   }

   List<Node<Element,ElementPath>> getLeafNodes() {
      leafNodes
   }
}



