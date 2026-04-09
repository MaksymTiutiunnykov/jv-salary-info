package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate to = LocalDate.parse(dateTo.trim(), formatter);

        Set<String> nameSet = new HashSet<>(Arrays.asList(names));
        Map<String, Integer> salaryMap = new LinkedHashMap<>();

        for (String name : names) {
            salaryMap.put(name, 0);
        }

        for (String record : data) {
            String[] parts = record.split(" ");
            LocalDate date = LocalDate.parse(parts[0], formatter);
            String name = parts[1];
            int hours = Integer.parseInt(parts[2]);
            int rate = Integer.parseInt(parts[3]);

            if (date.isAfter(from) && !date.isAfter(to) && nameSet.contains(name)) {
                int income = hours * rate;
                salaryMap.put(name, salaryMap.get(name) + income);
            }
        }

        String lineSeparator = System.lineSeparator();
        StringBuilder result = new StringBuilder();

        result.append("Report for period ")
                .append(dateFrom.trim())
                .append(" - ")
                .append(dateTo.trim())
                .append(lineSeparator);

        for (String name : names) {
            result.append(name)
                    .append(" - ")
                    .append(salaryMap.get(name))
                    .append(lineSeparator);
        }

        return result.toString().trim();
    }
}
