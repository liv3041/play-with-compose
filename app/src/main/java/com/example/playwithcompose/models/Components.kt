package com.example.playwithcompose.models

data class Components (
    val type:String,
//    For buttons
    val label: String? = null,
//    Actions for buttons
    val action: String? = null,
//    For text
    val text: String? = null,
//    For images
    val url:String? = null

)