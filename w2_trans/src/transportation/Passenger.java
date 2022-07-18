package transportation;

public class Passenger {
    public void takeBus(Bus bus) {
        bus.take(1000);
    }

    public void takeTaxi(Taxi taxi) {
        taxi.take(1000);
    }
}
