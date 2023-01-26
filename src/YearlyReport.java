import java.io.File;

public class YearlyReport {

    Year year;

    void makeReport() {
        if(year == null){
            System.out.println("Годовой отчет не загружен");
            return;
        }

        int totalRevenue = 0;
        int totalExpenses = 0;

        System.out.println("Год " + year.year);

        for (Year.MonthlySummary summary : year.monthlySummaries) {
            int d = summary.revenue - summary.expenses;
            System.out.println("Месяц: " + summary.month + ". Прибыль: " + d);
            totalRevenue += summary.revenue;
            totalExpenses += summary.expenses;
        }

        int averageRevenue = totalRevenue / year.monthlySummaries.size();
        int averageExpenses = totalExpenses / year.monthlySummaries.size();
        System.out.println("Средний расход за все месяцы в году: " + averageRevenue);
        System.out.println("Средний доход за все месяцы в году: " + averageExpenses);
    }

    void parseYearReport() {
        File[] files = Resources.getFiles("^y.");
        if(files.length == 0){
            System.out.println("Не обнаружено годового отчета");
            return;
        }
        File file = Resources.getFiles("^y.")[0];
        String content = Resources.getContent(file);
        this.year = Year.parse(file.getName(), content);
    }
}
