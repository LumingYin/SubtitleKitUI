import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.*;


public final class SubGUI extends Application {

    private Desktop desktop = Desktop.getDesktop();

    public void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void start(final Stage stage) {
        if (!System.getProperty("os.name").contains("OS")) {
            showAlert("不支持 Windows 或 Linux","此工具经过测试在 macOS 下可以正常使用。\n你可以尝试在 Windows 或 Linux 下运行这个工具，但受到文本编码的限制，工具可能产生不可预料的错误。请在 Github 提交 Pull Request。");
        }

        stage.setTitle("双语字幕实用工具");

        final FileChooser fileChooser = new FileChooser();
        final Label LabelimportRaw = new Label("外部字幕预处理");
        final Button SRTNormalizerButton = new Button("SRT: 转换电视台 CC 字幕为普通字幕");
        final Button SRTPrepareButton = new Button("SRT: 转换电视台 CC 字幕为普通字幕并增加空白中文行");

        final Label LabelFixError = new Label("字幕翻译纠错");
        final Button SRTTimeAlignButton = new Button("SRT: 让每一个次行的起始时间与前一行的结束时间对齐");
        final Button SRTLanFlipButton = new Button("SRT: 互换中英文两行翻译之间的顺序");

        final Label LabelModifyLooks = new Label("调整字幕样式");
        final Button ASSStyleButton = new Button("ASS: 自动设置中英文字体样式");

        final Label LabelConvertToText = new Label("转换字幕至文本");
        final Button SRTTextButton = new Button("SRT: 删除字幕时间码以转换为纯文本");
        final Button SRTToScriptButton = new Button("SRT: 试图通过 SRT 重建电视剧或电影剧本");


        SRTNormalizerButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTNormalizer.SRTNormalizer(file);
                            showAlert("已成将电视台 CC 字幕转换为普通字幕", "新的字幕文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_normalized.srt");
                        } catch (StringIndexOutOfBoundsException alreadyConverted) {
                            showAlert("字幕文件已经是普通字幕", "因此，无需再次进行转换。");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });

        SRTPrepareButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTPrepare.SRTPrepare(file);
                            showAlert("已将电视台 CC 字幕转换为普通字幕并增加空白中文行", "新的字幕文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_prepared.srt");
                        } catch (StringIndexOutOfBoundsException alreadyConverted) {
                            showAlert("字幕文件已经包含空白中文行", "因此，不会生成新文件。");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });

        SRTTimeAlignButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTTimeAlign.SRTTimeAlign(file);
                            showAlert("已将次行的起始时间与前一行的结束时间对齐", "新的字幕文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_aligned.srt");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });


        SRTLanFlipButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTLanFlip.SRTLanFlip(file);
                            showAlert("已成功互换中英文两行翻译之间的顺序", "新的字幕文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_flipped.srt");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });


        ASSStyleButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            ASSStyle.ASSStyle(file);
                            showAlert("已成功自动设置中英文字体样式", "新的字幕文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_stylized.ass");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });

        SRTTextButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTText.SRTText(file);
                            showAlert("已成功删除字幕时间码并转换为纯文本", "纯文本文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_text.txt");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });

        SRTToScriptButton.setOnAction(
            new EventHandler < ActionEvent > () {
                @Override
                public void handle(final ActionEvent e) {
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        try {
                            SRTToScript.SRTToScript(file);
                            showAlert("已成功试重建电视剧或电影剧本", "文件导出为" 
                                + file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) 
                                + "_script.txt");
                        } catch (Throwable mistake) {
                            showAlert("错误诊断信息", mistake.getMessage());
                        }
                    }
                }
            });


        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(LabelimportRaw, 0, 0);
        GridPane.setConstraints(SRTNormalizerButton, 0, 1);
        GridPane.setConstraints(SRTPrepareButton, 0, 2);
        GridPane.setConstraints(LabelFixError, 0, 4);
        GridPane.setConstraints(SRTTimeAlignButton, 0, 5);
        GridPane.setConstraints(SRTLanFlipButton, 0, 6);
        GridPane.setConstraints(LabelModifyLooks, 0, 8);
        GridPane.setConstraints(ASSStyleButton, 0, 9);
        GridPane.setConstraints(LabelConvertToText, 0, 11);
        GridPane.setConstraints(SRTTextButton, 0, 12);
        GridPane.setConstraints(SRTToScriptButton, 0, 13);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(LabelimportRaw, SRTNormalizerButton, SRTPrepareButton, LabelFixError, SRTTimeAlignButton, SRTLanFlipButton, LabelModifyLooks, ASSStyleButton, LabelConvertToText, SRTTextButton, SRTToScriptButton);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                SubGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}