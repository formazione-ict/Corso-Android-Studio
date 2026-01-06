package com.example.form_di_login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    // Regola per avviare l'Activity prima di ogni test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loginFlow_Success() {
        // 1. Inserimento dati validi
        onView(withId(R.id.et_username)).perform(typeText("utente_tecnico"), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(typeText("password123"), closeSoftKeyboard())

        // 2. Esecuzione click
        onView(withId(R.id.btn_login)).perform(click())

        // 3. Verifica asincrona
        // Poiché nella MainActivity abbiamo un delay di 1.5s, dobbiamo gestire l'attesa.
        // In un ambiente di produzione useresti IdlingResource. Per ora, verifichiamo la comparsa:
        Thread.sleep(2000) // Solo per debug rapido, vedi nota tecnica sotto
        onView(withId(R.id.tv_status_message))
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("Benvenuto utente_tecnico"))))
    }

    @Test
    fun loginFlow_Error_PasswordTooShort() {
        // Testiamo il ramo di errore della validazione
        onView(withId(R.id.et_username)).perform(typeText("user"), closeSoftKeyboard())
        onView(withId(R.id.et_password)).perform(typeText("123"), closeSoftKeyboard())

        onView(withId(R.id.btn_login)).perform(click())

        // Verifica che il TextInputLayout mostri l'errore corretto
        // Approfondimento tecnico: l'errore non è nella EditText ma nel TextInputLayout genitore
        onView(withId(R.id.til_password))
            .check(matches(hasDescendant(withText("Password troppo corta (min 6 caratteri)"))))
    }
}