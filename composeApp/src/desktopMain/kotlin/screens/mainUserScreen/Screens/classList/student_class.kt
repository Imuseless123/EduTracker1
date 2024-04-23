package screens.mainUserScreen.Screens.classList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.*
import androidx.compose.material.TextField

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

data class studentAddList(
    var studentAddList: MutableList<StudentRecord>,
    var studentAdd: MutableList<String>
){
    companion object{
        private lateinit var instance: studentAddList

        fun initStudetnAddList(){
            var studentAddList = mutableListOf<StudentRecord>()

            runBlocking {
                launch {
                    studentAddList.addAll(SupabaseModel.studentClassJoinedStudentRecordQuery1())
                }
            }

            var studentAdd = mutableListOf<String>()

            studentAddList.forEach {
                item -> studentAdd.addLast(item.id.toString() + ' ' + item.name)
            }

            instance = studentAddList(studentAddList,studentAdd)
        }

        fun getStudentAddList(): MutableList<StudentRecord>{
            return instance.studentAddList
        }

        fun getStudentAdd(): MutableList<String>{
            return instance.studentAdd
        }

        fun getStudentAddi(index: Int): String{
            return instance.studentAdd[index]
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun student_class(id:String, name: String,viewStudentClass: (s:Boolean) -> Unit){
    val addStudent = remember { mutableStateOf(false)}
    val studentList:MutableList<String> = mutableListOf()
    var studentAddId = remember {mutableStateOf(0)}
    studentClassInfo.initStudentClassInfo(id)
    studentAddList.initStudetnAddList()

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
                    Box(modifier = Modifier.align(Alignment.CenterVertically)
                        .fillMaxWidth()){
                        Text(text = name,
                             modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            
            Box(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.87f)){
                LazyColumn {
                    items(studentClassInfo.getStudentClassInfo()){
                        Box(modifier = Modifier.height(50.dp)){
                            recordRow(it.student_id.id.toString(),it.student_id.name, it.student_id.gender.toString(),it.student_id.date)
                            studentList.add(it.student_id.id.toString())
                        }
                    }
                }
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
            
            if(addStudent.value==true){
                Box(){
                    Row(modifier = Modifier.background(color = Color(0xFFdbe4e6))
                        .fillMaxSize()
                        .padding( end = 15.dp)){
                        Image(painter = painterResource("drawable/add.svg"),
                              contentScale = ContentScale.Fit,
                              contentDescription = null,
                              modifier = Modifier.align(Alignment.CenterVertically)
                                  .fillMaxWidth(0.08f)
                                  .size(20.dp)
                                  .clickable {
                                      addStudent.value=false
                                      SupabaseModel.addStudentId(studentAddId,id,studentList)
                                  })

                        var expanded by remember { mutableStateOf(false) }
                        var selectedText by remember {mutableStateOf("student")}

                        Box(modifier = Modifier.align(Alignment.CenterVertically)
                            .fillMaxWidth()){
                            ExposedDropdownMenuBox(modifier = Modifier.align(Alignment.Center),
                                                   expanded = expanded,
                                                   onExpandedChange = {expanded = !expanded}
                            ){
                                TextField(
                                    value = selectedText,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    colors = TextFieldDefaults.textFieldColors(
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        backgroundColor = Color.Transparent)
                                )

                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    studentAddList.getStudentAdd().forEach { item ->
                                        DropdownMenuItem(
                                            onClick = {
                                                selectedText = item
                                                studentAddId.value = selectedText[0].code-48
                                                println(studentAddId)
                                                expanded = false
                                            }
                                        ){
                                            Text(text = item)
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun recordRow(ID: String, Name: String, Gender: String, DOB: String){
    Box(){
        Row(modifier = Modifier.background(color = Color.White)
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)){
            Text(text = ID,
                 modifier = Modifier.align(Alignment.CenterVertically)
                     .fillMaxWidth(0.1f))
                Text(text = Name,
                     modifier = Modifier.align(Alignment.CenterVertically)
                         .fillMaxWidth(0.5f))
                Text(text = Gender,
                     modifier = Modifier.align(Alignment.CenterVertically)
                         .fillMaxWidth(0.5f))
                Text(text = DOB,
                     modifier = Modifier.align(Alignment.CenterVertically)
                         .fillMaxWidth(0.4f))
                Box(modifier = Modifier.align(Alignment.CenterVertically)
                    .fillMaxWidth()){
                    Image(painter = painterResource("drawable/trash.svg"),
                          contentScale = ContentScale.Fit,
                          contentDescription = null,
                          modifier = Modifier
                              .align(Alignment.CenterEnd)
                              .size(20.dp)
                              .clickable {
                                  SupabaseModel.deleteStudentClass(id = ID)
                              })
                }
        }
    }
}