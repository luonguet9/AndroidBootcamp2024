# Section Summery(Short Note)

When we are using an android app, when a configuration change like screen rotation happens, app has to destroy and recreate the activity or fragment with new configurations.

As a result of that, values(states) created during the running period of the activity will also be destroyed.

The Android Jetpack View Model architecture component has introduced as a solution for above problem. We can use a view model object to safely keep and retrieve values(states) of that activity. (Note: this only available during the run time of the app, if we need a permanent data storage we need to use a database)

As the name suggests, a ViewModel is a model for a view. Typically, we create a separate ViewModel for each activity. A ViewModel survives configuration changes, such as activity or fragment recreation, ensuring that the lifecycle changes of activities and fragments do not affect it. However, the `onCleared()` method of a ViewModel is called only when the app is put into the background, and the app process is killed to free up system memory.

As its name says, view model is a model for a view. We usually create a view model for each activity.

A ViewModel’s onCleared() is called only when the app is put into the background and the app process is killed in order to free up the system’s memory.Therefore, lifecycle changes of activities and fragments does not affect to their ViewModels.(Activities and fragments may destroy and recreate, but view model will live throughout the process)

### Example: Creating and Using a ViewModel

#### Step 1: Create a ViewModel Class
```kotlin
class MainActivityViewModel : ViewModel() {
    // ViewModel logic goes here
}
```

#### Step 2: Get a ViewModel Instance
You can retrieve a ViewModel instance using the `ViewModelProvider`.

```kotlin
private lateinit var viewModel: MainActivityViewModel

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // Initialize ViewModel
    viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
}
```

### Using a ViewModelFactory
If your ViewModel requires constructor parameters, use a `ViewModelFactory` to provide them.

#### Step 1: Create a ViewModelFactory
```kotlin
class MainActivityViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
```

#### Step 2: Get a ViewModel Instance with ViewModelFactory
```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewModelFactory and ViewModel
        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }
}
