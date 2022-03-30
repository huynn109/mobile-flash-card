import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: FirstPage(),
      debugShowCheckedModeBanner: false,
    ),
  );
}

class FirstPage extends StatelessWidget {
  const FirstPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("PageRouteBuilder"),
      ),
      body: Center(
        child: OutlinedButton(
          onPressed: () {
            Navigator.push(
              context,
              pageBuilder(
                SecondPage(),
              ),
            );
          },
          child: Text("Goto page 2"),
        ),
      ),
    );
  }

  PageRouteBuilder<SlideTransition> pageBuilder(Widget child) {
    return PageRouteBuilder(
        fullscreenDialog: true,
        pageBuilder: (context, animation, secondaryAnimation) {
          return child;
        },
        transitionsBuilder: (context, animation, secondAnimation, child) {
          var tween =
              Tween<Offset>(begin: const Offset(0.0, 1.0), end: Offset.zero);
          var curveTween = CurveTween(curve: Curves.ease);

          var opacity = Tween<double>(begin: 0, end: 1).animate(animation);
          return FadeTransition(
            opacity: opacity,
            child: child,
          );
        });
  }
}

class SecondPage extends StatelessWidget {
  const SecondPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Page 2"),
            OutlinedButton(
              onPressed: () => Navigator.of(context).pop(),
              child: Text("Back"),
            ),
          ],
        ),
      ),
    );
  }
}
