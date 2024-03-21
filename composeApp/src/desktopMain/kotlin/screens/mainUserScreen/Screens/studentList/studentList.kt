package screens.mainUserScreen.Screens.studentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import model.SupabaseModel

@Composable
fun studentList(){
    val studentList = remember {mutableStateListOf<model.StudentRecord>()}
    val composableCoroutine = rememberCoroutineScope()
    composableCoroutine.launch {
        studentList.clear()
        studentList.addAll(SupabaseModel.supabase.from("student").select(columns = Columns.list("id ,name, gender, date")) {  }.decodeList<model.StudentRecord>())
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxWidth(0.9f)
            .fillMaxHeight(0.9f)
            .align(Alignment.Center)){
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

            Box(modifier = Modifier.fillMaxSize()){
                LazyColumn {
                    items(studentList){
                        Box(modifier = Modifier.height(50.dp)){
                            recordRow(it.id.toString(),it.name, if(it.gender) {"male"} else{"female"},it.date)
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
                     modifier = Modifier.align(Alignment.CenterVertically))
        }
    }
}