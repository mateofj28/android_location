package com.nico.unilocal

class User {
    var id: Int=0
    var name: String=""
    var lastname: String=""
    var email: String=""
    var password: String=""
    var city: String=""

    constructor(name: String, lastname: String, email: String, password: String, city: String) {
        this.name = name
        this.lastname = lastname
        this.email = email
        this.password = password
        this.city = city
    }
}