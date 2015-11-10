/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wrm.draftstore.auxiliares;

import java.util.Timer;

/**
 *
 * @author Wilson A. Oliveira
 */
public class ScheduleMain extends InterruptedException {

    public ScheduleMain() {
        Timer time = new Timer(); // Instantiate Timer Object
        ScheduleProdutos st = new ScheduleProdutos(); // Instantiate SheduledTask class
        time.schedule(st, 0, 8600); // Create Repetitively task for every 24Hrs in secs
        time.schedule(st, 0, 86400000); // Create Repetitively task for every 24Hrs in secs
    }
}
