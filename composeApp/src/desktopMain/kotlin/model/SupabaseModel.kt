package model

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
import kotlinx.serialization.Serializable
import screens.mainUserScreen.Screens.profile.profileInfo

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

        suspend fun studentQuery(): List<StudentRecord>{
            return SupabaseModel.supabase.from("student").select ( columns = Columns.ALL ){}.decodeList<StudentRecord>()
        }

        suspend fun classQuery(): List<Class>{
            return SupabaseModel.supabase.from("class").select (columns = Columns.ALL){}.decodeList<Class>()
        }

        suspend fun studentClassJoinedStudentRecordQuery(classId:String): List<StudentClassJoinedStudentRecord>{
            return SupabaseModel.supabase.from("student").select (
                columns = Columns.list("student_class(class_id), id, name, gender, date"
                )
            ){
                filter {
                    eq("class_id",classId)
                }
            }.decodeList<StudentClassJoinedStudentRecord>()
        }

        suspend fun join(): List<test2>{
            return SupabaseModel.supabase.from("text1").select (
                columns = Columns.raw("id(id, text1_id)"
                )
            ){

            }.decodeList<test2>()
        }

        fun addStudent(name: String, gender: String,year: String): Boolean{
            if(name.isEmpty() || year.isEmpty()){
                return false
            }
            runBlocking {
                launch {
                    val addStudent=StudentRecord(
                        id = profileInfo.getNOfStudent() + 1,
                        name = name,
                        gender = if(gender=="male") {true} else{false},
                        date = year
                    )
                    SupabaseModel.supabase.from("student").insert(addStudent)
                }
            }

            return true
        }

        fun addClass(name: String): Boolean{
            if(name.isEmpty()){
                return false
            }

            runBlocking {
                launch {
                    val addClass=Class(
                        id = profileInfo.getNOfClass()+1,
                        name = name
                    )

                    supabase.from("class").insert(addClass)
                }
            }

            return true
        }

        fun deleteStudent(id: String): Boolean{
            try {
                runBlocking {
                    launch {
                        SupabaseModel.supabase.from("student")
                            .delete{
                                filter {
                                    eq("id",id)
                                }
                            }
                    }
                }
            }
            catch (e: Exception){
                return false
            }
            return true
        }

        fun deleteClass(id: String):Boolean{
            try {
                runBlocking {
                    launch {
                        SupabaseModel.supabase.from("class")
                            .delete{
                                filter {
                                    eq("id",id)
                                }
                            }
                    }
                }
            }
            catch (e: Exception){
                return false
            }
            return true
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

@Serializable
data class StudentClass(
    val studentId: Int,
    val classId: Int
)

@Serializable
data class StudentClassJoinedStudentRecord(
//    val student: StudentRecord,
//    val classId: Int
    val s_c: StudentClass,
    val id: Int,
    val name: String,
    val gender: Boolean,
    val date: String
)

data class test1(
    val id: test2
)

data class test2(
    val id: Int,
    val text1_id: Int
)