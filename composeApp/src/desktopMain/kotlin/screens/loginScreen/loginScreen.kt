package screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import model.SupabaseModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun loginScreen(signUpButtonClick: (Email: String, Password: String)-> Unit) {
//    val coroutineScope = rememberCoroutineScope()
    var signUpMenu by remember { mutableStateOf(false) }
    
    Box(modifier = Modifier.fillMaxSize()){
        Box(Modifier.fillMaxHeight(0.8f)
            .fillMaxWidth(0.6f)
            .align(Alignment.Center)
            .background(color = Color(0x7743483e))){
            Column(Modifier.align(Alignment.Center)){
                Box(Modifier.align(Alignment.CenterHorizontally)){
                    Text(text = "Welcome to EduTracker",
                        color = Color.White)
                }

                Spacer(Modifier.height(25.dp))

                Box(Modifier.clip(CircleShape)
                    .align(Alignment.CenterHorizontally)){
                    Image(painter = painterResource("drawable/user_icon.png"),
                          contentScale = ContentScale.Fit,
                          contentDescription = null,
                          modifier = Modifier.size(50.dp))
                }
                Box(Modifier.align(Alignment.CenterHorizontally)){

                    Column(){
                        Box(Modifier.align(Alignment.CenterHorizontally)){
                            Text(text = "GUEST",
                                 color = Color.White,
                                )
                        }

                        Spacer(Modifier.height(25.dp))

                        var userEmail by remember {mutableStateOf("")}
                        TextField(value =  userEmail,
                                  onValueChange = {userEmail = it},
                                  maxLines = 1,
                                  label = { Text("Email",color = Color.White) },
                                  modifier =Modifier.align(Alignment.CenterHorizontally)
                                      .clip(RoundedCornerShape(40.dp))
                                      .width(200.dp)
                                      .height(50.dp))

                        Spacer(Modifier.height(25.dp))

                        var Password by remember {mutableStateOf("")}
                        TextField(value =  Password,
                                  onValueChange = {Password = it},
                                  maxLines = 1,
                                  visualTransformation = PasswordVisualTransformation(),
                                  label = { Text("Password",color = Color.White) },
                                  modifier = Modifier.align(Alignment.CenterHorizontally)
                                      .clip(RoundedCornerShape(40.dp))
                                      .width(200.dp)
                                      .height(50.dp))

                        Spacer(Modifier.height(25.dp))

                        Button(onClick = {
                            if(!signUpMenu){
                                signUpButtonClick(userEmail,Password)
                            }
                            else{
                                SupabaseModel.signUpUser(userEmail,Password)
                            }
                                         },
                               Modifier.align(Alignment.CenterHorizontally)
                                   .clip(RoundedCornerShape(40.dp))
                                   .width(200.dp)
                                   .height(50.dp)){
                            Text(text = if(!signUpMenu) {"SIGN IN"} else{"SIGN UP"},
                                 color = Color.White)
                        }

                        Row(Modifier.align(Alignment.CenterHorizontally)){
                            Text(text = if(!signUpMenu) {"Don't have an account?"} else{"Have an account?"},
                                 color = Color.White)
                            Text(text = if(!signUpMenu) {"sign up"} else{"sign in"},
                                 color = Color.Blue,
                                 modifier = Modifier.clickable {
                                     signUpMenu=!signUpMenu
                                 })
                        }
                    }
                }
            }
        }
    }
}

//fun user() = runBlocking{
//    launch {
//        supabase.auth.signInWith(Email) {
//            email = "21521099@gm.uit.edu.vn"
//            password = "password"
//        }
//    }
//}

//fun user() = runBlocking{
//    launch {
//        supabase.auth.signOut()
//    }
//}

//fun user() = runBlocking{
//    launch {
//        supabase.auth.signInWith(Email) {
//            email = "keny7503@gmail.com"
//            password = "wasd1234"
//        }
//    }
//}