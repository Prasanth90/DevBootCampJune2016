package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.SlotNotAvailableException;

import java.util.*;

/**
 * Created by rajats on 6/28/16.
 */
public class ParkingLot {


    private List<Object> issuedTokens = new LinkedList<>();
    List<parkingLotObserver> fullListeners;
    List<parkingLotObserver> spaceAvailableListenrs;

    private int capacity;

    public static String SLOT_UNAVAILABLE_EXCEPTION_MSG = "Slot not available";

    public ParkingLot(int capacity) {

        this.capacity = capacity;
        this.spaceAvailableListenrs = new ArrayList<parkingLotObserver>();
        this.fullListeners = new ArrayList<parkingLotObserver>();
    }

    public Object occupy() throws SlotNotAvailableException {
        if (issuedTokens.size() == capacity) {

            throw new SlotNotAvailableException(SLOT_UNAVAILABLE_EXCEPTION_MSG);
        } else {
            Object token = new Object();
            issuedTokens.add(token);
            if (issuedTokens.size() == capacity) {

                notifyParkingFullObservers();
            }
            return token;
        }
    }

    private void notifyParkingFullObservers() {
        fullListeners.forEach(parkingLotObserver::updatedFull);
    }


    public Object unOccupy(Object parkingToken) throws InvalidParkingLotTokenException {
        if(issuedTokens.size()==capacity)
            notifyParkingLotSpaceNowAvailableListeners();
        if(!issuedTokens.remove(parkingToken))
            throw new InvalidParkingLotTokenException("Token is not valid");
        return new Object();
    }

    private void notifyParkingLotSpaceNowAvailableListeners() {
        spaceAvailableListenrs.forEach(parkingLotObserver::updateBackToNotFull);

    }



    public void addParkingLotFullListener(parkingLotObserver listener) {
        fullListeners.add(listener);
    }

    public void addParkingLotBackToHoldListener(parkingLotObserver listener) {
        spaceAvailableListenrs.add(listener);
    }
}
