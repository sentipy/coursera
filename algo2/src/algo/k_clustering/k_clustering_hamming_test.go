package k_clustering

import (
	"testing"
	"strconv"
	"common_utils"
)

func getUint32FromBinarytring(str string) uint32 {
	value, err := strconv.ParseInt(str, 2, 32)
	common_utils.PanicIfError(err)
	return uint32(value)
}

func TestHamming_2Nodes_Spacing3_2Clusters(t *testing.T) {
	nodes := make([]uint32, 2)
	nodes[0] = getUint32FromBinarytring("11000")
	nodes[1] = getUint32FromBinarytring("00011")
	data := &Data{Nodes:nodes,NumberOfBits:5}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 2 {
		t.Error("Expected 2, got ", clustersRequired)
	}
}

func TestHamming_4Nodes_Spacing3_2Clusters(t *testing.T) {
	nodes := make([]uint32, 4)
	nodes[0] = getUint32FromBinarytring("11100")
	nodes[1] = getUint32FromBinarytring("00001")
	nodes[2] = getUint32FromBinarytring("00010")
	nodes[3] = getUint32FromBinarytring("00011")
	data := &Data{Nodes:nodes,NumberOfBits:5}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 2 {
		t.Error("Expected 2, got ", clustersRequired)
	}
}

func TestHamming_4Nodes_Spacing3_3Clusters(t *testing.T) {
	nodes := make([]uint32, 4)
	nodes[0] = getUint32FromBinarytring("111000")
	nodes[1] = getUint32FromBinarytring("000001")
	nodes[2] = getUint32FromBinarytring("000011")
	nodes[3] = getUint32FromBinarytring("001100")
	data := &Data{Nodes:nodes,NumberOfBits:6}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 3 {
		t.Error("Expected 3, got ", clustersRequired)
	}
}

func TestHamming_4Nodes_Spacing3_4Clusters(t *testing.T) {
	nodes := make([]uint32, 4)
	nodes[0] = getUint32FromBinarytring("000000000")
	nodes[1] = getUint32FromBinarytring("000000111")
	nodes[2] = getUint32FromBinarytring("000111000")
	nodes[3] = getUint32FromBinarytring("111000000")
	data := &Data{Nodes:nodes,NumberOfBits:9}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 4 {
		t.Error("Expected 4, got ", clustersRequired)
	}
}

func TestHamming_8Nodes_Spacing3_3Clusters(t *testing.T) {
	nodes := make([]uint32, 8)
	nodes[0] = getUint32FromBinarytring("000011100")
	nodes[1] = getUint32FromBinarytring("000011000")
	nodes[2] = getUint32FromBinarytring("000001100")
	nodes[3] = getUint32FromBinarytring("000010100")
	nodes[4] = getUint32FromBinarytring("000000001")
	nodes[5] = getUint32FromBinarytring("000000010")
	nodes[6] = getUint32FromBinarytring("000000011")
	nodes[7] = getUint32FromBinarytring("111000000")
	data := &Data{Nodes:nodes,NumberOfBits:9}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 3 {
		t.Error("Expected 3, got ", clustersRequired)
	}
}

func TestHamming_11Nodes_Spacing3_6Clusters(t *testing.T) {
	nodes := make([]uint32, 11)
	nodes[0] = getUint32FromBinarytring("10001000")
	nodes[1] = getUint32FromBinarytring("11101011")
	nodes[2] = getUint32FromBinarytring("10101011")
	nodes[3] = getUint32FromBinarytring("00000100")
	nodes[4] = getUint32FromBinarytring("10011001")
	nodes[5] = getUint32FromBinarytring("01011000")
	nodes[6] = getUint32FromBinarytring("11100100")
	nodes[7] = getUint32FromBinarytring("01110101")
	nodes[8] = getUint32FromBinarytring("01100001")
	nodes[9] = getUint32FromBinarytring("11010101")
	nodes[10] = getUint32FromBinarytring("10101011")
	data := &Data{Nodes:nodes,NumberOfBits:8}
	clustersRequired := KClustertingHamming(data, 3)
	if clustersRequired != 6 {
		t.Error("Expected 6, got ", clustersRequired)
	}
}