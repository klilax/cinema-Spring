package com.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

        return new ResponseEntity<>(cinema.reservedSeat(seat),HttpStatus.OK);
    }
    


    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

}
