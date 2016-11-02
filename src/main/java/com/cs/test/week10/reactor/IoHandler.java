package com.cs.test.week10.reactor;

public interface IoHandler {

	public void attach(IoSession session);

	public void read(IoSession session);

	public void write(IoSession session);

	public void detach(IoSession session);

}
