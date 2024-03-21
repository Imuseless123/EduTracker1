import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.PropertyConversionMethod
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import model.Class
import model.SupabaseModel
//import screens.loginScreen.user
import screens.viewModel.SupabaseService.Companion.studentQuery
import viewModel.ClassRoom

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "EduTracker") {
//        runBlocking {
//            launch {
//                println(SupabaseModel.supabase.from("class").select(columns = Columns.ALL) {  }.decodeList<Class>())
//            }
//        }
        App()
    }
}
