package dijkstra

import (
	"testing"
	"algo/graph"
)

func Test5Vertices(t *testing.T) {
	graph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(5)
	graph.AddVertex(1)
	graph.AddVertex(2)
	graph.AddVertex(3)
	graph.AddVertex(4)
	graph.AddVertex(5)
	graph.AddEdge(1, 2, 1)
	graph.AddEdge(2, 3, 0)
	graph.AddEdge(3, 4, 2)
	graph.AddEdge(4, 2, 3)
	graph.AddEdge(2, 5, 4)
	result := Dijkstra(graph, 1)
	if result[2] != 1 {
		t.Error("Expected to have distance = 1 between 1 and 2, but got", result[2])
	}
	if result[3] != 1 {
		t.Error("Expected to have distance = 1 between 1 and 3, but got", result[3])
	}
	if result[4] != 3 {
		t.Error("Expected to have distance = 3 between 1 and 4, but got", result[4])
	}
	if result[5] != 5 {
		t.Error("Expected to have distance = 5 between 1 and 5, but got", result[5])
	}
}

func Test3Vertices(t *testing.T) {
	graph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(3)
	graph.AddVertex(1)
	graph.AddVertex(2)
	graph.AddVertex(3)
	graph.AddEdge(1, 2, 3)
	graph.AddEdge(1, 3, 1)
	graph.AddEdge(3, 2, 1)
	result := Dijkstra(graph, 1)
	if result[2] != 2 {
		t.Error("Expected to have distance = 2 between 1 and 2, but got", result[2])
	}
	if result[3] != 1 {
		t.Error("Expected to have distance = 1 between 1 and 3, but got", result[3])
	}
}