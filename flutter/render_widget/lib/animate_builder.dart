import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: AnimateBuilder(),
      debugShowCheckedModeBanner: false,
    ),
  );
}

class AnimateBuilder extends StatefulWidget {
  const AnimateBuilder({Key? key}) : super(key: key);

  @override
  State<AnimateBuilder> createState() => _AnimateBuilderState();
}

class _AnimateBuilderState extends State<AnimateBuilder>
    with SingleTickerProviderStateMixin {
  late AnimationController animationController;
  late Animation<Color?> animateColor;

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(vsync: this, duration: Duration(seconds: 2))
          ..addListener(() {
            setState(() {});
          });
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
      appBar: CustomAppBar(
        height: kToolbarHeight,
        child: AnimatedBuilder(
          builder: (context, child) {
            return AppBar(
              title: child,
              backgroundColor: animateColor.value,
            );
          },
          animation: animateColor,
          child: Text("AnimatedBuilder"),
        ),
      ),
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ConstrainedBox(
              constraints: BoxConstraints(maxWidth: 400),
              child: AnimatedBuilder(
                builder: (context, child) {
                  return MaterialButton(
                    color: animateColor.value,
                    onPressed: () {
                      if (animationController.status ==
                          AnimationStatus.completed) {
                        animationController.reverse();
                      } else {
                        animationController.forward();
                      }
                    },
                    child: child,
                  );
                },
                animation: animateColor,
                child: const Text(
                  "Change color",
                  style: TextStyle(color: Colors.white),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class CustomAppBar extends PreferredSize {
  @override
  final Widget child;
  final double height;

  CustomAppBar({
    required this.height,
    required this.child,
  }) : super(child: child, preferredSize: Size.fromHeight(height));
}
