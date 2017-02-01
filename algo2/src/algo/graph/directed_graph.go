package graph

import (
	"fmt"
)

type DirectedGraph struct {
	edges        map[int64]map[int64][]float64
}

func (graph *DirectedGraph) AddVertex(id int64) bool {
	if _, ok := graph.edges[id]; ok {
		return false
	}
	graph.edges[id] = make(map[int64][]float64)
	return true
}

func (graph *DirectedGraph) AddEdge(fromVertex int64, toVertex int64, weight float64) {
	m, ok1 := graph.edges[fromVertex]
	if !ok1 {
		panic(fmt.Sprint("No vertex with id = ", fromVertex))
	}
	_, ok2 := graph.edges[toVertex]
	if !ok2 {
		panic(fmt.Sprint("No vertex with id = ", toVertex))
	}
	listOfEdges, ok := m[toVertex]
	if !ok {
		listOfEdges = make([]float64, 0)
	}
	listOfEdges = append(listOfEdges, weight)
	graph.edges[fromVertex][toVertex] = listOfEdges
}

func (graph *DirectedGraph) VerticesAmount() int64 {
	return int64(len(graph.edges))
}

func (graph *DirectedGraph) GetVertices() []int64 {
	result := make([]int64, len(graph.edges))
	counter := 0
	for id := range graph.edges {
		result[counter] = id
		counter++
	}
	return result
}

func (graph *DirectedGraph) GetNeighbourVertices(vertexId int64) ([]int64, bool) {
	edges, ok := graph.edges[vertexId]
	if !ok {
		return []int64{}, false
	}
	result := make([]int64, len(edges))
	counter := 0
	for id := range edges {
		result[counter] = id
		counter++
	}
	return result, true
}

func (graph *DirectedGraph) GetEdgeWeightsBetweenVertices(fromVertex int64, toVertex int64) ([]float64, bool) {
	edges, ok := graph.edges[fromVertex]
	if !ok {
		return []float64{}, false
	}
	weights, ok := edges[toVertex]
	if !ok {
		return []float64{}, false
	}
	result := make([]float64, len(weights))
	copy(result, weights)
	return result, true
}

func (graph *DirectedGraph) VertexWithIdExists(vertexId int64) bool {
	_, ok := graph.edges[vertexId]
	return ok
}

func CreateEmptyDirectedGraph() *DirectedGraph {
	return CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(10)
}

func CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(vertexCount int64) *DirectedGraph {
	m := make(map[int64]map[int64][]float64)
	return &DirectedGraph{edges: m}
}

func CloneGraph(graphInstance *DirectedGraph) *DirectedGraph {
	newGraph := CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(graphInstance.VerticesAmount())
	for _, vertexId := range graphInstance.GetVertices() {
		newGraph.AddVertex(vertexId)
	}
	for _, vertexId := range graphInstance.GetVertices() {
		neighbours, ok := graphInstance.GetNeighbourVertices(vertexId);
		if !ok {
			continue
		}
		for _, neighbourVertexId := range neighbours {
			weights, ok2 := graphInstance.GetEdgeWeightsBetweenVertices(vertexId, neighbourVertexId)
			if !ok2 {
				continue
			}
			for _, weight := range weights {
				newGraph.AddEdge(vertexId, neighbourVertexId, weight)
			}
		}
	}
	return newGraph
}