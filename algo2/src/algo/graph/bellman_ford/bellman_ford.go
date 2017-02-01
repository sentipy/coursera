package bellman_ford

import (
	"algo/graph"
	"fmt"
)

func createShortestEdgesMap(graph *graph.DirectedGraph) map[int64]map[int64]float64 {
	verticesAmount := graph.VerticesAmount()
	result := make(map[int64]map[int64]float64, verticesAmount)
	for _, vertexId := range graph.GetVertices() {
		subMap := make(map[int64]float64)
		neighbours, ok := graph.GetNeighbourVertices(vertexId);
		if !ok {
			continue
		}
		for _, neighbourId := range neighbours {
			weights, ok2 := graph.GetEdgeWeightsBetweenVertices(vertexId, neighbourId);
			if !ok2 {
				continue
			}
			min := weights[0]
			for i := 1; i < len(weights); i++ {
				if weights[i] < min {
					min = weights[i]
				}
			}
			subMap[neighbourId] = min
		}
		result[vertexId] = subMap
	}
	return result
}

func BellmanFord(graph *graph.DirectedGraph, sourceVertex int64) (map[int64]float64, bool) {
	verticesAmount := graph.VerticesAmount()
	shortestEdgesMap := createShortestEdgesMap(graph)
	result := make(map[int64]float64)
	neighbours, ok := graph.GetNeighbourVertices(sourceVertex);
	if !ok {
		panic(fmt.Sprintln("Vertex with id =", sourceVertex, "is not found in the graph"))
	}
	for _, neighbourVertexId := range neighbours {
		result[neighbourVertexId] = shortestEdgesMap[sourceVertex][neighbourVertexId]
	}
	for i := int64(0); i < verticesAmount; i++ {
		for vertex1, vertex1Edges := range shortestEdgesMap {
			if vertex1Edges == nil || len(vertex1Edges) == 0 {
				continue
			}
			bestWeightToVertex1, ok := result[vertex1]
			if !ok {
				continue
			}
			for vertex2, weightVertex1Vertex2 := range vertex1Edges {
				if currentBestToVertex2, ok2 := result[vertex2]; !ok2 || currentBestToVertex2 > bestWeightToVertex1 + weightVertex1Vertex2 {
					result[vertex2] = bestWeightToVertex1 + weightVertex1Vertex2
				}
			}
		}
	}
	noNegativeCycle := true
	for vertex1, vertex1Edges := range shortestEdgesMap {
		if vertex1Edges == nil || len(vertex1Edges) == 0 {
			continue
		}
		bestWeightToVertex1, ok := result[vertex1]
		if !ok {
			continue
		}
		for vertex2, weightVertex1Vertex2 := range vertex1Edges {
			if currentBestToVertex2, ok2 := result[vertex2]; ok2 && currentBestToVertex2 > bestWeightToVertex1 + weightVertex1Vertex2 {
				noNegativeCycle = false
				break
			}
		}
		if !noNegativeCycle {
			break
		}
	}

	return result, noNegativeCycle
}