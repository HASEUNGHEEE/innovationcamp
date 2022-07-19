package transportation;
import java.util.Scanner;
public class Bus extends Trans{
    int max_passenger = 30;
    int lastPassengerCount;
    int passengerCount2;
    int intake2;

    public Bus(int serialNum) {
        super(serialNum);
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
        super.changeSpeed();
    }
    public void take(int intake){
        super.take();
        lastPassengerCount = max_passenger - this.passengerCount;
        this.intake += intake;
    }
    public void take2(int intake2) {
        passengerCount2++;
        lastPassengerCount = max_passenger - this.passengerCount2;
        this.intake2 += intake2;
    }

    public void showInfo() {
        System.out.println(serialNum +"번의 탑승 승객 수 = "+ passengerCount +"명");
        System.out.println(serialNum +"번의 잔여 숭객 수 = " + lastPassengerCount + "명");
        System.out.println(serialNum +"번의 요금 = " + intake + "원");
    }
    public void showInfo2() {
        System.out.println(serialNum +"번의 탑승 승객 수 = "+ passengerCount2 +"명");
        System.out.println(serialNum +"번의 잔여 숭객 수 = " + lastPassengerCount + "명");
        System.out.println(serialNum +"번의 요금 = " + intake2 + "원");
    }

}
