package graph

type UndirectedGraph struct {
	*DirectedGraph
}

func (graph *UndirectedGraph) AddEdge(fromVertex int64, toVertex int64, weight float64) {
	graph.DirectedGraph.AddEdge(fromVertex, toVertex, weight)
	graph.DirectedGraph.AddEdge(toVertex, fromVertex, weight)
}

func CreateEmptyUndirectedGraph() *UndirectedGraph {
	return CreateEmptyUndirectedGraphWithSpecifiedVertexAmountHint(10)
}

func CreateEmptyUndirectedGraphWithSpecifiedVertexAmountHint(vertexCount int64) *UndirectedGraph {
	return &UndirectedGraph{ CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(vertexCount) }
}