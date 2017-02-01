package main

import (
	"algo/knapsack"
	"bufio"
	"common_utils"
	"fmt"
	"os"
	"strings"
)

func readFileWeek3_2(file string) *knapsack.KnapsackProblem {
	f, err := os.Open(file)
	if err != nil {
		return nil
	}
	defer f.Close()
	return parseFileWeek3(f)
}

func parseFileWeek3_2(file *os.File) *knapsack.KnapsackProblem {
	scanner := bufio.NewScanner(file)
	if !scanner.Scan() {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	knapsackSizeAndNumberOfItems := scanner.Text()
	splitted := strings.Split(knapsackSizeAndNumberOfItems, " ")
	knapsackSize := common_utils.ConvertStringToInt64WithPanic(splitted[0])
	numberOfItems := common_utils.ConvertStringToInt64WithPanic(splitted[1])
	items := make([]*knapsack.KnapsackItem, numberOfItems)
	var counter int64
	for scanner.Scan() {
		str := scanner.Text()
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 2 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 2 values must be separated by space"))
		}
		value := common_utils.ConvertStringToInt64WithPanic(splitted[0])
		weight := common_utils.ConvertStringToInt64WithPanic(splitted[1])
		items[counter] = &knapsack.KnapsackItem{
			Value: value, Weight: weight,
		}
		counter++
	}
	return &knapsack.KnapsackProblem{KnapsackSize: knapsackSize, Items: items}
}

func main() {
	if len(os.Args) < 2 {
		fmt.Println("File with data must be specified!")
		os.Exit(-1)
	}
	file := os.Args[1]
	_, err := os.Stat(file)
	if os.IsNotExist(err) {
		fmt.Printf("File \"%s\" does not exist!\n", file)
		os.Exit(-2)
	}
	knapsackProblem := readFileWeek3_2(file)
	result := knapsack.SolveKnapsackProblemRecursive(knapsackProblem)
	fmt.Println(result)
}
