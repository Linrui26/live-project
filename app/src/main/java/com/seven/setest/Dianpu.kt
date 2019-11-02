package com.seven.setest

data class Dianpu(override val name:String, override val imageUrl:String):Good {
    var prize:Int = 0
    var location:String = ""
}