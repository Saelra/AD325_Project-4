package GraphPackage;

import ADTPackage.LinkedListWithIterator;
import ADTPackage.ListWithIteratorInterface;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Vertex<T> implements VertexInterface<T>
{
    private T label;
    private ListWithIteratorInterface<Edge> edgeList; // Edges to neighbors
    private boolean visited;                          // True if visited
    private VertexInterface<T> previousVertex;        // On path to this vertex
    private double cost;                              // Of path to this vertex

    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedListWithIterator<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    /**{@inheritDoc}*/
    public T getLabel()
    {
        return label;
    } // end getLabel

    /**{@inheritDoc}*/
    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    /**{@inheritDoc}*/
    public void setPredecessor(VertexInterface<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    /**{@inheritDoc}*/
    public VertexInterface<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    /**{@inheritDoc}*/
    public void visit()
    {
        visited = true;
    } // end visit

    /**{@inheritDoc}*/
    public void unvisit()
    {
        visited = false;
    } // end unvisit

    /**{@inheritDoc}*/
    public boolean isVisited()
    {
        return visited;
    } // end isVisited

    /**{@inheritDoc}*/
    public double getCost()
    {
        return cost;
    } // end getCost

    /**{@inheritDoc}*/
    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    /**
     * Textual representation of the vertex's label.
     * @return the vertex's label.
     */
    public String toString()
    {
        return label.toString();
    } // end toString

    private class WeightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;

        private WeightIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**{@inheritDoc}*/
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**{@inheritDoc}*/
        public Double next()
        {
            Double edgeWeight = 0.0;
            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        } // end next

        /**{@inheritDoc}*/
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end WeightIterator

    /**{@inheritDoc}*/
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
    } // end getWeightIterator
// . . . to here Ex 8

    /**{@inheritDoc}*/
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        {  // Vertices are distinct
            Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    /**{@inheritDoc}*/
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect

    /**{@inheritDoc}*/
    public Iterator<VertexInterface<T>> getNeighborIterator()
    {
        return new NeighborIterator();
    } // end getNeighborIterator

    /**{@inheritDoc}*/
    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    /**{@inheritDoc}*/
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        VertexInterface<T> result = null;

        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        while ( neighbors.hasNext() && (result == null) )
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    /**
     * Compares two objects: returns true if equal; false otherwise.
     * @param other Object to be compared to.
     * @return true if equal.
     */
    @Override
    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            // The cast is safe within this else clause
            @SuppressWarnings("unchecked")
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    private class NeighborIterator implements Iterator<VertexInterface<T>>
    {
        private Iterator<Edge> edges;

        private NeighborIterator()
        {
            edges = edgeList.getIterator();
        } // end default constructor

        /**{@inheritDoc}*/
        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        /**{@inheritDoc}*/
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next

        /**{@inheritDoc}*/
        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end NeighborIterator

    protected class Edge
    {
        private VertexInterface<T> vertex; // Vertex at end of edge
        private double weight;

        protected Edge(VertexInterface<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        protected Edge(VertexInterface<T> endVertex)
        {
            vertex = endVertex;
            weight = 0;
        } // end constructor

        /**
         * Retrieves the vertex representing the end of an edge.
         * @return a vertex.
         */
        protected VertexInterface<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        /**
         * Retrieves the weight of an edge.
         * @return the edge weight.
         */
        protected double getWeight()
        {
            return weight;
        } // end getWeight
    } // end Edge

    /**
     * Textual representation of all vertices and their edge weights.
     */
    public void display() // For testing
    {
        System.out.print(label + " " );
        Iterator<VertexInterface<T>> i = getNeighborIterator();
        Iterator<Double> w = getWeightIterator();

        while (i.hasNext())
        {
            Vertex<T> v = (Vertex<T>)i.next();
            System.out.print(v + " " + w.next() + " ");
        } // end while

        System.out.println();
    } // end display
} // end Vertex
