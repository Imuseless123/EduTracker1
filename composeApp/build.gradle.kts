import org.jetbrains.compose.desktop.application.dsl.TargetFormat

val coroutinesVersion = "1.7.3"
val ktorVersion = "2.3.7"
val sqlDelightVersion = "1.5.5"
val dateTimeVersion = "0.4.1"


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
    
//    kotlin("plugin.serialization").version("1.9.22")
    
    kotlin("plugin.serialization").version("1.9.23")
    id("com.squareup.sqldelight").version("1.5.5")
}

//sqldelight {
//    database("AppDatabase") {
//        packageName = "com.jetbrains.handson.kmm.shared.cache"
//    }
//}

//sqldelight {
//    database("AppDatabase") {
//        create("Database") {
//            packageName.set("com.example")
//            dialect("app.cash.sqldelight:postgresql-dialect:2.0.1")
//        }
//    }
//}

buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.9.21"))
    }
}

kotlin {
    jvm("desktop")
    
    sourceSets {
        val desktopMain by getting
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation("io.github.jan-tennert.supabase:postgrest-kt:2.2.1")
            implementation("io.github.jan-tennert.supabase:gotrue-kt:2.2.1")
            //Corountine
//            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            //SLF4J
            implementation("org.slf4j:slf4j-api:2.0.9" )
            implementation("org.slf4j:slf4j-simple:2.0.9" )
            
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("io.ktor:ktor-client-java:2.3.9")
        }
//        jvmMain.dependencies {
//            implementation("io.ktor:ktor-client-java:2.3.9")
//        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.pkl.edutracker"
            packageVersion = "1.0.0"
        }
    }
}
