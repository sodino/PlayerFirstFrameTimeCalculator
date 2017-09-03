def baseDir = "./../res/"

def fileLog = "douyin.log"
ParseTag parseTag = new ParseTag(Const.Douyin.TAG_finger_release,
                                Const.Douyin.TAG_set_data_source,
                                Const.Douyin.TAG_metadata,
                                Const.Douyin.TAG_VIDEO_first_frame,
                                Const.Douyin.TAG_AUDIO_first_frame)

//def fileLog = "miaopai.log"
//ParseTag parseTag = new ParseTag(Const.Miaopai.TAG_finger_release,
//                                Const.Miaopai.TAG_set_data_source,
//                                Const.Miaopai.TAG_metadata,
//                                Const.Miaopai.TAG_VIDEO_first_frame,
//                                Const.Miaopai.TAG_AUDIO_first_frame)

//def fileLog = "kuaishou.log"
//ParseTag parseTag = new ParseTag(Const.Kuaishou.TAG_finger_release,
//                                Const.Kuaishou.TAG_set_data_source,
//                                Const.Kuaishou.TAG_metadata,
//                                Const.Kuaishou.TAG_VIDEO_first_frame)

def parser = new LogParser(parseTag)

def inputFile = new File(baseDir + fileLog)

println("index \t setDataSource  \t metadata \t\t firstVideoFrame \t firstAudioFrame \t All")

inputFile.eachLine{ line ->
    parser.parse(line)
}

TimeBean.printStatist()