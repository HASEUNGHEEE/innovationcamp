package transportation;
import java.util.Scanner;
public class Taxi extends Trans{

    String destination;
    int bsdistance;
    int distanceToDestination;
    int distanceToDestination2;
    int bsintake;
    int intake = 1000;
    int intake2;
    int max_passenger = 4;
    int passengerCount1;
    int passengerCount2;
    int lastPassengerCount2;


    public Taxi(int serialNum) {
        super(serialNum);
        bsintake = 3000;
        bsdistance = 1;
    }
    public void status() {
        Scanner sc = new Scanner(System.in);
        System.out.print("사용한 기름을 입력하세요 : ");
        int used_oil = sc.nextInt();
        oilAmount = oilAmount - used_oil;
    }

    public void changeSpeed(){
        super.changeSpeed();
    }
    public void take(int intake){
        super.take();
        lastPassengerCount = max_passenger - this.passengerCount;
        this.intake = intake*distanceToDestination - 1000 + bsintake;
    }
    public void take2(int intake2){
        passengerCount2++;
        lastPassengerCount2 = max_passenger - this.passengerCount2;
        this.intake2 = (1000 * distanceToDestination2) - 1000 + bsintake;
    }

    public void destination(){
        Scanner taxi1psg1 = new Scanner(System.in);
        System.out.println("목적지를 입력하세요 : ");
        destination = taxi1psg1.nextLine();
        System.out.println("목적지까지의 거리를 입력하세요 : ");
        distanceToDestination = taxi1psg1.nextInt();
    }
    public void destination2(){
        Scanner taxi1psg2 = new Scanner(System.in);
        System.out.println("목적지를 입력하세요 : ");
        destination = taxi1psg2.nextLine();
        System.out.println("목적지까지의 거리를 입력하세요 : ");
        distanceToDestination2 = taxi1psg2.nextInt();
    }

    public void showInfo() {
        System.out.println(serialNum +"번의 탑승 승객 수 = "+ passengerCount +"명");
        System.out.println(serialNum +"번의 잔여 숭객 수 = " + lastPassengerCount + "명");
        System.out.println(serialNum +"번의 기본 요금 확인 = " + bsintake + "원");
        System.out.println(serialNum + "번의 목적지 = " + destination);
        System.out.println(serialNum + "번의 목적지까지의 거리 = " + distanceToDestination + "km");
        System.out.println(serialNum + "번의 지불할 요금 = " + intake + "원");
        System.out.println("상태 = " + "운행중");
        System.out.println("=====================");
    }
    public void showInfo2() {
        System.out.println(serialNum +"번의 탑승 승객 수 = "+ passengerCount2 +"명");
        System.out.println(serialNum +"번의 잔여 숭객 수 = " + lastPassengerCount2 + "명");
        System.out.println(serialNum +"번의 기본 요금 확인 = " + bsintake + "원");
        System.out.println(serialNum + "번의 목적지 = " + destination);
        System.out.println(serialNum + "번의 목적지까지의 거리 = " + distanceToDestination2 + "km");
        System.out.println(serialNum + "번의 지불할 요금 = " + intake2 + "원");
    }
}
