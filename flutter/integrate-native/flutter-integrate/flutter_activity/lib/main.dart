import 'dart:io';
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
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      routerDelegate: appRouter.delegate(),
      routeInformationParser: appRouter.defaultRouteParser(),
    );
  }
}
