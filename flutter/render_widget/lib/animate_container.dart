import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(AnimateContainerDemo());
}

Color generateColor() => Color(0xFFFFFFFF & Random().nextInt(0xFFFFFFFF));

Color generateTextColor() => Color(0xFFFFFFFF & Random().nextInt(0xFFFFFFFF));

class AnimateContainerDemo extends StatefulWidget {
  const AnimateContainerDemo({Key? key}) : super(key: key);

  @override
  State<AnimateContainerDemo> createState() => _AnimateContainerDemoState();
}

class _AnimateContainerDemoState extends State<AnimateContainerDemo> {
  var color = generateColor();
  var textColor = generateTextColor();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("AnimatedContainer"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SizedBox(
                width: 100,
                height: 100,
                child: AnimatedContainer(
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(8.0),
                    color: color,
                  ),
                  duration: Duration(
                    seconds: 1,
                  ),
                  child: Center(
                    child: Text(
                      "Hello Hello Hello Hello Hello Hello Hello",
                      textAlign: TextAlign.center,
                      style: TextStyle(color: textColor),
                    ),
                  ),
                ),
              ),
              OutlinedButton(
                onPressed: () {
                  setState(() {
                    color = generateColor();
                    textColor = generateTextColor();
                  });
                },
                child: Text("Update state"),
              )
            ],
          ),
        ),
      ),
    );
  }
}
