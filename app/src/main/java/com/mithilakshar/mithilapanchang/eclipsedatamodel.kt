package com.mithilakshar.mithilapanchang

class eclipsedatamodel {
    var eclipseName: String? = null
    var eclipseDesc: String? = null
    var imageUrl: String? = null

    constructor()
    constructor(eclipseName: String?, eclipseDesc: String?, imageUrl: String?) {
        this.eclipseName = eclipseName
        this.eclipseDesc = eclipseDesc
        this.imageUrl = imageUrl
    }
}
