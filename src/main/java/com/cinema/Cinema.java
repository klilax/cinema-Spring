package com.cinema;

import java.util.LinkedList;
import java.util.List;

class Seat {
    int row;
    int column;
    int price;

    public Seat() {

    }
     Seat(int row, int column) {
        this.row = row;
        this.column = column;
        if (this.row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}


public class Cinema {
    private int total_rows;
    private int total_columns;
    private boolean[][] isReserved;

    private List<Seat> available_seats;


     Cinema() {
        int noOfSeat = 9;
        this.total_rows = noOfSeat;
        this.total_columns = noOfSeat;

        isReserved = new boolean[this.total_rows][this.total_columns];
        List<Seat> temp = new LinkedList<>();

        for (int i = 1; i <= noOfSeat; i++) {
            for (int j = 1; j <= noOfSeat; j++) {
                temp.add(new Seat(i, j));
            }
        }
        this.available_seats = List.copyOf(temp);
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

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }


    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setAvailable_seats(List<Seat> available_seats) {
        this.available_seats = available_seats;
    }

    public void reservedSeat(int row, int column) {
        this.isReserved[--row][--column] = true;
    }

    public boolean isSeatReserved (int row, int column) {
         return  this.isReserved[--row][--column];
    }


}
