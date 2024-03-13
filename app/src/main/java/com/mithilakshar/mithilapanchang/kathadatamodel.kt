package com.mithilakshar.mithilapanchang

class kathadatamodel {
    var kathaName: String? = null
    var kathaStory: String? = null
    var kathaUrl: String? = null

    constructor()
    constructor(kathaName: String?, kathaStory: String?, kathaUrl: String?) {
        this.kathaName = kathaName
        this.kathaStory = kathaStory
        this.kathaUrl = kathaUrl
    }
}
