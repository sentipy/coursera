package k_clustering

import (
	"sort"
	"github.com/spakin/disjoint"
	"math"
)

type Item struct {
	IdFrom int64
	IdTo int64
	Distance float64
}

type ItemPointersSlice []*Item

func (items ItemPointersSlice) Len() int {
	return len(items)
}

func (items ItemPointersSlice) Less(i, j int) bool {
	dist1 := items[i].Distance
	dist2 := items[j].Distance
	return dist1 < dist2
}

func (items ItemPointersSlice) Swap(i, j int) {
	items[i], items[j] = items[j], items[i]
}

func KClustering(clustersTargetAmount int, items []*Item) float64 {
	mapIdsWithElements := make(map[int64]*disjoint.Element)
	distancesMap := make(map[int64]map[int64]float64)
	for _, item := range items {
		idFrom := item.IdFrom
		idTo := item.IdTo
		if _, ok := mapIdsWithElements[idFrom]; !ok {
			newElement := disjoint.NewElement();
			newElement.Data = idFrom
			mapIdsWithElements[idFrom] = newElement

			distancesMap[idFrom] = make(map[int64]float64)
		}
		if _, ok := mapIdsWithElements[idTo]; !ok {
			newElement := disjoint.NewElement();
			newElement.Data = idTo
			mapIdsWithElements[idTo] = newElement

			distancesMap[idTo] = make(map[int64]float64)
		}
		distancesMap[idFrom][idTo] = item.Distance
		distancesMap[idTo][idFrom] = item.Distance
	}
	sort.Sort(ItemPointersSlice(items))
	clustersLeft := len(mapIdsWithElements)
	var counter int64 = 0
	for clustersLeft > clustersTargetAmount {
		item := items[counter]
		idFrom := item.IdFrom
		idTo := item.IdTo
		parentFrom := mapIdsWithElements[idFrom].Find()
		parentTo := mapIdsWithElements[idTo].Find()
		if parentFrom != parentTo {
			disjoint.Union(parentFrom, parentTo)
			clustersLeft--
		}
		counter++
	}
	clusters := make(map[int64][]int64, clustersTargetAmount)
	for id, element := range mapIdsWithElements {
		clusterParentId := element.Find().Data.(int64);
		slice, ok := clusters[clusterParentId]
		if (!ok) {
			slice = make([]int64, 0)
		}
		slice = append(slice, id)
		clusters[clusterParentId] = slice
	}
	var maxSpacing float64 = math.Inf(1)
	for clusterParentId1, slice1 := range clusters{
		for clusterParentId2, slice2 := range clusters{
			if (clusterParentId1 == clusterParentId2) {
				continue
			}
			for _, id1 := range slice1 {
				for _, id2 := range slice2 {
					dist := distancesMap[id1][id2]
					if (dist < maxSpacing) {
						maxSpacing = dist
					}
				}
			}
		}
	}
	return maxSpacing
}