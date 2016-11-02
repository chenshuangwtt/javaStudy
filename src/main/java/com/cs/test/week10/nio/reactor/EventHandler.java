package com.cs.test.week10.nio.reactor;

import java.nio.channels.SelectionKey;

public interface EventHandler {
    public void handleEvent(SelectionKey handle) throws Exception;
}