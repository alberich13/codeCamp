package com.indigo.restapi.model;

import java.io.Serializable;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message implements Serializable {
	private static final long serialVersionUID = -8095077935188702303L;
	
	@ApiModelProperty(example = "10", notes = "Identificador de mensaje")
	private Integer id;
	@ApiModelProperty(example = "Eduardo", notes = "Usuario A")
	private String userNameA;
	@ApiModelProperty(example = "Aaron", notes = "Usuario B")
	private String userNameB;
	@ApiModelProperty(example = "[Eduardo, Alex, Aaron]", notes = "Lista de usuarios de respuesta")
	private List<String> userListResponse;
}