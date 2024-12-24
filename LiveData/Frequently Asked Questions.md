# Frequently Asked Questions
## 1) Where do we create/generate LiveData?
We usually define LiveData, inside ViewModel classes.

Also, supporting libraries like Room and Retrofit allows us to get data directly in LiveData format.

## 2) From where do we observe LiveData?
LiveData is a lifecycle-aware observable data holder class.

In android we have only 3 app components with lifecycles. Activities , fragments and services.

So, form activities, fragments and services we can observe LiveData.
## 3) What is the difference between RxJava and LiveData?
RxJava is not a lifecycle aware component.

So, data stream does not go off, when activity, fragment or service becomes inactive.

As a result of that, memory leaks or crashes can happen.

Therefore, we have to write codes to dispose them manually.

But, on the other hand, Android LiveData aware of lifecycle status changes.

And, they clean up themselves(stop emitting data) when their associated lifecycle is destroyed.
## 4) What is MutableLiveData, what is the difference between LiveData and MutableLiveData ?
MutableLiveData class is a subclass of LiveData class. In other words, MutableLiveData child class has created by extending the parent LiveData class.

A MutableLiveData instance can do everything a LiveData instnce can do and more.

Data in a LiveData object are only readable. We cannot update those values.

But, in the other hand, a Mutable LiveData object allows us to change(update) its values.

So, When we are creating our own live data(mostly in ViewModels), we define them as MutableLiveData.

But, when we are getting live data from other libraries such as Room and Retrofit we get them as LiveData.

We can easily transfer values between those two formats.