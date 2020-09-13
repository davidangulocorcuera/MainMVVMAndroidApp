package david.angulo.productsApp.modules.utils


import java.nio.charset.StandardCharsets
import java.security.Key
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


object CryptoUtils {
    private var desCipher: Cipher? = null


    fun initializeCipher() {
        try {
            desCipher = Cipher.getInstance("AES")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Encode
     * */

    fun encodeString(message: String, key: Key): ByteArray? = try {
        desCipher!!.init(Cipher.ENCRYPT_MODE, key)
        desCipher!!.doFinal(message.toByteArray(StandardCharsets.UTF_8))
    } catch (e: Exception) {
        null
    }

    /**
     * Decode
     * */

    fun decodeString(message: ByteArray, key: Key): String? = try {
        desCipher!!.init(Cipher.DECRYPT_MODE, key)
        String(desCipher!!.doFinal(message))
    } catch (e: Exception) {
        e.message
    }

    /**
     * Generate key
     **/

    fun generateKey(): Key {
        var keygen: KeyGenerator? = null
        try {
            keygen = KeyGenerator.getInstance("AES")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return keygen!!.generateKey()
    }

    fun generateHashFromString(string: String): String {
        val hash = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            .generateSecret(PBEKeySpec(CharArray(string.length), string.toByteArray(), 6700, 128))
            .encoded
        return byteArrayToHexString(hash)
    }

    private fun byteArrayToHexString(array: ByteArray): String {
        val strBuilder = StringBuilder("")
        array.forEach {
            strBuilder.append(it)
        }
        return strBuilder.toString()
    }


}