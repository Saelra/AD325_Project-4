package ADTPackage;
/**
   A class that implements the ADT priority queue by using a maxheap.

   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.01
*/
public final class HeapPriorityQueue<T extends Comparable<? super T>>
                   implements PriorityQueueInterface<T>
{
	private MaxHeapInterface<T> pq;

	public HeapPriorityQueue()
	{
		pq = new MaxHeap<>();
	} // end default constructor

	/**{@inheritDoc}*/
	public void add(T newEntry)
	{
		pq.add(newEntry);
	} // end add

	/**{@inheritDoc}*/
	public T remove()
	{
		return pq.removeMax();
	} // end remove

	/**{@inheritDoc}*/
	public T peek()
	{
		return pq.getMax();
	} // end peek

	/**{@inheritDoc}*/
	public boolean isEmpty()
	{
		return pq.isEmpty();
	} // end isEmpty

	/**{@inheritDoc}*/
	public int getSize()
	{
		return pq.getSize();
	} // end getSize

	/**{@inheritDoc}*/
	public void clear()
	{
		pq.clear();
	} // end clear
} // end HeapPriorityQueue
