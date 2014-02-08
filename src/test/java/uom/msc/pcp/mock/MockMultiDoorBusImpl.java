package uom.msc.pcp.mock;

import uom.msc.pcp.impl.MultiDoorBusImpl;

import java.util.concurrent.atomic.AtomicInteger;

public class MockMultiDoorBusImpl extends MultiDoorBusImpl {

    private AtomicInteger boardedInvoked = new AtomicInteger(0);
    private AtomicInteger departedInvoked = new AtomicInteger(0);

    public void board() {
        boardedInvoked.incrementAndGet();
    }

    public void depart() {
        departedInvoked.incrementAndGet();
    }

    public AtomicInteger getBoardedInvoked() {
        return boardedInvoked;
    }

    public AtomicInteger getDepartedInvoked() {
        return departedInvoked;
    }
}
