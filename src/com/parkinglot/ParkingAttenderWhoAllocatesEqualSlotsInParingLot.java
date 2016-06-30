package com.parkinglot;

import com.parkinglot.exception.ParkingLotNotAvailabelException;
import com.parkinglot.exception.SlotNotAvailableException;

import java.util.List;

/**
 * Created by bsneha on 6/30/2016.
 */
public class ParkingAttenderWhoAllocatesEqualSlotsInParingLot extends ParkingAttender {
    @Override
    Object park(List<ParkingLot> parkingLots) throws SlotNotAvailableException, ParkingLotNotAvailabelException {
        int positionOfLeastOccupiedParkinLot = -1;
        for (int i = 0; i < parkingLots.size(); i++) {
            int slotAvailableInCurrentParkingLot = parkingLots.get(i).getCountOfAvailableSlotsInParkingLot();
            if (slotAvailableInCurrentParkingLot > 0) {
                if (positionOfLeastOccupiedParkinLot == -1)
                    positionOfLeastOccupiedParkinLot = i;
                if (slotAvailableInCurrentParkingLot > parkingLots.get(positionOfLeastOccupiedParkinLot).getCountOfAvailableSlotsInParkingLot()) {
                    positionOfLeastOccupiedParkinLot = i;
                }
            }


        }
        if (positionOfLeastOccupiedParkinLot != -1) {
            Object token=parkingLots.get(positionOfLeastOccupiedParkinLot).occupy();
            this.allocatedTokenToParkingLotCollection.put(token,parkingLots.get(positionOfLeastOccupiedParkinLot));
            return token;
        }
        throw new ParkingLotNotAvailabelException("");
    }
}
