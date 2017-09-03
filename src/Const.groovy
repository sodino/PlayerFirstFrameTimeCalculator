/**
 * Created by sodino on 2017/9/1.
 */
class Const {
    public static final def PATTERN_LOG_TIME = /\d{2}-\d{2} \d{2}:\d{2}:\d{2}.\d{3}/
    public static final def TIME_FORMAT = "MM-dd HH:mm:ss.SSS"
    class Douyin {
        // Terminal日志过滤命令
        // adb shell logcat -v threadtime | grep -e "dragPercent = \[1.0\]" -e "setDataSource: path" -e "Video: first frame decoded"  -e "avcodec/Audio: first frame decoded"
        public static final String TAG_finger_release = "dragPercent = [1.0]"
        public static final String TAG_set_data_source = "setDataSource: path"
        // 解析出视频头信息
        public static final String TAG_metadata = "Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'http://127.0.0.1"
        public static final String TAG_VIDEO_first_frame = "Video: first frame decoded"
        public static final String TAG_AUDIO_first_frame = "avcodec/Audio: first frame decoded"
    }


    class Kuaishou{
        // Terminal日志过滤命令
        // adb shell logcat -v threadtime | grep -e "name = sound_effects_enabled" -e "System.out: open:http://" -e "Input #0, mov,mp4,m4a,3gp,3g2,mj2, from " -e "Video: first frame decoded"
        public static final String TAG_finger_release = "SettingsInterface:  from settings cache , name = sound_effects_enabled"
        // 对于未打开过的视频节目会输出以下日志
        // System.out: open:http://125.78.252.106/upic/2017/09/01/21/BMjAxNzA5MDEyMTA2NThfNjMxODM2ODI4XzMxMjE3Nzg4NTRfMl8z_hd.mp4?tag=1-1504344404-h-0-izswk0q1sj-e31f6f47a4cbb03f
        public static final String TAG_set_data_source = "System.out: open:http://"

        // 解析出视频头信息
        public static final String TAG_metadata = "Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'http://127.0.0.1"
        public static final String TAG_VIDEO_first_frame = "Video: first frame decoded"
    }

    class Miaopai{
        // Terminal日志过滤命令
        // adb shell logcat -v threadtime | grep -e "name = sound_effects_enabled" -e "setDataSource: path" -e "Input #0, mov,mp4,m4a,3gp,3g2,mj2," -e "Video: first frame decoded" -e "avcodec/Audio: first frame decoded"
        public static final String TAG_finger_release = "SettingsInterface:  from settings cache , name = sound_effects_enabled"
        public static final String TAG_set_data_source = "setDataSource: path"
        public static final String TAG_metadata = "Input #0, mov,mp4,m4a,3gp,3g2,mj2, from 'http://"
        public static final String TAG_VIDEO_first_frame = "Video: first frame decoded"
        public static final String TAG_AUDIO_first_frame = "avcodec/Audio: first frame decoded"
    }


}
