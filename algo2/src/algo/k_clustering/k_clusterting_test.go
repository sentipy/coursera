package k_clustering

import (
	"testing"
)

func Test2Items(t *testing.T) {
	items := make([]*Item, 1)
	items[0] = &Item{IdTo:2, IdFrom:1, Distance:1}
	maxSpacing := KClustering(2, items)
	if (maxSpacing != 1) {
		t.Error("Expected 1, got ", maxSpacing)
	}
}

func Test3Ids_2Clusters(t *testing.T) {
	items := make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:1}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:2}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:3}
	maxSpacing := KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}

	items = make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:1}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:3}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:2}
	maxSpacing = KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}

	items = make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:2}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:1}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:3}
	maxSpacing = KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}

	items = make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:2}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:3}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:1}
	maxSpacing = KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}

	items = make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:1}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:2}
	maxSpacing = KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}

	items = make([]*Item, 3)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:2, IdTo:3, Distance:2}
	items[2] = &Item{IdFrom:1, IdTo:3, Distance:1}
	maxSpacing = KClustering(2, items)
	if (maxSpacing != 2) {
		t.Error("Expected 2, got ", maxSpacing)
	}
}

func Test4Ids_2Clusters(t *testing.T) {
	items := make([]*Item, 6)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:4}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:5}
	items[3] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[4] = &Item{IdFrom:2, IdTo:4, Distance:6}
	items[5] = &Item{IdFrom:3, IdTo:4, Distance:7}

	maxSpacing := KClustering(2, items)
	if (maxSpacing != 5) {
		t.Error("Expected 5, got ", maxSpacing)
	}

	items = make([]*Item, 6)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:4}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:7}
	items[3] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[4] = &Item{IdFrom:2, IdTo:4, Distance:7}
	items[5] = &Item{IdFrom:3, IdTo:4, Distance:7}

	maxSpacing = KClustering(2, items)
	if (maxSpacing != 7) {
		t.Error("Expected 7, got ", maxSpacing)
	}
}

func Test4Ids_3Clusters(t *testing.T) {
	items := make([]*Item, 6)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:4}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:5}
	items[3] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[4] = &Item{IdFrom:2, IdTo:4, Distance:6}
	items[5] = &Item{IdFrom:3, IdTo:4, Distance:7}

	maxSpacing := KClustering(3, items)
	if (maxSpacing != 4) {
		t.Error("Expected 4, got ", maxSpacing)
	}

	items = make([]*Item, 6)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:4}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:5}
	items[3] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[4] = &Item{IdFrom:2, IdTo:4, Distance:6}
	items[5] = &Item{IdFrom:3, IdTo:4, Distance:7}

	maxSpacing = KClustering(2, items)
	if (maxSpacing != 5) {
		t.Error("Expected 5, got ", maxSpacing)
	}
}

func Test4Ids_4Clusters(t *testing.T) {
	items := make([]*Item, 6)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:3}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:4}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:5}
	items[3] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[4] = &Item{IdFrom:2, IdTo:4, Distance:6}
	items[5] = &Item{IdFrom:3, IdTo:4, Distance:7}

	maxSpacing := KClustering(4, items)
	if (maxSpacing != 3) {
		t.Error("Expected 3, got ", maxSpacing)
	}
}

/*
this is something like this
dist =   1     5      3       6     2
       * - * ----- * --- * ------ * -- *
       1   2       3     4        5    6
 */
func Test6Ids_2Clusters(t *testing.T) {
	items := make([]*Item, 15)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:1}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:6}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:9}
	items[3] = &Item{IdFrom:1, IdTo:5, Distance:15}
	items[4] = &Item{IdFrom:1, IdTo:6, Distance:17}
	items[5] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[6] = &Item{IdFrom:2, IdTo:4, Distance:8}
	items[7] = &Item{IdFrom:2, IdTo:5, Distance:14}
	items[8] = &Item{IdFrom:2, IdTo:6, Distance:16}
	items[9] = &Item{IdFrom:3, IdTo:4, Distance:3}
	items[10] = &Item{IdFrom:3, IdTo:5, Distance:9}
	items[11] = &Item{IdFrom:3, IdTo:6, Distance:11}
	items[12] = &Item{IdFrom:4, IdTo:5, Distance:6}
	items[13] = &Item{IdFrom:4, IdTo:6, Distance:8}
	items[14] = &Item{IdFrom:5, IdTo:6, Distance:2}

	maxSpacing := KClustering(2, items)
	if (maxSpacing != 6) {
		t.Error("Expected 6, got ", maxSpacing)
	}
}

/*
this is something like this
dist =   1     5      3       6     2
       * - * ----- * --- * ------ * -- *
       1   2       3     4        5    6
 */
func Test6Ids_3Clusters(t *testing.T) {
	items := make([]*Item, 15)
	items[0] = &Item{IdFrom:1, IdTo:2, Distance:1}
	items[1] = &Item{IdFrom:1, IdTo:3, Distance:6}
	items[2] = &Item{IdFrom:1, IdTo:4, Distance:9}
	items[3] = &Item{IdFrom:1, IdTo:5, Distance:15}
	items[4] = &Item{IdFrom:1, IdTo:6, Distance:17}
	items[5] = &Item{IdFrom:2, IdTo:3, Distance:5}
	items[6] = &Item{IdFrom:2, IdTo:4, Distance:8}
	items[7] = &Item{IdFrom:2, IdTo:5, Distance:14}
	items[8] = &Item{IdFrom:2, IdTo:6, Distance:16}
	items[9] = &Item{IdFrom:3, IdTo:4, Distance:3}
	items[10] = &Item{IdFrom:3, IdTo:5, Distance:9}
	items[11] = &Item{IdFrom:3, IdTo:6, Distance:11}
	items[12] = &Item{IdFrom:4, IdTo:5, Distance:6}
	items[13] = &Item{IdFrom:4, IdTo:6, Distance:8}
	items[14] = &Item{IdFrom:5, IdTo:6, Distance:2}

	maxSpacing := KClustering(3, items)
	if (maxSpacing != 5) {
		t.Error("Expected 5, got ", maxSpacing)
	}
}