package org.example.ticketing.ui;

import org.example.ticketing.domain.Event;
import org.example.ticketing.domain.Location;

import java.util.Scanner;
import java.util.UUID;

public class EventPrompter {

    public static Event create(Scanner in) {
        String name  = PromptUtils.ask(in,"Name: ");
        String st    = PromptUtils.ask(in,"Street: ");
        String city  = PromptUtils.ask(in,"City: ");
        String date  = PromptUtils.ask(in,"Date (YYYY-MM-DD): ");
        String time  = PromptUtils.ask(in,"Time (HH:MM): ");
        int cap      = PromptUtils.askInt(in,"Capacity: ");

        Location loc = new Location(st, city, cap);
        return new Event(UUID.randomUUID().toString(),
                name, loc,
                java.sql.Date.valueOf(date), time, cap);
    }

    public static void update(Event e, Scanner in) {
        e.setName(PromptUtils.ask(in,"New name ("+e.getName()+"): "));
    }
}
