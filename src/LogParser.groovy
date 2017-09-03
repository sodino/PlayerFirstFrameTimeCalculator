import java.text.DecimalFormat

/**
 * Created by sodino on 2017/9/1.
 */
class LogParser {

//    long timeFingerReleasae = 0;
//    long timeSetDataSource = 0;
//    long timeVideoFirstFrame = 0;
//    long timeAudioFirstFrame = 0;

    def TimeBean beanNow
    def array = []

    def parseTag

    LogParser(ParseTag tag) {
        array.clear()
        parseTag = tag
    }

    def parse(String line){
//        println(line)
        def matcher = (line =~ Const.PATTERN_LOG_TIME)
//        println(matcher.count + " " + matcher[0])
        long time = 0;
        Date date = null
        matcher.each{ match ->
            date = Date.parse(Const.TIME_FORMAT,match)
//            println("${match}  ${date.time}")
            time = date.time
        }

        if (parseTag.tagFingerRelease && line.contains(parseTag.tagFingerRelease)) {
            beanNow = new TimeBean()
            beanNow.finger_release = time
        } else if (parseTag.tagSetDataSource && line.contains(parseTag.tagSetDataSource) && beanNow) {
            beanNow.set_data_source = time
        } else if (parseTag.tagMetadata && line.contains(parseTag.tagMetadata) && beanNow) {
            beanNow.metadata = time
        } else if (parseTag.tagVideoFirstFrame && line.contains(parseTag.tagVideoFirstFrame) && beanNow) {
            beanNow.video_first_frame = time
        } else if (parseTag.tagAudioFirstFrame && line.contains(parseTag.tagAudioFirstFrame) && beanNow) {
            beanNow.audio_first_frame = time
        }

        if (beanNow && beanNow.isOK(parseTag)) {
            addData(beanNow)
            TimeBean.statist(beanNow)
            beanNow = null
        }
    }

    def addData(TimeBean bean) {
        if (bean) {
            bean.printInfo(array.size())
            array << bean
        }
    }
}
