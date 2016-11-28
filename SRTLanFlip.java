import java.util.*;
import java.io.*;

public class SRTLanFlip {
    public static boolean SRTLanFlip(File subFile) throws Exception {
    	BufferedReader in;
    	File file;
    	String output;
        file = subFile;
        if (!file.getAbsolutePath().substring(file.getAbsolutePath().length() - 3, file.getAbsolutePath().length()).equals("srt")) {
            throw new Exception("请导入 SRT 格式的字幕文件。如需转换 ASS 字幕至 SRT 字幕，下载 ffmpeg 后运行 \"ffmpeg -i 旧字幕.ass 新字幕.srt\"");
        }
        output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + "_flipped.srt";
        in = new BufferedReader(new FileReader(file));

        String str;

        List < String > list = new ArrayList < String > ();
        while ((str = in .readLine()) != null) {
            list.add(str);
        }

		String[] stringArr = list.toArray(new String[0]);
 		CharSequence cs1 = "-->";
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i-1).contains(cs1)) {
				String temp = list.get(i);
				list.set(i, list.get(i+1));
				list.set(i+1, temp);
			}
		}

		PrintWriter writer = new PrintWriter(output, "UTF-8");

		for (int i = 0; i < list.size(); i++) {
			writer.println(list.get(i));
		}

		writer.close();
		return true;

}

}