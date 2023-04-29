package com.example.charepo

class LoginTracker {
    companion object {
        var userLoggedIn = false
        var currentUser: String? = null

        fun setUserLoggedInVal(boolean: Boolean) {
            userLoggedIn = boolean
        }

        fun getUserLoggedInVal(): Boolean {
            return userLoggedIn
        }

        fun setCurrentUserName(name: String) {
            currentUser = name
        }

        fun getCurrentUserName(): String {
            return currentUser.toString()
        }
    }
}