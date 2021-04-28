
int calculate() {
  return 6 * 7;
}

Future<void> delayFunc([int totalAmount]) async{
  await Future.delayed(Duration(seconds: 1));
  print('delay ${++totalAmount}');
}
