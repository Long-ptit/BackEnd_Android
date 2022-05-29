package com.example.android.controller;

import com.example.android.model.*;
import com.example.android.repository.BillFoodRepository;
import com.example.android.repository.BillRepository;
import com.example.android.repository.RestaurantRepository;
import com.example.android.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/bill")
public class BillController {

    @Autowired
    BillRepository billRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    BillFoodRepository billFoodRepository;

    @PostMapping("/save")
    public Bill saveBill(@RequestBody Bill bill) throws ParseException {
        int idStaff = bill.getStaff().getId();
        int idRes = bill.getRestaurant().getId();

        bill.setRestaurant(restaurantRepository.findById(idRes).get());
        bill.setStaff(staffRepository.findById(idStaff).get());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        Date date = format.parse(dateStr);
        bill.setDateNew(date);
        bill.setTotalPrice("10000");
        Bill billResult = billRepository.save(bill);

        return billResult;
    }

    @PostMapping("/saveItem")
    public Bill saveFoodBill(@RequestBody FoodBilToSave data) throws ParseException {
        List<Food> list = data.getData();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        Date date = format.parse(dateStr);
        data.getBill().setDateNew(date);
       Bill billResult = billRepository.save(data.getBill());

        list.forEach(item -> {
            BillFood food = new BillFood();
            food.setFood(item);
            food.setQuantity(food.getQuantity());
            food.setPrice(food.getPrice());
            food.setBill(billResult);
            billFoodRepository.save(food);
        });

        return billResult;
    }

    @GetMapping("/getByIdBill/{id}")
    public List<BillFood> getByIdBill(@PathVariable("id") int id) {
        List<BillFood> billFoods = new ArrayList<>();
        billFoods = billFoodRepository.getBillFoodByIdBill(id);
        return billFoods;
    }

    @GetMapping("/getBillIdRes/{id}")
    public List<Bill> getBillByIdRes(@PathVariable("id") int id) {
        List<Bill> listBill = new ArrayList<>();
        listBill = billRepository.getListBillByIdRes(id);
        return listBill;
    }

    /**
     *
     * @param id
     * @param sort
     * 0 la normal
     * 1 la tang dan
     * 2 la giam dan
     * @return
     */
    @GetMapping("/getBillIdResSort")
    public List<Bill> getBillByIdRes(@RequestParam("id") int id,
                                     @RequestParam("from") String from,
                                     @RequestParam("to") String to,
                                     @RequestParam("sort") int sort ) throws ParseException {
        List<Bill> listBill = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        Date date1 = format.parse(from + " 00:00:00");
        Date date2 = format.parse(to + " 00:00:00");
        if (sort == 1) {
            listBill = billRepository.getDateBetweenAscend(date1, date2, id);
        } else if (sort == 2) {
            listBill = billRepository.getDateBetweenDescend(date1, date2, id);
        }  else {
            listBill = billRepository.getDateBetween(date1, date2, id);
        }
        return listBill;
    }

    @GetMapping("/total")
    public Statistic statistic(@RequestParam("id") int id) throws ParseException {
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
        List<Bill> listBillMonth =  billRepository.getDateLike(month, id);
        List<Bill> listBillWeek =  billRepository.getDateBetween(date1, date2, id);
        List<Bill> listBillToday =  billRepository.getDateLike(today.toString(), id);
        int sumMon = 0, sumToday = 0, sumWeek = 0;
        for (Bill item: listBillMonth) {
            sumMon += Integer.valueOf(item.getTotalPrice());
        }

        for (Bill item: listBillToday) {
            sumToday += Integer.valueOf(item.getTotalPrice());
        }

        for (Bill item: listBillWeek) {
            sumWeek += Integer.valueOf(item.getTotalPrice());
        }
        Statistic statisticResult = new Statistic();
        statisticResult.setTotalSumToday(sumToday);
        statisticResult.setTotalSumWeek(sumWeek);
        statisticResult.setTotalSumMonth(sumMon);


        return statisticResult;
    }

}
