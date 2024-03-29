package com.federicobenedetti.agendaunivpm.ui.main.classes

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseUser

/**
 * Classe derivata da un Firebase User
 * Serve per tener traccia di tutti quei dati tecnici utili alla descrizione di un utente
 */
open class User {
    private var _logTAG = "USERCLASS"

    /**
     * Id dell'utente
     */
    private lateinit var id: String
    fun getId(): String? {
        return id
    }

    /**
     * La matricola dell'utente
     */
    private lateinit var matricola: String

    fun setMatricola(_matricola: String) {
        matricola = _matricola
    }

    fun getMatricola(): String? {
        return matricola
    }

    /**
     * Numero di telefono dell'utente
     */
    private var phoneNumber: Int = 0
    fun setPhoneNumber(_phone: Int) {
        phoneNumber = _phone
    }

    fun getPhoneNumber(): Int {
        return phoneNumber
    }

    /**
     * Anno universitario dell'utente
     */
    private var courseYear: Int = 0
    fun setCourseYear(_year: Int) {
        courseYear = _year
    }

    fun getCourseYear(): Int {
        return courseYear
    }


    /**
     * Nome (o meglio username) dell'utente
     */
    private lateinit var displayName: String
    fun getDisplayName(): String? {
        return displayName
    }

    /**
     * Email associata all'utente
     */
    private lateinit var email: String
    fun getEmail(): String? {
        return email
    }

    /**
     * URI della foto associata all'utente
     */
    private lateinit var photoUrl: Uri
    fun getPhotoUrl(): Uri? {
        return photoUrl
    }

    /**
     * Situazione tasse dell'utente loggato
     */
    private lateinit var situazioneTasse: String

    fun setSituazioneTasse(situazione: Boolean) {
        if (situazione) {
            this.situazioneTasse = "regolare"
        } else {
            this.situazioneTasse = "da pagare!"
        }
    }

    fun getSituazioneTasse(): String {
        return this.situazioneTasse
    }

    /**
     * Firebase ci ritorna un FirebaseUser
     * Noi lo dobbiamo parsare per poterlo trasformare in qualcosa che la nostra View può capire
     */
    fun parseUserFromGoogleSignIn(user: FirebaseUser?) {
        displayName = user?.displayName.toString()
        email = user?.email.toString()
        id = user?.uid.toString()
        photoUrl = user?.photoUrl!!

        Log.w(_logTAG, "Parsed user: " + displayName + ", " + email + ", " + id + ", " + photoUrl)
    }
}
