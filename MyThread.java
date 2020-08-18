package phasers.project1;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Phaser;

public class MyThread implements Runnable{
    Phaser phaser;
    String name;

    MyThread(Phaser phaser, String name){
        this.phaser = phaser;
        this.name = name;
    }

    @Override
    public void run() {
        ConcurrentLinkedQueue<StringBuffer> linkedQueue = Phases.phase1();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }


        CopyOnWriteArrayList<ByteBuffer> arrayList = Phases.phase2(linkedQueue);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        Phases.phase3(arrayList, name);
    }


}
