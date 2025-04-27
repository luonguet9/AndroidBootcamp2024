(Please download and study the final project of lesson 162)

# In lib.versions.toml

[versions]
hilt = "2.46"
 
[libraries]
google-dagger-hilt = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
google-dagger-hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
 
[plugins]
google-dagger-hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }

# In project-level build.gradle

plugins {
 
 alias(libs.plugins.google.dagger.hilt) apply false
 
}

# In module-level build.gradle

plugins {
 
 kotlin("kapt")
 
 alias(libs.plugins.google.dagger.hilt)
 
}
 
 
dependencies {
 
 implementation(libs.google.dagger.hilt)
 
 kapt(libs.google.dagger.hilt.compiler)
 
}
 
 
kapt {
 
 correctErrorTypes = true
 
}
