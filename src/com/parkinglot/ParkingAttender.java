package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.SlotNotAvailableException;

/**
 * Created by bsneha on 6/29/2016.
 */
public class ParkingAttender {
    public Object park(ParkingLot parkingLot) throws SlotNotAvailableException {
       return  parkingLot.occupy();
    }

    public Object unPark(ParkingLot parkingLot, Object token) throws InvalidParkingLotTokenException {
        return parkingLot.unOccupy(token);
    }
}
