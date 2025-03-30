package com.example.jobsearchapplication.data.dataSources.remote.retrofit.datamodel

data class Location(
    val display_name: String,
    val area: List<String>,
    val latitude: Double?,
    val longitude: Double?
)