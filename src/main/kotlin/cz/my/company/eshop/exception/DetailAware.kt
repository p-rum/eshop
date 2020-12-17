package cz.my.company.eshop.exception

import java.util.*

interface DetailAware {
  val detail: Optional<Any>
}