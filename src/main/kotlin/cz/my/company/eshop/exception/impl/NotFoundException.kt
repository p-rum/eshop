package cz.my.company.eshop.exception.impl

import cz.my.company.eshop.annotation.ExceptionLabel
import cz.my.company.eshop.exception.eshopException

@ExceptionLabel("Requested Resource Not Found")
class NotFoundException : eshopException {
  constructor(message: String?) : super(message)

  companion object {
    private const val serialVersionUID = 1L
  }
}