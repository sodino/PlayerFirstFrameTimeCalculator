/**
 * Created by sodino on 2017/9/1.
 */
class ParseTag {
    def tagFingerRelease
    def tagSetDataSource
    def tagMetadata
    def tagVideoFirstFrame
    def tagAudioFirstFrame

    def tagFinish

    public ParseTag(String finger, String setSource, String metadata, String firstVideo, String firstAudio){
        this.tagFingerRelease = finger
        this.tagSetDataSource = setSource
        this.tagMetadata = metadata
        this.tagVideoFirstFrame = firstVideo
        this.tagFinish = this.tagAudioFirstFrame = firstAudio
    }

    public ParseTag(String finger, String setSource, String metadata, String firstVideo){
        this.tagFingerRelease = finger
        this.tagSetDataSource = setSource
        this.tagMetadata = metadata
        this.tagFinish = this.tagVideoFirstFrame = firstVideo
    }
}
