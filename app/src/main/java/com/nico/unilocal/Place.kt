package com.nico.unilocal

class Place {
    var id: Int=0
    var name: String=""
    var description: String=""
    var schedule: String=""
    var phone: String=""
    var category: String=""
    var comments: String=""
    var ratings: String=""
    var state: Int=0
    var lat: Double=0.0
    var lng: Double=0.0
    var moderator: User? =null

    constructor(
        name: String,
        description: String,
        schedule: String,
        phone: String,
        category: String,
        comments: String,
        ratings: String,
        lat: Double,
        lng: Double,
        state: Int,
        moderator: User?
    ) {
        this.name = name
        this.description = description
        this.schedule = schedule
        this.phone = phone
        this.category = category
        this.comments = comments
        this.ratings = ratings
        this.state = state
        this.moderator = moderator
        this.lat = lat
        this.lng = lng
    }
}