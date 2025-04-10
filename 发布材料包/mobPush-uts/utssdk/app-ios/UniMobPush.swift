//
//  UniMobPush.swift
//  DDDDDemo
//
//  Created by MobTech-iOS on 2025/4/9.
//

import MobPush
import Foundation
import MOBFoundation

public protocol UniMobPushEventReceiver {
    func onCustomMessageReceive(_ msg: String)
    func onNotifyMessageReceive(_ msg: String)
    func onNotifyMessageOpenedReceive(_ msg: String)
    func onTagsEventCallBack(_ tags: String, _ operation: Int, _ errorCode: Int)
    func onAliasEventCallBack(_ alias: String, _ operation: Int, _ errorCode: Int)
}

public class UniMobPush {
    static let SharedInstance = UniMobPush()
    
    private var receiver: UniMobPushEventReceiver?
    static func initApp(_ appKey: String?, _ appSecret: String?) {
        if let key = appKey, let secret = appSecret {
            MobSDK.initKey(key, secret: secret)
        }
    }
    
    static func agreePrivacy(_ isAgree: Bool = true, _ callback: @escaping (Bool) -> Void) {
        MobSDK.uploadPrivacyPermissionStatus(isAgree, onResult: callback)
    }
    
    static func setAPNsForProduction(_ isPro: Bool) {
        MobPush.setAPNsForProduction(isPro)
    }
    
    static func setupNotificationWithType(_ type: Int = 7) {
        let config = MPushNotificationConfiguration()
        config.types = MPushAuthorizationOptions(rawValue: UInt(type))
        MobPush.setupNotification(config)
    }
    
    static func getRegistrationID(_ callbck: @escaping (String, Int) -> Void) {
        MobPush.getRegistrationID { rid, err in
            callbck(rid ?? "", (err as? NSError)?.code ?? 0)
        }
    }
    
    /// Push
    static func stopPush() {
        MobPush.stop()
    }
    
    static func restartPush() {
        MobPush.restart()
    }
    
    static func isPushStoped() -> Bool {
        return MobPush.isPushStopped()
    }
    
    /// Badge
    static func setBadge(_ num: Int = 0) {
        MobPush.setBadge(num)
    }
    
    static func getServiceBadge(_ callback: @escaping (Int, Int)->Void) {
        MobPush.getBadgeWithhandler { count, err in
            callback(count, (err as? NSError)?.code ?? 0)
        }
    }
    
    /// Tags
    static func addTagsToService(_ tags: [String] = []) {
        let intance = UniMobPush.SharedInstance
        MobPush.addTags(tags) { err in
            intance.receiver?.onTagsEventCallBack(tags.joined(separator: ";"), 1, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func getTagsFromService() {
        let intance = UniMobPush.SharedInstance
        MobPush.getTagsWithResult { tags, err in
            var str = ""
            if let arr = tags as? [String] {
                str = arr.joined(separator: ";")
            }
            intance.receiver?.onTagsEventCallBack(str, 0, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func deleteTagsFromService(_ tags: [String] = []) {
        let intance = UniMobPush.SharedInstance
        MobPush.deleteTags(tags) { err in
            intance.receiver?.onTagsEventCallBack(tags.joined(separator: ";"), 2, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func cleanAllTags() {
        let intance = UniMobPush.SharedInstance
        MobPush.cleanAllTags { err in
            intance.receiver?.onTagsEventCallBack("", 3, (err as? NSError)?.code ?? 0)
        }
    }
    
    /// Alias
    static func addAliasToService(_ alias: String? = "") {
        let intance = UniMobPush.SharedInstance
        MobPush.setAlias(alias) { err in
            intance.receiver?.onAliasEventCallBack(alias ?? "", 1, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func getAliasFromService() {
        let intance = UniMobPush.SharedInstance
        MobPush.getAliasWithResult { alias, err in
            intance.receiver?.onAliasEventCallBack(alias ?? "", 0, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func deleteAliasFromService() {
        let intance = UniMobPush.SharedInstance
        MobPush.deleteAlias { err in
            intance.receiver?.onAliasEventCallBack("", 2, (err as? NSError)?.code ?? 0)
        }
    }
    
    static func addObserverToMsg(_ observer: UniMobPushEventReceiver?) {
        let center = NotificationCenter.default
        let instance = UniMobPush.SharedInstance
        let name = NSNotification.Name(rawValue: "MobPushDidReceiveMessageNotification")
        
        instance.receiver = observer
        center.addObserver(instance, selector: #selector(_UniMPushMessage(_:)), name:name, object:nil)
    }
    
    @objc private func _UniMPushMessage(_ noti: NSNotification?) {
        if let message = noti?.object as? MPushMessage {
            let instance = UniMobPush.SharedInstance
            let msgInfo = MOBFJson.jsonString(from: message.convertDictionary()) ?? ""
            switch message.messageType {
            case .custom:
                instance.receiver?.onCustomMessageReceive(msgInfo)
            case .apns:
                instance.receiver?.onNotifyMessageReceive(msgInfo)
            case .clicked:
                instance.receiver?.onNotifyMessageOpenedReceive(msgInfo)
            default:
                print("Don't support msg type: \(message.messageType).")
            }
        }
    }
    
    deinit {
        NotificationCenter.default.removeObserver(UniMobPush.SharedInstance)
    }
}
