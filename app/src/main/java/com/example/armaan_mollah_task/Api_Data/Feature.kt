package com.example.armaan_mollah_task.Api_Data

/**
 * Features class containing two attributes
 * id - Feature ID Number
 * points - is a list of points that contain latitude, longitude and acuracy information
 */
data class Feature(
    val id: Int,
    val points: List<Point>
)