package com.design.parking;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * Imagine you've been contacted by a small business owner who runs a small car parking lot. 
 * She wants to expand her business to multiple parking lot.  
 * In order to handle the expansion, she wants to have software system that manages where cars are parked. 
 * How would you design this system? 
 * Assume that you have access to all the latest in ticket-giving and ticket-accepting machines, 
 * and don't worry about the payment processing system at all - assume that part is outside the scope of the problem.
 *
 * Pointer for focus: When a customer arrives, they request a ticket from the system. 
 * The ticket tells the valet where to park the car. 
 * When the customer returns, they give the system the ticket and it tells the valet where to fetch the car and how much the customer is charged.
 */
public class ParkingLot {
	PriorityQueue<ParkingSpace> vacantParkingSpace = new PriorityQueue<>(new Comparator<ParkingSpace>() {
		@Override
		public int compare(ParkingSpace o1, ParkingSpace o2) {
			return o1.distance - o2.distance;
		}
	});
	BiMap<Vehicle, ParkingSpace> occupiedParkingSpace = new BiMap<>();

	ParkingSpace findNearestVacant(ParkingType type) {
		Iterator<ParkingSpace> iter = vacantParkingSpace.iterator();

		while (iter.hasNext()) {
			ParkingSpace parkingSpace = iter.next();
			if (parkingSpace.parkingType == type) {
				return parkingSpace;
			}
		}

		return null;
	}

	public void parkVehicle(Vehicle vehicle, ParkingType type) {
		ParkingSpace parkingSpace = findNearestVacant(type);
		if (parkingSpace == null) {
			throw new RuntimeException("Parking log is full");
		}
		parkingSpace.vehicle = vehicle;
		vacantParkingSpace.remove(parkingSpace);
		occupiedParkingSpace.put(vehicle, parkingSpace);
	}

	public void unparkVehicle(Vehicle vehicle) {
		if (!occupiedParkingSpace.containsKey(vehicle)) {
			throw new RuntimeException("There is no such a car");
		}
		ParkingSpace parkingSpace = occupiedParkingSpace.getVal(vehicle);
		parkingSpace.vehicle = null;
		vacantParkingSpace.add(parkingSpace);
	}

	public boolean isFull() {
		return vacantParkingSpace.isEmpty();
	}
}

class BiMap<K, V> {
	public void put(K key, V val) {
		if (!keyToVal.containsKey(key) && !valTokey.containsKey(val)) {
			keyToVal.put(key, val);
			valTokey.put(val, key);
		}
	}

	public boolean containsKey(K key) {
		return keyToVal.containsKey(key);
	}

	public boolean containsValue(V val) {
		return valTokey.containsKey(val);
	}

	public V getVal(K key) {
		return keyToVal.get(key);
	}

	public K getKey(V val) {
		return valTokey.get(val);
	}

	private Map<K, V> keyToVal = new HashMap<>();
	private Map<V, K> valTokey = new HashMap<>();
}
