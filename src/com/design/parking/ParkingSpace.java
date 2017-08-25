package com.design.parking;

public class ParkingSpace {
	Vehicle vehicle;
	ParkingType parkingType;
	int distance = Integer.MAX_VALUE;

	public ParkingSpace(ParkingType parkingType, int distance) {
		this.parkingType = parkingType;
		this.distance = distance;
	}

	public boolean isAvailable() {
		return vehicle == null;
	}

	@Override
	public String toString() {
		return distance + "m " + parkingType + " " + vehicle;
	}
	
	@Override
	public int hashCode() {
		return parkingType.hashCode() * 17 + parkingType.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if( !(obj instanceof ParkingSpace)) {
			return false;
		}
		ParkingSpace tmp = (ParkingSpace)obj;
		return (tmp.distance == this.distance) && (tmp.parkingType == this.parkingType);
	}
}
