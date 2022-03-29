import 'dart:io';
import 'dart:ui';

import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_activity/app_router.dart';

class HomePage extends StatefulWidget {
  HomePage({Key? key}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _counter = 0;
  static const platform = MethodChannel('com.huynn109/native');
  String _batteryLevel = 'Unknown battery level.';

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        print("WillPopScope");
        await SystemChannels.platform
            .invokeMethod<void>('SystemNavigator.pop', true);
        return false;
      },
      child: Scaffold(
        appBar: AppBar(
          title: Text("widget.title"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                'You have pushed the button this many times:',
              ),
              Text('Router ~> ${ModalRoute.of(context)?.settings.name}'),
              Text('Router ~> ${window.defaultRouteName}'),
              Text(
                '$_counter',
                style: Theme.of(context).textTheme.headline4,
              ),
              OutlinedButton(
                onPressed: () {
                  context.router.push(DetailRoute());
                },
                child: Text("Go to Detail"),
              ),
              OutlinedButton(
                onPressed: _getBatteryLevel,
                child: Text("Battery level: $_batteryLevel"),
              ),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: _incrementCounter,
          tooltip: 'Increment',
          child: Icon(Icons.add),
        ), // This trailing comma makes auto-formatting nicer for build methods.
      ),
    );
  }

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await platform.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    setState(() {
      _batteryLevel = batteryLevel;
    });
  }
}
