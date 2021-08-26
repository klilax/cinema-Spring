package com.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


class ApiError {
    private String error;

    ApiError(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();
    List<PurchaseTicket> tickets = new ArrayList<>();

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {

        if (cinema.getTotal_rows()< seat.row || cinema.getTotal_columns()< seat.column
                || 0 >= seat.row || 0 >= seat.column)  {
            ApiError error = new ApiError("The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        if (cinema.isSeatReserved(seat.row, seat.column)) {
            ApiError error = new ApiError("The ticket has been already purchased!");
            return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
        }
        PurchaseTicket purchaseTicket = new PurchaseTicket(seat.row, seat.column);
        cinema.reserveSeat(seat);
        tickets.add(purchaseTicket);
        return new ResponseEntity<>(purchaseTicket,HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Token token) {

        for (PurchaseTicket ticket : tickets) {
            if (token.isValidToken(ticket.getToken())) {
                tickets.remove(ticket);
                cinema.freeSeat(ticket.getTicket().getRow(), ticket.getTicket().getColumn());
                return new ResponseEntity<>(new ReturnedTicket(ticket), HttpStatus.OK);
            }
        }
        ApiError error = new ApiError("Wrong token!");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    


    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

}
