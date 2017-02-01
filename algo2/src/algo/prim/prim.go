package prim

import (
	"algo/data_structures"
	"container/heap"
	"github.com/gyuho/goraph"
	//"gopkg.in/oleiade/lane.v1"
)

type pqItemValue struct {
	nodeId goraph.ID
	weight float64
}

func panicIfError(err error) {
	if (err != nil) {
		panic(err.Error())
	}
}

func addEdgesFromNode(graph goraph.Graph, nodeId goraph.ID, pq *priority_queue.PriorityQueue, visitedNodeIds map[string]bool) {
	visitedNodeIds[nodeId.String()] = true
	targetNodes, err := graph.GetTargets(nodeId)
	panicIfError(err)
	for targetNodeId := range targetNodes {
		if (visitedNodeIds[targetNodeId.String()]) {
			continue
		}
		weight, err := graph.GetWeight(nodeId, targetNodeId)
		panicIfError(err)
		item := &priority_queue.Item{Priority:weight, Value:pqItemValue{nodeId:targetNodeId, weight:weight}}
		heap.Push(pq, item)
	}
}

func CalculateMSTSum(graph goraph.Graph) float64 {
	pq := &priority_queue.PriorityQueue{}
	heap.Init(pq)
	//var pq *lane.PQueue = lane.NewPQueue(lane.MINPQ)

	var firstNodeID goraph.ID;
	for id := range graph.GetNodes() {
		firstNodeID = id
		break
	}
	visitedNodeIds := make(map[string]bool)
	addEdgesFromNode(graph, firstNodeID, pq, visitedNodeIds)
	var mstSum float64 = 0
	for pq.Len() != 0 {
		element := heap.Pop(pq);
		pqItemValue := element.(*priority_queue.Item).Value.(pqItemValue);
		nodeId := pqItemValue.nodeId;
		if (visitedNodeIds[nodeId.String()]) {
			continue
		}
		mstSum += pqItemValue.weight
		addEdgesFromNode(graph, nodeId, pq, visitedNodeIds)
	}
	return mstSum
}