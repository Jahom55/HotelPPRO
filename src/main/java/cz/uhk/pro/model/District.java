package cz.uhk.pro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="districts")
public class District {
	/* Insert pro Jardu
	 * 
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Hlavn� m�sto Praha');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('St�edo�esk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Jiho�esk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Plze�sk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Karlovarsk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('�steck� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Libereck� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Kr�lovehradeck� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Pardubick� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Kraj Vyso�ina');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Jihomoravsk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Olomouck� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Moravskoslezk� kraj');
INSERT INTO `ppro_hotels`.`districts` (`name`) VALUES ('Zl�nsk� kraj');

	 */
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int districtId;
	
	private String name;

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + districtId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		District other = (District) obj;
		if (districtId != other.districtId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "District [districtId=" + districtId + ", name=" + name + "]";
	}
	
}
