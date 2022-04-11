package com.example.mysocialnetwork.model

import java.io.File
import java.nio.file.Paths.get

class User {
     var default: Boolean = true
         get
     var name: String
        get
    lateinit var email: String
        get
    lateinit var password: String
        get
    lateinit var pic: String
        get

    constructor(email: String, password: String, name: String) {
        this.email = email
        this.password = password
        this.name = name
        this.default = true
        this.pic= "hola"
    }
    constructor(email: String, password: String, name: String, pic: String) {
        this.email = email
        this.password = password
        this.name = name
        this.pic = pic
        this.default = true
    }

    fun setingName(name: String){
        this.name = name
    }
}

