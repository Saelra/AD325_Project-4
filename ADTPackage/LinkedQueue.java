package ADTPackage;
/**
 A class that implements a queue of objects by using
 a chain of linked nodes that has both head and tail references.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public final class LinkedQueue<T> implements QueueInterface<T>
{
   private Node firstNode; // References node at front of queue
   private Node lastNode;  // References node at back of queue

   public LinkedQueue()
   {
      firstNode = null;
      lastNode = null;
   } // end default constructor

   /**{@inheritDoc}*/
   public void enqueue(T newEntry)
   {
      Node newNode = new Node(newEntry, null);

      if (isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);

      lastNode = newNode;
   } // end enqueue

   /**{@inheritDoc}*/
   public T getFront()
   {
      if (isEmpty())
         throw new EmptyQueueException();
      else
         return firstNode.getData();
   } // end getFront

   /**{@inheritDoc}*/
   public T dequeue()
   {
      T front = getFront();  // Might throw EmptyQueueException
                             // Assertion: firstNode != null
      firstNode.setData(null);
      firstNode = firstNode.getNextNode();

      if (firstNode == null)
         lastNode = null;

      return front;
   } // end dequeue

   /**{@inheritDoc}*/
   public boolean isEmpty()
   {
      return (firstNode == null) && (lastNode == null);
   } // end isEmpty

   /**{@inheritDoc}*/
   public void clear()
   {
      firstNode = null;
      lastNode = null;
   } // end clear

   private class Node
   {
      private T    data; // Entry in queue
      private Node next; // Link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor

      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

      /**
       * Gets the data from a given node.
       * @return the node's data.
       */
      private T getData()
      {
         return data;
      } // end getData

      /**
       * Assigns data for a given node.
       * @param newData new data to set in a node.
       */
      private void setData(T newData)
      {
         data = newData;
      } // end setData

      /**
       * Retrieves an adjacent node relative to the current node.
       * @return the next node in the linked list.
       */
      private Node getNextNode()
      {
         return next;
      } // end getNextNode

      /**
       * Assigns an adjacent node relative to the current node.
       * @param nextNode the next node to be set in the linked list.
       */
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
   } // end Node
} // end Linkedqueue
