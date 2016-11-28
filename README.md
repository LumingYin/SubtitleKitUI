# SubtitleKitUI

一系列用于辅助制作双语字幕的 Java 小工具。

##主菜单
![主菜单](Screenshot.png?raw=true "主菜单截屏")

##用法
- 下载并安装 [Java 运行环境](https://java.com/en/download/)
- 打包下载 [SubtitleKitUI](https://github.com/HiKay/SubtitleKitUI/archive/master.zip)
- 解压后双击运行 SubGUI.class

##提示
- 由于 Windows 对文本编码的处理欠缺，推荐在 macOS 下运行 [SubtitleKitUI](https://github.com/HiKay/SubtitleKitUI/archive/master.zip)
- 打包下载的 [SubtitleKitUI](https://github.com/HiKay/SubtitleKitUI/archive/master.zip) 中包含名为 Testing_Subtitles 的文件夹。文件夹中包含多种样例字幕，可以用于测试

##外部字幕预处理
###SRT: 转换电视台 CC 字幕为普通字幕
将适用于听力失聪人士的可隐藏字幕转换为标准字幕，去除对环境声音的描述，移除多余的换行。
###SRT: 转换电视台 CC 字幕为普通字幕并增加空白中文行
在前者的基础上在每个英文字幕条上方添加空行，易于译者翻译。
##字幕翻译纠错
###SRT: 让每一个次行的起始时间与前一行的结束时间对齐
修改字幕的 SRT 时间码，从而使下一行字幕的起始时间与前一行字幕的结束时间一致。
###SRT: 互换中英文两行翻译之间的顺序
考虑到设置 AAS 的脚本需要中文在上、英文在下，这一功能可对调原文和译文的顺序。
##调整字幕样式
###ASS: 自动设置中英文字体样式
为双语字幕设置文泉驿微米黑字体，并为之赋予不同的字体大小和颜色，中文行字体较大且边框颜色为蓝色，英文行字体较小且边框颜色为黑色。
##转换字幕至文本
###SRT: 删除字幕时间码以转换为纯文本
移除时间码和特殊格式，将 SRT 字幕文件转换为纯文本。
###SRT: 试图通过 SRT 重建电视剧或电影剧本
在移除时间码和特殊格式的基础上进一步优化排版，试图重建电视剧或电影剧本。
