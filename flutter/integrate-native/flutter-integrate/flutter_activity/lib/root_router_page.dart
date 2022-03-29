import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter_activity/app_router.dart';
import 'package:flutter_activity/root_router_bloc.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class RootRouterPage extends StatelessWidget implements AutoRouteWrapper {
  const RootRouterPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: GestureDetector(
            child: Text("Root stack"),
            onTap: () {
              context.router.push(HomeRoute());
            }),
      ),
    );
  }

  @override
  Widget wrappedRoute(BuildContext context) {
    return BlocProvider(
      create: (context) => RootRouterBloc(),
      child: this,
    );
  }
}
