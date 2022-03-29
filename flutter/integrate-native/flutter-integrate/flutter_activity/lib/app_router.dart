import 'package:auto_route/auto_route.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_activity/app_router_name.dart';
import 'package:flutter_activity/detail_page.dart';
import 'package:flutter_activity/home_page.dart';
import 'package:flutter_activity/root_router_page.dart';

part 'app_router.gr.dart';

@AdaptiveAutoRouter(
  replaceInRouteName: 'Page,Route',
  routes: <AutoRoute>[
    AutoRoute(
      page: RootRouterPage,
      name: Routes.ROOT_ROUTER,
      initial: true,
    ),
    AutoRoute(
      page: HomePage,
      name: Routes.HOME,
    ),
    AutoRoute(page: DetailPage, name: Routes.DETAIL),
    RedirectRoute(path: '*', redirectTo: '/'),
  ],
)
// extend the generated private router
class AppRouter extends _$AppRouter {}
