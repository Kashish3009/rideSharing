package com.geektrust.backend.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.geektrust.backend.Constants.constants;

public class Util {
    public static double calculateDistanceForGivenCoordinates(int x1, int y1, int x2, int y2) {
          double distance = (double) Math.sqrt(Math.pow((x2-x1), constants.SQUARE) 
                                             + Math.pow((y2-y1), constants.SQUARE));
          return roundUp(distance);
    }

    public static double roundUp(double number) {
    BigDecimal roundedNumber = BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP);
    return roundedNumber.doubleValue(); 
    }

    public static String limitTo2DecimalPlace(double number) {
        DecimalFormat decimalFormat = new DecimalFormat(constants.TWO_DECIMAL_LIMIT_FORMAT);
        decimalFormat.setMinimumFractionDigits(constants.TWO); // Ensure minimum 2 decimal places
        decimalFormat.setMaximumFractionDigits(constants.TWO); // Ensure maximum 2 decimal places
        String formattedNumber = decimalFormat.format(number);
        return formattedNumber;
    }

    public static List<Entry<String, Double>> sortEntryList (HashMap<String, Double> mapDistances) {
        List<Entry<String, Double>> entryList = new ArrayList<>(mapDistances.entrySet());
        Collections.sort(entryList, new Comparator<Entry<String, Double>>() {
            @Override
            public int compare(Entry<String, Double> entry1, Entry<String, Double> entry2) {
                int distanceComparison = entry1.getValue().compareTo(entry2.getValue());
                if (distanceComparison == constants.ZERO) {
                    return entry1.getKey().compareTo(entry2.getKey());
                } else {
                    return distanceComparison;
                }
            }
        });
        return entryList;
    }
}
