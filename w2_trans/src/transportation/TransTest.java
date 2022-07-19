package transportation;
import java.util.Scanner;
public class TransTest {
    public static void main(String[] args) {
        System.out.println("========== 버스 ==========");
        TransTest test = new TransTest();
        test.busRun();
        System.out.println("========== 택시 ==========");
        test.taxiRun();
    }

    public static void busRun() {
        Bus bus1 = new Bus(1000);
        Bus bus2 = new Bus(1001);
        System.out.println(bus1.serialNum + "번");
        System.out.println(bus2.serialNum + "번");
        System.out.println("=====================");

        System.out.println(bus1.serialNum + "번의 승객수를 입력하세요 : ");
        Scanner bus1psg = new Scanner(System.in);
        int bus1_psg_num = bus1psg.nextInt();

        for (int i = 0; i < bus1_psg_num; i++) {
            Passenger psg1 = new Passenger();
            psg1.takeBus(bus1);
        }
        if (bus1_psg_num <= 30) {
            bus1.showInfo();
            bus1.status();
            bus1.changeSpeed();
            System.out.println("=====================");
        } else {
            System.out.println("최대 승객 수 초과");
            System.out.println("=====================");
        }

        while (true) {
            Scanner bus1psg2 = new Scanner(System.in);
            System.out.println(bus1.serialNum + "번의 승객수를 입력하세요 : ");
            int bus1_psg_num2 = bus1psg2.nextInt();

            if (bus1_psg_num2 > 30) {
                System.out.println("최대 승객 수 초과");
                System.out.println("=====================");
            } else {
                for (int i = 0; i < bus1_psg_num2; i++) {
                    Passenger psg2 = new Passenger();
                    psg2.takeBus2(bus1);
                }
                bus1.showInfo2();
                bus1.status();
                break;

            }
        }

    }

    public static void taxiRun() {
        Taxi taxi1 = new Taxi(1234);
        Taxi taxi2 = new Taxi(5555);
        System.out.println(taxi1.serialNum + "번");
        System.out.println(taxi2.serialNum + "번");
        System.out.println("주유량 = " + taxi1.oilAmount);
        System.out.println("상태 = " + "일반");
        System.out.println("=====================");

        int taxi1_psg_num;
        Scanner taxi1psg = new Scanner(System.in);
        System.out.println(taxi1.serialNum + "번의 승객수를 입력하세요 : ");
        taxi1_psg_num = taxi1psg.nextInt();
        taxi1.destination();

        for (int i = 0; i < taxi1_psg_num; i++) {
            Passenger psg1 = new Passenger();
            psg1.takeTaxi(taxi1);
        }
        if (taxi1_psg_num <= 4) {
            taxi1.showInfo();
            taxi1.status();
            System.out.println("=====================");
            System.out.println("지불할 요금은 = " + taxi1.intake + "원 입니다");
            System.out.println("=====================");
            System.out.println("주유량 = " + taxi1.oilAmount);
            System.out.println("누적 요금 = " + taxi1.intake);
        } else {
            System.out.println("최대 승객 수 초과");
            System.out.println("=====================");
        }

        while (true) {
            Scanner taxi1psg2 = new Scanner(System.in);
            System.out.println(taxi1.serialNum + "번의 승객수를 입력하세요 : ");
            int taxi1_psg_num2 = taxi1psg2.nextInt();
            taxi1.destination2();
            if (taxi1_psg_num2 > 4) {
                System.out.println("최대 승객 수 초과");
                System.out.println("=====================");
            } else {
                for (int i = 0; i < taxi1_psg_num2; i++) {
                    Passenger psg2 = new Passenger();
                    psg2.takeTaxi2(taxi1);
                }

                taxi1.showInfo2();
                taxi1.status();

                System.out.println("=====================");
                System.out.println("지불할 요금은 = " + taxi1.intake2 + "원 입니다");
                System.out.println("=====================");
                System.out.println("주유량 = " + taxi1.oilAmount);
                System.out.println("누적 요금 = " + (taxi1.intake + taxi1.intake2));
                if (taxi1.oilAmount <= 10) {
                    System.out.println("상태 = 운행불가");
                    System.out.println("주유 필요");
                }
                break;
            }
        }
    }
}

