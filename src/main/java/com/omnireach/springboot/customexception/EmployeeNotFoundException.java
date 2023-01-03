package com.omnireach.springboot.customexception;

public class EmployeeNotFoundException extends RuntimeException {
   public EmployeeNotFoundException(String message){
       super(message);
   }
}
