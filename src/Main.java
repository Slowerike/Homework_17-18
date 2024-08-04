import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        /*Задача 1:
        Пользователь вводит в консоль дату своего рождения. Программа должна вернуть дату,
        когда пользователю исполнится 100 лет. Использовать Date/Time API.*/
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entering day of your birth...");
        String dayOfBirth = scanner.nextLine();
        System.out.println(LocalDate.parse(dayOfBirth).plusYears(100));
        /* Задача 2:
        Используя Predicate среди массива чисел вывести только те, которые являются
        положительными.*/
        int[] massiveOfNumbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> positiveNumber = number -> number % 2 == 0;
        List<Integer> positiveArray = Arrays.stream(massiveOfNumbers).boxed().filter(positiveNumber).toList();
        System.out.println(positiveArray);
        /*Задача 3:
        Используя Function реализовать лямбду, которая будет принимать в себя строку в
        формате “*сумма* BYN”(через пробел, вместо *сумма* вставить любое значение), а
        возвращать сумму, переведенную сразу в доллары.*/
        Function<String, Double> currency = (x2) -> {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(x2);
            int amountOfBlr = 0;
            while (matcher.find()) {
                amountOfBlr = Integer.parseInt(matcher.group());
            }

            return (double) (amountOfBlr / 3.122);
        };
        double blr1000 = currency.apply("1000 BYN");
        System.out.println(blr1000);
        /*Задача 4:
        teachmeskills.by
        Используя Consumer реализовать лямбду, которая будет принимать в себя строку в
        формате “*сумма* BYN”(через пробел, вместо *сумма* вставить любое значение), а
        выводить сумму, переведенную сразу в доллары.*/
        String stringOfBLR = "1000 BYN";
        Consumer<String> currencyInConsumer = (BLRToUSD) -> {
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(BLRToUSD);
            double amountOfBlr;
            if (matcher.find()) {
                amountOfBlr = Integer.parseInt(matcher.group());
                amountOfBlr /= 3.122;
            } else {
                // Если сумма не найдена, возвращаем 0.0
                amountOfBlr = 0.0;
            }
            System.out.println(amountOfBlr);
        };
        currencyInConsumer.accept(stringOfBLR);
        /*Задача 5:
        Используя Supplier написать метод, который будет возвращать введенную с консоли
        строку задом наперед.*/
        Supplier<String> returnString = () -> {
            System.out.println("Введите строку:");
            StringBuilder line = new StringBuilder(scanner.nextLine());
            return line.reverse().toString();
        };
        System.out.println(returnString.get());
        /*Домашняя работа задача №1
          Создать коллекцию класса ArrayList наполнить ее элементами типа Integer. С помощью
          Stream'ов:
          - Удалить дубликаты
          - Оставить только четные элементы
          - Вывести сумму оставшихся элементов в стриме
          */
        ArrayList<Integer> collectionOfIntegers = new ArrayList<>();
        Stream.iterate(1, s -> s + 1)
                .limit(100)
                .forEach(collectionOfIntegers::add);
        int sum = collectionOfIntegers
                .stream()
                .distinct()
                .filter(s -> s % 2 == 0)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        /*Домашняя работа задача №2
          Создать набор данных в формате id-name, сохраненный в Map. Необходимо отобрать из
          этого набора только те данные, id которых попадает в числовой диапазон 1/2/5/8/9/13.
          Среди отобранных значений отобрать только те, которые имеют нечетное количество
          букв в имени. После чего вернуть список List имен, записанных буквами задом наперед.
          */
        Map<Integer, String> mapOfIdAndName = new TreeMap<>();
        mapOfIdAndName.put(1, "Vladislav");
        mapOfIdAndName.put(2, "Kostya");
        mapOfIdAndName.put(3, "Semen");
        mapOfIdAndName.put(4, "Roman");
        mapOfIdAndName.put(5, "Svetlana");
        mapOfIdAndName.put(6, "Lena");
        mapOfIdAndName.put(7, "Yana");
        mapOfIdAndName.put(8, "Valerya");
        mapOfIdAndName.put(9, "Dmitry");
        mapOfIdAndName.put(10, "Sonya");
        mapOfIdAndName.put(11, "Gordey");
        mapOfIdAndName.put(12, "Sergey");
        mapOfIdAndName.put(13, "Daniil");
        mapOfIdAndName.put(14, "Margarita");
        Set<Integer> targetId = Set.of(1, 2, 5, 8, 9, 13);

        List<String> collected = mapOfIdAndName.entrySet()
                .stream()
                .filter(e -> targetId.contains(e.getKey()))
                .filter(e -> e.getValue().length() % 2 != 0)
                .map(e -> {
                    StringBuilder sb = new StringBuilder(e.getValue());
                    return sb.reverse().toString();
                })
                .toList();
        System.out.println(collected);
    }
}