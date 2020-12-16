package cz.my.company.eshop.controller.advice

import cz.my.company.eshop.exception.eshopException
import cz.my.company.eshop.exception.impl.BadGatewayException
import cz.my.company.eshop.exception.impl.BadRequestException
import cz.my.company.eshop.exception.impl.NotFoundException
import cz.my.company.eshop.model.common.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


/**
 * Main application cz.my.company.eshop.exception handler.
 */
@ControllerAdvice
class RestExceptionHandler : AbstractExceptionHandler() {

  /**
   * Handles HTTP 400 errors - Bad Request
   *
   * @param e [cz.my.company.eshop.exception.impl.BadRequestException]
   * @return Error common
   */
  @ExceptionHandler(BadRequestException::class)
  fun handle400(e: eshopException): ResponseEntity<ErrorResponse> {
    return response(BAD_REQUEST, e.label, e, logWarn())
  }


  /**
   * Handles HTTP 404 errors - Not Found.
   *
   * @param e [cz.my.company.eshop.exception]
   * @return Error common
   */
  @ExceptionHandler(NotFoundException::class)
  fun handle404(e: eshopException): ResponseEntity<ErrorResponse> {
    return response(HttpStatus.NOT_FOUND, e.label, e, logWarn())
  }

  /**
   * Handles HTTP 502 errors - BadGateway.
   *
   * @param e [cz.my.company.eshop.exception]
   * @return Error common
   */
  @ExceptionHandler(BadGatewayException::class)
  fun handle502(e: eshopException): ResponseEntity<ErrorResponse> {
    return response(INTERNAL_SERVER_ERROR, e.label, e, logWarn())
  }

}