package floyd_warshall

import (
	"algo/graph"
	"fmt"
)

func FloydWarshall(graph *graph.DirectedGraph) map[int64]map[int64]float64 {
	verticesAmount := graph.VerticesAmount();
	result := make(map[int64]map[int64]float64, verticesAmount)
	for _, vertexId := range graph.GetVertices() {
		fromVertexMap := make(map[int64]float64);
		neighbours, ok := graph.GetNeighbourVertices(vertexId)
		if !ok {
			continue
		}
		for _, neighbourVertexId := range neighbours {
			weights, _ := graph.GetEdgeWeightsBetweenVertices(vertexId, neighbourVertexId)
			if len(weights) > 0 {
				fromVertexMap[neighbourVertexId] = weights[0]
				for i := 1; i < len(weights); i++ {
					weight := weights[i]
					if weight < fromVertexMap[neighbourVertexId] {
						fromVertexMap[neighbourVertexId] = weight
					}
				}
			}
		}
		result[vertexId] = fromVertexMap
	}
	for k := int64(0); k < verticesAmount; k++ {
		fmt.Println(k)
		newResult := make(map[int64]map[int64]float64, verticesAmount)
		for i := int64(0); i < verticesAmount; i++ {
			newResult[i] = make(map[int64]float64)
			for j := int64(0); j < verticesAmount; j++ {
				newResult[i][j] = result[i][j]
				weight1, ok1 := result[i][k]
				weight2, ok2 := result[k][j]
				if ok1 && ok2 && weight1 + weight2 < newResult[i][j] {
					newResult[i][j] = weight1 + weight2
				}
			}
		}
		result = newResult
	}
	return result
}