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
    }
    
    @objc func showFlutter() {
        let flutterEngine = (UIApplication.shared.delegate as! AppDelegate).flutterEngine
        flutterEngine.viewController = nil
        flutterViewController =
        FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
        flutterViewController.modalPresentationStyle = .fullScreen
        flutterViewController.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
        self.navigationController?.pushViewController(flutterViewController, animated: true)
        self.navigationController?.setNavigationBarHidden(true, animated: false)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        self.navigationController?.interactivePopGestureRecognizer?.isEnabled = true
        self.navigationController?.interactivePopGestureRecognizer?.delegate = self
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
    }
    
    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        return self.navigationController?.viewControllers.count ?? 0 > 1
    }
    
}

