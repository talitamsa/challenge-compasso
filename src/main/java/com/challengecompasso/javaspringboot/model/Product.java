package com.challengecompasso.javaspringboot.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable {

	private static final long serialVersionUID = 7389483453343246450L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@ApiModelProperty(value = "name")
	@Column(nullable = false)
	@NotNull
	private String name;

	@ApiModelProperty(value = "description")
	@Column(nullable = false)
	@NotNull
	private String description;

	@ApiModelProperty(value = "price")
	@Column(nullable = false)
	@NotNull
	@PositiveOrZero
	private BigDecimal price;

}
