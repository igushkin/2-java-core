import java.io.File;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport mReport = new MonthlyReport();
        YearlyReport yReport = new YearlyReport();
        Report report = new Report(mReport, yReport);

        while (true) {
            int action = getAction(scanner);

            switch (action) {
                case (1):
                    mReport.parseMontlyReports();
                    break;
                case (2):
                    yReport.parseYearReport();
                    break;
                case (3):
                    report.compareReports();
                    break;
                case (4):
                    mReport.makeReport();
                    break;
                case (5):
                    yReport.makeReport();
                    break;
                case (6):
                    System.out.println("Программа завершена");
                    return;
                default:
                    System.out.println("Такой команды нет. Попробуйте снова.");
                    break;
            }
        }
    }

    static int getAction(Scanner scanner) {
        System.out.println("Выберите номер действия:");
        for (int i = 0; i < actions.length; i++) {
            System.out.println(i + 1 + ". " + actions[i]);
        }
        return scanner.nextInt();
    }

    static String[] actions = new String[]{
            "Считать все месячные отчёты",
            "Считать годовой отчёт",
            "Сверить отчёты",
            "Вывести информацию о всех месячных отчётах",
            "Вывести информацию о годовом отчёте",
            "Выход"
    };
}

