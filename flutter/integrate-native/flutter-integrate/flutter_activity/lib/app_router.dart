import 'package:auto_route/auto_route.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_activity/app_router_name.dart';
import 'package:flutter_activity/detail_page.dart';
import 'package:flutter_activity/home_page.dart';

part 'app_router.gr.dart';

@AdaptiveAutoRouter(
  replaceInRouteName: 'Page,Route',
  routes: <AutoRoute>[
    AutoRoute(
      page: HomePage,
      name: Routes.HOME,
      initial: true,
    ),
    AutoRoute(page: DetailPage, name: Routes.DETAIL),
    RedirectRoute(path: '*', redirectTo: '/'),
  ],
)
// extend the generated private router
class AppRouter extends _$AppRouter {}
