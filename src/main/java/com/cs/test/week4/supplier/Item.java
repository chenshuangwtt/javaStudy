package com.cs.test.week4.supplier;

public class Item {
	private Boolean status;
	private Integer val;

	public Item(Boolean status, Integer val) {
		this.status = status;
		this.val = val;
	}

	public Boolean isStatus() {
		return status;
	}

	public Integer getVal() {
		return val;
	}
}