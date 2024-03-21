package screens.mainUserScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.mainUserScreen.Screens.classList
import screens.mainUserScreen.Screens.profile.profile
import screens.mainUserScreen.Screens.studentList.studentList

@OptIn(ExperimentalResourceApi::class)
@Composable
fun mainUserScreen(modifier: Modifier,selection: Int){
//    var Selection: Int by remember { mutableStateOf(0) };
    Box(modifier = modifier){
        Column(modifier = Modifier.background(color = Color(0xFF3f6375))
            .fillMaxSize()){
            Box(){
                topBar()
            }
                
            Spacer(Modifier.height(25.dp))
                
            if(selection !=0){
                Box(){
                    screachBar()
                }
            }

            Box(){
                if(selection==0){
                    profile()
                }
                if(selection==1){
                    classList()
                }
                if(selection==2){
                    studentList()
                }
            }
        }
    }
}