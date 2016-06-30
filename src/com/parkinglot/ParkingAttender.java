package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.ParkingLotNotAvailabelException;
import com.parkinglot.exception.SlotNotAvailableException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by bsneha on 6/29/2016.
 */
public abstract class ParkingAttender {
//    public Object  park(List<ParkingLot> parkingLot) throws SlotNotAvailableException {
//       return  parkingLot.get(0).occupy();
//    }
    protected Map<Object,ParkingLot> allocatedTokenToParkingLotCollection;
    public ParkingAttender(){
        this.allocatedTokenToParkingLotCollection=new HashMap<>();
    }
    abstract Object park(List<ParkingLot> parkingLots) throws SlotNotAvailableException, ParkingLotNotAvailabelException;
    public Object unPark(List<ParkingLot> parkingLots, Object token) throws InvalidParkingLotTokenException, SlotNotAvailableException {
        return allocatedTokenToParkingLotCollection.get(token).unOccupy(token);
    }
}
