package com.parkinglot;

import com.parkinglot.exception.ParkingLotNotAvailabelException;
import com.parkinglot.exception.SlotNotAvailableException;

import java.util.List;

/**
 * Created by bsneha on 6/30/2016.
 */
public class ParkingAttenderWhoAllocatesSomeParingLot extends ParkingAttender {
    @Override
    Object park(List<ParkingLot> parkingLots) throws ParkingLotNotAvailabelException, SlotNotAvailableException {
//        for (ParkingLot parkinglot:parkingLots
//             ) {
//            if(parkinglot.getCountOfAvailableSlotsInParkingLot()>0){
//                return parkinglot.occupy();
//            }
//
//        }

        for(int i=0;i<parkingLots.size();i++){
            if(parkingLots.get(i).getCountOfAvailableSlotsInParkingLot()>0) {
                this.allocatedTokenToParkingLotCollection.put(parkingLots.get(i).occupy(),parkingLots.get(i));
                return parkingLots.get(i).occupy();
            }

        }

        throw new ParkingLotNotAvailabelException("");
    }
}
