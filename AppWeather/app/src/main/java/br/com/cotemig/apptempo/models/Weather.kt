package br.com.cotemig.apptempo.models

data class Weather(
    var temp: Double = 0.0,
    var temp_min: Double = 0.0,
    var temp_max: Double = 0.0,
    var pressure: Double = 0.0,
    var humidity: Double = 0.0
)