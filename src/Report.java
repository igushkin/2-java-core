public class Report {
    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    Report(MonthlyReport mReport, YearlyReport yReport) {
        monthlyReport = mReport;
        yearlyReport = yReport;
    }

    void compareReports() {
        Month[] months = monthlyReport.months;
        Year year = yearlyReport.year;

        if (months == null) {
            System.out.println("Месячные отчеты не загружен");
        }
        if (year == null) {
            System.out.println("Годовой отчет не загружен");
        }
        if (year == null || months == null) {
            return;
        }

        boolean match = true;

        for (int i = 0; i < months.length; i++) {
            Month month = months[i];
            int revenue = month.getTotalRevenue();
            int expenses = month.getTotalExpenses();
            Year.MonthlySummary summary = year.getMontlySummary(month.monthNumber);

            if (summary.expenses != expenses || summary.revenue != revenue) {
                System.out.println("Обнаружено несоответствие в месяце " + month.monthNumber);
                match = false;
            }
        }
        if (match) {
            System.out.println("Операция успешно завершена.");
        }
    }
}
