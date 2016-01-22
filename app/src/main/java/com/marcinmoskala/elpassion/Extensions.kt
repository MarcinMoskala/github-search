package com.marcinmoskala.elpassion

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.longToast

infix fun Any.theSameClass(other: Any): Boolean = (this.javaClass == other.javaClass)

public val Context.picasso: Picasso
    get() = Picasso.with(this)

public val gson: Gson
    get() = Gson()