package k_clustering

import (
	"math"
	"github.com/spakin/disjoint"
)

type Data struct {
	Nodes []uint32
	NumberOfBits uint8
}

var powersOf2 []uint32 = getPowersOf2()

func getPowersOf2() []uint32 {
	result := make([]uint32, 32)
	for i := 0; i < 32; i++ {
		result[i] = uint32(math.Pow(2., float64(i)))
	}
	return result
}

func getAllCombinationsWithSpecificAmountOfBitsSet(currentBitSet uint32, currentBitNumber uint8, requiredAmountOfBitsSet uint8, numberOfBits uint8) []uint32 {
	result := []uint32{}
	if (requiredAmountOfBitsSet == 0) {
		result = append(result, currentBitSet)
		return result
	}
	if (currentBitNumber + requiredAmountOfBitsSet < numberOfBits) {
		result = append(result, getAllCombinationsWithSpecificAmountOfBitsSet(currentBitSet, currentBitNumber + 1, requiredAmountOfBitsSet, numberOfBits)...)
	}
	result = append(
		result,
		getAllCombinationsWithSpecificAmountOfBitsSet(
			currentBitSet + powersOf2[currentBitNumber], currentBitNumber + 1, requiredAmountOfBitsSet - 1, numberOfBits,
		)...
	)
	return result
}

func createXORs(numberOfBits uint8, minBitsSet uint8, maxBitsSet uint8) []uint32 {
	result := []uint32{}
	for currentBitAmount := minBitsSet; currentBitAmount <=maxBitsSet; currentBitAmount++ {
		result = append(result, getAllCombinationsWithSpecificAmountOfBitsSet(0, 0, currentBitAmount, numberOfBits)...)
	}
	return result
}

func KClustertingHamming(data *Data, minDesiredSpacing uint) uint {
	xors := createXORs(data.NumberOfBits, 1, uint8(minDesiredSpacing - 1));
	nodesMap := make(map[uint32]*disjoint.Element, len(data.Nodes))
	for _, node := range data.Nodes {
		element := disjoint.NewElement();
		element.Data = node
		nodesMap[node] = element
	}
	clustersRequired := uint(len(nodesMap))
	for _, node := range data.Nodes {
		elem1 := nodesMap[node]
		for _, xor := range xors {
			xoredNode := node ^ xor
			elem2, ok := nodesMap[xoredNode]
			if (ok) {
				if elem1.Find() != elem2.Find() {
					disjoint.Union(elem1, elem2)
					clustersRequired--
				}
			}
		}
	}
	return clustersRequired
}
