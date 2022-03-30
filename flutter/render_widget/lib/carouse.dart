import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(CarouseDemo());
}

class CarouseDemo extends StatelessWidget {
  CarouseDemo({Key? key}) : super(key: key);

  static final List<String> fileNames = [
    'assets/2.jpg',
    'assets/3.jpg',
    'assets/4.jpg',
  ];

  final List<Widget> images =
      fileNames.map((file) => Image.asset(file, fit: BoxFit.contain)).toList();

  @override
  Widget build(context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Carousel Demo'),
        ),
        body: Center(
          child: Padding(
            padding: const EdgeInsets.all(16),
            child: CarouseBuilder(
              itemBuilder: itemBuilder,
            ),
          ),
        ),
      ),
    );
  }

  Widget itemBuilder(BuildContext context, int index) {
    return images[index % fileNames.length];
  }
}

class CarouseBuilder extends StatefulWidget {
  final IndexedWidgetBuilder itemBuilder;

  const CarouseBuilder({Key? key, required this.itemBuilder}) : super(key: key);

  @override
  _CarouselBuilderState createState() => _CarouselBuilderState();
}

class _CarouselBuilderState extends State<CarouseBuilder> {
  late final PageController _controller;
  late int _currentPage;
  bool _pageHasChanged = false;

  @override
  void initState() {
    super.initState();
    _currentPage = 0;
    _controller = PageController(
      viewportFraction: .85,
      initialPage: _currentPage,
    );
  }

  @override
  Widget build(context) {
    var size = MediaQuery.of(context).size;
    return PageView.builder(
      onPageChanged: (value) {
        setState(() {
          _pageHasChanged = true;
          _currentPage = value;
        });
      },
      controller: _controller,
      scrollBehavior: ScrollConfiguration.of(context).copyWith(
        dragDevices: {
          PointerDeviceKind.touch,
          PointerDeviceKind.mouse,
        },
      ),
      itemBuilder: (context, index) => AnimatedBuilder(
        animation: _controller,
        builder: (context, child) {
          var result = _pageHasChanged ? _controller.page! : _currentPage * 1.0;

          // The horizontal position of the page between a 1 and 0
          var value = result - index;
          value = (1 - (value.abs() * .5)).clamp(0.0, 1.0);

          return Center(
            child: SizedBox(
              height: Curves.easeOut.transform(value) * size.height,
              width: Curves.easeOut.transform(value) * size.width,
              child: child,
            ),
          );
        },
        child: widget.itemBuilder(context, index),
      ),
    );
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }
}
