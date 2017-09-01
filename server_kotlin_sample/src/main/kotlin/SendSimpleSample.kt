import cn.jpush.api.JPushClient
import cn.jpush.api.push.model.Platform
import cn.jpush.api.push.model.PushPayload
import cn.jpush.api.push.model.audience.Audience
import cn.jpush.api.push.model.audience.AudienceTarget
import cn.jpush.api.push.model.notification.Notification

import java.util.HashMap


fun main(args: Array<String>) {

    val appkey = args[0]
    val secret = args[1]
    val registrationId = args[2]

    val jpushClient = JPushClient(secret, appkey)

    val target = AudienceTarget.registrationId(registrationId)
    val audience = Audience.newBuilder()
            .addAudienceTarget(target)
            .build()

    val extras = mapOf("key1" to "val1", "key2" to "val2")

    val payload = PushPayload.newBuilder()
            .setPlatform(Platform.android())
            .setAudience(audience)
            .setNotification(Notification.android("＊＊alert:1＊＊", "＊Simple＊Title", extras))
            .build()

    val result = jpushClient.sendPush(payload)
    println(result)
}
