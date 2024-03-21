package model

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.PropertyConversionMethod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

class SupabaseModel {
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
    }
}
@Serializable
data class Class(
    val id: Int,
    val name: String
)
@Serializable
data class StudentRecord (
    val id: Int,
    val name: String,
    val gender: Boolean,
    val date: String
)