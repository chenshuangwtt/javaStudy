package com.cs.test.week1;

public class MyItem implements Comparable<MyItem> {
	private byte type;
	private byte color;
	private byte price;

	public MyItem() {
		this.type = (byte) ((Math.random() * 127) - 128);
	}

	public MyItem(byte[] store) {
		this.type = store[0];
		this.color = store[1];
		this.price = store[2];
		System.out.println("MyItem [type=" + type + ", color=" + color + ", price=" + price + "]");
	}

	public MyItem(byte type, byte color, byte price) {
		this.type = type;
		this.color = color;
		this.price = price;
	}

	public byte[] getByte() {
		byte[] array = new byte[3];
		array[0] = this.type;
		array[1] = this.color;
		array[2] = this.price;
		return array;
	}

	@Override
	public String toString() {
		return "MyItem [type=" + type + ", color=" + color + ", price=" + price + "]";
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getColor() {
		return color;
	}

	public void setColor(byte color) {
		this.color = color;
	}

	public byte getPrice() {
		return price;
	}

	public void setPrice(byte price) {
		this.price = price;
	}

	public int compareTo(MyItem o) {
		if (this.price > o.price) {
			return -1;
		} else if (this.price < o.price) {
			return 1;
		} else {
			return 0;
		}
	}

	public int toByte() {
		int result = ((this.type & 0xFF)<< 16) | ((this.color & 0xFF) << 8) | (this.price & 0xFF);
		return result;
	}

}
