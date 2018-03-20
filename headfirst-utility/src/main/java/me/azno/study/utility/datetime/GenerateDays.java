package me.azno.study.utility.datetime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 产生2015年1月1日起，5年的日期序列
 */
public class GenerateDays {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        GenerateDays generater = new GenerateDays();
        List<Date> dates = new ArrayList<Date>();
        List<String> rsList = new ArrayList<String>();
        // 1. 5 years loop
        int startYear = 2015;
        for (int i = 0; i < 5; i++) {
            // generate days in one year
            dates.addAll(generater.generateDateInOneYear(startYear + i));
        }

        // 2. Handler 处理周末，假期
        // 2.1 获取假期数据
        Map<String, Integer> holidayMap = generater.getHolidaysMap();
        Calendar calendar = Calendar.getInstance();
        StringBuilder stringBuilder = new StringBuilder();
        for (Date d : dates) {
            stringBuilder.append(format.format(d)).append(",");
            calendar.setTime(d);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY) {
                stringBuilder.append(1);
            } else {
                stringBuilder.append(0);
            }
            rsList.add(stringBuilder.toString());
            stringBuilder.delete(0, 15);
        }

        // last step. print
        for (String str : rsList) {
            System.out.println(str);
        }


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
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(
                    this.getClass().getClassLoader().getResource("holiday").getFile()
            );
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splits = line.split(",");
                map.put(splits[0], Integer.parseInt(splits[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
