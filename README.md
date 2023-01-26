# 2-java-core
Java Standard Library and its features


Technical specification of the project
===========================

Your second independent project is "Accounting Automation". This is a task from the real world of development — you have not encountered such yet. Here, as in the previous task, you will need to build the structure yourself, but there will be more code, and the logic of its work is more complicated. You will definitely have to work hard on this project, but the result is worth it — you will be able to reach a new level!

Problem statement
-----------------

The amusement park accounting department uses simple excel spreadsheets for budget management. In their work, errors constantly occur when entering data and calculating balances. The company's management has set you a difficult task — to create a new accounting application.

It should provide the following functionality:

1. Have a console interface for managing the program.
2. Read monthly and annual accounting reports from files and bring them into application objects.
3. To reconcile data on monthly and annual reports.
4. Display information about monthly and annual reports.

At the moment, the accounting department is ready to provide three monthly reports: for January, February and March 2021, as well as a partial annual report for the same three months. So far, the application should only process this data.

Input file format
---------------------

The accounting department is ready to provide data on its activities in the form of files in the CSV — Comma-Separated Values format (English "comma-separated values"). You need to split the incoming file into components and convert it to application objects. The application should work with two types of reports:

* A monthly report containing data on income and expenses within one calendar month. In the program, they are represented by the `MonthlyReport` class.
* An annual report containing exactly 2 entries for each of the 12 months — the total income and expense for that month. Represented by the `YearlyReport' class.

Each CSV file consists of a set of lines. In the very first line there are field headers. Next, each line consists of values separated by a comma separator.

Naming files
-----------------

Report files are named in a certain way to simplify their reading and processing.

### Naming monthly reports

The names of files with monthly reports have the format `m.YYYYMM.csv`, where:

* `m` — the letter `m` at the beginning of the file to separate monthly and yearly reports;
* 'YYYY' — year. For example, 2021;
* `MM' is a month strictly in two digits. The account starts with one, that is, 01 is "January", and 11 is "November".

Example of naming: `m.202001.csv' — monthly report for January 2020, `m.201912.csv` — monthly report for December 2019.

### Naming of annual reports

The file names with the annual report have the format `y.YYYY.csv', where:

* `y` — the letter `y` at the beginning of the file to separate monthly and yearly reports;
* 'YYYY' — year. For example, 2021.

Example of naming conventions: `y.2020.csv' — annual report for 2020, `y.2018.csv` — annual report for 2018.

Monthly report format
-----------------------

The monthly report contains information about all expenses incurred during the calendar month. This includes information about both the income and expenses of the amusement park.

Example of a CSV file with a monthly report:

Copy the code

item_name,is_expense,quantity,sum_of_one
Balloons,TRUE,5000,5
Ice cream vending machines,TRUE,12,15000
Ice cream sale,FALSE,1000,120


Monthly reports consist of four fields:

* `item_name' — product name;
* `is_expense' — one of two values: `TRUE` or `FALSE'. Indicates whether the entry is a waste (TRUE) or an income (FALSE);
* `quantity' — quantity of purchased or sold goods;
* `sum_of_one` — the cost of one unit of goods. An integer.

Annual report format
----------------------

The annual report contains information on all expenses incurred during the year. It contains two entries for each month. The month is indicated strictly by two digits, starting from one, that is, 01 is "January", and 11 is "November".

Example of a CSV file with an annual report:

Copy the code

month,amount,is_expense
01,100000,false
01,30000,true
02,321690,false
02,130000,true
03,999999,true
03,999999,false


The annual report line consists of three fields:

* `month' — month. Integer;
* `amount' — amount;
* `is_expense' — one of two values: `true` or `false'. Indicates whether the record is a waste (true) or an income (false).

Implementation Details and hints
-----------------------------

### Console interface

The console interface for working with the software should allow the operator to perform one of five actions by choice:

1. Read all monthly reports
2. Read the annual report
3. Compare reports
4. Display information about all monthly reports
5. Display information about the annual report

After selecting and executing an action, the program should allow the operator to enter the next action. The program should terminate only when the operator enters a special sequence of characters. You can come up with such a sequence yourself.

Hint: reading data from the user

The `Scanner` class is suitable for reading data from the user. Create an infinite loop that exits only when a certain character or number is entered. Display the menu so that the user can select the correct function. Do not forget to handle the extreme case — when the user enters information not provided by the application.

Copy the codejava

Scanner scanner = new Scanner(System.in);
int userInput = scanner.nextInt();


### Reading files

The program allows you to read monthly and annual reports. When the operator selects the "read monthly report" action, three files should be read:

* `m.202101.csv`
* `m.202102.csv`
* `m.202103.csv`

When selecting the "read annual report" action, reading from a single file should occur:

* `y.2021.csv`

The contents of the files should be brought to the application objects for further processing.

There are several ways to read a file in Java. You can use the following code:

Copy the codejava

List<String> readFileContents(String path) {
try {
return Files.readAllLines(Path.of(path));
} catch (IOException e) {
System.out.println("The monthly report file cannot be read. Maybe the file is not in the right directory.");
return Collections.emptyList();
}
}

The method takes as a parameter the name of the file to be read, and returns either a list of lines of the file contents, or an empty list if the file is not found. The method argument is the full or relative path to the file.

Hint: getting the file name

Since there are several months, you need to process monthly reports in a cycle. According to the task, you are given a data format. You can convert the loop counter `i` and several string constants into a file name, for example `"m.20210" + i + ".csv"`.

### Working with CSV string

When working with strings of a known format, it is convenient to use the `split()` method. It divides the source string by the delimiter character and returns an array of strings. For example, to split our string from a file into columns, you need to call a method with the argument ",":

Copy the codejava

String[] lineContents = line.split(",");

This will allow you to access specific values inside the string. For example, to the values of the column _amount_ or _month_.

Please note: The CSV file starts with a header line, which does not need to be analyzed - you need exactly the values. Therefore, the loop for all lines from the file can be started with `1`, and not with `0'.

### Data reconciliation

Data reconciliation is a check that data in two or more different sources do not contradict each other. In this case, when reconciling the data, you need to check that the information on the month in the annual report does not contradict the information in the monthly report.

When calling data reconciliation, the program must:

1. Calculate two amounts: total income and total expenses for each of the months.
2. Compare the amounts received with the amount of income and expenses in the report for the year.

If an error is detected, the program should output the month in which the discrepancy was detected.

If no errors are detected, only information about the successful completion of the operation should be displayed.

Hint: how to organize methods so that it is convenient to work with them

For convenience, you can create additional methods in the report classes `MonthlyReport` and `YearlyReport`, which will return the maximum, minimum and average amounts for income and expenses. Before reconciliation, do not forget to check that the user has already called the methods for reading both types of reports. To do this, you can check the value of reports for `null`, or create an additional private `boolean` variable.

### Information output

The program must support the output of two short text reports:

1. Information about all monthly reports.
2. Information about the annual report.

### Information about all monthly reports

When calling this function, the program should output the following data:

* Name of the month;
* The most profitable product, that is, the product for which `is_expense == false`, and the product of the quantity (`quantity`) by the amount (`sum_of_one') is the maximum. Output the product name and amount;
* The biggest waste. Output the name of the product and the amount.

This information should be displayed for each of the months.

### Information about the annual report

When calling this function, the program should output the following data:

* The year in question;
* Profit for each month. Profit is the difference between income and expenses;
* Average consumption for all months of the year;
* Average income for all months of the year.

