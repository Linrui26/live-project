package com.seven.setest

data class Dianpu(override val name:String, override val imageUrl:String):Good {
    var prize:Int = 0
    var address:String = ""
    var lat = 0.0
    var lng = 0.0
    var tel = ""
    var rating = 0.0
}