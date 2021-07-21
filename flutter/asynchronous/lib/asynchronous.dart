int calculate() {
  return 6 * 7;
}

var numTmp = 0;

Future<void> delayFunc([int totalAmount]) async {
  await Future.delayed(Duration(seconds: 1));
  print('delay ${++totalAmount}');
}

Future<void> delayFunc1([int totalAmount]) async {
  await Future.delayed(Duration(seconds: 1));
  print('delay 1');
  numTmp++;
}

Future<void> delayFunc2([int totalAmount]) async {
  await Future.delayed(Duration(seconds: 1));
  print('delay 2');
  print('delay 2 $numTmp');
}
