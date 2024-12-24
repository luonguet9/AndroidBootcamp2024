# Section Summery(Short Note)
Android LiveData is a lifecycle-aware, observable data holder class.

As its name suggests, we use it to get live data.

In other words, to get real time updates from the data sources .

##  Define a live data instance(inside the ViewModel class)
```kotlin
class MainActivityViewModel : ViewModel() {
    var count = MutableLiveData<Int>()
    ........
    ........
}
```
##  Update the value of a live data instance.
```kotlin
class MainActivityViewModel : ViewModel() {
    var count = MutableLiveData<Int>()
 
    init {
        count.value = 0
    }
 
    .......
}
```
##  Observe a LiveData instance from a view(activity)
```kotlin
class MainActivity : AppCompatActivity() {
    ......
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        ........
        ........
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.count.observe(this, Observer {
            binding.countText.text = it.toString()
        })
 
        ........
    } 
}
```
