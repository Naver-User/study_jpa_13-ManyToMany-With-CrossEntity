package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Vector;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecyleListener;

import lombok.Data;
import lombok.ToString;


@Data

@EntityListeners(CommonEntityLifecyleListener.class)

@Entity(name = "Shopper3")
@Table(name="shopper3")
public class Shopper3 implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopper_id")	// PK
	private Long id;	
	
	
	// 2. 일반속성들(Generals)
	@Basic(optional = false)		// Not Null Constraint
	private String name;
	
	
	// 3. 연관관계 매핑
	
	// ==============================
	// T1(M, Shopper3), T2(N, Product3), Mapping
	// Using *Cross Entity with composite key (PK = T1 FK + T2 FK)
	// And Additionally Fields
	// ==============================
	
	/**
	 * 교차엔티티(=교차릴레이션)을 이용한, 다대다(M:M) 연관관계 매핑도

	 <Entity>             < Cross Entity>             <Entity>
	----------------------------------------------------------
	     1         :          N  ,    M         :        1
	----------------------------------------------------------
	[ Shopper ] <- *BI -> [ ShopperProduct ] - *UNI -> [Product]
	----------------------------------------------------------
	 (1) PK            (1) PK(T1 FK, T2 FK)           (1) PK
	 (2) Generals      (2) Additioanl fields          (2) Generals
	                   (3) *ID Class to make      
	 (3) Children           an composite key 		  *Nothing to do
	     (List<CE>)         using `@IdClass`
	     				(ShopperProductId.class)
	----------------------------------------------------------
	 */
	
	// -----------------------
	// 일대다(1 : N) 연관관계 매핑
	// -----------------------
	
	@OneToMany(mappedBy = "shopperFK", targetEntity = ShopperProduct.class)	// 2
	
	@ToString.Exclude
	private List<ShopperProduct> shopperProducts = new Vector<>(); 
	
	
	
   
} // end class
