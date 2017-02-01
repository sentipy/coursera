package main

import (
	"os"
	"fmt"
	"bufio"
	"common_utils"
	"strings"
	"algo/graph"
	"math"
	"algo/tsp"
)

func readFileWeek5(file string) *graph.UndirectedGraph {
	f, err := os.Open(file)
	if err != nil {
		return nil
	}
	defer f.Close()
	return parseFileWeek5(f)
}

func parseFileWeek5(file *os.File) *graph.UndirectedGraph {
	type location struct {
		x float64
		y float64
	}
	scanner := bufio.NewScanner(file)
	if !scanner.Scan() {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	amountOfCitiesString := scanner.Text()
	amountOfCities := common_utils.ConvertStringToInt64WithPanic(amountOfCitiesString)
	ug := graph.CreateEmptyUndirectedGraphWithSpecifiedVertexAmountHint(amountOfCities)
	locations := make([]location, amountOfCities, amountOfCities)
	var counter int64
	for scanner.Scan() {
		str := scanner.Text()
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 2 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 2 values must be separated by space"))
		}
		x := common_utils.ConvertStringToFloat64WithPanic(splitted[0])
		y := common_utils.ConvertStringToFloat64WithPanic(splitted[1])
		locations[counter] = location{x:x, y:y}
		counter++
	}
	for i := int64(0); i < amountOfCities; i++ {
		ug.AddVertex(i)
	}
	for i := int64(0); i < amountOfCities; i++ {
		loc1 := locations[i]
		for j := i + 1; j < amountOfCities; j++ {
			loc2 := locations[j]
			xDiff := loc1.x - loc2.x
			yDiff := loc1.y - loc2.y
			ug.AddEdge(i, j, math.Sqrt(xDiff * xDiff + yDiff * yDiff))
		}
	}
	return ug
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
	ug := readFileWeek5(file)
	result := tsp.TSP(ug)
	fmt.Println(result)
}