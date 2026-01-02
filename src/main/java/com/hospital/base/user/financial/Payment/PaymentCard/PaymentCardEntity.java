package com.hospital.base.user.financial.Payment.PaymentCard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "paymentcard")

public class PaymentCardEntity {

	public PaymentCardEntity() {
	
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "card_name")
	private String cardname;
	
	
	@Column(name = "card_number")
	private Long cardnumber;
	
	@Column(name = "expiry")
	private Long expiry;
	
	@Column(name = "CCV")
	private Long CCV;
	
	@Transient
	private boolean save;
		
	public String toString() {
		return "PaymentCardEntity [id=" + id + ", cardname=" + cardname + ",cardnumber=" + cardnumber + ", expiry=" + expiry + ", CCV=" + CCV + "]";
	}

	public PaymentCardEntity(String cardname, Long cardnumber, Long expiry, Long CCV) {
		this.cardname = cardname;
		this.cardnumber = cardnumber;
		this.expiry = expiry;
		this.CCV = CCV;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardname() {
		return cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public Long getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}

	public Long getExpiry() {
		return expiry;
	}

	public void setExpiry(Long expiry) {
		this.expiry = expiry;
	}

	public Long getCCV() {
		return CCV;
	}

	public void setCCV(Long cCV) {
		CCV = cCV;
	}

	public boolean isSave() {
		return save;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

			
}
