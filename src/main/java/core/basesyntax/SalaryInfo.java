package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom.trim(), FORMATTER);
        LocalDate to = LocalDate.parse(dateTo.trim(), FORMATTER);
        StringBuilder result = new StringBuilder();

        result.append("Report for period ")
                .append(dateFrom.trim())
                .append(" - ")
                .append(dateTo.trim())
                .append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;

            for (String record : data) {
                String[] parts = record.split(" ");
                LocalDate date = LocalDate.parse(parts[DATE_INDEX], FORMATTER);
                String employeeName = parts[NAME_INDEX];
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int rate = Integer.parseInt(parts[RATE_INDEX]);

                if (!date.isBefore(from) && !date.isAfter(to) && employeeName.equals(name)) {
                    salary += hours * rate;
                }
            }

            result.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }
}
