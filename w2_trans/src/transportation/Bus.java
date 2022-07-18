package transportation;

public class Bus{
    static int serialNum = 10000;
    int busNumber;
    int intake;
    int passengerCount;
    int lastPassengerCount;
    int oilAmount = 100;
    int status;
    int velocity = 0;

    public Bus() {
        serialNum++;
        busNumber = serialNum;
        status = 1;
    }
    public void status() {
        if(this.status == 1) {
            oilAmount -= 50;
            System.out.println("현재 주유량은 " + oilAmount + "입니다");
            if (oilAmount > 10 && oilAmount < 100) {
                System.out.println("주유가 필요하여 차고지로 이동합니다");
                System.out.println("=====================");
                status = 0;
            } else if (oilAmount < 10) {
                System.out.println("주유량을 확인해 주세요. 주유가 필요하여 차고지로 이동합니다");
                System.out.println("=====================");
                status = 0;
            }
        }
        if(this.status == 0 && oilAmount > 10) {
            oilAmount += 10;
            System.out.println("현재 주유량은 " + oilAmount + "입니다");
            System.out.println("주유가 완료되어 운행을 시작합니다");
            status = 1;
        }
    }
    public void take(int intake) {
        lastPassengerCount = 30 - passengerCount;
        this.intake += intake;
    }


    public void showInfo() {
        System.out.println("Bus "+ busNumber +"번의 승객은 "+ passengerCount +"명이고, 잔여 숭객 수는 " + lastPassengerCount + "명 입니다. 요금은 " + intake + "원 입니다");


    }
}
