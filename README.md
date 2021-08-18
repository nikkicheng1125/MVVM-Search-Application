# GM_Assignment

## Architecture
MVVM with databinding, Coroutines, Repository.  

## Libraries used 
Retrofit, Coroutine libraries, Retrofit converter factory, Retrofit logging-interceptor

## Testing 
- For Unit Testing, used **JUnit and Mockito**
- Added extension **mock-maker-inline** to make it possible **to mock final classes** in Kotlin
- Used **runBlockingTest** to test view model functions which contains coroutines 
- Written test cases to achieve **100% test coverage** for view model
- Mock responses are stored in resources folder and created a FileReader to read and use these responses along with Mockito 
