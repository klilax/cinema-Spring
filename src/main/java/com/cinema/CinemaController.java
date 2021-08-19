package com.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException(String cause) {
        super(cause);
    }
}

@RestController
public class CinemaController {
    Cinema cinema = new Cinema();

    @PostMapping("/purchase")
    public Seat purchaseTicket(@RequestBody int row, int column) {
//        if (cinema.getTotal_rows()<= row || cinema.getTotal_columns()<= row) {
//            throw new SeatNotFoundException("The number of a row or a column is out of bounds!");
//        }
//        if (cinema.isSeatReserved(row, column)) {
//            throw new SeatNotFoundException("The ticket has been already purchased!");
//        }

        return cinema.reservedSeat(row, column);
    }
    


    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

}
