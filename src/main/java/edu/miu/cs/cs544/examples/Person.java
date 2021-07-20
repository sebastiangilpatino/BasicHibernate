package edu.miu.cs.cs544.examples;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private long id;
	private String firstname;
	private String lastname;
	private LocalDate dateofbirth;
}
