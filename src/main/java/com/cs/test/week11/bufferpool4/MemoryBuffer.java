package com.cs.test.week11.bufferpool4;

import java.nio.ByteBuffer;
/**
 * 用来封装取出来的内存，主要是为了表示该内存用到了总内存中的哪些区间 
 */
class MemoryBuffer {  
        private ByteBuffer buf = null;  
        private int position = 0;  
        private int limit = 0;  
  
        public MemoryBuffer(ByteBuffer _buf, int _position, int _limit) {  
            this.buf = _buf;  
            this.position = _position;  
            this.limit = _limit;  
        }  
        public ByteBuffer getBuf() {  
            return buf;  
        }  
        public int getPosition() {  
            return position;  
        }  
        public int getLimit() {  
            return limit;  
        }  
    }  