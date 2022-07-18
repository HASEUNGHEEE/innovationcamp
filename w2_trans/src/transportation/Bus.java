package transportation;
import java.util.Scanner;
public class Bus extends TransTest{
    static int serialNum = 10000;
    int busNumber;
    int intake1;
    int intake2;
    int max_passenger = 30;
    int passengerCount1;
    int passengerCount2;
    int lastPassengerCount;
    int oilAmount = 100;
    int status;
    int speed;

    public Bus() {
        serialNum++;
        busNumber = serialNum;
        status = 1;
        speed = 20;
    }
    public void status() {
        Scanner sc = new Scanner(System.in);
        System.out.print("사용한 기름을 입력하세요 : ");
        int used_oil = sc.nextInt();
        oilAmount = oilAmount - used_oil;

        while(true) {
            if (this.status == 1) {
                System.out.println("현재 주유량 = " + oilAmount);
                System.out.println("=====================");
                if (oilAmount >= 10 && oilAmount < 100) {
                    System.out.println("상태 = 차고지행");
                    oilAmount += 10;
                    System.out.println("현재 주유량 = " + oilAmount);
                    System.out.println("=====================");
                    System.out.println("상태 = 운행중");
                    status = 1;
                    break;
                }
                else if (oilAmount < 10) {
                    System.out.println("상태 = 차고지행");
                    System.out.println("=====================");
                    System.out.println("주유 필요");
                    status = 0;
                    break;
                }
            }else if(this.status == 0) {
                oilAmount += 10;
                System.out.println("현재 주유량 = " + oilAmount);
                System.out.println("상태 = 운행중");
                status = 1;
                break;
            }
        }
    }
    public void changeSpeed(){
        Scanner sc = new Scanner(System.in);
        int change;
        String go;

        System.out.println("현재 속도는 " + speed + "입니다. 원하는 속도를 고르세요. \n 1.빠르게 2.느리게");
        go = sc.next();
        while(true) {
            if(go.equals("1")) {
                System.out.println("원하는 속도를 입력하세요 : ");
                change = sc.nextInt();
                speed = speed + change ;
                System.out.println("현재 속도는 " + speed +" 입니다");
                break;
            }else if (go.equals("2")) {
                System.out.println("원하는 속도를 입력하세요 : ");
                change = sc.nextInt();
                speed = speed - change ;
                if(speed == 0 || speed < 0) {
                    System.out.println("운행을 종료합니다");
                    System.out.println("=====================");
                }else {
                    System.out.println("현재 속도는 " + speed +" 입니다");
                    System.out.println("=====================");
                }
                break;
            }
        }
    }
    public void take1(int intake1) {
        passengerCount1++;
        lastPassengerCount = max_passenger - this.passengerCount1;
        this.intake1 += intake1;
    }
    public void take2(int intake2) {
        passengerCount2++;
        lastPassengerCount = max_passenger - this.passengerCount2;
        this.intake2 += intake2;
    }

    public void showInfo1() {
        System.out.println("Bus "+ busNumber +"번의 탑승 승객 수 = "+ passengerCount1 +"명");
        System.out.println("Bus "+ busNumber +"번의 잔여 숭객 수 = " + lastPassengerCount + "명");
        System.out.println("Bus "+ busNumber +"번의 요금 = " + intake1 + "원");
    }
    public void showInfo2() {
        System.out.println("Bus "+ busNumber +"번의 탑승 승객 수 = "+ passengerCount2 +"명");
        System.out.println("Bus "+ busNumber +"번의 잔여 숭객 수 = " + lastPassengerCount + "명");
        System.out.println("Bus "+ busNumber +"번의 요금 = " + intake2 + "원");
    }
}
