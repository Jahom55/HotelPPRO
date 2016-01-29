package cz.uhk.pro.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="trees")
public class Tree {
	/*CREATE TABLE `ppro_hotels`.`trees` (
  `treeId` INT(11) NOT NULL AUTO_INCREMENT,
  `body` LONGTEXT NULL,
  `userId` INT(11) NULL,
  `hotelId` INT(11) NULL,
  `depth` INT(11) NULL,
  `root` TINYINT(1) NULL DEFAULT NULL,
  `ancestor` INT(11) NULL,
  `descendant` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`treeId`),
  INDEX `FKsdf652` (`userId` ASC),
  INDEX `FK324awe` (`hotelId` ASC),
  CONSTRAINT `FK324awe`
    FOREIGN KEY (`hotelId`)
    REFERENCES `ppro_hotels`.`hotels` (`hotelId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FKsdf652`
    FOREIGN KEY (`userId`)
    REFERENCES `ppro_hotels`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

*/
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	int treeId;
	
	@Column(columnDefinition="LONGTEXT")
	String body;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="userId")
	@JsonBackReference
	User user;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="hotelId")
	@JsonBackReference
	Hotel hotel;
	
	int depth;
	
	boolean root;
	
	int ancestor;
	
	int descendant;

	public int getTreeId() {
		return treeId;
	}

	public void setTreeId(int treeId) {
		this.treeId = treeId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public int getAncestor() {
		return ancestor;
	}

	public void setAncestor(int ancestor) {
		this.ancestor = ancestor;
	}

	public int getDescendant() {
		return descendant;
	}

	public void setDescendant(int descendant) {
		this.descendant = descendant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ancestor;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + depth;
		result = prime * result + descendant;
		result = prime * result + ((hotel == null) ? 0 : hotel.hashCode());
		result = prime * result + (root ? 1231 : 1237);
		result = prime * result + treeId;
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
		Tree other = (Tree) obj;
		if (ancestor != other.ancestor)
			return false;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (depth != other.depth)
			return false;
		if (descendant != other.descendant)
			return false;
		if (hotel == null) {
			if (other.hotel != null)
				return false;
		} else if (!hotel.equals(other.hotel))
			return false;
		if (root != other.root)
			return false;
		if (treeId != other.treeId)
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
		return "Tree [treeId=" + treeId + ", body=" + body + ", user=" + user + ", hotel=" + hotel + ", depth=" + depth
				+ ", root=" + root + ", ancestor=" + ancestor + ", descendant=" + descendant + "]";
	}

}
