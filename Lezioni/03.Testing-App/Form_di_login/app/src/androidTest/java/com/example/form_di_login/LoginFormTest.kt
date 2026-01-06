package com.example.form_di_login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFormTest {

    // ActivityScenarioRule lancia l'activity prima di ogni test e la chiude dopo
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLoginSuccess_ShowsWelcomeMessage() {
        // 1. AZIONE: Inserimento Username
        // Usiamo typeText e poi chiudiamo la tastiera per evitare che copra altri elementi
        onView(withId(R.id.et_username))
            .perform(typeText("tecnico_android"), closeSoftKeyboard())

        // 2. AZIONE: Inserimento Password
        onView(withId(R.id.et_password))
            .perform(typeText("secret123"), closeSoftKeyboard())

        // 3. AZIONE: Click sul pulsante
        onView(withId(R.id.btn_login)).perform(click())

        // 4. VERIFICA (Assertion):
        // Controlliamo che il messaggio di stato riporti "Successo"
        onView(withId(R.id.tv_status_message))
            .check(matches(withText("Benvenuto tecnico_android")))
    }
}