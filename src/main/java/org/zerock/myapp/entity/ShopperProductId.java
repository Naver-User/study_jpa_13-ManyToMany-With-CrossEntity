package org.zerock.myapp.entity;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;


// *주의사항1* : ID클래스의 필수조건들
// 복합키를 구성해주기 위해 선언하는 ID 클래스는
// (1) 반드시 Java Beans 규약을 지켜야 하고, 
// (2) 반드시 Serializable 인터페이스도 구현해야 합니다.
// (3) 이 ID 클래스는 엔티티 클래스가 아니되,
//     반드시 영속성 설정파일에 클래스가 등록되어야 합니다.(***)

@Data
public class ShopperProductId implements Serializable {
	@Serial private static final long serialVersionUID = 1L;
	
	// Cross Entity 에 선언된 T1 FK 필드와 T2 FK 필드를 선언해야 합니다.
	// (1) T1 FK 필드의 이름/타입 모두 반드시 똑같이 선언해야 합니다.
	// (2) T2 FK 필드의 이름/타입 모두 반드시 똑같이 선언해야 합니다.
	// * 주의사항: 위에서 말하는 타입은, T1 필드의 PK타입을 의미합니다.
	//                                   T2 필드의 PK타입을 의미합니다.
	
	// 타입은 T1 엔티티의 PK 타입, 이름은 Cross Entity에 선언된 FK속성명
	private Long shopperFK;	// T1 FK 되어야 한다! -> T1 엔티티에 FK속성으로 선언되어야 한다!
							// 만일, 교차릴레이션(Cross Entity)가 나왔다면, T1 FK는
							// 이 교차릴레이션(Cross Entity)에 선언되어야 한다!!(***)
	
	// 타입은 T2의 엔티티PK 타입, 이름은 Cross Entity에 선언된 FK속성명
	private Long productFK;	// T2 FK 되어야 한다! -> T2 엔티티에 FK속성으로 선언되어야 한다!
							// 만일, 교차릴레이션(Cross Entity)가 나왔다면, T1 FK는
							// 이 교차릴레이션(Cross Entity)에 선언되어야 한다!!(***)

	
} // end class
