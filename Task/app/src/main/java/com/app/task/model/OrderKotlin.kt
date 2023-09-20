package com.app.task.model

import com.google.gson.annotations.SerializedName

data class OrderKotlin (
    @SerializedName("status"  ) var status  : Int?    = null,
    @SerializedName("message" ) var message : String? = null,
    @SerializedName("data"    ) var data    : Data?   = Data()
)

data class Data (
    @SerializedName("orderInfo"    ) var orderInfo   : OrderInfo? = OrderInfo(),
    @SerializedName("total_record" ) var totalRecord : Int?       = null
)

data class OrderInfo (
    @SerializedName("orders" ) var orders : ArrayList<Orders> = arrayListOf()
)

data class Orders (
    @SerializedName("order_id"                ) var orderId               : Int?                   = null,
    @SerializedName("phone"                   ) var phone                 : String?                = null,
    @SerializedName("phone_pin_code"          ) var phonePinCode          : String?                = null,
    @SerializedName("address"                 ) var address               : String?                = null,
    @SerializedName("restaurant_brand_id"     ) var restaurantBrandId     : String?                = null,
    @SerializedName("latitude"                ) var latitude              : String?                = null,
    @SerializedName("longitude"               ) var longitude             : String?                = null,
    @SerializedName("sequence_no"             ) var sequenceNo            : Int?                   = null,
    @SerializedName("type"                    ) var type                  : Int?                   = null,
    @SerializedName("order_type"              ) var orderType             : String?                = null,
    @SerializedName("company_support_no"      ) var companySupportNo      : String?                = null,
    @SerializedName("order_type_id"           ) var orderTypeId           : Int?                   = null,
    @SerializedName("amount"                  ) var amount                : Double?                = null,
    @SerializedName("sub_total"               ) var subTotal              : Double?                = null,
    @SerializedName("total"                   ) var total                 : Double?                = null,
    @SerializedName("tip"                     ) var tip                   : Int?                   = null,
    @SerializedName("tax"                     ) var tax                   : Double?                = null,
    @SerializedName("delivery_fee"            ) var deliveryFee           : Int?                   = null,
    @SerializedName("service_fee"             ) var serviceFee            : Int?                   = null,
    @SerializedName("discount"                ) var discount              : Int?                   = null,
    @SerializedName("bag_fee"                 ) var bagFee                : Int?                   = null,
    @SerializedName("delivery_date"           ) var deliveryDate          : String?                = null,
    @SerializedName("submited_date"           ) var submitedDate          : String?                = null,
    @SerializedName("expected_date"           ) var expectedDate          : String?                = null,
    @SerializedName("delivery_type"           ) var deliveryType          : Int?                   = null,
    @SerializedName("is_pickup"               ) var isPickup              : Int?                   = null,
    @SerializedName("order_status"            ) var orderStatus           : Int?                   = null,
    @SerializedName("order_notes"             ) var orderNotes            : String?                = null,
    @SerializedName("reference_no"            ) var referenceNo           : String?                = null,
    @SerializedName("diner_count"             ) var dinerCount            : String?                = null,
    @SerializedName("ordersItems"             ) var ordersItems           : ArrayList<OrdersItems> = arrayListOf(),
    @SerializedName("sort_date"               ) var sortDate              : String?                = null,
    @SerializedName("is_future"               ) var isFuture              : Int?                   = null,
    @SerializedName("driver_image"            ) var driverImage           : String?                = null,
    @SerializedName("driver_thumb_image"      ) var driverThumbImage      : String?                = null,
    @SerializedName("driver_id"               ) var driverId              : String?                = null,
    @SerializedName("company_driver"          ) var companyDriver         : CompanyDriver?         = CompanyDriver(),
    @SerializedName("is_only_receipt"         ) var isOnlyReceipt         : Int?                   = null,
    @SerializedName("is_foodreadyorder_new"   ) var isFoodreadyorderNew   : Int?                   = null,
    @SerializedName("is_cancelorder_new"      ) var isCancelorderNew      : Int?                   = null,
    @SerializedName("is_refundorder_new"      ) var isRefundorderNew      : Int?                   = null,
    @SerializedName("is_delayorder_new"       ) var isDelayorderNew       : Int?                   = null,
    @SerializedName("is_adjustorderprice_new" ) var isAdjustorderpriceNew : Int?                   = null,
    @SerializedName("is_outfordelivery_new"   ) var isOutfordeliveryNew   : Int?                   = null,
    @SerializedName("is_foodreadyorder"       ) var isFoodreadyorder      : Boolean?               = null,
    @SerializedName("is_cancelorder"          ) var isCancelorder         : Boolean?               = null,
    @SerializedName("is_refundorder"          ) var isRefundorder         : Boolean?               = null,
    @SerializedName("is_delayorder"           ) var isDelayorder          : Boolean?               = null,
    @SerializedName("is_adjustorderprice"     ) var isAdjustorderprice    : Boolean?               = null,
    @SerializedName("is_outfordelivery"       ) var isOutfordelivery      : Boolean?               = null,
    @SerializedName("print_count"             ) var printCount            : Int?                   = null,
    @SerializedName("adjusted_count"          ) var adjustedCount         : Int?                   = null,
    @SerializedName("canceled_count"          ) var canceledCount         : Int?                   = null,
    @SerializedName("modified_count"          ) var modifiedCount         : Int?                   = null
)

data class OrdersItems (
    @SerializedName("description"      ) var description     : String?                   = null,
    @SerializedName("printers"         ) var printers        : ArrayList<String>         = arrayListOf(),
    @SerializedName("default_printers" ) var defaultPrinters : ArrayList<String>         = arrayListOf(),
    @SerializedName("sort_no"          ) var sortNo          : Int?                      = null,
    @SerializedName("cat_sort_no"      ) var catSortNo       : Int?                      = null,
    @SerializedName("final_sort_no"    ) var finalSortNo     : Long?                      = null,
    @SerializedName("quantity"         ) var quantity        : Int?                      = null,
    @SerializedName("price"            ) var price           : Double?                   = null,
    @SerializedName("modifiers"        ) var modifiers       : ArrayList<Modifiers>      = arrayListOf(),
    @SerializedName("print_item_name"  ) var printItemName   : ArrayList<PrintItemName>  = arrayListOf(),
    @SerializedName("print_modifiers"  ) var printModifiers  : ArrayList<PrintModifiers> = arrayListOf()
)

data class Modifiers (
    @SerializedName("category" ) var category : String? = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("sort_no"  ) var sortNo   : Int?    = null,
    @SerializedName("price"    ) var price    : String? = null,
    @SerializedName("quantity" ) var quantity : Int?    = null
)

data class PrintItemName (
    @SerializedName("printer"   ) var printer  : String? = null,
    @SerializedName("item_name" ) var itemName : String? = null
)

data class PrintModifiers (
    @SerializedName("printer"   ) var printer   : String?              = null,
    @SerializedName("modifiers" ) var modifiers : ArrayList<Modifiers> = arrayListOf()
)

data class CompanyDriver (
    @SerializedName("driver_image"       ) var driverImage      : String? = null,
    @SerializedName("driver_thumb_image" ) var driverThumbImage : String? = null,
    @SerializedName("driver_id"          ) var driverId         : String? = null,
    @SerializedName("driver_name"        ) var driverName       : String? = null,
    @SerializedName("driver_number"      ) var driverNumber     : String? = null,
    @SerializedName("driver_eta"         ) var driverEta        : String? = null,
    @SerializedName("driver_status"      ) var driverStatus     : Int?    = null
)