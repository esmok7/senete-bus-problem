package uom.msc.pcp;

import junit.framework.TestCase;
import uom.msc.pcp.mock.MockBusImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BusImplTest extends TestCase {

    public void setUp() {

    }

    public void testZeroRiders() throws InterruptedException {
        final MockBusImpl bus = new MockBusImpl();
        bus.arrive();
        assertEquals(0, bus.getBoardedInvoked().get());
        assertEquals(1, bus.getDepartedInvoked().get());

        bus.arrive();
        assertEquals(0, bus.getBoardedInvoked().get());
        assertEquals(2, bus.getDepartedInvoked().get());
    }

    public void testRidersLessThanFifty() throws ExecutionException, InterruptedException {

        final MockBusImpl bus = new MockBusImpl();
        ExecutorService service = Executors.newFixedThreadPool(50);

        try {

            for (int i = 0; i < 30; i++) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bus.takeRide();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            bus.arrive();

            assertTrue((30 >= bus.getBoardedInvoked().get()));
            assertEquals(1, bus.getDepartedInvoked().get());
        } finally {
            service.shutdown();
        }

    }

    public void testFiftyRiders() throws InterruptedException {

        final MockBusImpl bus = new MockBusImpl();
        ExecutorService service = Executors.newFixedThreadPool(50);

        try {
            for (int i = 0; i < 50; i++) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bus.takeRide();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            bus.arrive();

            assertTrue((50 >= bus.getBoardedInvoked().get()));
            assertEquals(1, bus.getDepartedInvoked().get());

        } finally {
            service.shutdown();
        }
    }

    public void testMoreThanFiftyRiders() throws InterruptedException {

        final MockBusImpl bus = new MockBusImpl();

        ExecutorService service = Executors.newFixedThreadPool(60);

        try {
            for (int i = 0; i < 60; i++) {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            bus.takeRide();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            bus.arrive();

            assertTrue((50 >= bus.getBoardedInvoked().get()));
            assertEquals(1, bus.getDepartedInvoked().get());
        } finally {
            service.shutdown();
        }

    }
}
