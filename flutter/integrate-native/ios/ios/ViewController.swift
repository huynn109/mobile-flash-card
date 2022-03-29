//
//  ViewController.swift
//  ios
//
//  Created by Huy Nguyen on 29/03/2022.
//

import UIKit
import Flutter

class ViewController: UIViewController, UIGestureRecognizerDelegate {
    var flutterViewController: FlutterViewController!
    var engine: FlutterEngine!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Make a button to call the showFlutter function when pressed.
        let button = UIButton(type:UIButton.ButtonType.custom)
        button.addTarget(self, action: #selector(showFlutter), for: .touchUpInside)
        button.setTitle("Show!", for: UIControl.State.normal)
        button.frame = CGRect(x: 80.0, y: 210.0, width: 160.0, height: 40.0)
        button.center.x = self.view.frame.midX
        button.center.y = self.view.frame.midY
        button.backgroundColor = UIColor.blue
        self.view.addSubview(button)
        self.view.backgroundColor = UIColor.white
        
        engine = (UIApplication.shared.delegate as! AppDelegate).flutterEngine
        let batteryChannel = FlutterMethodChannel(name: "com.huynn109/native",
                                                  binaryMessenger: engine.binaryMessenger)
        batteryChannel.setMethodCallHandler({
            (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
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
    
    @objc func showFlutter() {
        engine.viewController = nil
        flutterViewController =
        FlutterViewController(engine: engine, nibName: nil, bundle: nil)
        flutterViewController.modalPresentationStyle = .fullScreen
        self.navigationController?.pushViewController(flutterViewController, animated: true)
        self.navigationController?.setNavigationBarHidden(true, animated: false)
        self.enableBack()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
    }
    
    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return self.navigationController?.viewControllers.count ?? 0 > 1
    }
    
    private func enableBack(){
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
        self.navigationController?.interactivePopGestureRecognizer?.delegate = self
    }
    
    private func disableBack(){
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = false
        self.navigationController?.interactivePopGestureRecognizer?.delegate = nil
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

