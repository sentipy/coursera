package knapsack

import (
	"sort"
	"fmt"
)

type recursiveSolverItem struct {
	knapsackItem *KnapsackItem
	ratio float64
}

type SortableRecursiveSolverItemSlice struct {
	items []*recursiveSolverItem
	lessFunc func (item1, item2 *recursiveSolverItem) bool
}

func (sortableRecursiveSolverItemSlice *SortableRecursiveSolverItemSlice) Len() int {
	return len(sortableRecursiveSolverItemSlice.items)
}

func (sortableRecursiveSolverItemSlice *SortableRecursiveSolverItemSlice) Less(i, j int) bool {
	item1 := sortableRecursiveSolverItemSlice.items[i]
	item2 := sortableRecursiveSolverItemSlice.items[j]
	return sortableRecursiveSolverItemSlice.lessFunc(item1, item2)
}

func (sortableRecursiveSolverItemSlice *SortableRecursiveSolverItemSlice) Swap(i, j int) {
	sortableRecursiveSolverItemSlice.items[i], sortableRecursiveSolverItemSlice.items[j] =
		sortableRecursiveSolverItemSlice.items[j], sortableRecursiveSolverItemSlice.items[i]
}

type recursiveSolver struct {
	items []*recursiveSolverItem
	knapsackSize int64
	currentBestValue int64
}

func (recursiveSolver *recursiveSolver) getUpperBound(firstAllowedItemIndex int64, weight int64) int64 {
	weightLeft := weight
	currentIndex := firstAllowedItemIndex
	itemsAmount := int64(len(recursiveSolver.items))
	upperBound := int64(0)
	currentItem := recursiveSolver.items[currentIndex].knapsackItem
	for weightLeft >= currentItem.Weight {
		upperBound += currentItem.Value
		weightLeft -= currentItem.Weight
		currentIndex++
		if (currentIndex >= itemsAmount) {
			return upperBound
		}
		currentItem = recursiveSolver.items[currentIndex].knapsackItem
	}
	lastAdd := float64(currentItem.Value) * float64(weightLeft) / float64(currentItem.Weight)
	return upperBound + int64(lastAdd)
}

func (recursiveSolver *recursiveSolver) solveInner(firstAllowedItemIndex int64, weightLeft int64, totalValue int64) {
	itemsAmount := int64(len(recursiveSolver.items))
	if firstAllowedItemIndex >= itemsAmount - 1 {
		if totalValue > recursiveSolver.currentBestValue {
			fmt.Println("New best value: ", totalValue, ", was: ", recursiveSolver.currentBestValue)
			recursiveSolver.currentBestValue = totalValue
		}
		return
	}
	nextItemIndex := firstAllowedItemIndex + 1;
	// do not take current item
	upperBoundWithoutCurrentItem := totalValue + recursiveSolver.getUpperBound(nextItemIndex, weightLeft)
	item := recursiveSolver.items[firstAllowedItemIndex]
	itemValue := item.knapsackItem.Value
	itemWeight := item.knapsackItem.Weight
	weightLeftAfterTakingCurrentItem := weightLeft - itemWeight
	if weightLeftAfterTakingCurrentItem < 0 {
		recursiveSolver.solveInner(nextItemIndex, weightLeft, totalValue)
		return
	}
	// take current item
	upperBoundWithCurrentItem := totalValue + itemValue + recursiveSolver.getUpperBound(nextItemIndex, weightLeft - item.knapsackItem.Weight)
	if upperBoundWithoutCurrentItem < recursiveSolver.currentBestValue && upperBoundWithCurrentItem < recursiveSolver.currentBestValue {
		return
	}
	if upperBoundWithoutCurrentItem > upperBoundWithCurrentItem {
		recursiveSolver.solveInner(nextItemIndex, weightLeft, totalValue)
		if upperBoundWithCurrentItem > recursiveSolver.currentBestValue {
			recursiveSolver.solveInner(nextItemIndex, weightLeftAfterTakingCurrentItem, totalValue + itemValue)
		}
	} else {
		recursiveSolver.solveInner(nextItemIndex, weightLeftAfterTakingCurrentItem, totalValue + itemValue)
		if upperBoundWithoutCurrentItem > recursiveSolver.currentBestValue {
			recursiveSolver.solveInner(nextItemIndex, weightLeft, totalValue)
		}
	}
}

func (recursiveSolver *recursiveSolver) solve() {
	recursiveSolver.solveInner(0, recursiveSolver.knapsackSize, 0)
}

func createRecursiveSolver(knapsackProblem *KnapsackProblem) *recursiveSolver {
	lessFunc := func(item1, item2 *recursiveSolverItem) bool {
		ratio1 := item1.ratio
		ratio2 := item2.ratio
		if (ratio1 > ratio2) {
			return true
		}
		return false
	}
	itemsAmount := len(knapsackProblem.Items)
	recursiveSolverItems := make([]*recursiveSolverItem, itemsAmount)
	for i := 0; i < itemsAmount; i++ {
		item := knapsackProblem.Items[i]
		ratio := float64(item.Value) / float64(item.Weight)
		recursiveSolverItems[i] = &recursiveSolverItem{knapsackItem:item,ratio:ratio}
	}
	sortableRecursiveSolverItemSlice := &SortableRecursiveSolverItemSlice{items:recursiveSolverItems,lessFunc:lessFunc}
	sort.Sort(sortableRecursiveSolverItemSlice)
	return &recursiveSolver{items:sortableRecursiveSolverItemSlice.items, knapsackSize:knapsackProblem.KnapsackSize, currentBestValue:0}
}

func SolveKnapsackProblemRecursive(knapsackProblem *KnapsackProblem) int64 {
	recursiveSolverInstance := createRecursiveSolver(knapsackProblem)
	recursiveSolverInstance.solve()
	return recursiveSolverInstance.currentBestValue
	//return solveKnapsackProblemRecursive(knapsackProblem, 0, knapsackProblem.KnapsackSize)
}