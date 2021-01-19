package com.example.motivation.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPrefs
import com.example.motivation.repositories.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var securityPrefs: SecurityPrefs
    private var filter: Int = MotivationConstants.PHRASES_FILTER.all

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        securityPrefs = SecurityPrefs(this)
        supportActionBar?.hide()

        val username = securityPrefs.getString(MotivationConstants.KEY.usernameKey)
        val greetingsText = "Olá, $username"
        tv_username.text = greetingsText

        handleIconsColorFilter(iv_all_texts.id)
        generateNewWord()

        bt_generate_new_text.setOnClickListener(this)
        bt_logout.setOnClickListener(this)

        iv_all_texts.setOnClickListener(this)
        iv_happy_texts.setOnClickListener(this)
        iv_good_morning_texts.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            bt_logout.id -> logout()

            bt_generate_new_text.id -> generateNewWord()

            iv_all_texts.id -> {
                handleIconsColorFilter(iv_all_texts.id)
                handleChangeWordFilter(MotivationConstants.PHRASES_FILTER.all)
            }

            iv_good_morning_texts.id -> {
                handleIconsColorFilter(iv_good_morning_texts.id)
                handleChangeWordFilter(MotivationConstants.PHRASES_FILTER.morning)
            }

            iv_happy_texts.id -> {
                handleIconsColorFilter(iv_happy_texts.id)
                handleChangeWordFilter(MotivationConstants.PHRASES_FILTER.happy)
            }

            else -> return
        }
    }

    private fun handleIconsColorFilter(id: Int) {
        iv_all_texts.setColorFilter(ContextCompat.getColor(this, R.color.white))
        iv_good_morning_texts.setColorFilter(ContextCompat.getColor(this, R.color.white))
        iv_happy_texts.setColorFilter(ContextCompat.getColor(this, R.color.white))

        when (id) {
            iv_all_texts.id -> iv_all_texts.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
            iv_good_morning_texts.id -> iv_good_morning_texts.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )

            iv_happy_texts.id -> iv_happy_texts.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.colorAccent
                )
            )
            else -> return
        }
    }

    private fun handleChangeWordFilter(filterId: Int) {
        filter = filterId
    }

    private fun generateNewWord() {
        val mock = Mock()
        tv_motivational_text.text = mock.getPhrase(filter)
    }

    private fun logout() {
        securityPrefs.removeString(MotivationConstants.KEY.usernameKey)
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }
}

