//
//  FlutterViewController.swift
//  ios
//
//  Created by Huy Nguyen on 29/03/2022.
//

import UIKit
import Flutter

class MyFlutterViewController: FlutterViewController {
    var flutterChannel: FlutterMethodChannel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let batteryChannel = FlutterMethodChannel(name: "com.huynn109/native",
                                                  binaryMessenger: self.binaryMessenger)
        batteryChannel.setMethodCallHandler({
            (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
            // Note: this method is invoked on the UI thread.
            // Handle battery messages.
            // Note: this method is invoked on the UI thread.
            guard call.method == "getBatteryLevel" else {
                result(FlutterMethodNotImplemented)
                return
            }
            self.receiveBatteryLevel(result: result)
        })
    }
    
    private func receiveBatteryLevel(result: FlutterResult) {
        let device = UIDevice.current
        device.isBatteryMonitoringEnabled = true
        if device.batteryState == UIDevice.BatteryState.unknown {
            result(FlutterError(code: "UNAVAILABLE",
                                message: "Battery info unavailable",
                                details: nil))
        } else {
            result(Int(device.batteryLevel * 100))
        }
    }
}

