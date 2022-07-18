package transportation;

public class Passenger {
    public void takeBus1(Bus bus) {
        bus.take1(1000);
    }
    public void takeBus2(Bus bus) {
        bus.take2(1000);
    }

    public void takeTaxi(Taxi taxi) {
        taxi.take(1000);
    }
}
