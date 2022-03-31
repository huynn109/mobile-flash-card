import 'package:flutter/material.dart';

void main() => runApp(
      MaterialApp(
        home: TweenController(),
        debugShowCheckedModeBanner: false,
      ),
    );

class TweenController extends StatefulWidget {
  const TweenController({Key? key}) : super(key: key);

  @override
  State<TweenController> createState() => _TweenControllerState();
}

class _TweenControllerState extends State<TweenController>
    with SingleTickerProviderStateMixin {
  late AnimationController animationController;
  late Animation<double> animate;
  late Animation<Color?> animateColor;

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(vsync: this, duration: Duration(seconds: 2))
          ..addListener(() {
            setState(() {});
          });
    animate = Tween(begin: 0.0, end: 10000.0).animate(animationController);
    animateColor = ColorTween(begin: Colors.blue, end: Colors.red)
        .animate(animationController);
  }

  @override
  void dispose() {
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ConstrainedBox(
              constraints: BoxConstraints(maxWidth: 400),
              child: Text(
                "Animate ${animate.value.toStringAsFixed(2)}",
                style: TextStyle(color: animateColor.value),
              ),
            ),
            OutlinedButton(
              onPressed: () {
                if (animationController.isAnimating) {
                  animationController.stop();
                  return;
                }
                animationController.status == AnimationStatus.completed
                    ? animationController.reverse()
                    : animationController.forward();
              },
              child: Text("Start animation"),
            ),
          ],
        ),
      ),
    );
  }
}
