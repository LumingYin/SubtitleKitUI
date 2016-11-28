import java.util.*;
import java.io.*;

public class SRTText {
    public static boolean SRTText(File subFile) throws Exception{
        BufferedReader in ;
        File file;
        String output;
        file = subFile;
        if (!file.getAbsolutePath().substring(file.getAbsolutePath().length() - 3, file.getAbsolutePath().length()).equals("srt")) {
                throw new Exception("请导入 SRT 格式的字幕文件。如需转换 ASS 字幕至 SRT 字幕，下载 ffmpeg 后运行 \"ffmpeg -i 旧字幕.ass 新字幕.srt\"");
        }
        output = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + "_text.txt";
        in = new BufferedReader(new FileReader(file));

        String str;

        List < String > list = new ArrayList < String > ();
        while ((str = in .readLine()) != null) {
            list.add(str);
        }

		String[] stringArr = list.toArray(new String[0]);

		String[] itemsToRemove = new String[]{"</i> <i>", " <i> ", " <i>", "<i> ", "<i>", " </i> ", "</i> ", " </i>", "</i>"};

		List<String> organizedList = new ArrayList<>();

		for (int i = 0, newLineNumber = 1; i < list.size(); i++) {
			if (i > 0 && list.get(i - 1).contains(" --> ")) {
				String combinedString = list.get(i);
				boolean isCCSpecial = (combinedString.charAt(0) == '(') && (combinedString.charAt(combinedString.length() - 1) == ')');
				if (!isCCSpecial) {
					combinedString = combinedString.replaceAll("\\([^\\(]*\\) ", "");
					combinedString = combinedString.replaceAll("\\([^\\(]*\\)", "");
					for (int j = i; j < list.size() - 1; j++) {
						try {
							int temp = Integer.parseInt(list.get(j + 1));
							combinedString = combinedString.substring(0, combinedString.length() - 1);
							for (int k = 0; k < itemsToRemove.length; k++) {
								if (combinedString.contains(itemsToRemove[k])) {
									combinedString = combinedString.replaceAll(itemsToRemove[k],"");
								}
								if (combinedString.contains(":")) {
									combinedString = combinedString.replaceAll("\\b[A-Z]+\\b","");
									combinedString = combinedString.replaceAll(": ","");
								}
							}
							break;
						}
						catch (Throwable e) {
							combinedString = combinedString + " " + list.get(j + 1).replaceAll("^[[:upper:]]", "");
						}
					}
					organizedList.add(combinedString);
				}
			}
		}

 		PrintWriter writer = new PrintWriter(output, "UTF-8");

		for (int i = 0; i < organizedList.size(); i++) {
			writer.println(organizedList.get(i));
		}

		writer.close();

		System.out.println("Subtitle successfully converted to text.");
		System.out.println("Text is converted as " + output + ".");
		return true;

    }
}