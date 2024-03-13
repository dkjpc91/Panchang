package com.mithilakshar.mithilapanchang

class mantradatamodel {
    var mantraDesc: String? = null
    var mantraName: String? = null
    var mantraImageurl: String? = null

    constructor()
    constructor(mantraDesc: String?, mantraName: String?, mantraImageurl: String?) {
        this.mantraDesc = mantraDesc
        this.mantraName = mantraName
        this.mantraImageurl = mantraImageurl
    }
}
