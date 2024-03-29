package screens.mainUserScreen.Screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Class
import model.StudentRecord
import model.SupabaseModel

data class profileInfo(
    var nOfStudent: Int,
    val nOfClass: Int
){
    companion object {
        private lateinit var instance: profileInfo
        fun initProfileValue(){
            //khởi tạo giá trị khi gọi
            val countStudent = mutableListOf<StudentRecord>()
            val countClass = mutableListOf<Class>()
            runBlocking {
                launch {
                    countStudent.addAll(SupabaseModel.studentQuery())
                    countClass.addAll(SupabaseModel.classQuery())
                }
            }
            instance = profileInfo(countStudent.size, countClass.size)
        }
        fun getNOfStudent(): Int{
            //trả về nOfStudent
            return instance.nOfStudent
        }
        fun getNOfClass(): Int{
            //trả về nOfClass
            return  instance.nOfClass
        }
    }
}

@Composable
fun profile(){
    profileInfo.initProfileValue()

    Box(modifier = Modifier.fillMaxSize()){
        Box(modifier = Modifier.fillMaxHeight(0.9f)
            .fillMaxWidth(0.9f)
            .align(Alignment.Center)
            .background(color = Color.White)) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.7f)
                    .align(Alignment.BottomCenter)
                    .background(color = Color(0xFF003547))
            ) {
                Column(modifier = Modifier.fillMaxSize()
                    .align(Alignment.Center)){

                    Spacer(Modifier.fillMaxHeight(0.4f))

                    Text(
                        text = "User",
                        color = Color.White,
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(1.dp)
                        .background(color = Color.Yellow))

                    Row(modifier = Modifier.fillMaxWidth()){
                        Box(modifier = Modifier.fillMaxHeight()
                            .fillMaxWidth(0.5f)){
                            Column(modifier = Modifier.align(Alignment.Center)){
                                Text(text = "Students",
                                     color = Color.White,
                                     )

                                Spacer(Modifier.height(10.dp))

                                Text(text = profileInfo.getNOfStudent().toString(),
                                     color = Color.White,
                                     fontSize = 18.sp,
                                    modifier = Modifier.align(Alignment.CenterHorizontally))
                            }
                        }
                        Box(modifier = Modifier.fillMaxHeight()
                            .fillMaxWidth()){
                            Column(modifier = Modifier.align(Alignment.Center)){
                                Text(text = "Classes",
                                     color = Color.White,
                                    )
                                Spacer(Modifier.height(10.dp))

                                Text(text = profileInfo.getNOfClass().toString(),
                                     color = Color.White,
                                     fontSize = 18.sp,
                                     modifier = Modifier.align(Alignment.CenterHorizontally))
                            }
                        }
                    }
                }
            }

            Box(modifier = Modifier.fillMaxSize()){
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(Modifier.fillMaxHeight(0.15f))
                    Box(modifier = Modifier
                            .size(150.dp)
                            .align(Alignment.CenterHorizontally)
//                            .background(Color.Black)
                            .clip(CircleShape)
                    ){
                        Image(painter = painterResource("drawable/user.svg"),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                              modifier = Modifier.background(color = Color(0xFFe4dfff))
                                  .padding(10.dp))
                    }
                }
            }
        }
    }
}