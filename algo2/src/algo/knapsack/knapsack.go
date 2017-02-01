package knapsack

type KnapsackItem struct {
	Value int64
	Weight int64
}

type KnapsackProblem struct {
	KnapsackSize int64
	Items []*KnapsackItem
}

type resultStore struct {
	itemIds []int64
	value int64
}

func SolveKnapsackProblem(knapsackProblem *KnapsackProblem) int64 {
	itemsAmount := int64(len(knapsackProblem.Items))
	knapsackSize := knapsackProblem.KnapsackSize
	// weight, lastItemIndex
	resultMap := make(map[int64]map[int64]*resultStore, knapsackProblem.KnapsackSize)
	for currentWeight := int64(0); currentWeight <= knapsackSize; currentWeight++ {
		resultMap[currentWeight] = make(map[int64]*resultStore, itemsAmount)
		value := int64(0)
		if (knapsackProblem.Items[0].Weight <= currentWeight) {
			value = knapsackProblem.Items[0].Value
		}
		resultMap[currentWeight][0] = &resultStore{itemIds:[]int64{}, value:value}
	}
	for itemIndex := int64(0); itemIndex < itemsAmount; itemIndex++ {
		resultMap[0][itemIndex] = &resultStore{itemIds:[]int64{}, value:0}
	}
	for i := int64(1); i < itemsAmount; i++ {
		item := knapsackProblem.Items[i]
		itemWeight := item.Weight
		itemValue := item.Value
		for currentMaxWeight := int64(1); currentMaxWeight <= knapsackProblem.KnapsackSize; currentMaxWeight++ {
			bestResultStore := resultMap[currentMaxWeight][i - 1]
			if (itemWeight <= currentMaxWeight) {
				diffWeight := currentMaxWeight - itemWeight
				newValue := resultMap[diffWeight][i - 1].value + itemValue
				if (newValue > bestResultStore.value) {
					bestResultStore = &resultStore{value:newValue, itemIds:append(bestResultStore.itemIds[:], i)}
				}
			}
			resultMap[currentMaxWeight][i] = bestResultStore
		}
	}
	return resultMap[knapsackSize][itemsAmount - 1].value
}