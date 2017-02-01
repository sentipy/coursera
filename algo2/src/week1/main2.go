package main

import (
	"algo/prim"
	"github.com/gyuho/goraph"
	"os"
	"fmt"
	"bufio"
	"strings"
	"common_utils"
)

func readFileWeek1Part2(file string) goraph.Graph {
	f, err := os.Open(file)
	if err != nil {
		return nil;
	}
	defer f.Close()
	return parseFileWeek1Part2(f)
}

func parseFileWeek1Part2(file *os.File) goraph.Graph {
	scanner := bufio.NewScanner(file)
	if (!scanner.Scan()) {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	splitted := strings.Split(scanner.Text(), " ")
	edgesAmount := common_utils.ConvertStringToInt64WithPanic(splitted[1])
	graph := goraph.NewGraph()
	for i := int64(0); i < edgesAmount; i++ {
		scanner.Scan()
		str := scanner.Text();
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 3 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 3 values must be separated by space"))
		}
		length := common_utils.ConvertStringToFloat64WithPanic(splitted[2])
		fromNode := goraph.NewNode(splitted[0]);
		toNode := goraph.NewNode(splitted[1]);
		graph.AddNode(fromNode)
		graph.AddNode(toNode)
		graph.AddEdge(fromNode.ID(), toNode.ID(), length)
		graph.AddEdge(toNode.ID(), fromNode.ID(), length)
	}
	return graph
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
	graph := readFileWeek1Part2(file)
	mstSum := prim.CalculateMSTSum(graph)
	fmt.Printf("%f\n", mstSum)
}
