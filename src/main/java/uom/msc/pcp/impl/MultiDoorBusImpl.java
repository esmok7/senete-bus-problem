package uom.msc.pcp.impl;

import uom.msc.pcp.Bus;

import java.util.concurrent.Semaphore;

public class MultiDoorBusImpl implements Bus {

    private int waitingRiderCount = 0;
    private int noOfRidersAllowed = 0;
    private int boardedRidersCount = 0;
    private Semaphore mutex1 = new Semaphore(1);
    private Semaphore mutex2 = new Semaphore(1);
    private Semaphore bus = new Semaphore(0);
    private Semaphore allBoarded = new Semaphore(0);

    @Override
    public void takeRide() throws InterruptedException {
        mutex1.acquire();
        waitingRiderCount++;
        mutex1.release();

        bus.acquire();
        board();

        mutex2.acquire();
        boardedRidersCount++;
        if (boardedRidersCount == noOfRidersAllowed) {
            allBoarded.release();
        }
        mutex2.release();
    }

    @Override
    public void board() {

    }

    @Override
    public void arrive() throws InterruptedException {
        mutex1.acquire();
        noOfRidersAllowed = Math.min(50, waitingRiderCount);
        if (noOfRidersAllowed > 0) {
            bus.release(noOfRidersAllowed);
            allBoarded.acquire();
        }

        mutex1.release();
        depart();

    }

    @Override
    public void depart() {

    }
}
