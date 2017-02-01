package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"algo/weighted_sum"
	"common_utils"
)

func readFileWeek1Part1(file string) []weighted_sum.Job {
	f, err := os.Open(file)
	if err != nil {
		return nil;
	}
	defer f.Close()
	return parseFileWeek1Part1(f)
}

func parseFileWeek1Part1(file *os.File) []weighted_sum.Job {
	scanner := bufio.NewScanner(file)
	if (!scanner.Scan()) {
		return nil
	}
	common_utils.PanicIfError(scanner.Err())
	amount := common_utils.ConvertStringToInt64WithPanic(scanner.Text())
	result := make([]weighted_sum.Job, amount)
	for i := int64(0); i < amount; i++ {
		scanner.Scan()
		str := scanner.Text();
		common_utils.PanicIfErrorWithMessage(scanner.Err(), "Error while reading from file")
		splitted := strings.Split(str, " ")
		if len(splitted) < 2 {
			panic(fmt.Sprintln("Got a incorrect line \"", str, "\": 2 values must be separated by space"))
		}
		weight := common_utils.ConvertStringToInt64WithPanic(splitted[0])
		length := common_utils.ConvertStringToInt64WithPanic(splitted[1])
		result[i] = weighted_sum.Job{Weight:weight,Length:length};
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
	jobs := readFileWeek1Part1(file)
	weightedSumDiff := weighted_sum.CalculateWeightedSum(jobs[:], weighted_sum.SortFunctionByDecreasingOrderOfDifferenceWeightLengthHigherWeightFirst)
	weightedSumRatio := weighted_sum.CalculateWeightedSum(jobs[:], weighted_sum.SortFunctionByDecreasingOrderOfRatioWeightToLength)
	fmt.Println(weightedSumDiff)
	fmt.Println(weightedSumRatio)
}