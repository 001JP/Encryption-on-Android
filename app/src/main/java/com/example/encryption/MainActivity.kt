package com.example.encryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cryptoManager = CryptoManager()

        var messageToEncrypt = "Encrypt me"
        var messageToDecrypt = ""

        //Encryption start
        val bytes = messageToEncrypt.encodeToByteArray()
        val file = File(filesDir, "secret.txt")
        if (!file.exists()){
            file.createNewFile()
        }
        val fos = FileOutputStream(file)

        messageToDecrypt = cryptoManager.encrypt(
            bytes,
            fos
        ).decodeToString()
        Log.i(TAG, "Encrypted String: $messageToDecrypt")


        //Decrypt start
        val fileDecrypt = File(filesDir,"secret.txt")
        messageToEncrypt = cryptoManager.decrypt(
            inputStream = FileInputStream(fileDecrypt)
        ).decodeToString()
        Log.i(TAG, "Decrypted String: $messageToEncrypt")

    }

    companion object{
        private const val TAG = "MainActivity"
    }

}