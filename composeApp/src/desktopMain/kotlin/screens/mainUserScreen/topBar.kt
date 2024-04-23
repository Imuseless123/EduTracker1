package screens.mainUserScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import model.SupabaseModel
import userEmailDisplay

@Composable
fun topBar(sideBarPopup: (s:Boolean) -> Unit){
    Box(){
        Row(modifier = Modifier.background(Color.White)
            .fillMaxHeight(0.1f)){
            Column(modifier = Modifier.fillMaxWidth(0.5f)
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp)){
                Text("User")
                Text(userEmailDisplay)
//                Text("tesingUserEmail")
            }
            Box(modifier = Modifier.fillMaxWidth()
                .align(Alignment.CenterVertically)){
                Row(){
                    Box(modifier = Modifier.fillMaxWidth(0.8f)){
                        
                    }
                    Box(modifier = Modifier.padding(start = 20.dp)){
                        Image(painter = painterResource("drawable/exit.svg"),
                              contentScale = ContentScale.Fit,
                              contentDescription = null,
                              modifier = Modifier.size(20.dp)
                                  .clickable {
                                      sideBarPopup(false)
                                      SupabaseModel.user()
                                  })
                    }
                }

            }
        }
    }
}