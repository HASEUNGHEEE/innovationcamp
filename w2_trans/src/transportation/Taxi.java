package transportation;

public class Taxi {
    int taxiNumber;
    int intake = 3000;
    int passengerCount;

    public Taxi(int taxiNumber) {
        this.taxiNumber = taxiNumber;
    }

    public void take(int intake) {
        passengerCount++;
        this.intake += intake;
    }
    //    public int getBusNum() {
//        return busNum;
//    }
    public int getIntake() {
        return intake;
    }

//    public int getPassenger() {
//        return passenger;
//    }

    public void showInfo() {
        System.out.println("Taxi "+ taxiNumber +"번의 승객은 "+ passengerCount +"명이고, 수입은 " + intake + "원 입니다");
    }
}
