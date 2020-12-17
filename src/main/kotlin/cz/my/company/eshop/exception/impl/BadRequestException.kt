package cz.my.company.eshop.exception.impl

import cz.my.company.eshop.annotation.ExceptionLabel
import cz.my.company.eshop.exception.eshopException

@ExceptionLabel("Bad request.")
class BadRequestException : eshopException {
  constructor(message: String?) : super(message)
  constructor(message: String?, cause: Throwable?) : super(message, cause)

  companion object {
    private const val serialVersionUID = 1L
  }
}