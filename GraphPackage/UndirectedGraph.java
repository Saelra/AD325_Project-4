package GraphPackage;

import ADTPackage.StackInterface;

public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>
{
    public UndirectedGraph()
    {
        super();
    } // end default constructor

    /**{@inheritDoc}*/
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        return super.addEdge(begin, end, edgeWeight) &&
                super.addEdge(end, begin, edgeWeight);
        // Assertion: edge count is twice its correct value due to calling addEdge twice
    } // end addEdge

    /**{@inheritDoc}*/
    public boolean addEdge(T begin, T end)
    {
        return this.addEdge(begin, end, 0);
    } // end addEdge

    /**{@inheritDoc}*/
    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges() / 2;
    } // end getNumberOfEdges

    /**{@inheritDoc}*/
    public StackInterface<T> getTopologicalOrder()
    {
        throw new UnsupportedOperationException("Topological sort is illegal in an undirected graph.");
    } // end getTopologicalOrder
} // end UndirectedGraph
