package transportation;
import java.util.Scanner;
public abstract class Trans {
    public int serialNum;
    public int passengerCount;
    public int lastPassengerCount;
    public int oilAmount;
    public int status;
    public int speed;
    public int intake = 1000;
    public Trans(int serialNum) {
        this.serialNum = serialNum;
        this.oilAmount = 100;
        this.status = 1;
        this.speed = 0;
    }
    public abstract void status();
    public abstract void take(int intake);
    public abstract void showInfo();
    public void changeSpeed(){
        Scanner sc = new Scanner(System.in);
        int change;
        String go;

        System.out.println("현재 속도는 " + speed + "입니다. 원하는 속도를 고르세요. \n 1.빠르게 2.느리게");
        go = sc.next();
        while(true) {
            if(go.equals("1")) {
                System.out.print("원하는 속도를 입력하세요 : ");
                change = sc.nextInt();
                speed = speed + change ;
                System.out.println("현재 속도는 " + speed +" 입니다");
                break;
            }else if (go.equals("2")) {
                System.out.print("원하는 속도를 입력하세요 : ");
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


}