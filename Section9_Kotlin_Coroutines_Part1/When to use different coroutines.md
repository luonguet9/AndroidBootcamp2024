# When to use different coroutines
In Android development, coroutines can be launched in various scopes based on the lifecycle of components like ViewModel, Activity, or Fragment. Each of these scopes provides structured concurrency, meaning they help manage coroutines by canceling them automatically when they are no longer needed, preventing memory leaks or unnecessary background tasks. Here's an explanation of when to use each coroutine scope and typical scenarios where they apply:

## ViewModelScope
### What it is:

This scope is tied to the lifecycle of a ViewModel. Coroutines launched in this scope will automatically be canceled when the ViewModel is cleared (typically when the Fragment or Activity that owns it is destroyed).

### When to use:

Use this for operations that need to survive configuration changes (like screen rotations) and should only be canceled when the ViewModel is destroyed. These tasks include data fetching or saving operations that can continue even after a configuration change.

### Typical scenarios:

1. Fetching data from a remote source (like making API calls) and updating the UI.

2. Persisting data to a local database.     



## LifecycleScope
### What it is: 
This scope is tied to the lifecycle of LifecycleOwner (such as Activity or Fragment). Coroutines launched in this scope are automatically canceled when the LifecycleOwner is destroyed (i.e., the activity or fragment is no longer in use).

### When to use: 
Use this when you want a task to be tied to the lifecycle of a UI component like an Activity or Fragment but don't want it to persist across configuration changes.

### Typical scenarios:

UI-related tasks that should cancel when the user navigates away from the screen, like animations or short-lived network requests that should stop when leaving the activity.

Performing cleanup tasks during lifecycle events.



## CoroutineScope
### What it is: 
This is a general-purpose coroutine scope that you can manually create, giving you more flexibility. It's not tied to a lifecycle unless you explicitly associate it with one.

### When to use: 
Use this when you need a custom scope for certain tasks that are not directly tied to any Android lifecycle. Make sure to cancel it manually to avoid memory leaks.

### Typical scenarios:

Non-lifecycle dependent tasks that you want to control manually.

Custom scopes for background processing.

## GlobalScope
### What it is: 
A global coroutine scope that is not tied to any lifecycle and lives throughout the entire lifetime of the app.

### When to use: 
Generally, it's not recommended to use GlobalScope unless you're doing a very specific task that truly needs to exist for the lifetime of the application. Using GlobalScope can lead to memory leaks because coroutines will not be canceled automatically.

### Typical scenarios:

Rare cases like application-wide singletons or long-running tasks that should not be canceled.

## SupervisorScope
### What it is: 
A special type of scope where failure in one child coroutine does not automatically cancel the other children coroutines. This is useful when you want to handle failures of coroutines independently.

### When to use: 
Use this when you have several coroutines that can fail independently, and the failure of one coroutine should not cancel others.

### Typical scenarios:

Handling multiple independent network requests where one failure shouldn't stop others.