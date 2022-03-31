import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(home: AnimateController()));
}

class AnimateController extends StatefulWidget {
  const AnimateController({Key? key}) : super(key: key);

  @override
  State<AnimateController> createState() => _AnimateControllerState();
}

class _AnimateControllerState extends State<AnimateController>
    with SingleTickerProviderStateMixin {
  late AnimationController controller;

  @override
  void initState() {
    super.initState();
    controller =
        AnimationController(vsync: this, duration: Duration(seconds: 1))
          ..addListener(() {
            print("listener ${controller.value}");
            print("listener ${controller.status}");
            setState(() {});
          });
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('AnimationController'),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ConstrainedBox(
              constraints: BoxConstraints(maxWidth: 400),
              child: Text(
                "Animate Text: ${controller.value.toStringAsFixed(2)}",
                textScaleFactor: 1 + controller.value,
              ),
            ),
            OutlinedButton(
              onPressed: () {
                if (controller.status == AnimationStatus.completed) {
                  controller.reverse();
                } else {
                  controller.forward();
                }
              },
              child: Text("Start animation"),
            ),
          ],
        ),
      ),
    );
  }
}
