import java.util.ArrayList;

public class Month {
    String monthNumber;
    ArrayList<Transaction> transactions;

    Month(String monthNumber, ArrayList<Transaction> transactions) {
        this.monthNumber = monthNumber;
        this.transactions = transactions;
    }

    private int getTotal(boolean isExpense) {
        int sum = 0;
        for (Transaction transaction : transactions) {
            if (transaction.isExpense == isExpense) {
                sum += transaction.sumOfOne * transaction.quantity;
            }
        }
        return sum;
    }

    public int getTotalExpenses() {
        return getTotal(true);
    }

    public int getTotalRevenue() {
        return getTotal(false);
    }

    static Month parse(String fileName, String data) {
        String date = fileName.split("\\.")[1];
        String monthNumber = date.substring(date.length() - 2, date.length());
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        String[] lines = data.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] lineContents = line.split(",");
            String itemName = lineContents[0];
            boolean isExpense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int sumOfOne = Integer.parseInt(lineContents[3]);
            transactions.add(new Transaction(itemName, isExpense, quantity, sumOfOne));
        }
        return new Month(monthNumber, transactions);
    }

    static class Transaction {
        String itemName;
        boolean isExpense;
        int quantity;
        int sumOfOne;

        Transaction(String itemName, boolean isExpense, int quantity, int sumOfOne) {
            this.itemName = itemName;
            this.isExpense = isExpense;
            this.quantity = quantity;
            this.sumOfOne = sumOfOne;
        }
    }
}
