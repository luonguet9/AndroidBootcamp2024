plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
	alias(libs.plugins.navigation.safe.args.kotlin)
}

android {
	namespace = "com.example.newsapiclient"
	compileSdk = 35
	
	defaultConfig {
		applicationId = "com.example.newsapiclient"
		minSdk = 24
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"
		buildConfigField("String", "API_KEY", "${project.properties["MY_KEY"]}")
		buildConfigField("String", "BASE_URL", "${project.properties["MY_URL"]}")
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources  = true
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
		buildConfig = true
		viewBinding = true
	}
}

dependencies {
	
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	testImplementation(libs.junit)
	testImplementation(libs.mockwebserver)
	testImplementation(libs.truth)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	
	// Gson
	implementation(libs.gson)
	
	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	
	// Okhttp3
	implementation(libs.okhttp3)
	
	// Lifecycle Viewmodel
	implementation(libs.lifecycle.viewmodel)
	implementation(libs.lifecycle.livedata)
	ksp(libs.lifecycle.compiler)
	
	// Hilt
	implementation(libs.hilt.android)
	ksp(libs.hilt.compiler)
	
	// Navigation Component
	implementation(libs.navigation.fragment)
	implementation(libs.navigation.ui)
	
	// Glide
	implementation(libs.glide)
	ksp(libs.glide.compiler)
	
	// Room
	implementation(libs.room.runtime)
	implementation(libs.room.ktx)
	ksp(libs.room.compiler)
}
