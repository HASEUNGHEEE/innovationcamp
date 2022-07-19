package transportation;

public class Passenger {
    public void takeBus(Bus bus) {
        bus.take(1000);
    }
    public void takeBus2(Bus bus) {
        bus.take2(1000);
    }

    public void takeTaxi(Taxi taxi) {
        taxi.take(1000);
    }

    public void takeTaxi2(Taxi taxi) {
        taxi.take2(1000);
    }
}

