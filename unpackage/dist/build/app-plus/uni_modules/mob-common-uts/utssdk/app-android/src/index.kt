@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER")
package uts.sdk.modules.mobCommonUts;
import com.mob.MobSDK;
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
typealias MobSDKInit = () -> Unit;
typealias MobSDKsubmitPolicyGrantResult = (granted: Boolean) -> Unit;
val mobSDKInit: MobSDKInit = fun(): Unit {
    MobSDK.init(UTSAndroid.getAppContext());
}
;
val mobSDKsubmitPolicyGrantResult: MobSDKsubmitPolicyGrantResult = fun(granted: Boolean): Unit {
    MobSDK.submitPolicyGrantResult(true);
}
;
fun mobSDKInitByJs(): Unit {
    return mobSDKInit();
}
fun mobSDKsubmitPolicyGrantResultByJs(granted: Boolean): Unit {
    return mobSDKsubmitPolicyGrantResult(granted);
}
