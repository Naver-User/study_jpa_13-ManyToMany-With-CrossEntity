package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecyleListener;

import lombok.Data;


@Data

@EntityListeners(CommonEntityLifecyleListener.class)

@Entity(name = "Product3")
@Table(name="product3")
public class Product3
	implements Serializable {		// T2, Many (M)
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")	// PK
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

	 <Entity>            < Cross Entity>              <Entity>
	----------------------------------------------------------
	     1         :          N  ,    N          :        1
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
	
	// Nothing to do.
	
   
} // end class
