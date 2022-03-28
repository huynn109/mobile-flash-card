import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter_activity/app_router.dart';

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
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or press Run > Flutter Hot Reload in a Flutter IDE). Notice that the
        // counter didn't reset back to zero; the application is not restarted.
        primarySwatch: Colors.blue,
      ),
      routerDelegate: appRouter.delegate(),
      routeInformationParser: appRouter.defaultRouteParser(),
    );
  }
}
