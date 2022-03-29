import 'dart:io';
import 'dart:ui';

import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_activity/app_router.dart';
import 'package:flutter_activity/router_observer.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  final AppRouter appRouter = AppRouter();

  @override
  Widget build(BuildContext context) {
    print('Router ~> ${ModalRoute.of(context)?.settings.name}');
    print('Router ~> ${window.defaultRouteName}');
    return MaterialApp.router(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      routerDelegate: AutoRouterDelegate(
        appRouter,
        // this should always return new instances
        navigatorObservers: () => [RouterObserver()],
      ),
      routeInformationParser: appRouter.defaultRouteParser(),
    );
  }
}
