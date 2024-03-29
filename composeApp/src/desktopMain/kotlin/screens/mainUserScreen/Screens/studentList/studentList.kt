package screens.mainUserScreen.Screens.studentList

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
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.StudentRecord
import model.SupabaseModel
import model.SupabaseModel.Companion.addStudent
import screens.mainUserScreen.Screens.profile.profileInfo

data class studentInfo(
    var info: MutableList<StudentRecord>
){
    companion object{
        private lateinit var instance: studentInfo

        fun initStudentInfo(){
            val studentInfo = mutableListOf<StudentRecord>()

            runBlocking {
                launch {
                    studentInfo.addAll(SupabaseModel.studentQuery())
                }
            }

            instance= studentInfo(studentInfo)
        }

        fun getStudentInfo(): MutableList<StudentRecord> {
            return instance.info
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun studentList(){
    val addStudent = remember { mutableStateOf(false)}
    studentInfo.initStudentInfo()

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxWidth(0.9f)
            .fillMaxHeight(0.9f)
            .background(color = Color.White)
            .align(Alignment.Center)){

                // list meta data row
            Box(){
                Row(modifier = Modifier.background(color = Color(0xFFdbe4e6))
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(start = 15.dp, end = 15.dp)){
                    Text(text = "ID",
                        modifier = Modifier.align(Alignment.CenterVertically)
                            .fillMaxWidth(0.1f))
                    Text(text = "Name",
                         modifier = Modifier.align(Alignment.CenterVertically)
                             .fillMaxWidth(0.5f))
                    Text(text = "Gender",
                         modifier = Modifier.align(Alignment.CenterVertically)
                             .fillMaxWidth(0.5f))
                    Text(text = "Year of birth",
                         modifier = Modifier.align(Alignment.CenterVertically))
                }
            }

                //student list
            Box(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.87f)){
                LazyColumn {
                    items(studentInfo.getStudentInfo()){
                        Box(modifier = Modifier.height(50.dp)){
                            recordRow(it.id.toString(),it.name, if(it.gender) {"male"} else{"female"},it.date)
                        }
                    }
                }
            }

                //add student button
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

                //add student sheet
            if(addStudent.value == true){
                Box(){
                    Row(modifier = Modifier.background(color = Color(0xFFdbe4e6))
                        .fillMaxSize()
                        .padding( end = 15.dp)){

                        var addName by remember { mutableStateOf("") }
                        val addGender = arrayOf("male","female")
                        var selectedText by remember { mutableStateOf(addGender[0]) }
                        var addYear by remember { mutableStateOf("") }

                        Image(painter = painterResource("drawable/add.svg"),
                              contentScale = ContentScale.Fit,
                              contentDescription = null,
                              modifier = Modifier.align(Alignment.CenterVertically)
                                  .fillMaxWidth(0.08f)
                                  .size(20.dp)
                                  .clickable {
                                      addStudent.value=false
                                      addStudent(addName,selectedText,addYear)
                                  })

                        TextField(value=addName,
                                  onValueChange = {addName=it},
                                  maxLines = 1,
                                  label = { Text(text = "name")},
                                  modifier = Modifier.align(Alignment.CenterVertically)
                                      .fillMaxWidth(0.5f),
                                  colors = TextFieldDefaults.textFieldColors(
                                      focusedIndicatorColor = Color.Transparent,
                                      unfocusedIndicatorColor = Color.Transparent,
                                      disabledIndicatorColor = Color.Transparent,
                                      backgroundColor = Color.Transparent)
                        )

                        var expanded by remember { mutableStateOf(false) }

                        ExposedDropdownMenuBox(modifier = Modifier.align(Alignment.CenterVertically)
                            .fillMaxWidth(0.5f),
                            expanded = expanded,
                            onExpandedChange = {expanded = !expanded}
                        ) {
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
                                addGender.forEach { item ->
                                    DropdownMenuItem(
                                        onClick = {
                                            selectedText = item
                                            expanded = false
                                        }
                                    ){
                                        Text(text = item)
                                    }
                                }
                            }
                        }

                        TextField(value=addYear,
                                  onValueChange = {addYear=it},
                                  maxLines = 1,
                                  label = { Text(text = "date")},
                                  modifier = Modifier.align(Alignment.CenterVertically),
                                  colors = TextFieldDefaults.textFieldColors(
                                      focusedIndicatorColor = Color.Transparent,
                                      unfocusedIndicatorColor = Color.Transparent,
                                      disabledIndicatorColor = Color.Transparent,
                                      backgroundColor = Color.Transparent)
                        )
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
                              SupabaseModel.deleteStudent(id = ID)
                          })
            }
        }
    }
}