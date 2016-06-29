package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.SlotNotAvailableException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by bsneha on 6/29/2016.
 */
public class ParkingAttenderTest {



    @Test
    public void ShouldParkCarInTheParkingLotWithAvailableSpace() throws SlotNotAvailableException {
        ParkingAttender parkingAttender = new ParkingAttender();
        List<ParkingLot> parkingLot = mock(ArrayList.class);
        Object token = new Object();
        Mockito.when(parkingLot.occupy()).thenReturn(token);
        assertEquals(token, parkingAttender.park(parkingLot));
        verify(parkingLot, times(1)).occupy();
    }

    @Test(expected = SlotNotAvailableException.class)
    public void ShouldNotParkCarInTheParkingLotWhenNoAvailableSpace() throws SlotNotAvailableException {
        ParkingAttender parkingAttender = new ParkingAttender();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Mockito.when(parkingLot.occupy()).thenThrow(new SlotNotAvailableException(""));
        parkingAttender.park(parkingLot);
        verify(parkingLot, times(1)).occupy();
    }

    @Test
    public void ShouldUnParkCarWhenDriverGivingValidToken() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        ParkingAttender parkingAttender = new ParkingAttender();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Mockito.when(parkingLot.occupy()).thenReturn(new Object());
        Object token = parkingAttender.park(parkingLot);
        parkingAttender.unPark(parkingLot, token);
        Mockito.when(parkingLot.unOccupy(token)).thenReturn(true);
        verify(parkingLot, times(1)).unOccupy(token);
    }

    @Test(expected = InvalidParkingLotTokenException.class)
    public void ShouldNotBeAbleUnParkCarWhenDriverGivingInValidToken() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        ParkingAttender parkingAttender = new ParkingAttender();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Mockito.when(parkingLot.occupy()).thenReturn(new Object());
        Object token = parkingAttender.park(parkingLot);
        Object invalidToken = new Object();

        Mockito.when(parkingLot.unOccupy(invalidToken)).thenThrow(new InvalidParkingLotTokenException("Invalid token"));
        parkingAttender.unPark(parkingLot, invalidToken);
        verify(parkingLot, times(1)).unOccupy(invalidToken);
    }


}
