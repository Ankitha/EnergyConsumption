package com.zenhomes.Consumption.exception;

public class ConsumptionBadRequest extends RuntimeException {

	private static final long serialVersionUID = 2364538703568691092L;

	public ConsumptionBadRequest(String message){
        super(message);
    }
}
