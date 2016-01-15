package cz.uhk.pro.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="hotels")
public class Hotel {
	
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int hotelId;
	
	private String name;
	
	private byte stars;
	
    @Column(columnDefinition="LONGTEXT")
	private String description;
	
	private String image;
	
	private String website;
	
	private int counter;
	
	private boolean functional;
	
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "addressId")
	private Address address;
	
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userId")    
	private User user;
	
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "typeId") 
	private Type type;
	
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="equipmentId")
	private Equipment equipment;
    
    @OneToMany(mappedBy="hotel")
    private List<Review> reviews;
	
	@OneToMany(mappedBy="hotel")
	private List<Image> images;

    
	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public int getHotelId() {
		return hotelId;
	}


	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public byte getStars() {
		return stars;
	}


	public void setStars(byte stars) {
		this.stars = stars;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	public boolean isFunctional() {
		return functional;
	}


	public void setFunctional(boolean functional) {
		this.functional = functional;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	public Equipment getEquipment() {
		return equipment;
	}


	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + counter;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
		result = prime * result + (functional ? 1231 : 1237);
		result = prime * result + hotelId;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + stars;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
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
		Hotel other = (Hotel) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (counter != other.counter)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (equipment == null) {
			if (other.equipment != null)
				return false;
		} else if (!equipment.equals(other.equipment))
			return false;
		if (functional != other.functional)
			return false;
		if (hotelId != other.hotelId)
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stars != other.stars)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Hotel [hotelId=" + hotelId + ", name=" + name + ", stars=" + stars + ", description=" + description
				+ ", image=" + image + ", website=" + website + ", counter=" + counter + ", functional=" + functional
				+ ", address=" + address + ", user=" + user + ", type=" + type + ", equipment=" + equipment + "]";
	}

}
