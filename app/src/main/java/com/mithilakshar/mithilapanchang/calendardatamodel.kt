package com.mithilakshar.mithilapanchang

class calendardatamodel {
    var date: String? = null
    var day: String? = null
    var desc: String? = null
    var itemWidth = 0

    constructor()
    constructor(date: String?, day: String?, desc: String?, itemWidth: Int) {
        this.date = date
        this.day = day
        this.desc = desc
        this.itemWidth = itemWidth
    }
}
