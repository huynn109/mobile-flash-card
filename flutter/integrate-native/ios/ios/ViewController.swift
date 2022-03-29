//
//  ViewController.swift
//  ios
//
//  Created by Huy Nguyen on 29/03/2022.
//

import UIKit
import Flutter

class ViewController: UIViewController {
    var flutterChannel: FlutterMethodChannel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Make a button to call the showFlutter function when pressed.
        let button = UIButton(type:UIButton.ButtonType.custom)
        button.addTarget(self, action: #selector(showFlutter), for: .touchUpInside)
        button.setTitle("Show Flutter!", for: UIControl.State.normal)
        button.frame = CGRect(x: 80.0, y: 210.0, width: 160.0, height: 40.0)
        button.center.x = self.view.frame.midX
        button.center.y = self.view.frame.midY
        button.backgroundColor = UIColor.blue
        self.view.addSubview(button)
    }
    
    @objc func showFlutter() {
        let flutterEngine = (UIApplication.shared.delegate as! AppDelegate).flutterEngine
        let flutterViewController =
        MyFlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
        present(flutterViewController, animated: true, completion: nil)
    }
}

