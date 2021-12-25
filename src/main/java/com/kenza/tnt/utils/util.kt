package com.kenza.tnt.utils

fun Thread.isRenderThread(): Boolean {
    return this.name == "Render thread"
}