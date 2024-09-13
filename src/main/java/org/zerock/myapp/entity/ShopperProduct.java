package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.zerock.myapp.listener.CommonEntityLifecyleListener;

import lombok.Data;


// 교차릴레이션(Cross Entity)은, T1의 FK와 T2의 FK를 가져와서
// 복합키(T1 FK + T2 FK)로 PK를 구성해야 합니다. 이는
// 우리가 배운 알고리즘 7단계 중에, 다진관계에 대한 해법으로
// 교차릴레이션이 반드시 가져야 하는 PK의 형태입니다.
// 때문에, T1 타입의 FK 속성을 선언하고, T2 타입의 FK속성 선언이
// 필요합니다. 더불어서, 이 2개의 FK 속성이 복합키의 구성요소로
// 사용된다는 것을 알리기 위해서, @Id를 붙여야 합니다.

@Data

@EntityListeners(CommonEntityLifecyleListener.class)

// Important: 다대다 관계를 풀어내는, 교차 릴레이션에 대한 엔티티입니다. (***)
@Entity(name = "ShopperProduct")
@IdClass(ShopperProductId.class)

// 주의사항: 복합단어로 구성된 엔티티가 매핑될 테이블명은,
//           각 단어를 언더스코어('_')로 이어서 만듭니다.
@Table(name="shopper_product")
public class ShopperProduct implements Serializable {
	@Serial private static final long serialVersionUID = 1L;

	// 1. Set PK
	// 교차 릴레이션에 대한 키는 기존과는 다르게 선언해야 합니다!! (***)
	// 아래의 `3. 연관관계 매핑` 부분을 참고하세요!

		
	// 2. 일반속성들(Generals)
	@Basic(optional = false)		// Not Null Constraint
	private Integer amount;			// 예: 주문수량
	
	
	// 3. 연관관계 매핑
	
	/**
	 * 교차엔티티(=교차릴레이션)을 이용한, 다대다(M:M) 연관관계 매핑도

	 <Entity>            < Cross Entity>              <Entity>
	----------------------------------------------------------
	     1         :          N  ,    M          :        1
	----------------------------------------------------------
	[ Shopper ] <- *BI -> [ ShopperProduct ] - *UNI -> [Product]
	----------------------------------------------------------
	 (1) PK            (1) PK(T1 FK, T2 FK)           (1) PK
	 (2) Generals      (2) Additioanl fields          (2) Generals
	                   (3) *ID Class to make      
	 (3) Children           an composite key 		  *Nothing to do
	     (List<CE>)         using `@IdClass`
	     			   (4) @IdClass(ID클래스.class)
	----------------------------------------------------------
	 */
	
	// -- 1 ----------------
	// 다대일(N:1) 연관관계 매핑
	
	@Id
	@ManyToOne(targetEntity = Shopper3.class)	// 연관관계(N:1) 매핑
	@JoinColumn(name = "shopper_id")			// T1(Shopper3) FK 선언
	
	private Shopper3 shopperFK;	// T1 FK
	
	
	// -- 2 ----------------
	// 다대일(M:1) 연관관계 매핑
	
	@Id
	@ManyToOne(targetEntity = Product3.class)	// 연관관계(M:1) 매핑	
	@JoinColumn(name = "product_id")			// T2(Product3) FK 선언
	
	private Product3 productFK;	// T2 FK
	
   
} // end class


