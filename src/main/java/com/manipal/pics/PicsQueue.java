package com.manipal.pics;

import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

@Service
public class PicsQueue {
    Queue<String> picsQueue = new PriorityBlockingQueue<>();

    public void addToQueue(String picsFileName) {
        this.picsQueue.add(picsFileName);
    }

    public String getPicsFileFromQueue(){
        return this.picsQueue.poll();
    }

    public String getOutputFileName() { return this.picsQueue.peek(); }
}
