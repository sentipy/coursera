package main

import "algo/k_clustering"

import (
	"common_utils"
	"os"
	"fmt"
	"bufio"
	"strings"
	//"github.com/spakin/disjoint"
	//"github.com/thcyron/graphs"
	//"github.com/gonum/graph"
)

func readFileWeek2Part1(file string) []*k_clustering.Item {
	f, err := os.Open(file)
	if err != nil {
		return nil;
	}
	defer f.Close()
	return parseFileWeek2Part1(f)
}

func parseFileWeek2Part1(file *os.File) []*k_clustering.Item {
	scanner := bufio.NewScanner(file)
	if (!scanner.Scan()) {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	nodesAmount := scanner.Text()
	nodesAmountInt64 := common_utils.ConvertStringToInt64WithPanic(nodesAmount)
	result := make([]*k_clustering.Item, nodesAmountInt64 * (nodesAmountInt64 - 1) / 2)
	var counter int64 = 0
	for scanner.Scan() {
		str := scanner.Text();
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 3 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 3 values must be separated by space"))
		}
		idFrom := common_utils.ConvertStringToInt64WithPanic(splitted[0])
		idTo := common_utils.ConvertStringToInt64WithPanic(splitted[1])
		distance := common_utils.ConvertStringToFloat64WithPanic(splitted[2])
		result[counter] = &k_clustering.Item{
			IdFrom:idFrom, IdTo:idTo, Distance:distance,
		}
		counter++
	}
	return result
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
	data := readFileWeek2Part1(file)
	maxSpacing := k_clustering.KClustering(4, data)
	fmt.Printf("%f\n", maxSpacing)

}