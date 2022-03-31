import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: CustomTween(),
      debugShowCheckedModeBanner: false,
    ),
  );
}

const String textStr =
    '''Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium
doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore
veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim
ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia
consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque
porro quisquam est, qui dolorem ipsum, quia dolor sit amet consectetur
adipisci[ng] velit, sed quia non-numquam [do] eius modi tempora inci[di]dunt, ut
labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam,
quis nostrum[d] exercitationem ullam corporis suscipit laboriosam, nisi ut
aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in
ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui
dolorem eum fugiat, quo voluptas nulla pariatur?''';

class WriterTween extends Tween<String> {
  WriterTween({String begin = '', String end = ''})
      : super(begin: begin, end: end);

  @override
  String lerp(double t) {
    var cutoff = (end!.length * t).round();
    return end!.substring(0, cutoff);
  }
}

class CustomTween extends StatefulWidget {
  const CustomTween({Key? key}) : super(key: key);

  @override
  State<CustomTween> createState() => _CustomTweenState();
}

class _CustomTweenState extends State<CustomTween>
    with SingleTickerProviderStateMixin {
  late AnimationController animationController;
  late Animation<String> writerTween;

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(vsync: this, duration: Duration(seconds: 5))
          ..addListener(() {
            setState(() {});
          });
    writerTween =
        WriterTween(begin: "", end: textStr).animate(animationController);
  }

  @override
  void dispose() {
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Custom Tween"),
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Container(
            alignment: Alignment.bottomLeft,
            child: SingleChildScrollView(
              child: Center(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    AnimatedBuilder(
                      builder: (context, child) {
                        return Text(writerTween.value);
                      },
                      animation: writerTween,
                    ),
                    OutlinedButton(
                      onPressed: () {
                        if (animationController.status ==
                            AnimationStatus.completed) {
                          animationController.reverse();
                        } else {
                          animationController.forward();
                        }
                      },
                      child: Text("Start Animation"),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
