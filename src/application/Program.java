package application;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Renda anual com salario: ");
        double salary = sc.nextDouble();
        System.out.print("Renda anual com prestacao de servico: ");
        double service = sc.nextDouble();
        System.out.print("Renda anual com ganho de capital: ");
        double capital = sc.nextDouble();
        System.out.print("Gastos médicos: ");
        double health = sc.nextDouble();
        System.out.print("Gastos educacionais: ");
        double education = sc.nextDouble();

        double gross = grossTax(salary, service, capital);
        double rebate = taxRebate(health, education, gross);
        double net = netTax(gross, rebate);

        double max = gross * 0.3;
        double expenses = health + education;

        System.out.println();
        System.out.println("RELATÓRIO DE IMPOSTO DE RENDA");
        System.out.println();
        System.out.println("CONSOLIDADO DE RENDA");
        System.out.println("Imposto sobre salário: " + String.format("%.2f",salaryTax(salary)));
        System.out.println("Imposto sobre serviços: " + String.format("%.2f",serviceTax(service)));
        System.out.println("Imposto sobre ganho de capital: " + String.format("%.2f",capitalTax(capital)));
        System.out.println();

        System.out.println("DEDUÇÕES:");
        System.out.println("Máximo dedutível: " + String.format("%.2f",max));
        System.out.println("Gastos dedutíveis: " + String.format("%.2f",expenses));
        System.out.println();

        System.out.println("RESUMO:");
        System.out.println("Imposto bruto total: " + String.format("%.2f",gross));
        System.out.println("Abatimento: " + String.format("%.2f",rebate));
        System.out.println("Imposto devido: " + String.format("%.2f",net));

        sc.close();
    }

    public static double salaryTax(double salary){
        if (salary < 36000.00){
            return 0;
        } else if (salary > 60000.00){
            return salary * 0.2;
        } else {
            return salary * 0.1;
        }
    }

    public static double serviceTax(double service){
        if (service > 0){
            return service * 0.15;
        } else {
            return 0;
        }
    }

    public static double capitalTax(double capital){
        if (capital > 0) {
            return capital * 0.2;
        } else {
            return 0;
        }
    }

    public static double grossTax(double salary, double service, double capital){
        return salaryTax(salary) + serviceTax(service) + capitalTax(capital);
    }

    public static double taxRebate(double health, double education, double gross){
        return Math.min((health + education), (gross * 0.3));
    }

    public static double netTax(double gross, double rebate){
        return gross - rebate;
    }

}