package com.federicobenedetti.agendaunivpm.ui.main.singletons

import android.content.Context
import android.content.Intent
import com.federicobenedetti.agendaunivpm.ui.main.activities.LoginActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.ktx.functions
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

/**
 * Singleton utilizzato per mantenere un'istanza unica
 * di vari agganci alla parte di Firebase.
 */
object FirebaseUtils {
    // Unica istanza dell'auth di Firebase
    private var mFirebaseAuth: FirebaseAuth? = null

    // Unica istanza delle Firebase Functions
    private var mFirebaseFunctions: FirebaseFunctions? = null

    // Unica istanza di Firebase Storage
    private var mFirebaseStorage: FirebaseStorage? = null

    private var mStorageRef: StorageReference? = null

    private var mGoogleSignInClient: GoogleSignInClient? = null

    var mStreamingPath: String =
        "https://firebasestorage.googleapis.com/v0/b/agenda-univpm.appspot.com/o/01.mp4?alt=media&token=0836eb52-290b-4f23-86dd-4379d40b0ec0"

    // Mi serve per capire se l'auth listener è stato impostato (dalla Login Activity)
    // Altrimenti si rischia un loop
    private var authListenerSet: Boolean = false

    init {
        initFirebase()
    }

    fun initFirebase() {
        mFirebaseAuth = FirebaseAuth.getInstance()
        mFirebaseFunctions = Firebase.functions
        mFirebaseStorage = Firebase.storage
        mStorageRef = mFirebaseStorage!!.getReferenceFromUrl(mStreamingPath)
        mGoogleSignInClient = null
    }

    fun setGoogleSignInClient(client: GoogleSignInClient) {
        mGoogleSignInClient = client
    }

    fun getGoogleSignInClientIntent(): Intent {
        return mGoogleSignInClient!!.signInIntent
    }

    /**
     * Imposto l'authListener dalla LoginActivity
     * Questo mi permette di ricaricarla se l'utente dovesse fare il logout
     */
    fun setAuthStateListener(context: Context) {
        if (authListenerSet == false) {
            mFirebaseAuth!!.addAuthStateListener {
                if (mFirebaseAuth!!.currentUser == null) {
                    ActivityUtils.launchActivity(context, LoginActivity::class)
                }
            }
        }

        authListenerSet = true
    }

    /**
     * Ritorno l'unica istanza disponibile per l'auth di Firebase
     */
    fun getFirebaseAuthInstance(): FirebaseAuth? {
        return mFirebaseAuth
    }

    fun signOut() {
        mFirebaseAuth!!.signOut()
        mGoogleSignInClient!!.signOut()
    }

    /**
     * Ritorno l'unica istanza disponibile per le Firebase Functions
     */
    fun getFirebaseFunctionsInstance(): FirebaseFunctions? {
        return mFirebaseFunctions
    }
}