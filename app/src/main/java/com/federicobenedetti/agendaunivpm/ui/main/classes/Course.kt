package com.federicobenedetti.agendaunivpm.ui.main.classes

/**
 * Classe che descrive il corso e il suo dettaglio
 */
open class Course {
    private var _logTAG = "COURSECLASS"

    /**
     * Id del corso
     */
    lateinit var id: String

    /**
     * Titolo del corso
     */
    lateinit var title: String

    /**
     * Breve descrizione del corso, visualizzabile dalle liste
     */
    lateinit var shortDescription: String

    /**
     * Descrizione del corso per esteso, visualizzabile dal dettaglio
     */
    lateinit var description: String

    /**
     * Anno accademico del corso
     */
    lateinit var session: String

    /**
     * Professore che tiene il corso
     */
    lateinit var teacherId: String

    /**
     * L'ora nella quale il corso verrà tenuto
     */
    lateinit var bookedHour: String

    lateinit var lessonIds: ArrayList<String>

    constructor()

    constructor(
        _id: String,
        _title: String,
        _shortDescription: String,
        _description: String,
        _session: String,
        _teacherId: String,
        _bookedHour: String,
        _lessonIds: ArrayList<String>,
    ) {
        id = _id
        title = _title
        shortDescription = _shortDescription
        description = _description
        session = _session
        teacherId = _teacherId
        bookedHour = _bookedHour
        lessonIds = _lessonIds
    }

}