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
	"strconv"
)

func readFileWeek2Part2(file string) *k_clustering.Data {
	f, err := os.Open(file)
	common_utils.PanicIfError(err)
	defer f.Close()
	return parseFileWeek2Part2(f)
}

func parseFileWeek2Part2(file *os.File) *k_clustering.Data {
	scanner := bufio.NewScanner(file)
	if (!scanner.Scan()) {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	nodesAmountAndBitLength := scanner.Text()
	splitted := strings.Split(nodesAmountAndBitLength, " ")
	nodesAmountInt64 := common_utils.ConvertStringToInt64WithPanic(splitted[0])
	numberOfBitsInt64 := common_utils.ConvertStringToInt64WithPanic(splitted[1])
	var counter int64 = 0
	nodes := make([]uint32, nodesAmountInt64)
	for scanner.Scan() {
		str := scanner.Text();
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		str = strings.Replace(str, " ", "", -1)
		parsedInteger, err := strconv.ParseInt(str, 2, 64)
		common_utils.PanicIfErrorWithMessage(err, "Error while parsing string \"" + str + "\" to number")
		nodes[counter] = uint32(parsedInteger)
		counter++
	}
	return &k_clustering.Data{Nodes:nodes,NumberOfBits:uint8(numberOfBitsInt64)}
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
	data := readFileWeek2Part2(file)
	requiredClusters := k_clustering.KClustertingHamming(data, 3)
	fmt.Println(requiredClusters)

}