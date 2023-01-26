import java.util.ArrayList;

public class Year {
    ArrayList<MonthlySummary> monthlySummaries;
    String year;

    Year(String year, ArrayList<MonthlySummary> monthlySummaries) {
        this.monthlySummaries = monthlySummaries;
        this.year = year;
    }

    MonthlySummary getMontlySummary(String month) {
        MonthlySummary monthlySummary = null;
        for (MonthlySummary summary : monthlySummaries) {
            if (summary.month.equals(month)) {
                monthlySummary = summary;
            }
        }
        return monthlySummary;
    }

    static Year parse(String fileName, String data) {
        String year = fileName.split("\\.")[1];
        ArrayList<MonthlySummary> summary = new ArrayList<MonthlySummary>();
        String[] lines = data.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i += 2) {
            String month = "";
            int spent = 0;
            int earned = 0;
            for (int j = 0; j < 2; j++) {
                int index = i + j;
                String line = lines[index];
                String[] lineContents = line.split(",");
                month = lineContents[0];
                boolean isExpense = Boolean.parseBoolean(lineContents[2]);
                if (isExpense) {
                    spent = Integer.parseInt(lineContents[1]);
                } else {
                    earned = Integer.parseInt(lineContents[1]);
                }
            }
            summary.add(new MonthlySummary(month, spent, earned));
        }
        return new Year(year, summary);
    }

    static class MonthlySummary {
        String month;
        int expenses;
        int revenue;

        MonthlySummary(String month, int expenses, int revenue) {
            this.month = month;
            this.expenses = expenses;
            this.revenue = revenue;
        }
    }
}
