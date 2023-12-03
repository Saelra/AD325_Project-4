package ADTPackage;
import java.util.EmptyStackException;
/**
   A class of stacks whose entries are stored in a chain of nodes.

   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain

	public LinkedStack()
	{
		topNode = null;
	} // end default constructor

	/**{@inheritDoc}*/
	public void push(T newEntry)
	{
		Node newNode = new Node(newEntry, topNode);
		topNode = newNode;
//    topNode = new Node(newEntry, topNode); // Alternate code
	} // end push

	/**{@inheritDoc}*/
	public T peek()
	{
		if (isEmpty())
			throw new EmptyStackException();
		else
         return topNode.getData();
	} // end peek

	/**{@inheritDoc}*/
	public T pop()
	{
	   T top = peek();  // Might throw EmptyStackException
   // Assertion: topNode != null
      topNode = topNode.getNextNode();

	   return top;
	} // end pop

	/**{@inheritDoc}*/
	public boolean isEmpty()
	{
		return topNode == null;
	} // end isEmpty

	/**{@inheritDoc}*/
	public void clear()
	{
		topNode = null;  // Causes deallocation of nodes in the chain
	} // end clear

	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node

      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor

      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor

	  //TODO add javadoc
      private T getData()
      {
         return data;
      } // end getData

	  //TODO add javadoc
      private void setData(T newData)
      {
         data = newData;
      } // end setData

	  //TODO add javadoc
      private Node getNextNode()
      {
         return next;
      } // end getNextNode

	  //TODO add javadoc
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LinkedStack
