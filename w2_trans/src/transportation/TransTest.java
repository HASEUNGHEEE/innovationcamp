package transportation;
import java.util.Scanner;
public class TransTest {
    public static void main(String[] args) {
        Scanner psg_input = new Scanner(System.in);
        busRun(psg_input);
        psg_input.close();
        psg_input.reset();
    }
    public static void busRun(Scanner psg_input){
        Bus bus1 = new Bus();
        System.out.println(bus1.busNumber);

        Bus bus2 = new Bus();
        System.out.println(bus2.busNumber);

        System.out.println("=====================");

        while(true) {
            System.out.println("Bus " + bus1.busNumber + "번의 승객수를 입력하세요 : ");

            int psg_num= psg_input.nextInt();

            for(int i = 0; i < psg_num; i++){
                Passenger psg = new Passenger();
                psg.takeBus(bus1);
            }
            while (true) {
                if(psg_num <= 30){
                    bus1.showInfo();
                    System.out.println("=====================");
                    bus1.status();
                    System.out.println("=====================");
                    break;
                }else {
                    System.out.println("최대 승객 수를 초과하여 탑승할 수 없습니다");
                }
            }

        }

    }

}
