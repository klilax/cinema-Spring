package com.cinema;

import java.util.List;

public class Stats {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    Stats(Cinema cinema) {
        this.current_income = 0;
        this.number_of_available_seats = cinema.getTotal_columns() * cinema.getTotal_rows();
        this.number_of_purchased_tickets = 0;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void updateStats (List<PurchaseTicket> purchaseTickets, Cinema cinema) {
        int ticketCount = 0;
        int ticketIncome = 0;

        for (PurchaseTicket ticket : purchaseTickets) {
            ticketCount++;
            ticketIncome += ticket.getTicket().price;
        }
        this.setNumber_of_available_seats(cinema.getTotal_columns() * cinema.getTotal_rows() - ticketCount);

        this.setNumber_of_purchased_tickets(ticketCount);
        this.setCurrent_income(ticketIncome);
    }

}
