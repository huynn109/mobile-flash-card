## Flutter
1. [Dart SDK](https://github.com/dart-lang/sdk)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L1517)
    - [Từ Dart IO gọi qua native](https://github.com/dart-lang/sdk/blob/bddded13aad0ee8b1bff7f235a548df5228e5df6/sdk/lib/_internal/vm/bin/socket_patch.dart#L25)
    - [Code native để kết nối socket nè](https://github.com/dart-lang/sdk/blob/81c3e8cbb42f9cd6d2c1a7b4f95a7eb70fa3f64c/runtime/bin/socket.cc#L362)
2. Widget
    - Flutter render widget như thế nào?
      `- Widget chỉ là một bản thiết kế, dựa vào widget Flutter sẽ tiến hành vẽ lên UI nhờ vào Element và RenderObject
       - Element: Là đại diện cho widget biểu diển tại một vị trí của widget trên cây (Giữ ref của Widget và Render Object)
       - Render Object: Sẽ tiến hành tô vẽ và căn chỉnh kích thước cho Element`
    
