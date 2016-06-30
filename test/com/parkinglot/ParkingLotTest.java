package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.SlotNotAvailableException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by rajats on 6/28/16.
 */
public class ParkingLotTest {


    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBeAbleToReturnTheCountOfOccupiedSlotsInParkingLotWhenAtLeastOneSlotIsOccupied() throws SlotNotAvailableException {
        ParkingLot parkingLot=new ParkingLot(2);
        Object parkingTokenA = parkingLot.occupy();
        assertEquals(1,parkingLot.getCountOfAvailableSlotsInParkingLot());

    }

    @Test
    public void shouldReturnTheCountOfOccupiedSlotsInParkingLotAsFullWhenNoSlotIsOccupied() throws SlotNotAvailableException {
        ParkingLot parkingLot=new ParkingLot(2);
        assertEquals(2,parkingLot.getCountOfAvailableSlotsInParkingLot());

    }

    @Test
    public void shouldParkMyCarWhenSlotsAvailable() throws SlotNotAvailableException {

        ParkingLot parkingLot = new ParkingLot(1);

        Object parkingTokenA = parkingLot.occupy();
        assertNotNull(parkingTokenA);

        exception.expect(SlotNotAvailableException.class);
        exception.expectMessage(ParkingLot.SLOT_UNAVAILABLE_EXCEPTION_MSG);

        parkingLot.occupy();

    }


    @Test
    public void shouldNotParkMyCarIfSpaceUnavailable() throws SlotNotAvailableException {


        ParkingLot parkingLot = new ParkingLot(1);

        Object parkingTokenA = parkingLot.occupy();
        assertNotNull(parkingTokenA);

        exception.expect(SlotNotAvailableException.class);
        exception.expectMessage(ParkingLot.SLOT_UNAVAILABLE_EXCEPTION_MSG);

        parkingLot.occupy();

    }

    @Test
    public void shouldUnparkMyCarWithValidToken() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        ParkingLot parkingLot = new ParkingLot(1);
        Object parkingToken = parkingLot.occupy();
        Object car = parkingLot.unOccupy(parkingToken);
        assertNotNull(car);
    }

    @Test(expected = InvalidParkingLotTokenException.class)
    public void shouldNotUnparkMyCarWithWrongToken() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.occupy();
        Object car = parkingLot.unOccupy(new Object());
       // assertFalse(isUnparked);
    }

    @Test(expected = InvalidParkingLotTokenException.class)
    public void shouldNotUnparkMyCarIfTokenAlreadyUsed() throws SlotNotAvailableException, InvalidParkingLotTokenException {

        ParkingLot parkingLot = new ParkingLot(1);
        Object parkingToken = parkingLot.occupy();

        Object car= parkingLot.unOccupy(parkingToken);
        assertNotNull(car);

          parkingLot.unOccupy(parkingToken);
      //  Assert.assertFalse(isUnparked);

    }

    @Test
    public void shouldNotifyProductOwnerWhenTheParkingLotIsFull() throws SlotNotAvailableException {
        parkingLotObserver parkingOwner = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.addParkingLotFullListener(parkingOwner);
        Object parkingToken = parkingLot.occupy();
        verify(parkingOwner, times(1)).updatedFull();
    }


    @Test
    public void shouldNotNotifyProductOwnerWhenTheParkingLotIsNotFull() throws SlotNotAvailableException {
        parkingLotObserver parkingOwner = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addParkingLotFullListener(parkingOwner);

        Object parkingToken = parkingLot.occupy();

        verify(parkingOwner, times(0)).updatedFull();
    }

    @Test
    public void shouldNotifySecurityPersonnelWhenTheParkingLotIsFull() throws SlotNotAvailableException {
        parkingLotObserver airportSecurityPersonnel = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.addParkingLotFullListener(airportSecurityPersonnel);

        Object parkingToken = parkingLot.occupy();

        verify(airportSecurityPersonnel, times(1)).updatedFull();
    }

    @Test
    public void shouldNotNotifySecurityPersonnelWhenTheParkingLotIsNotFull() throws SlotNotAvailableException {
        parkingLotObserver airpostSecurityPersonnel = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addParkingLotFullListener(airpostSecurityPersonnel);

        Object parkingToken = parkingLot.occupy();

        verify(airpostSecurityPersonnel, times(0)).updatedFull();
    }

    @Test
    public void shouldNotifyParkingLotOwnnerWhenTheParkingLotWasFullAndIsNowAvailbale() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        parkingLotObserver parkingOwner = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.addParkingLotFullListener(parkingOwner);
        Object parkingToken = parkingLot.occupy();
        parkingLot.addParkingLotBackToHoldListener(parkingOwner);
        parkingLot.unOccupy(parkingToken);
        verify(parkingOwner,times(1)).updateBackToNotFull();
    }

    @Test
    public void shouldNotNotifyParkingLotOwnnerWhenTheParkingLotWasNotFullAndIsAvailbale() throws SlotNotAvailableException, InvalidParkingLotTokenException {
        parkingLotObserver parkingOwner = mock(parkingLotObserver.class);
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addParkingLotFullListener(parkingOwner);
        Object parkingToken = parkingLot.occupy();
        parkingLot.addParkingLotBackToHoldListener(parkingOwner);
        parkingLot.unOccupy(parkingToken);
        verify(parkingOwner,times(0)).updateBackToNotFull();
    }
}
