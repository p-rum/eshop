package cz.my.company.eshop.constant

/**
 * Rest paths for @RequestMapping
 */
object Mappings {

  const val API_V1 = "/api/v1"

  // Bonjour
  const val BONJOUR = "/_bonjour.json"

  const val PROBES = "/probes"
  const val MAGENTO_PRODUCTS = "/rest/V1/products"
  const val IPF_GET_PRODUCT_AVAILABILITY = "/stock-item-services/api/v1/stock-item-check/get-pharmacy-product-availability"
  private const val XMANAGER_SCHEMAS = "/xmanager/api/v2/apps/c7609e47-a63c-4d6a-a4c3-3d8253dbf443/schemas"
  const val XMANAGER_UPDATE_EXTERNAL_JOBS = "$XMANAGER_SCHEMAS/f8d81230-57e4-455b-9d20-ed905933943a/data"
  const val XMANAGER_UPDATE_EXTERNAL_JOBS_FILTER = "$XMANAGER_UPDATE_EXTERNAL_JOBS/filter"
  const val XMANAGER_PRODUCTS = "$XMANAGER_SCHEMAS/04ca188c-1182-44ce-8462-f33063de5e84/data"
  const val XMANAGER_COUNT = "$XMANAGER_PRODUCTS/count"
  const val XMANAGER_BULK_UPDATE = "$XMANAGER_PRODUCTS/bulk"
  const val XMANAGER_PRODUCTS_FILTER = "$XMANAGER_PRODUCTS/filter"
  const val XMANAGER_PRODUCTS_COUNT_BY_FILTER = "$XMANAGER_PRODUCTS/count/filter"
}