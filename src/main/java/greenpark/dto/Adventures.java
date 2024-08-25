package greenpark.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adventures {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String adventures;
	private double price ;
	private double childPrice ;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdventures() {
		return adventures;
	}

	public void setAdventures(String adventures) {
		this.adventures = adventures;
	}

	@Override
	public String toString() {
		return "Adventures [id=" + id + ", adventures=" + adventures + "]";
	}

	public double getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(double childPrice) {
		this.childPrice = childPrice;
	}

//	private String camping;
//	private String bungeeJumping;
//	private String boating;
//	private String ziplining;
//	private String hillClimbing;
//	private String skyDiving;
//	private String rockClimbing;

}
