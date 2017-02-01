package weighted_sum

import (
	"sort"
)

type Job struct {
	Weight int64
	Length int64
}

type SortableJobSlice struct {
	jobs []Job
	lessFunc func (job1, job2 Job) bool
}

func (sortableJobSlice SortableJobSlice) Len() int {
	return len(sortableJobSlice.jobs)
}

func (sortableJobSlice SortableJobSlice) Less(i, j int) bool {
	job1 := sortableJobSlice.jobs[i]
	job2 := sortableJobSlice.jobs[j]
	return sortableJobSlice.lessFunc(job1, job2)
}

func (sortableJobSlice SortableJobSlice) Swap(i, j int) {
	sortableJobSlice.jobs[i], sortableJobSlice.jobs[j] = sortableJobSlice.jobs[j], sortableJobSlice.jobs[i]
}

func CalculateWeightedSum(jobs []Job, lessFunction func (job1, job2 Job) bool) int64 {
	sortableJobSlice := SortableJobSlice{jobs:jobs, lessFunc:lessFunction}
	sort.Sort(sortableJobSlice)
	var weightedSum int64 = 0
	var currentLength int64 = 0
	for _, job := range sortableJobSlice.jobs {
		weightedSum += job.Weight * (job.Length + currentLength)
		currentLength += job.Length
	}
	return weightedSum
}