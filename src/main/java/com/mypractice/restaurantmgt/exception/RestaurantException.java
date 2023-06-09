package com.mypractice.restaurantmgt.exception;

public class RestaurantException extends RuntimeException{
    public RestaurantException(String msg){
        super(msg);
    }

    public RestaurantException(String msg, Throwable e){
        super(msg, e);
    }

}
