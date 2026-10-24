package com.pr2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction t3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testTopExpenses() {
        Transaction t1 = new Transaction("01-02-2023", -500.0, "Витрата 1");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата 2");
        Transaction t3 = new Transaction("05-03-2023", -100.0, "Витрата 3");
        Transaction t4 = new Transaction("05-03-2023", 5000.0, "Зарплата");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3, t4);

        List<Transaction> top = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(3, top.size());
        Assertions.assertEquals(-500.0, top.get(0).getAmount());
    }
}