package com.example.delta_task2




import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.http.Query


class ApiInteraction : ViewModel() {
    private val _obstacleCount = mutableStateOf(ObstacleState())
    val obstacleCount: State<ObstacleState> = _obstacleCount
    init{
        fetchLimit()
    }

    private fun fetchLimit(){
        viewModelScope.launch {
            Log.d(1.toString(),"StartedFetching")
            try{
                val response1= obstacleRetrofit.getObstacleLimit()
                _obstacleCount.value=_obstacleCount.value.copy(
                    limit=response1.obstacleLimit,
                    loading = false,
                    error = null
                )


            }catch (e: Exception){
                _obstacleCount.value=_obstacleCount.value.copy(
                    loading = false,
                    error="Error is ${e.message}"
                )
            }
        }
    }

    data class ObstacleState(
        val limit: Int=2,
        val loading: Boolean = true,
        val error: String? = null
    )


}







