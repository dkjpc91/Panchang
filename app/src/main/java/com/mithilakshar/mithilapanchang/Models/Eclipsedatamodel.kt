package com.mithilakshar.mithilapanchang.Models

class eclipsedatamodel{
var  eclipseName: String? =null
var eclipseDesc: String? =null
var  imageUrl: String?=null

constructor()
constructor(eclipseDesc: String?, eclipseName: String?, imageUrl: String?) {
    this.eclipseDesc = eclipseDesc
    this.eclipseName = eclipseName
    this.imageUrl = imageUrl
}

}