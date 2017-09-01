import cn.jpush.api.JPushClient
import cn.jpush.api.push.model.Platform
import cn.jpush.api.push.model.PushPayload
import cn.jpush.api.push.model.audience.Audience
import cn.jpush.api.push.model.audience.AudienceTarget
import cn.jpush.api.push.model.notification.AndroidNotification
import cn.jpush.api.push.model.notification.Notification

fun main(args: Array<String>) {

    val appkey = args[0]
    val secret = args[1]
    val registrationId = args[2]

    val jpushClient = JPushClient(secret, appkey)

    val audience = Audience.newBuilder()
            .addAudienceTarget(AudienceTarget.registrationId(registrationId))
            .build()

    val androidNotification = AndroidNotification.newBuilder()
            .setStyle(1)//maybe Big Text Style
            .setTitle("＊BigText＊Title＊")
            .setAlert("＊＊alert:2＊＊")
            .setBigText("""BigText🐼Line1
BigText🐨Line2
BigText🐯Line3""".trimMargin())
            .addExtras(mapOf("key1" to "val1", "key2" to "val2"))
            .build()

    val notification = Notification.newBuilder()
            .addPlatformNotification(androidNotification)
            .build()

    val payload = PushPayload.newBuilder()
            .setPlatform(Platform.android())
            .setAudience(audience)
            .setNotification(notification)
            .build()

    val result = jpushClient.sendPush(payload)
    println(result)
}