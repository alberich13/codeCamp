package com.indigo.restapi.controller;

import static com.indigo.restapi.util.LogVariable.ID;
import javax.validation.Valid;
import org.slf4j.MDC;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.indigo.restapi.exception.ErrorResponseDto;
import com.indigo.restapi.exception.ValidationErrorResponseDto;
import com.indigo.restapi.model.Message;
import com.indigo.twiiter.service.TwiiterService;

@Api(tags = "Codecamp")
@Validated
@RestController
@RequestMapping(value = "/codecamp")
public class TwiiterController {
	
	@Autowired
	private TwiiterService twiiterService;

	@ApiOperation(value = "Consume servicios .", notes = "Consulta seguidores de una cuenta enviada.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mensaje entregado correctamente. Proceso derivado, ejecutado correctamente", response = Boolean.class),
			@ApiResponse(code = 202, message = "Cuando ocurre alguna excepci\u00f3n y/o no cumple con ciertas caracter\u00EDsticas para enviar el mensaje.", response = ErrorResponseDto.class),
			@ApiResponse(code = 400, message = "Datos de la petici\u00f3n incorrectos.", response = ValidationErrorResponseDto.class),
			@ApiResponse(code = 500, message = "Cuando ocurre un error no identificado en el sistema.", response = ErrorResponseDto.class) })
	@PostMapping(path = "/getUserFollowers", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Message> getUserFollowers(
			@Valid @ApiParam(value = "Mensaje enviado por el usuario para realizar el proceso de consulta en twiiter", required = true) @RequestBody Message message) {
		MDC.put(ID, message.getId().toString());
		message.setUserListResponse(
				twiiterService.getUserFollowers(message.getUserNameA()));
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Consume servicios .", notes = "Consulta amigos en comun de un par de cuentas enviadas.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Mensaje entregado correctamente. Proceso derivado, ejecutado correctamente", response = Boolean.class),
			@ApiResponse(code = 202, message = "Cuando ocurre alguna excepci\u00f3n y/o no cumple con ciertas caracter\u00EDsticas para enviar el mensaje.", response = ErrorResponseDto.class),
			@ApiResponse(code = 400, message = "Datos de la petici\u00f3n incorrectos.", response = ValidationErrorResponseDto.class),
			@ApiResponse(code = 500, message = "Cuando ocurre un error no identificado en el sistema.", response = ErrorResponseDto.class) })
	@PostMapping(path = "/getCommonFriends", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Message> getCommonFriends(
			@Valid @ApiParam(value = "Mensaje enviado por el usuario para realizar el proceso de consulta en twiiter", required = true) @RequestBody Message message) {
		MDC.put(ID, message.getId().toString());
		message.setUserListResponse(
				twiiterService.getCommonFriends(message.getUserNameA(), message.getUserNameB()));
		return new ResponseEntity<Message>(message, HttpStatus.OK);
	}
}
