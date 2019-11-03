package com.seven.setest

data class PoiData(
    val name:String,
    val address:String,
    val location:String,
    val tel:String,
    val photos:List<PhotoData>,
    val biz_ext:RatingData
)

data class RatingData(
    val rating:Float
)

data class PhotoData(
    val url:String
)
