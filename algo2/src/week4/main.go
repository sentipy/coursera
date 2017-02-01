package main

import (
	"os"
	"fmt"
	"bufio"
	"common_utils"
	"strings"
	"algo/graph"
	"algo/graph/johnson"
	"math"
	"algo/graph/bellman_ford"
)

func readFileWeek4(file string) *graph.DirectedGraph {
	f, err := os.Open(file)
	if err != nil {
		return nil
	}
	defer f.Close()
	return parseFileWeek4(f)
}

func parseFileWeek4(file *os.File) *graph.DirectedGraph {
	scanner := bufio.NewScanner(file)
	if !scanner.Scan() {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	verticesAmountAndEdgesAmount := scanner.Text()
	splitted := strings.Split(verticesAmountAndEdgesAmount, " ")
	verticesAmount := common_utils.ConvertStringToInt64WithPanic(splitted[0])
	graph := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(verticesAmount);
	for scanner.Scan() {
		str := scanner.Text()
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 3 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 2 values must be separated by space"))
		}
		fromVertex := common_utils.ConvertStringToInt64WithPanic(splitted[0])
		toVertex := common_utils.ConvertStringToInt64WithPanic(splitted[1])
		weight := common_utils.ConvertStringToInt64WithPanic(splitted[2])
		graph.AddVertex(fromVertex)
		graph.AddVertex(toVertex)
		graph.AddEdge(fromVertex, toVertex, float64(weight))
	}
	return graph
}

func main() {
	if len(os.Args) < 2 {
		fmt.Println("At least one file with data must be specified!")
		os.Exit(-1)
	}
	files := os.Args[1:]
	for _, file := range files {
		_, err := os.Stat(file)
		if os.IsNotExist(err) {
			fmt.Printf("File \"%s\" does not exist!\n", file)
			os.Exit(-2)
		}
	}
	graphInstance := graph.CreateEmptyDirectedGraphWithSpecifiedVertexAmountHint(1)
	graphInstance.AddVertex(1)
	bellman_ford.BellmanFord(graphInstance, 1)
	shortest := math.MaxFloat64
	for _, file := range files {
		fmt.Println("Processing file: ", file)
		graph := readFileWeek4(file)
		//floyd_warshall.FloydWarshall(graph)
		result, noNegativeCycle := johnson.Johnson(graph)
		if !noNegativeCycle {
			fmt.Println("Graph from file", file, "has negative cycle!")
		} else {
			for _, edges := range result {
				for _, distance := range edges {
					if distance < shortest {
						shortest = distance
					}
				}
			}
		}
	}
	fmt.Println(shortest)
}