# Frequently Asked Questions.
## 1) What is the difference between ViewModel() and AndroidViewModel() ?


The AndroidViewModel class extends ViewModel class, so it has all the same functionality.

The only added functionality for AndroidViewModel is that it is context aware, when initialising AndroidViewModel we have to pass the application context as a parameter.

AndroidViewModel is helpful if we require context to get a system service or have a similar requirement(displaying a Toast message).

```kotlin
class MyAnViewModel(application: Application) : AndroidViewModel(application) {
   ........
   ........
}
```

## 2) What is "ViewModelProvider" ?


We can not construct a ViewModel instance on our own. We need to use the ViewModelProvider utility provided by Android to create instances of ViewModels.



## 3) When do we need to create a ViewModelFactory class ?


ViewModelProvider can only instantiate ViewModels with no arg constructors.

So, if the ViewModel has constructor parameters(arguments) , ViewModelProvider need a little extra support to create instances of it.

We provide that extra support by creating a Factory class and passing its instance to the ViewModelProvider.



## 4) When we are extending AndroidViewModel, since it should always has "application" as a constructor parameter, do we need to use a ViewModelFactory ?
No, if the ViewModel created extending AndroidViewModel, does not have parameters other than "application", we do not need to use a ViewModelFactory for that.



## 5) What is the onCleared() function of a ViewModel?
  When a ViewModel object is no longer required, system will call to its onCleared() function to destroy(clear) it.

   It will be called when the app is put into the background and the app process is killed in order to free up the system's memory.

   When the user invokes finish() of an activity, its view model will be cleared().

   Also when we click on the back button, ViewModel of current activity will be cleared (onCleared() will be invoked)