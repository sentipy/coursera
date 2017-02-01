package knapsack

import "testing"

func Test4Items_Weight10_Recursive(t *testing.T) {
	items := make([]*KnapsackItem, 4)
	items[0] = &KnapsackItem{Weight:6,Value:14}
	items[1] = &KnapsackItem{Weight:5,Value:6}
	items[2] = &KnapsackItem{Weight:5,Value:7}
	items[3] = &KnapsackItem{Weight:5,Value:8}
	knapsack := &KnapsackProblem{Items:items, KnapsackSize:10}
	result := SolveKnapsackProblemRecursive(knapsack)
	if (result != 15) {
		t.Error("Expected 15, got ", result)
	}
}