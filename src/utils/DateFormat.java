/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class DateFormat {

    public static SimpleDateFormat dateFormat = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            dateFormat.applyPattern(pattern);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toString(Date date, String pattern) {
        dateFormat.applyPattern(pattern);
        return dateFormat.format(date);
    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static void main(String[] args) {
        System.out.println(toDate("11-03-2004", "dd-MM-yyyy"));
        try {
            Date date = dateFormat.parse("2004-03-11");
            System.out.println("Line 42: " + toString(date, "yyyy-MM-dd"));
            System.out.println("Line 43: " + addDays(date, 30));
            Date date1 = dateFormat.parse("12-04-2004");
            System.out.println("Line 45: " + toString(date1, "dd-MM-yyyy"));
            
            
            Date date_ngay = dateFormat.parse("Sat Oct 07 00:00:00 ICT 2023");
            
            String ngay_moi = DateFormat.toString(date_ngay, "yyyy-MM-dd");
            System.out.println(ngay_moi);
            
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
