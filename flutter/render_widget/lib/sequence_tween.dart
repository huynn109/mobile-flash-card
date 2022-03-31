import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: SequenceTween(),
    ),
  );
}

class SequenceTween extends StatefulWidget {
  const SequenceTween({Key? key}) : super(key: key);

  @override
  State<SequenceTween> createState() => _SequenceTweenState();
}

class _SequenceTweenState extends State<SequenceTween>
    with TickerProviderStateMixin {
  late AnimationController animationController;
  late AnimationController animationControllerPosition;
  late Animation<Color?> animationColor;
  late Animation<Offset> animationPosition;
  static final colors = [
    Colors.red,
    Colors.orange,
    Colors.yellow,
    Colors.green,
    Colors.blue,
    Colors.indigo,
    Colors.purple,
  ];

  @override
  void initState() {
    super.initState();

    final sequenceItems = <TweenSequenceItem<Color?>>[];

    for (var i = 0; i < colors.length; i++) {
      final beginColor = colors[i];
      final endColor = colors[(i + 1) % colors.length];
      final weight = 1 / colors.length;

      sequenceItems.add(
        TweenSequenceItem<Color?>(
          weight: weight,
          tween: ColorTween(begin: beginColor, end: endColor),
        ),
      );
    }

    animationController = AnimationController(
      vsync: this,
      duration: Duration(seconds: colors.length),
    );
    animationControllerPosition = AnimationController(
      vsync: this,
      duration: Duration(seconds: 2),
    );
    animationColor = TweenSequence(sequenceItems).animate(animationController);
    animationPosition = Tween(begin: Offset(0, 0), end: Offset(0, -16))
        .animate(animationControllerPosition);
  }

  @override
  void dispose() {
    animationController.dispose();
    animationControllerPosition.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Sequence Tween"),
      ),
      body: Container(
        child: Center(
          child: ConstrainedBox(
            constraints: BoxConstraints(
              maxWidth: 400,
            ),
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                AnimatedBuilder(
                  builder: (context, child) {
                    return Text(
                      "Sequence Tween",
                      style: TextStyle(
                        color: animationColor.value,
                      ),
                    );
                  },
                  animation: animationColor,
                ),
                SlideTransition(
                  position: animationPosition,
                  child: Text(
                    "Start slide",
                  ),
                ),
                OutlinedButton(
                  onPressed: () async {
                    await animationController.forward();
                    animationController.reset();
                  },
                  child: Text("Start animation sequence"),
                ),
                OutlinedButton(
                  onPressed: () {
                    animationControllerPosition.repeat(reverse: true);
                  },
                  child: Text("Start animation slide"),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
