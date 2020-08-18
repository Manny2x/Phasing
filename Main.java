package phasers.project1;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);

        System.out.println("Starting phases...");

        new Thread(new MyThread(phaser, "A1")).start();
        new Thread(new MyThread(phaser, "B1")).start();
        new Thread(new MyThread(phaser, "C1")).start();

        for (int curPhase = 0; curPhase < 4; curPhase = phaser.getPhase()){
            if (phaser.isTerminated())
                break;
            phaser.arriveAndAwaitAdvance();
            System.out.println("Phase: " + curPhase + " has completed");

        }

        System.out.println("All phases complete");
    }
}
