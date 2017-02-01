package dijkstra

import (
	"algo/data_structures"
	"algo/graph"
	"container/heap"
)

type pqItemValue struct {
	vertexId int64
}

func addEdgesFromNode(vertexId int64, graph *graph.DirectedGraph, pq *priority_queue.PriorityQueue, distance float64) {
	edges, ok := graph.GetNeighbourVertices(vertexId)
	if !ok {
		return
	}
	for _, neighbourId := range edges {
		weights, ok2 := graph.GetEdgeWeightsBetweenVertices(vertexId, neighbourId)
		if !ok2 || len(weights) == 0 {
			continue
		}
		minWeight := weights[0]
		for i := 1; i < len(weights); i++ {
			if weights[i] < minWeight {
				minWeight = weights[i]
			}
		}
		item := &priority_queue.Item{Priority: distance + minWeight, Value: pqItemValue{vertexId: neighbourId}}
		heap.Push(pq, item)
	}
}

func Dijkstra(graph *graph.DirectedGraph, sourceVertex int64) map[int64]float64 {
	result := make(map[int64]float64, graph.VerticesAmount())
	result[sourceVertex] = 0
	pq := &priority_queue.PriorityQueue{}
	heap.Init(pq)
	addEdgesFromNode(sourceVertex, graph, pq, 0)
	for pq.Len() != 0 {
		element := heap.Pop(pq);
		pqItem := element.(*priority_queue.Item);
		pqItemValue := pqItem.Value.(pqItemValue);
		if _, ok := result[pqItemValue.vertexId]; ok {
			continue
		}
		result[pqItemValue.vertexId] = pqItem.Priority
		addEdgesFromNode(pqItemValue.vertexId, graph, pq, pqItem.Priority)
	}
	return result
}
