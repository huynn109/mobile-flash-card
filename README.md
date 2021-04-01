# Fucking note
Mình hay bị ngáo. Nhìu khi làm xong méo nhớ làm cái mịa j luôn <br>
=> Thế nên repo này được sinh ra là nơi để lưu lại những thứ mình từng làm và tìm hiểu
## Bắt đầu nào
## Kotlin
1. [Scope function](https://kotlinlang.org/docs/scope-functions.html)
    - [also](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Also.kt)
    - [apply](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Apply.kt)
    - [let](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Let.kt)
    - [run](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/Run.kt)
    - [with - Mỗi thằng này không phải extension méo hiểu =)))](https://github.com/huynn109/mobile-flash-card/blob/main/kotlin/src/main/kotlin/scope/With.kt)
## Android
1. [Đây là nơi để người méo biết gì học Android (Kotlin)](https://developer.android.com/courses/android-basics-kotlin/course):
    - [Basic nè](https://developer.android.com/courses/android-basics-kotlin/unit-1)
    - [Layout nè](https://developer.android.com/courses/android-basics-kotlin/unit-2)
    - [Navigation nè](https://developer.android.com/courses/android-basics-kotlin/unit-3)
    - [Call Api nè](https://developer.android.com/courses/android-basics-kotlin/unit-3)
3. [Coroutine](https://kotlinlang.org/docs/coroutines-guide.html) <br>
    - Xài thằng này vì code nhìn như là chạy tuần tự chứ thật ra nó chạy bất đồng bộ
    - Return được chứ k phải méo return được như thread củ chuối
    - Không phải thread nhưng chạy mội nùi tác vụ được. Thread chạy một nùi để ngẽn cổ chai die luôn à
    - Một thread chạy được nhìu coroutine, chạy được cả trên main thread
    - Cách implement giống nhau cho nhìu ngôn ngữ, sau này thất nghiệp qua làm Js cũng biết code coroutine
    
## Flutter
1. [Dart SDK](https://github.com/dart-lang/sdk)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L1517)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L25)
    - [Code native để kết nối socket nè](https://github.com/dart-lang/sdk/blob/81c3e8cbb42f9cd6d2c1a7b4f95a7eb70fa3f64c/runtime/bin/socket.cc#L362)
    