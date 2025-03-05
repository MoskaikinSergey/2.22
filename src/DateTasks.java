import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class DateTasks {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        System.out.println("Введите дату в формате dd.MM.yyyy:");
        String inputDate = scanner.nextLine();
        Date date = dateFormat.parse(inputDate);

        // Задание 1: Увеличить дату на 45 дней
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 45);
        Date dateAfter45Days = calendar.getTime();
        System.out.println("Дата после увеличения на 45 дней: " + dateFormat.format(dateAfter45Days));

        // Задание 2: Сдвинуть дату на начало года
        calendar.set(Calendar.DAY_OF_YEAR, 1); // Устанавливаем первый день года
        Date startOfYear = calendar.getTime();
        System.out.println("Дата после сдвига на начало года: " + dateFormat.format(startOfYear));

        // Задание 3: Увеличить дату на 10 рабочих дней
        calendar.setTime(date); // Возвращаемся к исходной дате
        int workingDaysToAdd = 10;
        Date dateAfter10WorkingDays = addWorkingDays(calendar, workingDaysToAdd);
        System.out.println("Дата после увеличения на 10 рабочих дней: " + dateFormat.format(dateAfter10WorkingDays));

        // Задание 4: Ввод второй даты
        System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
        String secondInputDate = scanner.nextLine();
        Date secondDate = dateFormat.parse(secondInputDate);

        // Задание 5: Посчитать количество рабочих дней между датами
        int workingDaysBetween = countWorkingDays(date, secondDate);
        System.out.println("Количество рабочих дней между введенными датами: " + workingDaysBetween);
    }

    // Метод для добавления рабочих дней
    private static Date addWorkingDays(Calendar calendar, int workingDaysToAdd) {
        int addedDays = 0;
        while (workingDaysToAdd > 0) {
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Добавляем один день
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            // Проверяем, является ли день рабочим (не суббота и не воскресенье)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDaysToAdd--;
            }
        }
        return calendar.getTime();
    }

    // Метод для подсчета рабочих дней между двумя датами
    private static int countWorkingDays(Date startDate, Date endDate) {
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        // Если startDate позже endDate, меняем их местами
        if (startCalendar.after(endCalendar)) {
            Calendar temp = startCalendar;
            startCalendar = endCalendar;
            endCalendar = temp;
        }

        // Исключаем начальную дату из подсчета
        startCalendar.add(Calendar.DAY_OF_MONTH, 1);

        int workingDays = 0;

        while (!startCalendar.after(endCalendar)) {
            int dayOfWeek = startCalendar.get(Calendar.DAY_OF_WEEK);

            // Проверяем, является ли день рабочим (не суббота и не воскресенье)
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDays++;
            }

            startCalendar.add(Calendar.DAY_OF_MONTH, 1); // Переходим к следующему дню
        }

        return workingDays;
    }
}