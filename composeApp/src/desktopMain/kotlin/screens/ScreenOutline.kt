package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import screens.mainUserScreen.mainUserScreen
import screens.sideBar.sideBar

@Composable
fun ScreenOutline(sideBarPopup: (s:Boolean) -> Unit){
    Row(modifier = Modifier.fillMaxSize()){
        var Selection: Int by remember { mutableStateOf(0) };
        sideBar(modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(0.3f),
                selection = Selection, sideBarSelection={Selection=it} )
        mainUserScreen(modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(), selection = Selection, sideBarPopup = {
                s -> sideBarPopup(s)
            })
    }
}