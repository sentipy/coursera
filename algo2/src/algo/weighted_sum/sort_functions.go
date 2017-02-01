package weighted_sum

func SortFunctionByDecreasingOrderOfDifferenceWeightLengthHigherWeightFirst(job1, job2 Job) bool {
	diff1 := job1.Weight - job1.Length
	diff2 := job2.Weight - job2.Length
	if (diff1 == diff2) {
		return job1.Weight > job2.Weight
	}
	return diff1 > diff2
}

func SortFunctionByDecreasingOrderOfRatioWeightToLength(job1, job2 Job) bool {
	ratio1 := float64(job1.Weight) / float64(job1.Length)
	ratio2 := float64(job2.Weight) / float64(job2.Length)
	return ratio1 > ratio2
}
