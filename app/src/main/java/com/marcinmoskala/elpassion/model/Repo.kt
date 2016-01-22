package com.marcinmoskala.elpassion.model

data class Repo(val id: Int, val name: String = "", val full_name: String = "", val owner: User) {}
