package screens.sideBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import io.ktor.http.HttpMethod.Companion.Post

@Composable
fun sideBar(modifier: Modifier){
    AppTheme() {
        Box(modifier = modifier){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF191c1a))){
                Box(modifier = Modifier.fillMaxWidth()
                    .fillMaxHeight(0.1f)){
                    Text("EduTracker",
                         color = Color(0xFFe0e4d6)
                    )
                }
                    sideBarButton("Profile","drawable/home.svg")
                    sideBarButton("Class List","drawable/graduation-cap.svg")
                    sideBarButton("Student List","drawable/users-alt.svg")
            }
            

        }
    }
}

@Composable
fun sideBarButton(buttonName: String, iconPath: String){
    Box(modifier = Modifier.fillMaxWidth()
    .clickable {  }){
        Row(){
            Image(painter = painterResource(iconPath),
                contentScale = ContentScale.Fit,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
                    .align(Alignment.CenterVertically)
                    .padding(top = 5.dp, bottom = 5.dp, end = 5.dp, start = 10.dp),
                colorFilter = ColorFilter.lighting(Color.White, Color.White)
                )
            Text(text = buttonName,
                 color = Color.White,
                 modifier = Modifier.padding(10.dp))
        }
    }
}