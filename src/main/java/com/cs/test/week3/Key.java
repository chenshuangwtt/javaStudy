package com.cs.test.week3;

public class Key implements Comparable<Key> {
	private final int value;

	public Key(int value) {
		this.value = value;
	}

	@Override
	public int compareTo(Key o) {
		return Integer.compare(this.value, o.value);
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Key key = (Key) o;
		return value == key.value;
	}

	public int hashCode() {
		return value;
	}
}
