package dev.ajinkyajape.kmpilot

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform