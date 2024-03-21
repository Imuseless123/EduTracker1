package screens.viewModel

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.PropertyConversionMethod
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import viewModel.ClassRoom
import viewModel.Student
import viewModel.SummaryPrize

//import supabase

class SupabaseService(){
    companion object{
        val supabase = createSupabaseClient(
            supabaseUrl = "https://zvukdugznrucavucofvn.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp2dWtkdWd6bnJ1Y2F2dWNvZnZuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTA4OTU2MTMsImV4cCI6MjAyNjQ3MTYxM30.oT92ZKhAYZDxQqkWHMGMnKyyvxXyKcbAGo9rmp4Kw-Y"
        ) {
            install(Auth)
            install(Postgrest){
                defaultSchema = "public" // default: "public"
                propertyConversionMethod = PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE // defau
            }
        //install other modules
        }
        
        val supabase2 = createSupabaseClient(
            supabaseUrl = "https://aytiizmcoutggwbggutk.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImF5dGlpem1jb3V0Z2d3YmdndXRrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTA5MjA1NjcsImV4cCI6MjAyNjQ5NjU2N30.0yKSsZ6TBG_XXjGrrBR-V2H-lvU3pAXRgyXYXThAWgQ"
        ) {
            install(Postgrest) {
                defaultSchema = "public" // default: "public"
                propertyConversionMethod = PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE // default: PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE
            }
            install(Auth)
        }
        
        val supabase3 = createSupabaseClient(
            supabaseUrl = "https://pskpouhbuvzeqdhjtfdu.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBza3BvdWhidXZ6ZXFkaGp0ZmR1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDkzMDU2NzEsImV4cCI6MjAyNDg4MTY3MX0.Dayhg5OljEZ0mX4mDG8YkWvdmi3wwBystnRr4i0PnqE"
        ) {
            install(Postgrest) {
                defaultSchema = "public" // default: "public"
                propertyConversionMethod = PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE // default: PropertyConversionMethod.CAMEL_CASE_TO_SNAKE_CASE
            }
            install(Auth)
        }
        
        suspend fun loginEmail(em: String, pw: String): Result<String>{
            try {
                val loginResult = supabase.auth.signInWith(Email){
                    email = em
                    password = pw
                }
                return Result.success("Login success")


            } catch (e: Exception) {
                // Handle other exceptions
                println("Login fail")
                return Result.failure(Exception("Login fail!"))
            }
        }
        
        suspend fun studentQuery(){
//            supabase.from("class")
//            .select(columns = Columns.list("id, name"))
//            .decodeList<ClassRoom>()
//            print(supabase3.from("class")
//                .select(columns = Columns.ALL)
//                .decodeList<ClassRoom>()
//                )
            
            println(SupabaseService.supabase2.from("class").select(columns = Columns.list("id, name")) {  }.decodeList<ClassRoom>())
        }
        


    }
}

