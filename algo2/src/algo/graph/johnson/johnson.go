package johnson

import (
	"algo/graph"
	"algo/graph/bellman_ford"
	"algo/graph/dijkstra"
)

func createReweightedGraph(graphInstance *graph.DirectedGraph, reweightMap map[int64]float64) *graph.DirectedGraph {
	newGraph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(graphInstance.VerticesAmount())
	for _, vertexId := range graphInstance.GetVertices() {
		newGraph.AddVertex(vertexId)
	}
	for _, vertexId := range graphInstance.GetVertices() {
		neighbours, ok := graphInstance.GetNeighbourVertices(vertexId);
		if !ok {
			continue
		}
		for _, neighbourVertexId := range neighbours {
			weights, _ := graphInstance.GetEdgeWeightsBetweenVertices(vertexId, neighbourVertexId)
			if len(weights) == 0 {
				continue
			}
			minWeight := weights[0]
			for i := 0; i < len(weights); i++ {
				if minWeight > weights[i] {
					minWeight = weights[i]
				}
			}
			newGraph.AddEdge(vertexId, neighbourVertexId, minWeight + reweightMap[vertexId] - reweightMap[neighbourVertexId])
		}
	}
	return newGraph
}

func Johnson(graphInstance *graph.DirectedGraph) (map[int64]map[int64]float64, bool) {
	// check that graph does not have negative cycle
	fakeVertex := int64(-1)
	for graphInstance.VertexWithIdExists(fakeVertex) {
		fakeVertex--
	}
	newGraph := graph.CloneGraph(graphInstance)
	newGraph.AddVertex(fakeVertex)
	for _, vertexId := range graphInstance.GetVertices() {
		newGraph.AddEdge(fakeVertex, vertexId, 0)
	}
	bfRsult, noNegativeCycles := bellman_ford.BellmanFord(newGraph, fakeVertex)
	if !noNegativeCycles {
		return nil, false
	}
	// reweight original graph
	reweightMap := make(map[int64]float64, graphInstance.VerticesAmount())
	for vertexId, distance := range bfRsult {
		reweightMap[vertexId] = distance
	}
	reweightedGraph := createReweightedGraph(graphInstance, reweightMap)
	result := make(map[int64]map[int64]float64, graphInstance.VerticesAmount())
	for _, sourceVertexId := range reweightedGraph.GetVertices() {
		subMap := make(map[int64]float64, graphInstance.VerticesAmount())
		dijkstraResult := dijkstra.Dijkstra(reweightedGraph, sourceVertexId)
		for vertexId, distance := range dijkstraResult {
			subMap[vertexId] = distance + reweightMap[vertexId] - reweightMap[sourceVertexId]
		}
		result[sourceVertexId] = subMap
	}
	return result, true
}