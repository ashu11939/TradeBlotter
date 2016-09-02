package team6.onlinetradeblotter.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the pricing_info database table.
 * 
 */
@Entity
@Table(name="pricing_info")
@NamedQuery(name="PricingInfo.findAll", query="SELECT p FROM PricingInfo p")
public class PricingInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tradeID;

	private String firm;

	private BigDecimal price;

	private String product;

	private int qty;

	private String side;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timeUpdated;

	private String tradeStatus;

	public PricingInfo() {
	}


	public String getFirm() {
		return this.firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getSide() {
		return this.side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Date getTimeUpdated() {
		return this.timeUpdated;
	}

	public void setTimeUpdated(Date timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

}