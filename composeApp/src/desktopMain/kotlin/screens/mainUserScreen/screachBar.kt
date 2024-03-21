package screens.mainUserScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun screachBar(){
    Box(modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier.fillMaxWidth(0.9f)
            .fillMaxHeight(0.1f)
            .background(Color.White)
            .align(Alignment.Center)){
            Row(modifier = Modifier.fillMaxHeight()){
                Image(painter = painterResource("drawable/search.svg"),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 15.dp)
                        .clickable {  })
                var screachKey by remember { mutableStateOf("")};
                TextField(value = screachKey,
                          onValueChange = {screachKey=it},
                          maxLines = 1,
                          modifier = Modifier.fillMaxWidth()
                              .fillMaxHeight()
                              .align(Alignment.CenterVertically),
                          colors = TextFieldDefaults.textFieldColors(
                              focusedIndicatorColor = Color.Transparent,
                              unfocusedIndicatorColor = Color.Transparent,
                              disabledIndicatorColor = Color.Transparent,
                              backgroundColor = Color.Transparent
                          ))
            }
        }
    }
}