package ADTPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 A class that implements the ADT list by using a chain of linked nodes.
 The list has an iterator. The class is similar to LList.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public class LinkedListWithIterator<T> implements ListWithIteratorInterface<T>
{
   private Node firstNode;
   private int  numberOfEntries;

   public LinkedListWithIterator()
   {
      initializeDataFields();
   } // end default constructor

    /**{@inheritDoc}*/
	public void clear()
	{
      initializeDataFields();
	} // end clear

    /**{@inheritDoc}*/
	public void add(T newEntry) 	               // OutOfMemoryError possible
	{
		Node newNode = new Node(newEntry);

		if (isEmpty())
			firstNode = newNode;
		else                                      // Add to end of non-empty list
		{
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.setNextNode(newNode);         // Make last node reference new node
		} // end if

		numberOfEntries++;
	}  // end add

    /**{@inheritDoc}*/
   public void add(int newPosition, T newEntry) // OutOfMemoryError possible
	{
 		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			Node newNode = new Node(newEntry);

			if (newPosition == 1)                  // Case 1
			{
				newNode.setNextNode(firstNode);
				firstNode = newNode;
			}
			else									         // Case 2: list is not empty
			{                                      // and newPosition > 1
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.getNextNode();
				newNode.setNextNode(nodeAfter);
				nodeBefore.setNextNode(newNode);
			} // end if

			numberOfEntries++;
		}
      else
         throw new IndexOutOfBoundsException("Illegal position given to add operation.");
   } // end add

    /**{@inheritDoc}*/
	public T remove(int givenPosition)
	{
      T result = null;                          // Return value

      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: !isEmpty()

         if (givenPosition == 1)                // Case 1: remove first entry
         {
            result = firstNode.getData();       // Save entry to be removed
            firstNode = firstNode.getNextNode();
         }
         else                                   // Case 2: not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            result = nodeToRemove.getData();    // Save entry to be removed
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
         } // end if

         numberOfEntries--;                     // Update count
         return result;                         // Return removed entry
      }
      else
         throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

    /**{@inheritDoc}*/
	public T replace(int givenPosition, T newEntry)
	{
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
      	// Assertion: !isEmpty()

			Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
			desiredNode.setData(newEntry);
         return originalEntry;
      }
		else
         throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
   } // end replace

    /**{@inheritDoc}*/
   public T getEntry(int givenPosition)
   {
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			// Assertion: !isEmpty()
         return getNodeAt(givenPosition).getData();
     	}
      else
         throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
   } // end getEntry

    /**{@inheritDoc}*/
   public T[] toArray()
   {
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] result = (T[])new Object[numberOfEntries];

      int index = 0;
      Node currentNode = firstNode;
      while ((index < numberOfEntries) && (currentNode != null))
      {
         result[index] = currentNode.getData();
         currentNode = currentNode.getNextNode();
         index++;
      } // end while

      return result;
   } // end toArray

    /**{@inheritDoc}*/
	public boolean contains(T anEntry)
	{
		boolean found = false;
		Node currentNode = firstNode;

		while (!found && (currentNode != null))
		{
			if (anEntry.equals(currentNode.getData()))
				found = true;
			else
				currentNode = currentNode.getNextNode();
		} // end while

		return found;
	} // end contains

    /**{@inheritDoc}*/
   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

    /**{@inheritDoc}*/
   public boolean isEmpty()
   {
      boolean result;

      if (numberOfEntries == 0) // Or getLength() == 0
      {
         // Assertion: firstNode == null
         result = true;
      }
      else
      {
         // Assertion: firstNode != null
         result = false;
      } // end if

      return result;
   } // end isEmpty

    /**{@inheritDoc}*/
   public Iterator<T> iterator()
   {
      return new IteratorForLinkedList();
   } // end iterator

    /**{@inheritDoc}*/
   public Iterator<T> getIterator()
   {
      return iterator();
   } // end getIterator

  /**
   * Initializes the class's data fields to indicate an empty list.
   */
   private void initializeDataFields()
   {
      firstNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields

    /**
     * Returns a reference to the node at a given position.
     * @param givenPosition a node's position within the linked list.
     * @return the node at the given position.
     */
   private Node getNodeAt(int givenPosition)
   {
      // Assertion: (firstNode != null) &&
      //            (1 <= givenPosition) && (givenPosition <= numberOfEntries)
      Node currentNode = firstNode;

      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
      for (int counter = 1; counter < givenPosition; counter++)
         currentNode = currentNode.getNextNode();
      // Assertion: currentNode != null
      return currentNode;
   } // end getNodeAt

   private class IteratorForLinkedList implements Iterator<T>
   {
      private Node nextNode;

      private IteratorForLinkedList()
      {
         nextNode = firstNode;
      } // end default constructor

       /**{@inheritDoc}*/
      public T next()
      {
         T result;
         if (hasNext())
         {
            result = nextNode.getData();
            nextNode = nextNode.getNextNode(); // Advance iterator
         }
         else
            throw new NoSuchElementException("Illegal call to next(); " +
                                             "iterator is after end of list.");
         return result; // Return next entry in iteration
      } // end next

       /**{@inheritDoc}*/
      public boolean hasNext()
      {
         return nextNode != null;
      } // end hasNext

       /**{@inheritDoc}*/
      public void remove()
      {
         throw new UnsupportedOperationException("remove() is not supported " +
                                                 "by this iterator");
      } // end remove
   } // end IteratorForLinkedList

   private class Node
   {
      private T    data; // Entry in list
      private Node next; // Link to next node

      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor

      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
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
} // end LinkedListWithIterator



