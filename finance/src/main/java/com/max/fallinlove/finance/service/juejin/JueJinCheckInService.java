package com.max.fallinlove.finance.service.juejin;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.net.MediaType;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JueJinCheckInService {

    public void autoCheckInJueJin() {
        this.checkIn();

        String result = this.bugNotCollect();
        JueJinResult jueJinResult = JSONUtil.toBean(result, JueJinResult.class);
        for (BugNotCollectionRes notCollectionRes : jueJinResult.getData()) {
            this.collectBug(JSONUtil.toJsonStr(notCollectionRes));
        }

    }

    public static void main(String[] args) {
        String result = HttpRequest.post(
                "https://api.juejin.cn/user_api/v1/bugfix/not_collect?aid=2608&uuid=7121709547476125199&spider=0")
            .header("authority", "api.juejin.cn")
            .header("accept", "*/*")
            .header("accept-language", "zh-CN,zh;q=0.9")
            .header("content-type", "application/json")
            .header("cookie",
                "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304")
            .header("origin", "https://juejin.cn")
            .header("referer", "https://juejin.cn/")
            .header("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"macOS\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
            .header("x-secsdk-csrf-token", "000100000001fe081d00128fd65799b11b21ab1a35630d52316ede51f821e126b28140721c891745674f1d3d7314")
            .body("{}")
            .execute().body();
        System.out.println(result);
    }
    public void checkIn() {
        Map<String, String> body = new HashMap<>();
        String result = HttpRequest.post(
                "https://api.juejin.cn/growth_api/v1/check_in?aid=2608&uuid=7121709547476125199&spider=0")
            .header("authority", "api.juejin.cn")
            .header("accept", "*/*")
            .header("accept-language", "zh-CN,zh;q=0.9")
            .header("content-type", "application/json")
            .header("cookie",
                "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304; _gat=1")
            .header("origin", "https://juejin.cn")
            .header("referer", "https://juejin.cn/")
            .header("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"macOS\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
            .header("x-secsdk-csrf-token",
                "0001000000018c60690eb2143e9f9c878dc7508daa1a6d256eb9c7e6482a09009ece01406eaf174566a88de82807")
            .execute().body();
        System.out.println(result);
//        OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{}");
//        Request request = new Request.Builder()
//            .url("https://api.juejin.cn/growth_api/v1/check_in?aid=2608&uuid=7121709547476125199&spider=0")
//            .method("POST", body)
//            .addHeader("authority", "api.juejin.cn")
//            .addHeader("accept", "*/*")
//            .addHeader("accept-language", "zh-CN,zh;q=0.9")
//            .addHeader("content-type", "application/json")
//            .addHeader("cookie", "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304; _gat=1")
//            .addHeader("origin", "https://juejin.cn")
//            .addHeader("referer", "https://juejin.cn/")
//            .addHeader("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
//            .addHeader("sec-ch-ua-mobile", "?0")
//            .addHeader("sec-ch-ua-platform", "\"macOS\"")
//            .addHeader("sec-fetch-dest", "empty")
//            .addHeader("sec-fetch-mode", "cors")
//            .addHeader("sec-fetch-site", "same-site")
//            .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
//            .addHeader("x-secsdk-csrf-token", "0001000000018c60690eb2143e9f9c878dc7508daa1a6d256eb9c7e6482a09009ece01406eaf174566a88de82807")
//            .build();
//        Response response = client.newCall(request).execute();
    }

    public String bugNotCollect() {
        String result = HttpRequest.post(
                "https://api.juejin.cn/user_api/v1/bugfix/not_collect?aid=2608&uuid=7121709547476125199&spider=0")
            .header("authority", "api.juejin.cn")
            .header("accept", "*/*")
            .header("accept-language", "zh-CN,zh;q=0.9")
            .header("content-type", "application/json")
            .header("cookie",
                "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304")
            .header("origin", "https://juejin.cn")
            .header("referer", "https://juejin.cn/")
            .header("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"macOS\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
            .header("x-secsdk-csrf-token", "000100000001fe081d00128fd65799b11b21ab1a35630d52316ede51f821e126b28140721c891745674f1d3d7314")
            .body("{}")
            .execute().body();
        System.out.println(result);
        return result;
    }

    public void collectBug(String body) {

        String result = HttpRequest.post(
                "https://api.juejin.cn/user_api/v1/bugfix/collect?aid=2608&uuid=7121709547476125199&spider=0")
            .header("authority", "api.juejin.cn")
            .header("accept", "*/*")
            .header("accept-language", "zh-CN,zh;q=0.9")
            .header("content-type", "application/json")
            .header("cookie",
                "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304")
            .header("origin", "https://juejin.cn")
            .header("referer", "https://juejin.cn/")
            .header("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
            .header("sec-ch-ua-mobile", "?0")
            .header("sec-ch-ua-platform", "\"macOS\"")
            .header("sec-fetch-dest", "empty")
            .header("sec-fetch-mode", "cors")
            .header("sec-fetch-site", "same-site")
            .header("user-agent",
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
            .header("x-secsdk-csrf-token", "000100000001fe081d00128fd65799b11b21ab1a35630d52316ede51f821e126b28140721c891745674f1d3d7314")
            .body(body)
            .execute().body();
        System.out.println(result);
//        OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"bug_type\":11,\"bug_time\":1676649600}");
//        Request request = new Request.Builder()
//            .url("https://api.juejin.cn/user_api/v1/bugfix/collect?aid=2608&uuid=7121709547476125199&spider=0")
//            .method("POST", body)
//            .addHeader("authority", "api.juejin.cn")
//            .addHeader("accept", "*/*")
//            .addHeader("accept-language", "zh-CN,zh;q=0.9")
//            .addHeader("content-type", "application/json")
//            .addHeader("cookie", "_ga=GA1.2.660869139.1658152224; __tea_cookie_tokens_2608=%257B%2522web_id%2522%253A%25227121709547476125199%2522%252C%2522user_unique_id%2522%253A%25227121709547476125199%2522%252C%2522timestamp%2522%253A1658152224741%257D; MONITOR_WEB_ID=2d433e7d-7be5-4eeb-a3ee-a00eca122ac0; csrf_session_id=271615a1f8892416c38f32f3b296fdd2; _tea_utm_cache_2608={%22utm_source%22:%22gold_browser_extension%22}; msToken=HRu-wBxeDQBNjFiCiR--5C-27DMrHTJ-JGIfEihHtWfsNFurrqhg-tdeX5k2Qfy5UZPfmeG-G8b2y1qEB_bJJjLxe12KCy_olYXKRpTgcgkI; passport_csrf_token=9f14f9e1537838bbeb95d7cbf3e63fd7; passport_csrf_token_default=9f14f9e1537838bbeb95d7cbf3e63fd7; odin_tt=376a88033a9546e25f1e8072e904e92760e6212ab0191ce1e65d4dfd9e13d495518b39861fbca6dca610ac93b29304befe4192bd9532694886223e831e78b90b; n_mh=9-mIeuD4wZnlYrrOvfzG3MuT6aQmCUtmr8FxV8Kl8xY; sid_guard=3a30915911e1640e606d2ffaeaafcf30%7C1676859305%7C31536000%7CTue%2C+20-Feb-2024+02%3A15%3A05+GMT; uid_tt=216bdd984df96457b96491a269a5ca8a; uid_tt_ss=216bdd984df96457b96491a269a5ca8a; sid_tt=3a30915911e1640e606d2ffaeaafcf30; sessionid=3a30915911e1640e606d2ffaeaafcf30; sessionid_ss=3a30915911e1640e606d2ffaeaafcf30; sid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; ssid_ucp_v1=1.0.0-KDZhNWNkZThhMzI0NTg3YTdhYzM0MGNiODM4NTZkM2RhZmFiZGRiMDYKFgj4pNDr8o27ARCpr8ufBhiwFDgIQAsaAmxmIiAzYTMwOTE1OTExZTE2NDBlNjA2ZDJmZmFlYWFmY2YzMA; store-region=cn-sh; store-region-src=uid; _gid=GA1.2.1720404080.1676859304")
//            .addHeader("origin", "https://juejin.cn")
//            .addHeader("referer", "https://juejin.cn/")
//            .addHeader("sec-ch-ua", "\"Chromium\";v=\"110\", \"Not A(Brand\";v=\"24\", \"Google Chrome\";v=\"110\"")
//            .addHeader("sec-ch-ua-mobile", "?0")
//            .addHeader("sec-ch-ua-platform", "\"macOS\"")
//            .addHeader("sec-fetch-dest", "empty")
//            .addHeader("sec-fetch-mode", "cors")
//            .addHeader("sec-fetch-site", "same-site")
//            .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36")
//            .addHeader("x-secsdk-csrf-token", "000100000001fe081d00128fd65799b11b21ab1a35630d52316ede51f821e126b28140721c891745674f1d3d7314")
//            .build();
//        Response response = client.newCall(request).execute();

    }

}
