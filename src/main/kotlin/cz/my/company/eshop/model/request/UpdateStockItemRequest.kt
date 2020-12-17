package cz.my.company.eshop.model.request

import com.fasterxml.jackson.annotation.JsonInclude
import java.nio.file.Path
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UpdateStockItemRequest(val name: String?, val description: String?, val category: String?, val price: Long?, val imageDirPath:String?)
