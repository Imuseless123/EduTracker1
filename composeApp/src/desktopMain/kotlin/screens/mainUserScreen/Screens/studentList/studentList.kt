package screens.mainUserScreen.Screens.studentList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class StudentRecord (
    val ID: String,
    val Name: String,
    val Gender: String,
    val DOB: String
    )

@Composable
fun studentList(){
    
    val tempList= mutableListOf(StudentRecord("1","Long","Male","12/01/2003"))
    Box(modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.fillMaxWidth(0.9f)
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
                    Text(text = "Date of birth",
                         modifier = Modifier.align(Alignment.CenterVertically))
                }
            }
                Box(modifier = Modifier.fillMaxSize()){
                LazyColumn {
                    items(tempList){
                        Box(modifier = Modifier.height(50.dp)){
                            recordRow(it.ID,it.Name,it.Gender,it.DOB)
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
//            .fillMaxWidth()
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