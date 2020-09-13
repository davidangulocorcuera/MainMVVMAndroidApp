package david.angulo.productsApp.modules.utils


import java.util.*
import java.util.regex.Pattern


object Valid {
    var EMAIL_REGEX = "[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}"
    var PHONE_REGEX = "^[5-9]{1}-? ?[0-9]{8}$"
    var DNI_REGEX = "(^[XYZ-xyz]\\d{7,8}|^\\d{8})[A-HJ-NP-TV-Za-hj-np-tv-z]$"
    var PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}\$"

    fun isEmailValid(string: String): Boolean {
        return isValid(
            string,
            EMAIL_REGEX
        )
    }

    fun isPasswordValid(string: String): Boolean {
        return isValid(
            string,
            PASSWORD_REGEX
        )
    }

    fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        val isValid =
                isPasswordValid(password)
                && isPasswordValid(confirmPassword)
                && confirmPassword == password

        return isValid
    }

    fun isNotNullOrEmpty(vararg strings: String?): Boolean {
        var isValid = false
        if (!strings.isEmpty()) {
            for (data in strings) {
                if (data != null) {
                    isValid = data.isNotEmpty() && data.isNotEmpty()
                    if (!isValid) {
                        break
                    }
                } else {
                    isValid = false
                    break
                }
            }
        }
        return isValid
    }

    fun isValid(string: String, regex: String): Boolean {
        var isValid = false
        if (isNotNullOrEmpty(string)) {
            isValid = checkRegex(string, regex)
        }
        return isValid
    }

    fun checkRegex(field: String, regex: String): Boolean {
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(field)
        return matcher.matches()
    }

    fun checkValidKilograms(string: String): Boolean {
        val data = Integer.parseInt(string)

        if (data in 100 downTo 20)
            return true

        return false
    }

    fun checkValidHeight(string: String): Boolean {
        val data = Integer.parseInt(string)

        if (data in 230 downTo 100)
            return true

        return false
    }

    fun isPhoneNumberValid(string: String): Boolean {
        return isValid(
            string,
            PHONE_REGEX
        )
    }

    fun isDniValid(string: String): Boolean {
        if (isNotNullOrEmpty(string) && string.length >= 8) {
            val dniLetters = "TRWAGMYFPDXBNJZSQVHLCKE"
            val numberStr = string.substring(0, 8)
            if (isNumber(numberStr)) {
                val number = Integer.parseInt(numberStr)
                val letter = dniLetters[number % 23]

                return isValid(
                    string,
                    DNI_REGEX
                ) && letter == string[8].toUpperCase()
            }
        }
        return false
    }

    fun isNieValid(string: String): Boolean {
        var nie = string
        if (isNotNullOrEmpty(nie)) {
            var letter = nie.substring(0, 1)

            if (letter.equals("X", ignoreCase = true)) {
                letter = "0"
            } else if (letter.equals("Y", ignoreCase = true)) {
                letter = "1"
            } else if (letter.equals("Z", ignoreCase = true)) {
                letter = "2"
            } else {
                return false
            }
            nie = letter + nie.substring(1, nie.length)

            return isValid(
                nie,
                DNI_REGEX
            )
        }
        return false
    }

    fun isNumber(value: String): Boolean {
        var isNumber = false
        if (isNotNullOrEmpty(value)) {
            try {
                java.lang.Double.parseDouble(value)
                isNumber = true
            } catch (e: Exception) {
                isNumber = false
            }

        }
        return isNumber
    }

    fun isNumber(data: Any): Boolean {
        if (data is String) {
            return isNumber(data)
        } else if (data is Int) {
            return true
        }
        return false
    }

    fun isCardNumberValid(number: String): Boolean {
        val newNumber = number.replace(" ", "")
        return isNumber(newNumber) && newNumber.length >= 14 && newNumber.length < 17
    }

    fun isValidCvc(cvc: String): Boolean {
        return isNumber(cvc) && cvc.length == 3
    }

    fun isValidExpirationDate(date: Date?): Boolean {
        if (date != null) {
            return date.after(Date()) || date == Date()
        } else {
            return false
        }
    }

}