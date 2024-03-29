package screens.mainUserScreen.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Class
import model.SupabaseModel
import model.SupabaseModel.Companion.addClass

data class classInfo(
    var name: MutableList<Class>
){
    companion object{
        private lateinit var instance: classInfo

        fun initClassInfo(){
            val classInfo = mutableListOf<Class>()

            runBlocking {
                launch {
                    classInfo.addAll(SupabaseModel.classQuery())
                }
            }

            instance = classInfo(classInfo)
        }

        fun getClassInfo(): MutableList<Class>{
            return instance.name
        }
    }
}

@Composable
fun classList(selection: Boolean,
              returnedClassId: String,
              returnedClassName: String,
              viewStudentClass: (s: Boolean, id:String, name: String) -> Unit){
    val addClass = remember { mutableStateOf(false)}
    classInfo.initClassInfo()

    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier.fillMaxSize(0.9f)
            .background(color = Color.White)
            .align(Alignment.Center)){

            Box(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.88f)){
                LazyColumn {
                    items(classInfo.getClassInfo()){
                        classRecordRow(it.id.toString(), it.name,viewStudentClass)
                    }
                }
            }

            if(addClass.value == false){
                Box(){
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFdbe4e6))
                        .clickable { addClass.value =true }){
                        Spacer(modifier = Modifier.fillMaxWidth(0.4f))
                            Image(painter = painterResource("drawable/user-add.svg"),
                                  contentScale = ContentScale.Fit,
                                  contentDescription = null,
                                  modifier = Modifier.align(Alignment.CenterVertically)
                                      .size(20.dp)
                                      .fillMaxWidth(0.5f))
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Add class",
                                 modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
            }

            if(addClass.value == true){
                Box(){
                    Row(modifier = Modifier.background(color = Color(0xFFdbe4e6))
                        .fillMaxSize()
                        .padding( end = 15.dp)){

                        var addName by remember { mutableStateOf("") }

                        Image(painter = painterResource("drawable/add.svg"),
                              contentScale = ContentScale.Fit,
                              contentDescription = null,
                              modifier = Modifier.align(Alignment.CenterVertically)
                                  .fillMaxWidth(0.08f)
                                  .size(20.dp)
                                  .clickable {
                                      addClass.value=false
                                      addClass(addName)
                                  })

                        TextField(value=addName,
                                  onValueChange = {addName=it},
                                  maxLines = 1,
                                  label = { Text(text = "name")},
                                  modifier = Modifier.align(Alignment.CenterVertically)
                                      .fillMaxWidth(),
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
fun classRecordRow(id:String, name: String,viewStudentClass: (s: Boolean,id: String,name:String) -> Unit){
    Box(){
        Row(modifier = Modifier.fillMaxWidth()
            .height(70.dp)
            .padding(start = 15.dp, end = 15.dp)){
            Box(modifier = Modifier.height(50.dp)
                .width(50.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
                .background(color = Color(0xFFb9c3ff))){
                Image(painter = painterResource("drawable/briefcase.svg"),
                      contentScale = ContentScale.Fit,
                      contentDescription = null,

                      modifier = Modifier.align(Alignment.Center)
                          .size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Text(text = name,
                modifier = Modifier.align(Alignment.CenterVertically)
                    .fillMaxWidth(0.7f))

            Box(modifier = Modifier.fillMaxWidth(0.7f)
                .align(Alignment.CenterVertically)){
                Button(onClick = {
                    viewStudentClass(true,id,name)
                },
//                       colors = Button ,
                       modifier = Modifier.align(Alignment.CenterEnd)){
                    Text(text = "More")
                }
            }

            Box(modifier = Modifier.align(Alignment.CenterVertically)
                .fillMaxWidth()){
                Image(painter = painterResource("drawable/trash.svg"),
                      contentScale = ContentScale.Fit,
                      contentDescription = null,
                      modifier = Modifier
                          .align(Alignment.CenterEnd)
                          .size(20.dp)
                          .clickable {
                                SupabaseModel.deleteClass(id = id)
                          })
            }
        }
    }
}