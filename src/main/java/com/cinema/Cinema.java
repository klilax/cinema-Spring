package com.cinema;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

class Seat {
    int row;
    int column;


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

class CinemaSeat extends Seat {
    int price;

    CinemaSeat(int row, int column) {
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


public class Cinema {

    private int total_rows;
    private int total_columns;
    private boolean[][] isReserved;

//    private List<Seat> available_seats;
    private Collection<CinemaSeat> available_seats;


     Cinema() {
        int noOfSeat = 9;
        this.total_rows = noOfSeat;
        this.total_columns = noOfSeat;

        isReserved = new boolean[this.total_rows][this.total_columns];

        available_seats = new ConcurrentLinkedQueue<>();

        for (int i = 1; i <= noOfSeat; i++) {
            for (int j = 1; j <= noOfSeat; j++) {
                available_seats.add(new CinemaSeat(i, j));
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

    public Collection<CinemaSeat> getAvailable_seats() {
        return available_seats;
    }


    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setAvailable_seats(List<CinemaSeat> available_seats) {
        this.available_seats = available_seats;
    }



    public CinemaSeat reservedSeat(Seat seat) {

        CinemaSeat reservedSeat = new CinemaSeat(seat.row, seat.column);
        this.isReserved[seat.row - 1][seat.column - 1] = true;
        available_seats.remove(reservedSeat);
        return reservedSeat;
    }

    public boolean isSeatReserved (int row, int column) {
         return  this.isReserved[--row][--column];
    }


}
