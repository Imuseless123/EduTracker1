package screens.mainUserScreen.Screens.classList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Class
import model.StudentClassJoinedStudentRecord
import model.StudentRecord
import model.SupabaseModel

data class studentClassInfo(
    var studentClassInfo: MutableList<StudentClassJoinedStudentRecord>
){
    companion object{
        private lateinit var instance:studentClassInfo

        fun initStudentClassInfo(classId:String){
            val studentClassInfo = mutableListOf<StudentClassJoinedStudentRecord>()

            runBlocking {
                launch {
                    studentClassInfo.addAll(SupabaseModel.studentClassJoinedStudentRecordQuery(classId = classId))
                }
            }

            instance = studentClassInfo(studentClassInfo)
        }

        fun getStudentClassInfo(): MutableList<StudentClassJoinedStudentRecord>{
            return instance.studentClassInfo
        }
    }
}

@Composable
fun student_class(id:String, name: String,viewStudentClass: (s:Boolean) -> Unit){
    val addStudent = remember { mutableStateOf(false)}
    studentClassInfo.initStudentClassInfo(id)
//    runBlocking {
//        launch {
//            SupabaseModel.join()
//        }
//    }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(0.9f)
            .background(color = Color.White)
            .align(Alignment.Center)){
            Box(){
                Row(modifier = Modifier.background(color = Color(0xFFdbe4e6))
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(start = 15.dp, end = 15.dp)){
                    Box(modifier = Modifier.align(Alignment.CenterVertically)
                        .fillMaxWidth(0.1f)){
                        Image(painter = painterResource("drawable/arrow-left.svg"),
                              contentScale = ContentScale.Fit,
                              contentDescription = null,
                              modifier = Modifier
                                  .align(Alignment.CenterEnd)
                                  .size(20.dp)
                                  .clickable {
                                        viewStudentClass(false)
                                  })
                    }
                    Text(text = name,
                         modifier = Modifier.align(Alignment.CenterVertically)
                             .fillMaxWidth(0.5f))
                }
            }
            
            Box(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.87f)){

            }
            
            if(addStudent.value == false){
                Box(){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFdbe4e6))
                        .clickable { addStudent.value =true }){
                        Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                            Image(painter = painterResource("drawable/user-add.svg"),
                                  contentScale = ContentScale.Fit,
                                  contentDescription = null,
                                  modifier = Modifier.align(Alignment.CenterVertically)
                                      .size(20.dp)
                                      .fillMaxWidth(0.5f))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Add student",
                                 modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
            }
            
            
        }
    }
}