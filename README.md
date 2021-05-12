# Fucking note
Dạo này già rồi. Note lại để thôi quên :|
## Kotlin
1. [Scope function](https://kotlinlang.org/docs/scope-functions.html)
    - [also](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Also.kt)
    - [apply](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Apply.kt)
    - [let](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Let.kt)
    - [run](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Run.kt)
    - [with - Mỗi thằng này không phải extension méo hiểu =)))](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/With.kt)
2. [Coroutine](https://kotlinlang.org/docs/coroutines-guide.html) <br>
    - Xài thằng này vì code nhìn như là chạy tuần tự chứ thật ra nó chạy bất đồng bộ
    - Return được chứ k phải méo return được như thread
    - Không phải thread nhưng chạy mội nùi tác vụ được. Thread chạy một nùi để ngẽn cổ chai die luôn à
    - Một thread chạy được nhìu coroutine, chạy được cả trên main thread
    - Cách implement giống nhau cho nhìu ngôn ngữ, sau này thất nghiệp qua làm Js cũng biết code coroutine
    - [Basic](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/Basic.kt)
    - [Context - Dispatchter](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/ContextAndDispatcher.kt)
    - [Job - Cancel - Timeout](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/JobCancellationTimeout.kt)
    - [Async - Await](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/AsyncAwait.kt)
    - [CoroutineScope](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/CoroutineScope.kt)
    - [Exception](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/ExceptionAndSuperVisionJobAndSuperVisionScope.kt)
    - [Flow](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/coroutine/Flow.kt)
## Android
1. [Nơi tình yêu bắt đầu](https://developer.android.com/courses/android-basics-kotlin/course)
    - [Basic nè](https://developer.android.com/courses/android-basics-kotlin/unit-1)
    - [Layout nè](https://developer.android.com/courses/android-basics-kotlin/unit-2)
    - [Navigation nè](https://developer.android.com/courses/android-basics-kotlin/unit-3)
    - [Call Api nè](https://developer.android.com/courses/android-basics-kotlin/unit-3)
2. [Thread](https://github.com/huynn109/mobile-flash-card/blob/main/android/android-thread/app/src/main/java/com/example/androidthread/MainActivity.kt) 
## Android interview
### Core
- Kể tên các Android application component trong Android?
- `Context` là gì? sử dụng nó như thế nào?
    - Context là một thành phần trong ứng dụng Android cung cấp các quyền truy cập thông tin về trạng thái của ứng dụng và resource đồng thời
    nó còn giúp khởi chạy các component như Activity, Service, Receiver
    - Có các loại 
- `AndroidManifest.xml` để làm gì?
- `Application` class là gì?
### Activity và Fragment
- Vòng đời của `Activity`?
- Khác nhau giữa `onCreate()` và `onStart()`?
- Khi nào thì `onDestroy()` được gọi mà không chạy qua `onPause()` và `onStop()`?
- Tại sao phải gọi `setContentView()` trong `onCreate()` của Activity?
- `onSavedInstanceState()` và `onRestoreInstanceState()` trong Activity để làm gì?
- Vòng đời của `Fragment`?
- Kể tên các `launchMode`? Nó hoạt động như thế nào?
- Nêu các điểm khác nhau giữa `Activity` và `Fragment`? Khi nào thì sử dụng?
- Khác nhau giữa `FragmentPagerAdapter` và `FragmentStatePagerAdapter`?
- Khác nhau giữa `Add/Replace` fragment trong backstack?
- Làm thế nào để giao tiếp giữa 2 `Fragment`?
- Fragment `retain` là gì?
- 
## Flutter
1. [Dart SDK](https://github.com/dart-lang/sdk)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L1517)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L25)
    - [Code native để kết nối socket nè](https://github.com/dart-lang/sdk/blob/81c3e8cbb42f9cd6d2c1a7b4f95a7eb70fa3f64c/runtime/bin/socket.cc#L362)
## Design pattern
#### Creational Design Pattern (Nhóm khởi tạo)
1. Singleton
2. 