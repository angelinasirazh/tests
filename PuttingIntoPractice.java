

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("Исходные данные:");
        transactions.forEach(System.out::println);
        split();

        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей)");
        transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        split();

        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры.");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .collect(Collectors.groupingBy(Trader::getCity))
                .entrySet()
                .stream()
                .filter(y -> y.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);
        split();

        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        split();

        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.");
        String str = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(", "));
        System.out.println(str);
        split();

        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана.");
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(s -> s.getCity().equals("Milan"));
        System.out.println(milan);
        split();

        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа.");
        transactions.stream()
                .filter(x -> x.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        split();

        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        int max = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max().orElse(0);
        System.out.println("Максимальная сумма среди всех тразакций равна " + max);
        split();

        System.out.println("8. Найти транзакцию с минимальной суммой.");
        transactions.stream()
                .filter(t -> t.getValue() == transactions.stream()
                        .mapToInt(Transaction::getValue)
                        .min().orElse(0))
                .forEach(System.out::println);
    }

    private static void split() {
        System.out.println("________________________________________________________________________________________");
    }
}
