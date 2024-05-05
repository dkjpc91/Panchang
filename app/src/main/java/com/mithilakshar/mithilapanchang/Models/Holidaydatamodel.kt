package com.mithilakshar.mithilapanchang.Models



class holidaydatamodel{
    var  holidayName: String? =null
    var holidayDesc: String? =null
    var  imageUrl: String?=null

    constructor()
    constructor(holidayDesc: String?, holidayName: String?, imageUrl: String?) {
        this.holidayDesc = holidayDesc
        this.holidayName = holidayName
        this.imageUrl = imageUrl
    }

}
