package uom.msc.pcp;

public interface Bus {

    /**
     * Rider invoke when he is coming to bus stand
     */
    void takeRide() throws InterruptedException;

    /**
     * Invoked when rider get into bus
     */
    void board();

    /**
     * Bus invoke when it comes to bus stand
     */
    void arrive() throws InterruptedException;

    /**
     * Invoked when bus depart bus stand
     */
    void depart();
}
