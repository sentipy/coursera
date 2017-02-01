package common_utils

import (
	"strconv"
	"fmt"
)

func PanicIfErrorWithMessage(err error, message string) {
	if (err != nil) {
		panic(message + "\nError: " + err.Error())
	}
}

func PanicIfError(err error) {
	if (err != nil) {
		panic(err)
	}
}

func ConvertStringToInt64WithPanic(str string) (number int64) {
	number, err := strconv.ParseInt(str, 10, 64)
	PanicIfErrorWithMessage(err, fmt.Sprintln("Could not convert %s to number.", number))
	return
}

func ConvertStringToFloat64WithPanic(str string) (number float64) {
	number, err := strconv.ParseFloat(str, 64)
	PanicIfErrorWithMessage(err, fmt.Sprintln("Could not convert %s to number.", number))
	return
}