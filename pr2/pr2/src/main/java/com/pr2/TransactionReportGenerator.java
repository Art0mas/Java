package com.pr2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class TransactionReportGenerator {

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("10 найбільших витрат:");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount());
        }
    }

    public static void printExpensesStarReport(List<Transaction> transactions) {
        System.out.println("\n--- Звіт по витратах (1 зірочка = 1000 грн) ---");

        System.out.println("По категоріях:");
        Map<String, Double> byCategory = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(Transaction::getDescription, 
                    Collectors.summingDouble(t -> Math.abs(t.getAmount()))));
        byCategory.forEach((cat, amt) -> System.out.println(cat + 
            ": " + "*".repeat(Math.max(0, (int)(amt / 1000))) + " (" + amt + ")"));

        System.out.println("\nПо місяцях:");
        Map<String, Double> byMonth = transactions.stream()
                .filter(t -> t.getAmount() < 0)
                .collect(Collectors.groupingBy(
                        t -> LocalDate.parse(t.getDate(), DateTimeFormatter.
                            ofPattern("dd-MM-yyyy")).format(DateTimeFormatter.ofPattern
                            ("MM-yyyy")),
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))
                ));
        byMonth.forEach((month, amt) -> System.out.println(month + ": " + 
            "*".repeat(Math.max(0, (int)(amt / 1000))) + " (" + amt + ")"));
    }
}