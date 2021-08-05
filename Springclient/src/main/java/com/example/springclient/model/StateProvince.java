package com.example.springclient.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StateProvince {

	private Long id;
	
	private String stateProvinceCode;
	
	private String name;
	
}
