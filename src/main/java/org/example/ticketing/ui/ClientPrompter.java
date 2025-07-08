package org.example.ticketing.ui;

import org.example.ticketing.domain.Address;
import org.example.ticketing.domain.Client;

import java.util.Scanner;
import java.util.UUID;

public class ClientPrompter {

    /** Builds a brand-new Client from console input. */
    public static Client create(Scanner in) {
        String fn   = PromptUtils.ask(in, "First name: ");
        String ln   = PromptUtils.ask(in, "Last name: ");
        String st   = PromptUtils.ask(in, "Street: ");
        String city = PromptUtils.ask(in, "City: ");
        int age     = PromptUtils.askInt(in,"Age: ");
        String tel  = PromptUtils.ask(in, "Phone: ");
        return new Client(UUID.randomUUID().toString(),
                fn, ln, new Address(st, city), age, tel);
    }

    /** Minimal update (phone only, for demo). */
    public static void update(Client c, Scanner in) {
        c.setPhoneNumber(PromptUtils.ask(in,"New phone ("+c.getPhoneNumber()+"): "));
    }
}

