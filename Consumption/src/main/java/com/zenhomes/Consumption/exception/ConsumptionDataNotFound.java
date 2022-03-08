package com.zenhomes.Consumption.exception;


public class ConsumptionDataNotFound extends RuntimeException {

	private static final long serialVersionUID = -7805309236982716022L;

	public ConsumptionDataNotFound(String message){
        super(message);
    }
}
