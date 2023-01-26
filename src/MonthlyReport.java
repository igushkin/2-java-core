import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    Month[] months;
    void makeReport() {
        if(months == null){
            System.out.println("Месячные отчеты не загружен");
            return;
        }

        for (Month month : months) {
            int maxRevenue = 0;
            int maxExpense = 0;
            String revenueItem = "";
            String expenseItem = "";

            for (Month.Transaction transaction : month.transactions) {
                int total = transaction.quantity * transaction.sumOfOne;
                if (transaction.isExpense && total > maxExpense) {
                    maxExpense = total;
                    expenseItem = transaction.itemName;
                } else if (!transaction.isExpense && total > maxRevenue) {
                    maxRevenue = total;
                    revenueItem = transaction.itemName;
                }
            }
            System.out.println(month.monthNumber + ". " + "Самый прибильный торвар " + revenueItem + ". " + "Сумма " + maxRevenue);
            System.out.println(month.monthNumber + ". " + "Самые большие траты. " + expenseItem + ". " + "Сумма " + maxExpense);
        }
    }

    void parseMontlyReports() {
        File[] files = Resources.getFiles("^m.");
        if(files.length == 0){
            System.out.println("Не обнаружено месячных отчетов");
            return;
        }
        Month[] months = new Month[files.length];
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String content = Resources.getContent(file);
            months[i] = Month.parse(file.getName(), content);
        }
        this.months = months;
    }
}
