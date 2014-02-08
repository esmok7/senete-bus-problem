package uom.msc.pcp.impl;

import uom.msc.pcp.Bus;

import java.util.concurrent.Semaphore;

public class BusImpl implements Bus {

    private int waiting = 0;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore bus = new Semaphore(0);
    private Semaphore boarded = new Semaphore(0);

    @Override
    public void takeRide() throws InterruptedException {
        mutex.acquire();
        waiting++;
        mutex.release();

        bus.acquire();

        board();
        boarded.release();
    }

    public void board() {
        System.out.println("Rider boarded");
    }

    @Override
    public void arrive() throws InterruptedException {

        mutex.acquire();
        int n = Math.min(waiting, 50);

        for(int i = 0; i < n; i++) {
            bus.release();
            boarded.acquire();
        }

        waiting = Math.max((waiting - 50), 0);
        mutex.release();

        depart();
    }

    public void depart() {
        System.out.println("Bus departed.");
    }

}
