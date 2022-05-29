package com.example.android;

import com.example.android.model.Bill;
import com.example.android.repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AndroidApplicationTests {

    @Autowired
    BillRepository billRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    void test() throws ParseException {
//        List<Bill> listBill = new ArrayList<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = format.format(new Date());
//        LocalDate today = LocalDate.now();
//        LocalDate monday = today;
//        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
//            monday = monday.minusDays(1);
//        }
//
//        // Go forward to get Sunday
//        LocalDate sunday = today;
//        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
//            sunday = sunday.plusDays(1);
//        }
//
//        String month = today.toString().substring(0,today.toString().length() - 3);
//
//        Date date1 = format.parse(monday + " 00:00:00");
//        Date date2 = format.parse(sunday + " 00:00:00");
//        Date dateMonth = format.parse(month + " 00:00:00");
//
//        int sum = 0;
//
//        List<Bill> list = billRepository.getDateLike(dateMonth.toString(), 1);
//        for(Bill item: list) {
//            sum += Integer.valueOf(item.getTotalPrice());
//        }
//
//        System.out.println(sum);
//
////        System.out.println("size"+billRepository.getDateBetween(date1, date2).size());
//
//    }

    @Test
    void testSave() throws ParseException {
        List<Bill> listBill = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        LocalDate today = LocalDate.now();
        LocalDate monday = today;
        while (monday.getDayOfWeek() != DayOfWeek.MONDAY) {
            monday = monday.minusDays(1);
        }

        // Go forward to get Sunday
        LocalDate sunday = today;
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            sunday = sunday.plusDays(1);
        }

        String month = today.toString().substring(0,today.toString().length() - 3);

        Date date1 = format.parse(monday + " 00:00:00");
        Date date2 = format.parse(sunday + " 00:00:00");

        System.out.println("size"+billRepository.getDateLike(month, 1).size());




    }

}
