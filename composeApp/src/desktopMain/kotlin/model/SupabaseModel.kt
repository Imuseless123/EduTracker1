package model

import androidx.compose.runtime.MutableState
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

        fun user() = runBlocking{
            launch {
                supabase.auth.signOut()
            }
        }

        fun signUpUser(userEmail: String, userPassword: String){
            runBlocking{
                launch {
                    supabase.auth.signUpWith(Email) {
                        email = userEmail
                        password = userPassword
                    }
                }
            }
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
            return SupabaseModel.supabase.from("student").select ( columns = Columns.ALL ){

            }.decodeList<StudentRecord>()
        }

        suspend fun classQuery(): List<Class>{
            return SupabaseModel.supabase.from("class").select (columns = Columns.ALL){

            }.decodeList<Class>()
        }

        suspend fun studentClassJoinedStudentRecordQuery(classId:String): List<StudentClassJoinedStudentRecord>{
            return SupabaseModel.supabase.from("class_student").select (
                columns = Columns.list("student_id(id, name, gender, date), class_id"
                )
            ){
                filter {
                    eq("class_id",classId)
                }
            }.decodeList<StudentClassJoinedStudentRecord>()
        }

        suspend fun studentClassJoinedStudentRecordQuery1(): List<StudentRecord>{
            return SupabaseModel.supabase.from("student").select (
                columns = Columns.ALL
            ){}
            .decodeList<StudentRecord>()
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

        fun addStudentId(studentId: MutableState<Int>, classId: String, notIn: MutableList<String>): Boolean{
            if(studentId.value==0){
                return false
            }
            var a:Int =1
            notIn.forEach {
                item ->
                run {
                    if (studentId.toString() == item) {
                        a *= (-a)
                    }
                }
            }
            if(a==-1){
                return false
            }

            runBlocking {
                launch {
                    SupabaseModel.supabase.from("class_student").insert(StudentClass(
                        student_id = studentId.value,
                        class_id = classId.toInt()
                    ))
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

        fun deleteStudentClass(id: String): Boolean{
            try{
                runBlocking {
                    launch {
                        SupabaseModel.supabase.from("class_student")
                            .delete {
                                filter {
                                    eq("student_id",id)
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
){

}

@Serializable
data class StudentRecord (
    val id: Int,
    val name: String,
    val gender: Boolean,
    val date: String
)

@Serializable
data class StudentClass(
    val student_id: Int,
    val class_id: Int
)

@Serializable
data class StudentClassJoinedStudentRecord(
    val student_id: StudentRecord,
    val class_id: Int
)