package com.cinema;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

class Seat {
    protected int row;
    protected int column;


    public Seat() {

    }
     Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}

class Ticket extends Seat {
    int price;

    Ticket(int row, int column) {
        super(row, column);
        if (this.row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

class PurchaseTicket {
    private String token;
    private Ticket ticket;

    PurchaseTicket(int row, int column) {
        ticket = new Ticket(row, column);
        token = UUID.randomUUID().toString();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

class ReturnedTicket {
    private Ticket returned_ticket;

    ReturnedTicket(PurchaseTicket purchaseTicket) {
        this.returned_ticket = purchaseTicket.getTicket();
    }

    public void setReturned_ticket(Ticket returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Ticket getReturned_ticket() {
        return returned_ticket;
    }
}

public class Cinema {

    private int total_rows;
    private int total_columns;
    private boolean[][] isReserved;

//    private List<Seat> available_seats;
    private Collection<Ticket> available_seats;


     Cinema() {
        int noOfSeat = 9;
        this.total_rows = noOfSeat;
        this.total_columns = noOfSeat;

        isReserved = new boolean[this.total_rows][this.total_columns];

        available_seats = new ConcurrentLinkedQueue<>();

        for (int i = 1; i <= noOfSeat; i++) {
            for (int j = 1; j <= noOfSeat; j++) {
                available_seats.add(new Ticket(i, j));
            }
        }

    }

    public Cinema(int total_columns, int total_rows) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;

    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public Collection<Ticket> getAvailable_seats() {
        return available_seats;
    }


    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setAvailable_seats(List<Ticket> available_seats) {
        this.available_seats = available_seats;
    }



    public void reserveSeat(Seat seat) {

        Ticket reservedSeat = new Ticket(seat.row, seat.column);
        this.isReserved[seat.row - 1][seat.column - 1] = true;
        available_seats.remove(reservedSeat);

    }

    public void freeSeat(int row, int column) {

        Ticket reservedSeat = new Ticket(row, column);
        this.isReserved[row - 1][column - 1] = false;
        available_seats.remove(reservedSeat);

    }

    public boolean isSeatReserved (int row, int column) {
         return  this.isReserved[--row][--column];
    }


}
