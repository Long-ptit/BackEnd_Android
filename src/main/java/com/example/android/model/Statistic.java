package com.example.android.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Statistic {
    int totalSumToday;
    int totalSumWeek;
    int totalSumMonth;
}
