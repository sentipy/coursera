package tsp

import (
	"algo/graph"
	"math"
	"fmt"
)

func getSubsetsOfSize(items []int64, subsetSize int64) [][]int64 {
	l := len(items)
	if subsetSize > int64(l) {
		return [][]int64{}
	}
	if subsetSize == int64(l) {
		return [][]int64{items}
	}
	if subsetSize == 1 {
		result := [][]int64{}
		for _, item := range items {
			result = append(result, []int64{item})
		}
		return result
	}
	subs := getSubsetsOfSize(items[1:], subsetSize - 1)
	for i := 0; i < len(subs); i++ {
		subs[i] = append(subs[i], items[0])
	}
	return append(subs, getSubsetsOfSize(items[1:], subsetSize)...)
}

func getInt64ByBits(bits []int64) int64 {
	i := int64(0)
	for _, bit := range bits {
		i |= int64(math.Pow(float64(2), float64(bit)))
	}
	return i
}

type TSP_Solver struct {
	ug        *graph.UndirectedGraph
	resultMap map[int64]map[int64]float64
	//bestValue float64
	//minDistancesForVertices map[int64]float64
}

/*func (tspSolver *TSP_Solver) getUnvisitedVertices(visitedMap map[int64]bool) []int64 {
	result := []int64{}
	for _, vertexId := range tspSolver.ug.GetVertices() {
		if _, ok := visitedMap[vertexId]; ok {
			continue
		}
		result = append(result, vertexId)
	}
	return result
}

func (tspSolver *TSP_Solver) getMinPossibleDistanceLeft(firstVertex int64, visitedMap map[int64]bool, unvisitedVertexIds []int64) float64 {
	minDistancesForVertices := make(map[int64]float64, tspSolver.ug.VerticesAmount())
	for _, vertexId := range unvisitedVertexIds {
		neighbours, _ := tspSolver.ug.GetNeighbourVertices(vertexId)
		for _, neighbourId := range neighbours {
			if _, ok1 := visitedMap[neighbourId]; ok1 && neighbourId != firstVertex {
				continue
			}
			weights, _ := tspSolver.ug.GetEdgeWeightsBetweenVertices(vertexId, neighbourId)
			for _, weight := range weights {
				if currentMinWeight, ok2 := minDistancesForVertices[vertexId]; !ok2 || currentMinWeight > weight {
					minDistancesForVertices[vertexId] = weight
				}
			}
		}
	}
	result := float64(0)
	for _, vertexId := range unvisitedVertexIds {
		result += minDistancesForVertices[vertexId]
	}
	return result
}*/

/*func (tspSolver *TSP_Solver) solveInner(vertex int64, path []int64, visitedMap map[int64]bool, currentDistance float64) {
	visitedMap[vertex] = true
	defer func() {
		delete(visitedMap, vertex)
	}()
	newPath := append(path[:], vertex)
	if int64(len(newPath)) == tspSolver.ug.VerticesAmount() {
		weights, _ := tspSolver.ug.GetEdgeWeightsBetweenVertices(vertex, path[0])
		minWeight := weights[0]
		for i := 1; i < len(weights); i++ {
			if minWeight > weights[i] {
				minWeight = weights[i]
			}
		}
		if tspSolver.bestValue > currentDistance + minWeight {
			tspSolver.bestValue = currentDistance + minWeight
			fmt.Println("New best:", tspSolver.bestValue)
		}
		return
	}
	unvisitedVertices := tspSolver.getUnvisitedVertices(visitedMap);
	if currentDistance + tspSolver.getMinPossibleDistanceLeft(newPath[0], visitedMap, unvisitedVertices) > tspSolver.bestValue {
		return
	}
	for _, neighbourId := range unvisitedVertices {
		weights, _ := tspSolver.ug.GetEdgeWeightsBetweenVertices(vertex, neighbourId)
		minWeight := weights[0]
		for i := 1; i < len(weights); i++ {
			if minWeight > weights[i] {
				minWeight = weights[i]
			}
		}
		if currentDistance + minWeight < tspSolver.bestValue {
			tspSolver.solveInner(neighbourId, newPath, visitedMap, currentDistance + minWeight)
		}
	}
}*/

func createTSPSolver(ug *graph.UndirectedGraph) *TSP_Solver {
	/*minDistancesForVertices := make(map[int64]float64, ug.VerticesAmount())
	for _, vertexId := range ug.GetVertices() {
		neighbours, _ := ug.GetNeighbourVertices(vertexId)
		for _, neighbourId := range neighbours {
			weights, _ := ug.GetEdgeWeightsBetweenVertices(vertexId, neighbourId)
			for _, weight := range weights {
				if currentMinWeight, ok := minDistancesForVertices[vertexId]; !ok || currentMinWeight > weight {
					minDistancesForVertices[vertexId] = weight
				}
			}
		}
	}*/
	/*return &TSP_Solver{ug:ug, bestValue: math.MaxFloat64, minDistancesForVertices:minDistancesForVertices}*/
	return &TSP_Solver{ug:ug, resultMap:make(map[int64]map[int64]float64)}
	}

/*func (tspSolver *TSP_Solver) solve() float64 {
	startVertex := tspSolver.ug.GetVertices()[0]
	tspSolver.bestValue = math.MaxFloat64
	//tspSolver.bestValue = 55000
	tspSolver.solveInner(startVertex, []int64{}, make(map[int64]bool), 0)
	return tspSolver.bestValue
}*/

func (tspSolver *TSP_Solver) solveByDP() float64 {
	shortestEdgesMap := make(map[int64]map[int64]float64)
	for _, vertexId := range tspSolver.ug.GetVertices() {
		shortestEdgesMap[vertexId] = make(map[int64]float64)
		neighbours, _ := tspSolver.ug.GetNeighbourVertices(vertexId)
		for _, neighbourId := range neighbours {
			weights, _ := tspSolver.ug.GetEdgeWeightsBetweenVertices(vertexId, neighbourId)
			minWeight := weights[0]
			for i := 1; i < len(weights); i++ {
				minWeight = math.Min(minWeight, weights[i])
			}
			shortestEdgesMap[vertexId][neighbourId] = minWeight
		}
	}
	vertices := tspSolver.ug.GetVertices();
	startPoint := vertices[0];
	startInt64Bit := getInt64ByBits([]int64{startPoint});
	tspSolver.resultMap[startInt64Bit] = make(map[int64]float64)
	tspSolver.resultMap[startInt64Bit][startPoint] = 0
	for m := int64(2); m <= tspSolver.ug.VerticesAmount(); m++ {
		fmt.Println(m)
		subsets := getSubsetsOfSize(vertices[1:], m - 1)
		for _, subset := range subsets {
			setWithFirstPoint := append(subset[:], vertices[0])
			encodedSet := getInt64ByBits(setWithFirstPoint)
			for _, j := range subset {
				subMap, ok := tspSolver.resultMap[encodedSet]
				if !ok {
					subMap = make(map[int64]float64)
					tspSolver.resultMap[encodedSet] = subMap
				}
				encodedSetWithoutJ := encodedSet ^ getInt64ByBits([]int64{j})
				min := math.MaxFloat64
				for _, k := range setWithFirstPoint {
					if k == j {
						continue
					}
					subMap2, ok2 := tspSolver.resultMap[encodedSetWithoutJ]
					if !ok2 {
						continue
					}
					value, ok3 := subMap2[k]
					if !ok3 {
						continue
					}
					min = math.Min(min, value + shortestEdgesMap[k][j])
				}
				subMap[j] = min
			}
			/*if _, ok := tspSolver.resultMap[encodedSet]; !ok {
				tspSolver.resultMap[encodedSet] = make(map[int64]float64)
			}
			j := subset[0]
			encodedSetWithoutJ := encodedSet ^ getInt64ByBits([]int64{j})
			tspSolver.resultMap[encodedSet][j] = tspSolver.resultMap[encodedSetWithoutJ]
			for i := 1; i < len(subset); i++ {
				j = subset[i]
			}*/
		}
	}
	result := math.MaxFloat64
	encodedSet := getInt64ByBits(vertices)
	for vertexId, value := range tspSolver.resultMap[encodedSet] {
		if vertexId == startPoint {
			continue
		}
		result = math.Min(result, value + shortestEdgesMap[vertexId][startPoint])
	}
	return result
}

func TSP(ug *graph.UndirectedGraph) float64 {
	return createTSPSolver(ug).solveByDP()
}