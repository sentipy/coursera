package johnson

import (
	"testing"
	"algo/graph"
)

func Test5Vertices_NoNegativeCycle(t *testing.T) {
	graph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(5)
	graph.AddVertex(1)
	graph.AddVertex(2)
	graph.AddVertex(3)
	graph.AddVertex(4)
	graph.AddVertex(5)
	graph.AddEdge(1, 2, 1)
	graph.AddEdge(2, 3, -1)
	graph.AddEdge(3, 4, 2)
	graph.AddEdge(4, 2, 3)
	graph.AddEdge(2, 5, 4)
	result, noNegativeCycle := Johnson(graph)
	if !noNegativeCycle {
		t.Error("Expected to have no negative cycle")
	}
	if result[1][2] != 1 {
		t.Error("Expected to have distance = 1 between 1 and 2, but got", result[1][2])
	}
	if result[1][3] != 0 {
		t.Error("Expected to have distance = 0 between 1 and 3, but got", result[1][3])
	}
	if result[1][4] != 2 {
		t.Error("Expected to have distance = 2 between 1 and 4, but got", result[1][4])
	}
	if result[1][5] != 5 {
		t.Error("Expected to have distance = 5 between 1 and 5, but got", result[1][5])
	}
	if result[2][3] != -1 {
		t.Error("Expected to have distance = -1 between 2 and 3, but got", result[2][3])
	}
	if result[2][4] != 1 {
		t.Error("Expected to have distance = 1 between 2 and 5, but got", result[2][4])
	}
	if result[2][5] != 4 {
		t.Error("Expected to have distance = 5 between 2 and 5, but got", result[2][5])
	}
	if result[3][4] != 2 {
		t.Error("Expected to have distance = 2 between 3 and 4, but got", result[3][4])
	}
	if result[3][5] != 9 {
		t.Error("Expected to have distance = 9 between 3 and 5, but got", result[3][5])
	}
	if result[4][5] != 7 {
		t.Error("Expected to have distance = 7 between 4 and 5, but got", result[4][5])
	}
}

func Test4Vertices_NoNegativeCycle(t *testing.T) {
	graph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(4)
	graph.AddVertex(1)
	graph.AddVertex(2)
	graph.AddVertex(3)
	graph.AddVertex(4)
	graph.AddEdge(1, 2, 3)
	graph.AddEdge(2, 4, -7)
	graph.AddEdge(1, 3, -2)
	graph.AddEdge(3, 4, -1)
	result, noNegativeCycle := Johnson(graph)
	if !noNegativeCycle {
		t.Error("Expected to have no negative cycle")
	}
	if result[1][4] != -4 {
		t.Error("Expected to have distance = -4 between 1 and 4, but got", result[1][4])
	}
}