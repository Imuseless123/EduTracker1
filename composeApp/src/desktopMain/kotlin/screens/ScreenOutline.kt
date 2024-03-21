package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.compose.AppTheme
import screens.mainUserScreen.mainUserScreen
import screens.sideBar.sideBar

@Composable
fun ScreenOutline(){
    Row(modifier = Modifier.fillMaxSize()){
        sideBar(modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(0.3f))
        mainUserScreen(modifier = Modifier.fillMaxHeight()
            .fillMaxWidth())
    }
}