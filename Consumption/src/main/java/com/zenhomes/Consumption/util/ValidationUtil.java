package com.zenhomes.Consumption.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.zenhomes.Consumption.entity.Counter;
import com.zenhomes.Consumption.exception.ConsumptionBadRequest;

public class ValidationUtil {

    public static boolean validateConsumptionData(Counter consumption) {
        try{
            boolean isValidCounterId = validateCounterId(consumption.getCounterId());
            boolean isValidAmount = validateAmount(consumption.getAmount());
            if(isValidCounterId && isValidAmount){
                return true;
            } else {
                return false;
            }
        } catch(Exception e) {
            throw new ConsumptionBadRequest("Invalid input");
        }

    }

    public static boolean validateCounterId(int counterId){
        if(counterId > 0){
            String regex = "^[1-9]+[0-9]*$";
            String cId = String.valueOf(counterId);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(cId);
            if(matcher.matches()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean validateAmount(Double amount){
        if(amount != null){
            String regex = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
            String amt = String.valueOf(amount);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(amt);
            if(matcher.matches()){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
