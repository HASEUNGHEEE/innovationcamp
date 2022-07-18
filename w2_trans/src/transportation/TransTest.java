package transportation;
import java.util.Scanner;
public class TransTest {
    public static void main(String[] args) {
        busRun();
    }
    public static void busRun(){
        Bus bus1 = new Bus();
        System.out.println("Bus " + bus1.busNumber+"번");
        Bus bus2 = new Bus();
        System.out.println("Bus " + bus2.busNumber+"번");

        System.out.println("=====================");
        Scanner sc1 = new Scanner(System.in);
       System.out.println("Bus " + bus1.busNumber + "번의 승객수를 입력하세요 : ");
       int psg_num1 = sc1.nextInt();

        for (int i = 0; i < psg_num1; i++) {
            Passenger psg1 = new Passenger();
            psg1.takeBus1(bus1);
        }

        if (psg_num1 <= 30) {
            bus1.showInfo1();
            System.out.println("=====================");
            bus1.status();
            System.out.println("=====================");
            bus1.changeSpeed();
        } else {
            System.out.println("최대 승객 수 초과");
        }
        while(true){
            Scanner sc2 = new Scanner(System.in);
            System.out.println("Bus " + bus1.busNumber + "번의 승객수를 입력하세요 : ");
            int psg_num2= sc2.nextInt();

            if(psg_num2 > 30){
                System.out.println("최대 승객 수 초과");
            }else{
                for(int i = 0; i < psg_num2; i++) {
                    Passenger psg2 = new Passenger();
                    psg2.takeBus2(bus1);
                }
                bus1.showInfo2();
                System.out.println("=====================");
                bus1.status();
                System.out.println("=====================");
                break;
            }
        }

    }
}

