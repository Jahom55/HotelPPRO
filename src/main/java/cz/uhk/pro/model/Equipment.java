package cz.uhk.pro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="equipments")
public class Equipment {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int equipmentId;
    
    private boolean parking;
    
    private boolean internet;
    
    private boolean restauration;
    
    private boolean pets;
    
    private boolean pool;
    
    private boolean wellness;
    
    private boolean wheellchairAccess;
    
    private boolean saloon;

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isInternet() {
		return internet;
	}

	public void setInternet(boolean internet) {
		this.internet = internet;
	}

	public boolean isRestauration() {
		return restauration;
	}

	public void setRestauration(boolean restauration) {
		this.restauration = restauration;
	}

	public boolean isPets() {
		return pets;
	}

	public void setPets(boolean pets) {
		this.pets = pets;
	}

	public boolean isPool() {
		return pool;
	}

	public void setPool(boolean pool) {
		this.pool = pool;
	}

	public boolean isWellness() {
		return wellness;
	}

	public void setWellness(boolean wellness) {
		this.wellness = wellness;
	}

	public boolean isWheellchairAccess() {
		return wheellchairAccess;
	}

	public void setWheellchairAccess(boolean wheellchairAccess) {
		this.wheellchairAccess = wheellchairAccess;
	}

	public boolean isSaloon() {
		return saloon;
	}

	public void setSaloon(boolean saloon) {
		this.saloon = saloon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + equipmentId;
		result = prime * result + (internet ? 1231 : 1237);
		result = prime * result + (parking ? 1231 : 1237);
		result = prime * result + (pets ? 1231 : 1237);
		result = prime * result + (pool ? 1231 : 1237);
		result = prime * result + (restauration ? 1231 : 1237);
		result = prime * result + (saloon ? 1231 : 1237);
		result = prime * result + (wellness ? 1231 : 1237);
		result = prime * result + (wheellchairAccess ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipment other = (Equipment) obj;
		if (equipmentId != other.equipmentId)
			return false;
		if (internet != other.internet)
			return false;
		if (parking != other.parking)
			return false;
		if (pets != other.pets)
			return false;
		if (pool != other.pool)
			return false;
		if (restauration != other.restauration)
			return false;
		if (saloon != other.saloon)
			return false;
		if (wellness != other.wellness)
			return false;
		if (wheellchairAccess != other.wheellchairAccess)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", parking=" + parking + ", internet=" + internet
				+ ", restauration=" + restauration + ", pets=" + pets + ", pool=" + pool + ", wellness=" + wellness
				+ ", wheellchairAccess=" + wheellchairAccess + ", saloon=" + saloon + "]";
	}
    

}
