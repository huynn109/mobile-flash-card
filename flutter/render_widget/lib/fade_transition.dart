import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: FadeTransitionDemo(),
    ),
  );
}

class FadeTransitionDemo extends StatefulWidget {
  const FadeTransitionDemo({Key? key}) : super(key: key);

  @override
  State<FadeTransitionDemo> createState() => _FadeTransitionDemoState();
}

class _FadeTransitionDemoState extends State<FadeTransitionDemo>
    with SingleTickerProviderStateMixin {
  late AnimationController animationController;
  late Animation<double> animation;
  late CurvedAnimation curvedAnimation;

  @override
  void initState() {
    super.initState();
    animationController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 500),
    );
    curvedAnimation =
        CurvedAnimation(parent: animationController, curve: Curves.easeIn);
    animation = Tween(begin: 1.0, end: 0.0).animate(curvedAnimation);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Fade transition"),
      ),
      body: Container(
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              FadeTransition(
                opacity: animation,
                child: Icon(
                  Icons.star,
                  size: 60,
                ),
              ),
              TextButton(
                onPressed: () {
                  animationController.repeat(reverse: true);
                },
                child: Text("Animation Button ${DateTime.now()}"),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
