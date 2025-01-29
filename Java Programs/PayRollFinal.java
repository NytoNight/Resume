import java.util.InputMismatchException;
import java.util.Scanner;

public class PayRollFinal {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        char key; 
        System.out.println("+-------------------------------------------------------+\n|\t\tSALARY SNAP PAYROLL CALCULATOR\t\t|\n+-------------------------------------------------------+");
           
        do {
            try {
                // User Inputs || USED NoNegativeNumbers method so it wont take any negative numbers
                System.out.println("\nTo Calculate your Monthly Payroll, Please Enter the Following Details");
                double hrs = NoNegativeNumbers(read, "Enter Number of Hours Worked per Month > \t");
                double rt = NoNegativeNumbers(read, "Enter its Rate per Hour (Example: 500 ) > \t");
                double oth = NoNegativeNumbers(read, "Enter Overtime Hours (Enter 0 if none) > \t");
                double otr = NoNegativeNumbers(read, "Enter Overtime Rate per Hour (Example: 500 ) (Enter 0 if none)> ");
                double bonus = NoNegativeNumbers(read, "Enter Extra Bonus (Enter 0 if none) > \t\t");

                System.out.println("\nResults");
                System.out.println("----------------------------------");
                Calculator calculator = new Calculator();
                calculator.calculate(hrs, rt, oth, otr, bonus);

                //Continuation
                System.out.print("\nWould you like to enter more? Type 'Y' for Yes: " + "Press any key if not:  ");
                key = read.next().charAt(0);
            } catch (InputMismatchException invalid) {
                System.out.print("Invalid Input provided. Please enter valid numbers >");
                read.nextLine();
                key = 'Y';
            }
        } while (Character.toUpperCase(key) == 'Y');
        System.out.println("Thank you for using our Payroll Calculator!!!");
        read.close();
    }

    //Method to Authenticate if the Input is valid or not
    private static double NoNegativeNumbers(Scanner scanner, String errorprompt) {
        double input;
        do {
            System.out.print(errorprompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            input = scanner.nextDouble();
            if (input < 0) {
                System.out.println("Invalid input. Please enter a positive number.");
            }
        } while (input < 0);
        return input;
    }
}


// Claculator class is responsible for all the calculations needed by the main class
class Calculator {
    public void calculate(double hrs, double rt, double oth, double otr, double bonus) {
        // Calculations
        double gp = grosspay(hrs, rt);
        double otp = overtimepay(oth, otr);
        double ip = Initialpay(gp, otp, bonus);
        double Pagibig = PIdeductions(gp); 
        double PhilHealth = PHdeductions(gp); 
        double SSS = SSSdeductions(gp); 
        Taxtable(gp);
        double IC = IncomeTax(ip); 
        double Np = Netpay(gp, otp, ip, Pagibig, SSS, PhilHealth);
        double AD = AfterDeduction(Pagibig, PhilHealth, SSS, ip);
    
        //Results
        System.out.println("Gross Pay:\t\t" + gp); 
        System.out.println("Overtime Pay:\t\t"+ otp);
        System.out.println("-\nPag-ibig:\t\t" + Pagibig);
        System.out.println("Philhealth:\t\t" + PhilHealth);
        System.out.println("SSS:\t\t\t" + SSS);
        System.out.println("Income tax:\t\t" + IC);
        System.out.println("----------------------------------");
        System.out.println("Total Deductions:\t" + (AD-bonus));
        System.out.println("Additive Bonus:\t\t" + bonus);
        System.out.println("----------------------------------");
        System.out.println("Net pay:\t\t" + Np);
    }
    

    // Gross Pay Calculator
    public static double grosspay(double hrs, double rt) {
        return hrs * rt;
    }

    // Overtime Pay Calculator
    public static double overtimepay(double oth, double otr) {
        return oth * otr;
    }

    // Initial Pay Calculator 
    public static double Initialpay(double grosspay, double overtimepay, double bonus) {
        return grosspay + overtimepay + bonus;
    }

    // Pag-Ibig Calculator
    //Values taken from the Pag-Ibig Table
    public static double PIdeductions(double ip) {
        if (ip < 1000) {
            return 0;
        } else if (ip <= 1500 && ip>1001) {
            return ip * 0.01;
        } else {
            return ip * 0.02;
        }
    }

    // PhilHealth Calculator
    //Values taken from the PhilHealth Table
    public static double PHdeductions (double ip) {
        if (ip <= 10000 && ip >= 4999) {
            return 500;
        } else if (ip >= 10001 && ip <= 99999.999) {
            return ip * 0.05;
        }  else   if (ip >= 100000){
            return 5000;
         } else {
                return 0;
            }
        
    }
    // SSS Calculator
    //Values taken from the SSS Table
    public static double SSSdeductions(double ip) {
        if (ip > 29750) {
            return 1350;
        } else {
            return ip * 0.0363;
        }
    }

    // Tax Table
    //Taken from the Bureau of Internal Revenue Income Tax Table
    //CL = COMPENSATION LEVEL
    //FT = FIXED TAX
    //FTR = FIXED TAX RATE

    static double FT;
    static double FTR;
    static double CL;

    public static void Taxtable(double gp) {
        if (gp < 20833) {
            FT = 0;
            FTR = 0;
            CL = 0;
        } else if (gp <= 33332) {
            FT = 0;
            FTR = .20;
            CL = 20833;
        } else if (gp <= 66666) {
            FT = 2500;
            FTR = .25;
            CL = 33333;
        } else if (gp <= 166666) {
            FT = 10800.33;
            FTR = .30;
            CL = 66667;
        } else if (gp <= 666666) {
            FT = 40833.33;
            FTR = .32;
            CL = 166667;
        } else {
            FT = 200833.33;
            FTR = .35;
            CL = 666667.00;
        }
    }

    //Income Tax
    public static double IncomeTax(double ip) {
        double AF = AfterDeduction(PIdeductions(ip), PHdeductions(ip), SSSdeductions(ip), ip);
        return FT + FTR * (AF - CL);
    }

    // Deduction Calculator
    public static double AfterDeduction(double Pagibig, double PhilHealth, double SSS, double ip) {
        return ip - (Pagibig + PhilHealth + SSS);
    }

    // NetPay Calculator
    public static double Netpay(double gp, double otp, double ip, double Pagibig, double SSS, double PhilHealth) {
        return ip - Pagibig - PhilHealth - SSS;
    }
}