package com.cs.test.week2;

public class Item {
	public int sum;
	public int count;

	public Item add(Item item){
		return new Item(sum+item.getSum(),count+item.getCount());
	}
	
	@Override
	public String toString() {
		return "Item [sum=" + sum + ", count=" + count + "]";
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Item(int sum, int count) {
		super();
		this.sum = sum;
		this.count = count;
	}

	public Item() {
		super();
	}
	
}
