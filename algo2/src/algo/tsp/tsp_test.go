package tsp

import (
	"testing"
	"algo/graph"
)

func Test4Nodes(t *testing.T) {
	ug := graph.CreateEmptyUndirectedGraphWithSpecifiedVertexAmountHint(4)
	ug.AddVertex(1)
	ug.AddVertex(2)
	ug.AddVertex(3)
	ug.AddVertex(4)
	ug.AddEdge(1, 2, 1)
	ug.AddEdge(1, 3, 3)
	ug.AddEdge(2, 3, 2)
	ug.AddEdge(2, 4, 4)
	ug.AddEdge(3, 4, 5)
	ug.AddEdge(4, 1, 6)
	result := TSP(ug)
	if (result != 13) {
		t.Error("Expected 13, got ", result)
	}
}