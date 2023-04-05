package com.kmt.passwordmanager.passwordgenerator

class PasswordGenerationUtil {
    companion object {
        fun generate(length: Int,
                     useLowerCaseChars: Boolean,
                     useUpperCaseChars: Boolean,
                     useDigits: Boolean,
                     useSpecialChars: Boolean) : String {
            val generators : ArrayList<() -> Char> = ArrayList()

            if (useLowerCaseChars) {
                generators.add { ('a'..'z').random() }
            }

            if (useUpperCaseChars) {
                generators.add { ('A'..'Z').random() }
            }

            if (useDigits) {
                generators.add { ('0'..'9').random() }
            }

            if (useSpecialChars) {
                generators.add { "!@#$%^&*()_-+=.,:;*".random() }
            }

            val password : StringBuilder = StringBuilder()

            repeat(length) {
                password.append(generators.random()())
            }

            return password.toString()
        }
    }
}