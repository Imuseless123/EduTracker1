import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import screens.ScreenOutline
import screens.loginScreen.loginScreen
import screens.sideBar.sideBar

var userEmailDisplay: String="";

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xFFdbe5de),
        targetValue = Color(0xFF707974),
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val subColor by infiniteTransition.animateColor(
        initialValue = Color(0xFF404944),
        targetValue = Color(0xFFfbfdf9),
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val sub1Color by infiniteTransition.animateColor(
        initialValue = Color(0xFF191c1a),
        targetValue = Color(0xFF191c1a),
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
//    Canvas(
//        modifier = Modifier.fillMaxSize(),
//        onDraw = {
//            drawRect(Brush.linearGradient(listOf(color, subColor,sub1Color)))
//        }
//    )
    
    var login:Result<String> = Result.failure(Exception("Login fail!"));
    var sideBarPopup by remember {mutableStateOf(false)};
    val coroutineScope = rememberCoroutineScope()
//    if(!sideBarPopup){
//        loginScreen(){email, password ->
//            coroutineScope.launch {
//                login = SupabaseService.loginEmail(email,password)
//                if(login==Result.success("Login success")){
//                    sideBarPopup=true;
//                    userEmailDisplay=email;
//                }
//            }
//        }
//    }
//
//    if(sideBarPopup){
//        ScreenOutline()
//    }

    ScreenOutline()
}

//fun user() = runBlocking{
//    launch {
//        supabase.auth.signUpWith(Email) {
//            email = "stickboy7777@gmail.com"
//            password = "password"
//        }
//    }
//}
