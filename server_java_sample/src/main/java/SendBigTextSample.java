import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.HashMap;
import java.util.Map;

public class SendBigTextSample {

    public static void main(String[] args) throws APIConnectionException, APIRequestException {

        String appkey = args[0];
        String secret = args[1];
        String registrationId = args[2];

        JPushClient jpushClient = new JPushClient(secret, appkey);

        AudienceTarget target = AudienceTarget.registrationId(registrationId);
        Audience audience = Audience.newBuilder()
                .addAudienceTarget(target)
                .build();

        Map<String, String> extras = new HashMap<>();
        extras.put("key1", "val1");
        extras.put("key2", "val2");

        // styleに何を入れるのかドキュメントが見つけられませんでした。
        // AndroidNotification.javaにstyleは0 ~ 4とのコメントがありました。
        // Big Text Styleは1のようです
        AndroidNotification androidNotification = AndroidNotification.newBuilder()
                .setStyle(1)
                .setTitle("＊BigText＊Title＊")
                .setAlert("＊＊alert:2＊＊")
                .setBigText("BigText🐼Line1、\nBigText🐨Line2\nBigText🐯Line3")
                .build();

        Notification notification =  Notification.newBuilder()
                .addPlatformNotification(androidNotification)
                .build();

        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(audience)
                .setNotification(notification)
                .build();

        PushResult result = jpushClient.sendPush(payload);
        System.out.println(result);
    }
}
