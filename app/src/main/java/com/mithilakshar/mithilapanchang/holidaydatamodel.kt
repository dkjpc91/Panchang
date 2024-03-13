package com.mithilakshar.mithilapanchang

class holidaydatamodel {
    var holidayName: String? = null
    var holidayDesc: String? = null
    var imageUrl: String? = null

    constructor()
    constructor(holidayName: String?, holidayDesc: String?, imageUrl: String?) {
        this.holidayName = holidayName
        this.holidayDesc = holidayDesc
        this.imageUrl = imageUrl
    }
}
