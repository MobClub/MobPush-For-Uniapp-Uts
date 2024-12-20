@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER")
package uts.sdk.modules.mobPushUts;
import android.content.Context;
import com.mob.MobSDK;
import com.mob.commons.MOBPUSH;
import com.mob.pushsdk.MobPush;
import com.mob.pushsdk.MobPushCallback;
import com.mob.pushsdk.MobPushCustomMessage;
import com.mob.pushsdk.MobPushNotifyMessage;
import com.mob.pushsdk.MobPushReceiver;
import io.dcloud.uniapp.*;
import io.dcloud.uniapp.extapi.*;
import io.dcloud.uts.*;
import io.dcloud.uts.Map;
import io.dcloud.uts.Set;
import io.dcloud.uts.UTSAndroid;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.async;
import kotlin.Array as KotlinArray;
open class UniMobPushCallback (
    open var onCallback: (res: Any) -> Unit,
) : UTSObject()
open class UniMobPushReceiver (
    open var onCustomMessageReceive: (message: String) -> Unit,
    open var onNotifyMessageReceive: (message: String) -> Unit,
    open var onNotifyMessageOpenedReceive: (message: String) -> Unit,
    open var onTagsCallback: (tags: UTSArray<String>, operation: Number, errorCode: Number) -> Unit,
    open var onAliasCallback: (alias: String, operation: Number, errorCode: Number) -> Unit,
) : UTSObject()
typealias RegistrationId = (callback: UniMobPushCallback) -> Unit;
typealias StopPush = () -> Unit;
typealias RestartPush = () -> Unit;
typealias IsPushStopped = (callback: UniMobPushCallback) -> Unit;
typealias SetAlias = (alias: String) -> Unit;
typealias GetAlias = () -> Unit;
typealias DeleteAlias = () -> Unit;
typealias AddTags = (tags: UTSArray<String>) -> Unit;
typealias GetTags = () -> Unit;
typealias DeleteTags = (tags: UTSArray<String>) -> Unit;
typealias CleanTags = () -> Unit;
typealias SetShowBadge = (showBadge: Boolean) -> Unit;
typealias GetShowBadge = (callback: UniMobPushCallback) -> Unit;
typealias SetSilenceTime = (startHour: Int, startMinute: Int, endHour: Int, endMinute: Int) -> Unit;
val getRegistrationID: RegistrationId = fun(callback: UniMobPushCallback) {
    setChannel();
    MobPush.getRegistrationId(unicallback<String>(callback));
}
;
fun addPushReceiver(receiver: UniMobPushReceiver) {
    setChannel();
    MobPush.addPushReceiver(unireceiver(receiver));
}
val stopPush: StopPush = fun(): Unit {
    setChannel();
    MobPush.stopPush();
}
;
val restartPush: RestartPush = fun(): Unit {
    setChannel();
    MobPush.restartPush();
}
;
val isPushStopped: IsPushStopped = fun(callback: UniMobPushCallback) {
    setChannel();
    MobPush.isPushStopped(unicallback<Boolean>(callback));
}
;
val setAlias: SetAlias = fun(alias: String): Unit {
    setChannel();
    MobPush.setAlias(alias);
}
;
val getAlias: GetAlias = fun(): Unit {
    setChannel();
    MobPush.getAlias();
}
;
val deleteAlias: DeleteAlias = fun(): Unit {
    setChannel();
    MobPush.deleteAlias();
}
;
val addTags: AddTags = fun(tags: UTSArray<String>): Unit {
    setChannel();
    MobPush.addTags(tags.toTypedArray());
}
;
val getTags: GetTags = fun(): Unit {
    setChannel();
    MobPush.getTags();
}
;
val deleteTags: DeleteTags = fun(tags: UTSArray<String>): Unit {
    setChannel();
    MobPush.deleteTags(tags.toTypedArray());
}
;
val cleanTags: CleanTags = fun(): Unit {
    setChannel();
    MobPush.cleanTags();
}
;
val setShowBadge: SetShowBadge = fun(isShow: Boolean): Unit {
    setChannel();
    MobPush.setShowBadge(isShow);
}
;
val getShowBadge: GetShowBadge = fun(callback: UniMobPushCallback) {
    setChannel();
    MobPush.getShowBadge(unicallback<Boolean>(callback));
}
;
val setSilenceTime: SetSilenceTime = fun(startHour: Int, startMinute: Int, endHour: Int, endMinute: Int): Unit {
    setChannel();
    MobPush.setSilenceTime(startHour, startMinute, endHour, endMinute);
}
;
fun setChannel() {
    UTSAndroid.getDispatcher("io").async(fun(_) {
        MobSDK.setChannel(MOBPUSH(), MobSDK.CHANNEL_UNIAPP);
    }
    , null);
}
open class unicallback<T> : MobPushCallback<T> {
    open var jsCallback: UniMobPushCallback;
    constructor(callback: UniMobPushCallback){
        this.jsCallback = callback;
    }
    override fun onCallback(data: T): Unit {
        this.jsCallback.onCallback(data as Any);
    }
}
open class unireceiver : MobPushReceiver {
    open var jsReceiver: UniMobPushReceiver;
    constructor(receive: UniMobPushReceiver){
        this.jsReceiver = receive;
    }
    override fun onCustomMessageReceive(context: Context, mobPushCustomMessage: MobPushCustomMessage): Unit {
        this.jsReceiver.onCustomMessageReceive(JSON.stringify(mobPushCustomMessage));
    }
    override fun onNotifyMessageReceive(context: Context, mobPushNotifyMessage: MobPushNotifyMessage): Unit {
        this.jsReceiver.onNotifyMessageReceive(JSON.stringify(mobPushNotifyMessage));
    }
    override fun onNotifyMessageOpenedReceive(context: Context, mobPushNotifyMessage: MobPushNotifyMessage): Unit {
        this.jsReceiver.onNotifyMessageOpenedReceive(JSON.stringify(mobPushNotifyMessage));
    }
    override fun onTagsCallback(context: Context, reassignedTags: KotlinArray<String>?, operation: Int, errorCode: Int): Unit {
        var tags = reassignedTags;
        if (tags == null) {
            tags = arrayOf("");
        }
        this.jsReceiver.onTagsCallback(UTSArray.fromNative(tags), operation, errorCode);
    }
    override fun onAliasCallback(context: Context, alias: String, operation: Int, errorCode: Int): Unit {
        this.jsReceiver.onAliasCallback(alias, operation, errorCode);
    }
}
open class UniMobPushCallbackJSONObject : UTSJSONObject() {
    open lateinit var onCallback: UTSCallback;
}
open class UniMobPushReceiverJSONObject : UTSJSONObject() {
    open lateinit var onCustomMessageReceive: UTSCallback;
    open lateinit var onNotifyMessageReceive: UTSCallback;
    open lateinit var onNotifyMessageOpenedReceive: UTSCallback;
    open lateinit var onTagsCallback: UTSCallback;
    open lateinit var onAliasCallback: UTSCallback;
}
fun getRegistrationIDByJs(callback: UniMobPushCallbackJSONObject): Unit {
    return getRegistrationID(UniMobPushCallback(onCallback = fun(res: Any): Unit {
        callback.onCallback(res);
    }
    ));
}
fun addPushReceiverByJs(receiver: UniMobPushReceiverJSONObject) {
    return addPushReceiver(UniMobPushReceiver(onCustomMessageReceive = fun(message: String): Unit {
        receiver.onCustomMessageReceive(message);
    }
    , onNotifyMessageReceive = fun(message: String): Unit {
        receiver.onNotifyMessageReceive(message);
    }
    , onNotifyMessageOpenedReceive = fun(message: String): Unit {
        receiver.onNotifyMessageOpenedReceive(message);
    }
    , onTagsCallback = fun(tags: UTSArray<String>, operation: Number, errorCode: Number): Unit {
        receiver.onTagsCallback(tags, operation, errorCode);
    }
    , onAliasCallback = fun(alias: String, operation: Number, errorCode: Number): Unit {
        receiver.onAliasCallback(alias, operation, errorCode);
    }
    ));
}
fun stopPushByJs(): Unit {
    return stopPush();
}
fun restartPushByJs(): Unit {
    return restartPush();
}
fun isPushStoppedByJs(callback: UniMobPushCallbackJSONObject): Unit {
    return isPushStopped(UniMobPushCallback(onCallback = fun(res: Any): Unit {
        callback.onCallback(res);
    }
    ));
}
fun setAliasByJs(alias: String): Unit {
    return setAlias(alias);
}
fun getAliasByJs(): Unit {
    return getAlias();
}
fun deleteAliasByJs(): Unit {
    return deleteAlias();
}
fun addTagsByJs(tags: UTSArray<String>): Unit {
    return addTags(tags);
}
fun getTagsByJs(): Unit {
    return getTags();
}
fun deleteTagsByJs(tags: UTSArray<String>): Unit {
    return deleteTags(tags);
}
fun cleanTagsByJs(): Unit {
    return cleanTags();
}
fun setShowBadgeByJs(showBadge: Boolean): Unit {
    return setShowBadge(showBadge);
}
fun getShowBadgeByJs(callback: UniMobPushCallbackJSONObject): Unit {
    return getShowBadge(UniMobPushCallback(onCallback = fun(res: Any): Unit {
        callback.onCallback(res);
    }
    ));
}
fun setSilenceTimeByJs(startHour: Int, startMinute: Int, endHour: Int, endMinute: Int): Unit {
    return setSilenceTime(startHour, startMinute, endHour, endMinute);
}
