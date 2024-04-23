package screens.mainUserScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.mainUserScreen.Screens.classList
import screens.mainUserScreen.Screens.classList.student_class
import screens.mainUserScreen.Screens.profile.profile
import screens.mainUserScreen.Screens.studentList.studentList

@OptIn(ExperimentalResourceApi::class)
@Composable
fun mainUserScreen(modifier: Modifier,selection: Int, sideBarPopup: (s:Boolean) -> Unit){
    var viewStudentClass: Boolean by remember { mutableStateOf(false) };
    var returnedClassId: String by remember { mutableStateOf("")}
    var returnedClassName: String by remember { mutableStateOf("")}
    Box(modifier = modifier){
        Column(modifier = Modifier.background(color = Color(0xFF3f6375))
            .fillMaxSize()){
            Box(){
                topBar(sideBarPopup = {
                    s ->  sideBarPopup(s)
                })
            }
                
//            if(selection !=0){
//                Spacer(Modifier.height(25.dp))
//
//                Box(){
//                    screachBar()
//                }
//            }

            Box(){
                if(selection==0){
                    profile()
                }
                if(selection==1){
                    if(!viewStudentClass){
                        classList(selection = viewStudentClass,
                                  returnedClassId = returnedClassId ,
                                  returnedClassName = returnedClassName ,
                                  viewStudentClass = {s,id ,name ->
                                      viewStudentClass=s
                                      returnedClassId=id
                                      returnedClassName=name
                                  })
                    }else{
                        student_class(id =  returnedClassId,
                                      name =  returnedClassName,
                                      viewStudentClass = {s ->
                                          viewStudentClass=s
                                      })
                    }
                }
                if(selection==2){
                    studentList()
                }
            }
        }
    }
}