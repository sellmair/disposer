## Disposer
Easily dispose RxJava streams with Android's Lifecycle

![GitHub top language](https://img.shields.io/github/languages/top/sellmair/disposer.svg)
[![Build Status](https://travis-ci.org/sellmair/disposer.svg?branch=master)](https://travis-ci.org/sellmair/disposer)
![Bintray](https://img.shields.io/bintray/v/sellmair/sellmair/disposer.svg)

Checkout my [Medium](https://medium.com/@sellmair/disposing-on-android-the-right-way-97bd55cbf970) article.

### Usage

##### Gradle

```groovy
dependencies {
    implementation 'io.sellmair:disposer:1.0.0-alpha.1'
}
```

<br>

##### Disposer
A ```Disposer``` is the object managing multiple ```Disposable``` instances and disposes them
at the correct time. 

You can easily add a given disposable to a ```Disposer```:

```kotlin
val disposer: Disposer = /* ... */
val disposable = Service.queryAwesomeData().subscribe()
disposer.add(disposable)  // The disposable will now managed by the disposer
```



Or a much sweeter apis, that might look familiar to rxKotlin users: 

```kotlin
val disposer: Disposer = /* ... */
disposer += Service.queryAwesomeData().subscribe() // Managed by the disposer
```

```kotlin
val disposer: Disposer = /* ... */
Service.queryAwesomeData().subscribe().disposeBy(disposer) // Managed by the disposer
```

<br>

##### Get the correct ```Disposer```

RxLifecycle makes it easy to get a disposer for each lifecycle hook like 
```onCreate```,  ```onStart```, ```onResume```, ```onPause```, ```onStop``` and ```onDestroy```


One can simple call the extension function:

```kotlin
val onStopDisposer: Disposer = lifecycle.disposers.onStop
```


Much more shorter and convenient extensions are also offered, like for LifecycleOwner:

```kotlin
class MyCoolComponent: LifecycleOwner {
    
    // ...
    
    private val onStopDisposer: Disposer = this.onStop

}
```

Which leads to very concise and readable API's inside your ```Activity``` or ```Fragment``` classes:


<br>


##### Example:

```kotlin
class MyCoolFragment {
    
    // awesome other code 
    
    fun onStart(){
        super.onStart()
        
        awesomDataProvider.query()
            .flatMap(::pepareForAwesomeness)
            .filter(::isAwesome)
            .subscribe(::displayAwesomeData)
            .disposeBy(onStop) // <---  Will automatically be disposed when onStop() is called.
    }
}
```



##### Advanced: Upstream dispose

It is also possible to put the ```.disposeBy``` call before the ```.subscribe```. 
But be aware, that this will only dispose the upstream not the downstream, which is 
often okay, but should only be used with caution!

```kotlin
awesomDataProvider.query()
    .flatMap(::pepareForAwesomeness)
    .filter(::isAwesome)
    .disposeBy(onStop) // <--- Will dispose everything above it when .onStop() is called
    .subscribe(::displayAwesomeData)
```


##### Create you own Disposer

You can easily create your own ```Disposer``` by calling

```kotlin
val disposer = Disposer.create()
```

Each call of 
```kotlin
disposer.dispose()
```

Will dispose all currently managed disposables and reset the ```Disposer```

⚠️ Be aware: This behaviour differs from ```CompositeDisposable``` and actually
is more like ```CompositeDisposable.clear```. 
