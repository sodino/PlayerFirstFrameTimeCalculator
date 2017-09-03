import java.text.DecimalFormat

/**
 * Created by sodino on 2017/9/1.
 */
class TimeBean {
    long finger_release =0
    long set_data_source =0
    long metadata = 0
    long video_first_frame=0
    long audio_first_frame=0

    long consumeSetDataSource = 0
    long consumeMetadata = 0
    long consumeVideoFirstFrame = 0
    long consumeAudioFirstFrame = 0

    long consumeAll = 0


    static long sumSetDataSource = 0
    static long sumMetaData = 0
    static long sumVideoFirstFrame = 0
    static long sumAudioFirstFrame = 0
    static long sumAll = 0

    static long maxSetDataSource = 0
    static long minSetDataSource = Long.MAX_VALUE
    static long maxMetaData = 0
    static long minMetaData = Long.MAX_VALUE
    static long maxVideoFirstFrame = 0
    static long minVideoFirstFrame = Long.MAX_VALUE
    static long maxAudioFirstFrame = 0
    static long minAudioFirstFrame = Long.MAX_VALUE
    static long maxAll = 0
    static long minAll = Long.MAX_VALUE
    static long count = 0

    static def statist(TimeBean timeBean) {
        count ++
        sumSetDataSource += timeBean.consumeSetDataSource
        sumMetaData += timeBean.consumeMetadata
        sumVideoFirstFrame += timeBean.consumeVideoFirstFrame
        sumAudioFirstFrame += timeBean.consumeAudioFirstFrame
        sumAll += timeBean.consumeAll

        if (maxSetDataSource < timeBean.consumeSetDataSource) {
            maxSetDataSource = timeBean.consumeSetDataSource
        }
        if (minSetDataSource > timeBean.consumeSetDataSource) {
            minSetDataSource = timeBean.consumeSetDataSource
        }

        if (maxMetaData < timeBean.consumeMetadata) {
            maxMetaData = timeBean.consumeMetadata
        }
        if (minMetaData > timeBean.consumeMetadata) {
            minMetaData = timeBean.consumeMetadata
        }

        if (maxVideoFirstFrame < timeBean.consumeVideoFirstFrame) {
            maxVideoFirstFrame = timeBean.consumeVideoFirstFrame
        }
        if (minVideoFirstFrame > timeBean.consumeVideoFirstFrame) {
            minVideoFirstFrame = timeBean.consumeVideoFirstFrame
        }

        if (maxAudioFirstFrame < timeBean.consumeAudioFirstFrame) {
            maxAudioFirstFrame = timeBean.consumeAudioFirstFrame
        }
        if (minAudioFirstFrame > timeBean.consumeAudioFirstFrame) {
            minAudioFirstFrame = timeBean.consumeAudioFirstFrame
        }

        if (maxAll < timeBean.consumeAll) {
            maxAll = timeBean.consumeAll
        }
        if (minAll > timeBean.consumeAll) {
            minAll = timeBean.consumeAll
        }
    }

    static def printStatist() {
        double avgMetaData = sumMetaData * 1.0d / count
        double avgVideoFirstFrame = sumVideoFirstFrame * 1.0d / count
        double avgAudioFirstFrame = sumAudioFirstFrame * 1.0d / count
        double avgAll = sumAll * 1.0d / count
        double avgSetDataSource = sumSetDataSource * 1.0d / count

        DecimalFormat df = new DecimalFormat("#.00");
        println("average \t: setDataSource=" + df.format(avgSetDataSource)
                + " metaData=" + df.format(avgMetaData)
                + " firstVideo=" + df.format(avgVideoFirstFrame)
                + " firstAudio=" + df.format(avgAudioFirstFrame)
                + " all=" + df.format(avgAll)
        )


//        String strFormat = "% f"
//        println("average \t: setDataSource=" + String.format(strFormat, avgSetDataSource)
//                + " metaData=" + String.format(strFormat, avgMetaData)
//                + " firstVideo=" + String.format(strFormat, avgVideoFirstFrame)
//                + " firstAudio=" + String.format(strFormat, avgAudioFirstFrame)
//                + " all=" + String.format(strFormat, avgAll)
//        )

        println("max \t\t: setDataSource=${maxSetDataSource} metaData=${maxMetaData} firstVideo=${maxVideoFirstFrame} firstAudio=${maxAudioFirstFrame} all=${maxAll}")
        println("min \t\t: setDataSource=${minSetDataSource} metaData=${minMetaData} firstVideo=${minVideoFirstFrame} firstAudio=${minAudioFirstFrame} all=${minAll}")
    }

    def printInfo(int index) {
//        DecimalFormat df = new DecimalFormat("000");
//        println("${df.format(index)} \t\t ${consumeSetDataSource}  \t\t->\t\t ${consumeMetadata} \t->\t (${consumeVideoFirstFrame} \t\t\t\t ${consumeAudioFirstFrame}\t\t\t) \t->\t\t ${consumeAll}")

        def strFormat = "% 5d"
        println("${String.format(strFormat, index)} \t ${String.format(strFormat, consumeSetDataSource)}  \t->\t\t ${String.format(strFormat, consumeMetadata)} \t\t->\t\t (${String.format(strFormat, consumeVideoFirstFrame)} \t\t ${String.format(strFormat, consumeAudioFirstFrame)}\t) \t->\t\t ${String.format(strFormat, consumeAll)}")


    }

    def isOK(ParseTag tag) {
        boolean bool = false

        if (!tag) {
            return bool
        }

        if (tag.tagFingerRelease && finger_release > 0
        && tag.tagSetDataSource && set_data_source > finger_release
        && tag.tagMetadata && metadata > set_data_source
        && tag.tagVideoFirstFrame && video_first_frame > set_data_source) {
            consumeSetDataSource = set_data_source - finger_release
            consumeMetadata = metadata - set_data_source
            consumeVideoFirstFrame = video_first_frame - metadata

            if (tag.tagAudioFirstFrame) {
                if (audio_first_frame > set_data_source) {
                    bool = true
                    consumeAudioFirstFrame = audio_first_frame - metadata
                }
            } else {
                if (audio_first_frame == 0) {
                    bool = true
                }
            }


            consumeAll = Math.max(video_first_frame, audio_first_frame) - finger_release
        }

        return bool
    }
}
