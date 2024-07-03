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
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


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

    private val _course = mutableStateOf(Course())
    val course: State<Course> = _course

    private val _word = mutableStateOf(RandomWord())
    val word: State<RandomWord> = _word

    private val _theme = mutableStateOf(Theme())
    val theme: State<Theme> = _theme
    init{
        fetchLimit()
    }
    init{
        getObstacleCourseAgain()
    }
    fun getObstacleCourseAgain(){
        fetchObstacleCourse()
    }

    private fun fetchLimit(){
        viewModelScope.launch {
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

            //TO GET A RANDOM WORD FOR COLLECTION
            try{
                val request2 = RandomWordRequest(listOf(5,6,7,8,9,10).random())
                val response7= obstacleRetrofit.getWord(request2)
                _word.value=_word.value.copy(
                    word=response7.word,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _word.value=_word.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }

            //TO GET THE THEME

            try{
                val request3 = ThemeRequest(date = LocalDate.now().toString(),
                    time = LocalTime.now().withNano(0).toString()
                )
                val response8 = obstacleRetrofit.getTheme(request3)

                _theme.value=_theme.value.copy(
                    theme = response8.theme,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _theme.value=_theme.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }


        }
    }
    private fun fetchObstacleCourse(){
        viewModelScope.launch {

            //TO POST EXTENT VALUE AND GET LIST OF OBSTACLE TRACK
            try{
                val request1 = ObstacleCourseRequest(extent.value)
                val response6= obstacleRetrofit.getObstacleCourse(request1)
                _course.value=_course.value.copy(
                    course=response6.obstacleCourse,
                    loading = false,
                    error = null
                )
            }catch(e:Exception){
                _course.value=_course.value.copy(
                    loading = false,
                    error = "ERROR message ${e.message}"
                )
            }
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

    data class Course(
        var course:List<String>? =null,
        var loading:Boolean=true,
        var error: String?=null
    )

    data class RandomWord(
            var word:String?=null,
            var loading:Boolean=true,
            var error: String?=null
            )
    data class Theme(
        val theme:String="",
        var loading:Boolean=true,
        var error: String?=null
    )








