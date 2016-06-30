package com.parkinglot;

import com.parkinglot.exception.InvalidParkingLotTokenException;
import com.parkinglot.exception.ParkingLotNotAvailabelException;
import com.parkinglot.exception.SlotNotAvailableException;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Mock
    private List<ParkingLot> parkingLots;

    @Test
    public void ShouldParkCarInTheParkingLotWithAvailableSpaceInSomeParkingLot() throws SlotNotAvailableException, ParkingLotNotAvailabelException {
        ParkingAttender parkingAttender = new ParkingAttenderWhoAllocatesSomeParingLot();
        ParkingLot mockParkingLot1=mock(ParkingLot.class);
        ParkingLot mockParkingLot2=mock(ParkingLot.class);
        ParkingLot mockParkingLot3=mock(ParkingLot.class);
        parkingLots= new ArrayList<>() ;
        List parkingLots = mock(List.class);
        parkingLots.add(mockParkingLot1);
        parkingLots.add(mockParkingLot2);
        parkingLots.add(mockParkingLot3);
        Mockito.when(parkingLots.size()).thenReturn(3);
        // parkingLots = Arrays.asList(mockParkingLot1,mockParkingLot2,mockParkingLot3);
        Object token = new Object();
        Mockito.when(parkingLots.get(0)).thenReturn(mockParkingLot1);
        Mockito.when(parkingLots.get(1)).thenReturn(mockParkingLot2);
        Mockito.when(parkingLots.get(2)).thenReturn(mockParkingLot3);
        Mockito.when(mockParkingLot1.getCountOfAvailableSlotsInParkingLot()).thenReturn(2);
        Mockito.when(mockParkingLot2.getCountOfAvailableSlotsInParkingLot()).thenReturn(3);
        Mockito.when(mockParkingLot3.getCountOfAvailableSlotsInParkingLot()).thenReturn(1);
        Mockito.when(mockParkingLot1.occupy()).thenReturn(token);
        assertEquals(token, parkingAttender.park(parkingLots));
        verify(mockParkingLot1, times(1)).occupy();
    }

    @Test (expected = ParkingLotNotAvailabelException.class)
    public void ShouldNotParkCarInTheParkingLotAsNoParkingLotIsAvailable() throws SlotNotAvailableException, ParkingLotNotAvailabelException {
        ParkingAttender parkingAttender = new ParkingAttenderWhoAllocatesSomeParingLot();
        ParkingLot mockParkingLot1=mock(ParkingLot.class);
        ParkingLot mockParkingLot2=mock(ParkingLot.class);
        ParkingLot mockParkingLot3=mock(ParkingLot.class);
        parkingLots= new ArrayList<>() ;
        List parkingLots = mock(List.class);
        parkingLots.add(mockParkingLot1);
        parkingLots.add(mockParkingLot2);
        parkingLots.add(mockParkingLot3);
        Mockito.when(parkingLots.size()).thenReturn(3);
        Object token = new Object();
        Mockito.when(parkingLots.get(0)).thenReturn(mockParkingLot1);
        Mockito.when(parkingLots.get(1)).thenReturn(mockParkingLot2);
        Mockito.when(parkingLots.get(2)).thenReturn(mockParkingLot3);
        Mockito.when(mockParkingLot1.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot2.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot3.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        parkingAttender.park(parkingLots);
    }


    @Test
    public void ShouldParkCarInTheParkingLotWithAvailableSpaceSoThatEqualSlotsAreOccupiedInParkingLot() throws SlotNotAvailableException, ParkingLotNotAvailabelException {
        ParkingAttender parkingAttender = new ParkingAttenderWhoAllocatesEqualSlotsInParingLot();
        ParkingLot mockParkingLot1=mock(ParkingLot.class);
        ParkingLot mockParkingLot2=mock(ParkingLot.class);
        ParkingLot mockParkingLot3=mock(ParkingLot.class);
        parkingLots= new ArrayList<>() ;
        List parkingLots = mock(List.class);
        parkingLots.add(mockParkingLot1);
        parkingLots.add(mockParkingLot2);
        parkingLots.add(mockParkingLot3);
        Mockito.when(parkingLots.size()).thenReturn(3);
        // parkingLots = Arrays.asList(mockParkingLot1,mockParkingLot2,mockParkingLot3);
        Object token = new Object();
        Mockito.when(parkingLots.get(0)).thenReturn(mockParkingLot1);
        Mockito.when(parkingLots.get(1)).thenReturn(mockParkingLot2);
        Mockito.when(parkingLots.get(2)).thenReturn(mockParkingLot3);
        Mockito.when(mockParkingLot1.getCountOfAvailableSlotsInParkingLot()).thenReturn(2);
        Mockito.when(mockParkingLot2.getCountOfAvailableSlotsInParkingLot()).thenReturn(3);
        Mockito.when(mockParkingLot3.getCountOfAvailableSlotsInParkingLot()).thenReturn(1);
        Mockito.when(mockParkingLot2.occupy()).thenReturn(token);
        assertEquals(token, parkingAttender.park(parkingLots));
        verify(mockParkingLot2, times(1)).occupy();
    }

    @Test  (expected = ParkingLotNotAvailabelException.class)
    public void ShouldNotParkCarInTheParkingLotWithAvailableSpaceWhenDoingEqualSlotAllocationIfNoSlotAvailable() throws SlotNotAvailableException, ParkingLotNotAvailabelException {
        ParkingAttender parkingAttender = new ParkingAttenderWhoAllocatesEqualSlotsInParingLot();
        ParkingLot mockParkingLot1=mock(ParkingLot.class);
        ParkingLot mockParkingLot2=mock(ParkingLot.class);
        ParkingLot mockParkingLot3=mock(ParkingLot.class);
        parkingLots= new ArrayList<>() ;
        List parkingLots = mock(List.class);
        parkingLots.add(mockParkingLot1);
        parkingLots.add(mockParkingLot2);
        parkingLots.add(mockParkingLot3);
        Mockito.when(parkingLots.size()).thenReturn(3);
        // parkingLots = Arrays.asList(mockParkingLot1,mockParkingLot2,mockParkingLot3);
        Object token = new Object();
        Mockito.when(parkingLots.get(0)).thenReturn(mockParkingLot1);
        Mockito.when(parkingLots.get(1)).thenReturn(mockParkingLot2);
        Mockito.when(parkingLots.get(2)).thenReturn(mockParkingLot3);
        Mockito.when(mockParkingLot1.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot2.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot3.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot2.occupy()).thenReturn(token);
        assertEquals(token, parkingAttender.park(parkingLots));
        verify(mockParkingLot2, times(1)).occupy();
    }

    @Test
    public void ShouldUnParkCarWhenDriverGivingValidToken() throws SlotNotAvailableException, InvalidParkingLotTokenException, ParkingLotNotAvailabelException {
        ParkingAttender parkingAttender = new ParkingAttenderWhoAllocatesSomeParingLot();
        ParkingLot mockParkingLot1=mock(ParkingLot.class);
        ParkingLot mockParkingLot2=mock(ParkingLot.class);
        ParkingLot mockParkingLot3=mock(ParkingLot.class);
        parkingLots= new ArrayList<>() ;
        List parkingLots = mock(List.class);
        parkingLots.add(mockParkingLot1);
        parkingLots.add(mockParkingLot2);
        parkingLots.add(mockParkingLot3);
        Mockito.when(parkingLots.size()).thenReturn(3);
        // parkingLots = Arrays.asList(mockParkingLot1,mockParkingLot2,mockParkingLot3);
        Object token = new Object();
        Mockito.when(parkingLots.get(0)).thenReturn(mockParkingLot1);
        Mockito.when(parkingLots.get(1)).thenReturn(mockParkingLot2);
        Mockito.when(parkingLots.get(2)).thenReturn(mockParkingLot3);
        Mockito.when(mockParkingLot1.getCountOfAvailableSlotsInParkingLot()).thenReturn(0);
        Mockito.when(mockParkingLot2.getCountOfAvailableSlotsInParkingLot()).thenReturn(3);
        Mockito.when(mockParkingLot3.getCountOfAvailableSlotsInParkingLot()).thenReturn(1);
        Mockito.when(mockParkingLot2.occupy()).thenReturn(token);
        assertEquals(token, parkingAttender.park(parkingLots));
        verify(mockParkingLot2, times(1)).occupy();
        Mockito.when(mockParkingLot2.unOccupy(token)).thenReturn(true);
        parkingAttender.unPark(parkingLots,token);
        verify(mockParkingLot2, times(1)).unOccupy(token);
    }
////
//    @Test(expected = InvalidParkingLotTokenException.class)
//    public void ShouldNotBeAbleUnParkCarWhenDriverGivingInValidToken() throws SlotNotAvailableException, InvalidParkingLotTokenException {
//        ParkingAttender parkingAttender = new ParkingAttender();
//        ParkingLot parkingLot = mock(ParkingLot.class);
//        Mockito.when(parkingLot.occupy()).thenReturn(new Object());
//        Object token = parkingAttender.park(parkingLot);
//        Object invalidToken = new Object();
//
//        Mockito.when(parkingLot.unOccupy(invalidToken)).thenThrow(new InvalidParkingLotTokenException("Invalid token"));
//        parkingAttender.unPark(parkingLot, invalidToken);
//        verify(parkingLot, times(1)).unOccupy(invalidToken);
//    }


}
