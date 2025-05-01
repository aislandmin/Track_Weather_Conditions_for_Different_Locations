package com.packt.XiaominGuo_COMP304Lab3_Ex1.navigation

sealed interface ContentType {
    object List : ContentType
    object ListAndDetail : ContentType
}