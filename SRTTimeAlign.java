import java.util.*;
import java.io.*;

public class SRTTimeAlign {
    public static boolean SRTTimeAlign (File subFile) throws Exception {
    	BufferedReader in;
    	File file;
    	String output;
        file = subFile;
        System.out.println(file.getAbsolutePath().substring(file.getAbsolutePath().length() - 3, file.getAbsolutePath().length()));
        if (!file.getAbsolutePath().substring(file.getAbsolutePath().length() - 3, file.getAbsolutePath().length()).equals("srt")) {
            throw new Exception("请导入 SRT 格式的字幕文件。如需转换 ASS 字幕至 SRT 字幕，下载 ffmpeg 后运行 \"ffmpeg -i 旧字幕.ass 新字幕.srt\"");
        }
        output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + "_aligned.srt";
        in = new BufferedReader(new FileReader(file));

        String str;

        List < String > list = new ArrayList < String > ();
        while ((str = in .readLine()) != null) {
            list.add(str);
        }

		String[] stringArr = list.toArray(new String[0]);
 		String match = " --> ";
 		int lastLineOfTimecode = 1;

 		PrintWriter writer = new PrintWriter(output, "UTF-8");


		for (int i = 2; i < list.size(); i++) {
			if (list.get(i).contains(match)) {
				int pointOfBreakL = list.get(i).indexOf(match);
				int pointOfBreakR = list.get(lastLineOfTimecode).indexOf(match) + 5;
				String beginningTimeForThis = list.get(lastLineOfTimecode).substring(pointOfBreakR, list.get(lastLineOfTimecode).length());
				String brokenTimeForThis = list.get(i).substring(0, pointOfBreakL);
				String replacedString = list.get(i).replaceAll(brokenTimeForThis, beginningTimeForThis);
				int pointOfComma = replacedString.indexOf(",") + 1;
				int pointOfArrow = replacedString.indexOf(" --> ");
				String millisecondSubstring = replacedString.substring(pointOfComma, pointOfArrow);
				int millisecondPlusOne = Integer.parseInt(millisecondSubstring) + 1;
				replacedString = replacedString.substring(0, pointOfComma) + millisecondPlusOne + replacedString.substring(pointOfArrow, replacedString.length());
				list.set(i, replacedString);
				lastLineOfTimecode = i;
			}
		}

		for (int i = 0; i < list.size(); i++) {
			writer.println(list.get(i));
		}

		writer.close();
		return true;

    }
}