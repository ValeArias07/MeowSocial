package com.example.mysocialnetwork

import java.nio.file.Paths.get

class User {
    lateinit var name: String
        get
    lateinit var email: String
        get
    lateinit var password: String
        get

    constructor(email: String, password: String, name: String) {
        this.email = email
        this.password = password
        this.name = name
    }
}
