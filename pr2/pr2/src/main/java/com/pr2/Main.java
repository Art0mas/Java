package com.pr2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "12-2023";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        Transaction maxExp = TransactionAnalyzer.findMaxExpenseByPeriod(transactions, 
            "01-12-2023", "31-12-2023");
        Transaction minExp = TransactionAnalyzer.findMinExpenseByPeriod(transactions, 
            "01-12-2023", "31-12-2023");
        System.out.println("\nНайбільша витрата за грудень: " + 
            (maxExp != null ? maxExp.getDescription() + " (" + maxExp.getAmount() + ")" : "Немає"));
        System.out.println("Найменша витрата за грудень: " + 
            (minExp != null ? minExp.getDescription() + " (" + minExp.getAmount() + ")" : "Немає"));

        TransactionReportGenerator.printExpensesStarReport(transactions);
    }
}