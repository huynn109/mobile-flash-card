import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class RouterObserver extends AutoRouterObserver {
  var platform = MethodChannel("com.huynn109/native");

  @override
  void didPush(Route route, Route? previousRoute) {
    handleActionSwipeToBack(route);
    print('New route pushed: ${route.settings.name}');
  }

  // only override to observer tab routes
  @override
  void didInitTabRoute(TabPageRoute route, TabPageRoute? previousRoute) {
    print('Tab route visited: ${route.name}');
  }

  @override
  void didChangeTabRoute(TabPageRoute route, TabPageRoute previousRoute) {
    print('Tab route re-visited: ${route.name}');
  }

  @override
  void didPop(Route route, Route? previousRoute) {
    print('PreviousRoute did pop: ${previousRoute?.settings.name}');
    print('Route did pop: ${route.settings.name}');
    handleActionSwipeToBack(previousRoute);
    super.didPop(route, previousRoute);
  }

  Future<void> handleActionSwipeToBack(Route<dynamic>? route) async {
    if (route == null) return;
    if (route.settings.name == "RootRoute") {
      print("enableBack");
      var result = await platform.invokeMethod("enableBack");
      print(result);
    } else {
      print("disableBack");
      var result = await platform.invokeMethod("disableBack");
      print(result);
    }
  }
}
