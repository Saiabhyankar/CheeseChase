package com.example.delta_task2




import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.R
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.InputStream


class ApiInteraction : ViewModel() {
    private val _obstacleCount = mutableStateOf(ObstacleState())
    val obstacleCount: State<ObstacleState> = _obstacleCount

    private val _imageTom = mutableStateOf(ImageTomState())
    val imageTom: State<ImageTomState> = _imageTom

    private val _imageJerry = mutableStateOf(ImageJerryState())
    val imageJerry: State<ImageJerryState> = _imageJerry


    private val _imageObstacle = mutableStateOf(ImageObstacleState())
    val imageObstacle: State<ImageObstacleState> = _imageObstacle

    private val _rewardPunish = mutableStateOf(RewardPunishment())
    val rewardPunish: State<RewardPunishment> = _rewardPunish

//    private val _imageObstacle = mutableStateOf(ImageObstacleState())
//    val imageObstacle: State<ImageObstacleState> = _imageObstacle
    init{
        fetchLimit()
    }

    private fun fetchLimit(){
        viewModelScope.launch {
            Log.d(1.toString(),"StartedFetching")
            //TO GET THE OBSTACLE LIMIT
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
            //TO GET THE IMAGE OF TOM
            try{
                val response2:ResponseBody= obstacleRetrofit.getTom("tom")
                val inputStream:InputStream=response2.byteStream()
                val bitmap:Bitmap=BitmapFactory.decodeStream(inputStream)
                _imageTom.value=_imageTom.value.copy(
                    bitmap=bitmap,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _imageTom.value=_imageTom.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }
            // TO GET THE IMAGE OF JERRY

            try{
                val response3:ResponseBody= obstacleRetrofit.getTom("jerry")
                val inputStream:InputStream=response3.byteStream()
                val bitmap:Bitmap=BitmapFactory.decodeStream(inputStream)
                _imageJerry.value=_imageJerry.value.copy(
                    bitmap=bitmap,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _imageJerry.value=_imageJerry.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }
            //TO GET THE IMAGE OF OBSTACLE


            try{
                val response4:ResponseBody= obstacleRetrofit.getTom("obstacle")
                val inputStream:InputStream=response4.byteStream()
                val bitmap:Bitmap=BitmapFactory.decodeStream(inputStream)
                _imageObstacle.value=_imageObstacle.value.copy(
                    bitmap=bitmap,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _imageObstacle.value=_imageObstacle.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }
            //TO GET THE REWARDS/PUNISHMENT AFTER HITTING AN OBSTACLE
            try{
                val response5= obstacleRetrofit.getRewardPunish()
                _rewardPunish.value=_rewardPunish.value.copy(
                    type = response5.type,
                    amount = response5.amount,
                    process = response5.description,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _rewardPunish.value=_rewardPunish.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }



        }
    }

    data class ObstacleState(
        val limit: Int=2,
        val loading: Boolean = true,
        val error: String? = null
    )
    data class ImageTomState(
        val bitmap: Bitmap? = null,
        val loading: Boolean = true,
        val error: String? = null
    )

    data class ImageJerryState(
        val bitmap: Bitmap? = null,
        val loading: Boolean = true,
        val error: String? = null
    )

    data class ImageObstacleState(
        val bitmap: Bitmap? = null,
        val loading: Boolean = true,
        val error: String? = null
    )

    data class RewardPunishment(
        val type:Int=0,
        val amount:Int=0,
        val process:String="",
        val loading:Boolean=true,
        val error:String?=null
    )

}







