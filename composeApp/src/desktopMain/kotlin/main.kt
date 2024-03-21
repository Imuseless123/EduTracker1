import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//import screens.loginScreen.user
import screens.viewModel.SupabaseService.Companion.studentQuery

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "EduTracker") {
//        user()
        runBlocking {
            launch { studentQuery() }
        }
        
        App()
    }
}