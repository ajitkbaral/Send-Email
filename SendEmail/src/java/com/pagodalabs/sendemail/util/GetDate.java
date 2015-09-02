/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pagodalabs.sendemail.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class GetDate {
    
    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("E yyyy/MM/dd 'at' hh:mm a");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
