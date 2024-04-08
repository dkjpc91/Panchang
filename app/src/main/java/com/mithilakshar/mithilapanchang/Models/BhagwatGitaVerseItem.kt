package com.mithilakshar.mithilapanchang.Models

data class BhagwatGitaVerseItem(
    val chapter_number: Int,
    val commentaries: List<Commentary>,
    val id: Int,
    val text: String,
    val translations: List<Translation>,
    val verse_number: Int,
    val maithili_verse: String
)