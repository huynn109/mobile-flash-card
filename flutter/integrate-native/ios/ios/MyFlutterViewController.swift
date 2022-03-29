//
//  FlutterViewController.swift
//  ios
//
//  Created by Huy Nguyen on 29/03/2022.
//

import UIKit
import Flutter
import Foundation
import FlutterPluginRegistrant

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
            //            guard call.method == "getBatteryLevel" else {
            //                result(FlutterMethodNotImplemented)
            //                return
            //            }
            //            self.receiveBatteryLevel(result: result)
            print(call.method)
            switch call.method {
            case "enableBack":
                self.enableBack()
                
            case "disableBack":
                self.disableBack()
                
            case "getBatteryLevel":
                self.receiveBatteryLevel(result: result)
            default:
                result(FlutterMethodNotImplemented)
                break;
            }
        })
        
    }
    
    private func enableBack(){
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
        self.navigationController?.interactivePopGestureRecognizer?.delegate = self
    }
    
    private func disableBack(){
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false
        self.navigationController?.interactivePopGestureRecognizer?.delegate = self
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
    
    private func newBackFlutter(_ animated: Bool = true) {
        (UIApplication.shared.delegate as! AppDelegate).flutterVC.navigationController?.popViewController(animated: animated)
        
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false
        self.navigationController?.interactivePopGestureRecognizer?.delegate = nil
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false
        self.navigationController?.interactivePopGestureRecognizer?.delegate = nil
    }
}

