package main

import (
	"github.com/alonsovidales/go_graph"
	"os"
	"fmt"
	"common_utils"
	"strings"
	"bufio"
)

func readFileWeek6(file string) [][]uint64 {
	f, err := os.Open(file)
	if err != nil {
		return nil
	}
	defer f.Close()
	return parseFileWeek6(f)
}

func parseFileWeek6(file *os.File) [][]uint64 {
	scanner := bufio.NewScanner(file)
	if !scanner.Scan() {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	//amountAsString := scanner.Text()
	//amount := common_utils.ConvertStringToInt64WithPanic(amountAsString)
	edges := [][]uint64{}
	//var counter int64
	for scanner.Scan() {
		str := scanner.Text()
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 2 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 2 values must be separated by space"))
		}
		first := uint64(common_utils.ConvertStringToInt64WithPanic(splitted[0]))
		second := uint64(common_utils.ConvertStringToFloat64WithPanic(splitted[1]))
		edges = append(edges, []uint64{-first, second})
		edges = append(edges, []uint64{-second, first})
	}
	return edges
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
	for _, file := range files {
		g := graphs.GetUnWeightGraph(readFileWeek6(file), false)
		sccs, _ := g.StronglyConnectedComponents()
		result := true
		for k, sccNumber1 := range sccs {
			if sccNumber2, _ := sccs[-k]; sccNumber1 == sccNumber2 {
				result = false
				break
			}
		}
		fmt.Println(result)
	}
}
