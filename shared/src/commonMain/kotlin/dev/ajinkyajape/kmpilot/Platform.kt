package dev.ajinkyajape.kmpilot

expect class Platform{
    val osName: String
    val osVersion: String
    val deviceModel: String
    val density: Int

    fun getDeviceInfo()

}