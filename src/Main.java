import java.util.Scanner;

public class Main {
    private static final int NO_OF_DE = 5;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Booker booker = new Booker();
        booker.initializeExecutor(NO_OF_DE);
        boolean loop = true;
        while(loop){
            System.out.println("""
            Choose Options
             1.Order Food
             2.Display Activities of DE
             3.Delivery History
             4.<----Exit--->
            """);
            int input = scanner.nextInt();
            switch (input){
                case 1->{
                    try{
                        System.out.println("Restaurant (A, B, C, D, E)");
                        char restaurant = scanner.next().toUpperCase().charAt(0);
                        if(restaurant<='A' && restaurant>='D'){
                            System.out.println("Enter Valid Restaurant ");
                            break;
                        }
                        System.out.println("Delivery Point(A, B, C, D, E)");
                        char destination = scanner.next().toUpperCase().charAt(0);
                        if(destination<='A' && destination>='D'){
                            System.out.println("Enter Valid Restaurant ");
                            break;
                        }
                        System.out.println("Enter Pickup Time (Current Time :"+Math.ceil(Booker.currentTime)+"AM )");
                        double time =  scanner.nextDouble();
                        if(time<Booker.currentTime){
                            System.out.println("Enter a valid time");
                            break;
                        }
                        booker.book(restaurant, destination, time);
                    }catch (Exception e){
                        System.out.println("----------->>>>Enter Valid Input");
                    }

                }
                case 2->{
                    booker.printAvailableExecutive();
                }
                case 3->{
                    booker.displayTripRecord();
                }
                case 4->{
                    loop = false;
                }
                default -> {
                    System.out.println("-------->Enter Valid Input");
                }
            }
        }
    }
}