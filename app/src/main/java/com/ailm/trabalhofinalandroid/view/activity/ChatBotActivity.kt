package com.ailm.trabalhofinalandroid.view.activity

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ailm.trabalhofinalandroid.R
import com.ailm.trabalhofinalandroid.di.HerokuDialogFlow
import com.ailm.trabalhofinalandroid.domain.Message
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_chat_bot.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChatBotActivity : AppCompatActivity() {

    @Inject
    lateinit var dialogFlow: HerokuDialogFlow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
        btSend.setOnClickListener { send() }
    }

    private fun send(){

        GlobalScope.launch(Dispatchers.Main){
            val question = etQuestion.text.toString()
            val message = Message(question, "", "123")
            val response = dialogFlow.sendMessageAsync(message)
            if (response.isNotEmpty()) {
                tvResponse.text = response[0].queryResult.fulfillmentText
                etQuestion.setText("")
            }
        }

    }

}
