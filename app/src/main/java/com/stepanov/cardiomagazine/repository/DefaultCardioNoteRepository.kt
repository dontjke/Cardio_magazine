package com.stepanov.cardiomagazine.repository

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.stepanov.cardiomagazine.domain.CardioNote
import com.stepanov.cardiomagazine.domain.RemoteCardioNote
import com.stepanov.cardiomagazine.utils.CARDIO_NOTE_LIST
import java.util.Calendar

class DefaultCardioNoteRepository(
    private val firestoreDatabase: FirebaseFirestore = FirestoreDatabase().firestoreDatabase
) : CardioNoteRepository {
    var taskList: List<RemoteCardioNote> = listOf()

    override fun addCardioNote(cardioNote: CardioNote) {
        val cardioData = HashMap<String, Any>()
        cardioData["type"] = cardioNote.type
        cardioData["time"] = cardioNote.time
        cardioData["systolicPressure"] = cardioNote.systolicPressure
        cardioData["diastolicPressure"] = cardioNote.diastolicPressure
        cardioData["pulse"] = cardioNote.pulse
        firestoreDatabase.collection(CARDIO_NOTE_LIST)
            .add(cardioData)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

    override fun getCardioNotes(listener: (List<CardioNote>) -> Unit) {
        firestoreDatabase.collection(CARDIO_NOTE_LIST)
            .get()
            .addOnSuccessListener { querySnapshot ->
                taskList = querySnapshot.toObjects(RemoteCardioNote::class.java)
                listener.invoke(taskList.map(::mapToCardioNote))
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

    fun getLocalCardioNotes() = taskList.map(::mapToCardioNote)

    private fun mapToCardioNote(remoteCardioNote: RemoteCardioNote): CardioNote {
        return CardioNote(
            type = remoteCardioNote.type,
            time = remoteCardioNote.time,
            systolicPressure = remoteCardioNote.systolicPressure,
            diastolicPressure = remoteCardioNote.diastolicPressure,
            pulse = remoteCardioNote.pulse
        )
    }


}