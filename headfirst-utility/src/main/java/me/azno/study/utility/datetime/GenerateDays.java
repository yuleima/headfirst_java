package me.azno.study.utility.datetime;

import me.azno.study.io.IFileReader;
import me.azno.study.io.IFileWriter;
import me.azno.study.io.impl.MyFileReader;
import me.azno.study.io.impl.MyFileWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产生2015年1月1日起，5年的日期序列
 */
public class GenerateDays {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String delimiter = ",";
    private String writeFile = null;
    private int startYear = 2015;
    private int years = 5;

    public GenerateDays(int startYear, int years, String writeFile) {
        this.startYear = startYear;
        this.years = years;
        this.writeFile = writeFile;
    }

    public static void main(String[] args) {
        int startYear = 2015;
        int years = 5;
        GenerateDays generator = new GenerateDays(startYear, years, args[0]);
        generator.generate();
    }

    public void generate() {
        List<Date> dates = new ArrayList<Date>();
        IFileWriter writer = new MyFileWriter(writeFile);
        // 1. 5 years loop
        for (int i = 0; i < years; i++) {
            // generate days in one year
            dates.addAll(generateDateInOneYear(startYear + i));
        }

        // 2. Handler 处理周末，假期
        // 2.1 获取假期数据
        Map<String, Integer> holidayMap = getHolidaysMap();
        Calendar calendar = Calendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        for (Date d : dates) {
            String dateStr = format.format(d);
            stringBuilder.append(dateStr).append(delimiter);
            calendar.setTime(d);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
            stringBuilder.append(delimiter);
            if (holidayMap.containsKey(dateStr)) {
                stringBuilder.append(holidayMap.get(dateStr));
            } else {
                stringBuilder.append(0);
            }
            writer.writeLine(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
        writer.close();

    }

    private List<Date> generateDateInOneYear(int year) {
        List<Date> list = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 0, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        do {
            list.add(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        } while (calendar.get(Calendar.YEAR) == year);
        return list;
    }

    private Map<String, Integer> getHolidaysMap() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        IFileReader reader = new MyFileReader(
                this.getClass().getClassLoader().getResource("holidays").getFile()
        );
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splits = line.split(delimiter);
            map.put(splits[0], Integer.parseInt(splits[1]));
        }
        reader.close();
        return map;
    }
}
