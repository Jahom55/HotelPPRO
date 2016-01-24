package cz.uhk.pro.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	private int reviewId;
	
	private byte reviewPrice;
	
	private byte reviewFood;
	
	private byte reviewEnviroment;
	
	private byte reviewAccommodation;
	
	private byte reviewComplete;
	
	@ManyToOne()
	@JoinColumn(name="hotelId")
	private Hotel hotel;
	
	@ManyToOne()
	@JoinColumn(name="userId")
	private User user;

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public byte getReviewPrice() {
		return reviewPrice;
	}

	public void setReviewPrice(byte reviewPrice) {
		this.reviewPrice = reviewPrice;
	}

	public byte getReviewFood() {
		return reviewFood;
	}

	public void setReviewFood(byte reviewFood) {
		this.reviewFood = reviewFood;
	}

	public byte getReviewEnviroment() {
		return reviewEnviroment;
	}

	public void setReviewEnviroment(byte reviewEnviroment) {
		this.reviewEnviroment = reviewEnviroment;
	}

	public byte getReviewAccommodation() {
		return reviewAccommodation;
	}

	public void setReviewAccommodation(byte reviewAccommodation) {
		this.reviewAccommodation = reviewAccommodation;
	}

	public byte getReviewComplete() {
		return reviewComplete;
	}

	public void setReviewComplete(byte reviewComplete) {
		this.reviewComplete = reviewComplete;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + reviewAccommodation;
		result = prime * result + reviewComplete;
		result = prime * result + reviewEnviroment;
		result = prime * result + reviewFood;
		result = prime * result + reviewId;
		result = prime * result + reviewPrice;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Review other = (Review) obj;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (reviewAccommodation != other.reviewAccommodation)
			return false;
		if (reviewComplete != other.reviewComplete)
			return false;
		if (reviewEnviroment != other.reviewEnviroment)
			return false;
		if (reviewFood != other.reviewFood)
			return false;
		if (reviewId != other.reviewId)
			return false;
		if (reviewPrice != other.reviewPrice)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", reviewPrice=" + reviewPrice + ", reviewFood=" + reviewFood
				+ ", reviewEnviroment=" + reviewEnviroment + ", reviewAccommodation=" + reviewAccommodation
				+ ", reviewComplete=" + reviewComplete + ", hotel=" + hotel + ", user=" + user + "]";
	}
	

}
