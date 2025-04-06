plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.ksp)
}

android {
	namespace = "com.example.tmdbclient"
	compileSdk = 35
	
	defaultConfig {
		applicationId = "com.example.tmdbclient"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		
		buildConfigField("String", "API_KEY", "\"a242369471c9a891f1c3a09b12a43af3\"")
		buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
		
	}
	
	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	kotlinOptions {
		jvmTarget = "11"
	}
	buildFeatures {
		dataBinding = true
		buildConfig = true
	}
}

dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	
	// Lifecycle
	implementation(libs.lifecycle.viewmodel.ktx)
	implementation(libs.lifecycle.viewmodel.savedstate)
	ksp(libs.lifecycle.compiler)
	
	// Room
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	
	// Coroutines
	implementation(libs.kotlinx.coroutines.android)
	
	// Dagger
	implementation(libs.com.google.dagger)
	ksp(libs.com.google.dagger.compiler)
	
	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp)
	
	// Glide
	implementation(libs.glide)
	ksp(libs.glide.compiler)
	
}
