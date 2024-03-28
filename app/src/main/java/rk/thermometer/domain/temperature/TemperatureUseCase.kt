package rk.thermometer.domain.temperature

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TemperatureUseCase @Inject constructor(private val temperatureRepository: TemperatureRepository) {
    fun getTemperature(): Flow<String> {
        //return temperatureRepository.getTemperature()
        return callbackFlow {
            val ref = Firebase.database.getReference("temperature")
            val listener = ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    trySend(snapshot.value.toString())
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            awaitClose { ref.removeEventListener(listener) }
        }
    }
}