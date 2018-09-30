## RxLifecycle

Easily dispose RxJava streams with Android's Lifecycle

### Usage

##### Gradle

```groovy
dependencies {
    implementation 'io.sellmair:rxlifecycle:1.0.0-alpha.0'
}
```

##### Disposer
A ```Disposer``` is the object managing multiple ```Disposable``` instances and disposes them
at the correct time. 

You can easily add a given disposable to a ```Disposer``:

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