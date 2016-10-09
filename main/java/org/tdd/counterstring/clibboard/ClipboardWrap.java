package org.tdd.counterstring.clibboard;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Created by Tom on 10/9/2016.
 */
public class ClipboardWrap {

    public static void main(String[] args) {
        System.out.println(ClipboardWrap.getContent());
        ClipboardWrap.putContent("voices from java underground");
    }

    public static void putContent(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, new NotifyOwner());
    }

    public static String getContent() {
        String textContent = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(null);
        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                textContent = (String) content.getTransferData(
                        DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                ex.printStackTrace();
            }
        }
        return textContent;
    }

    static class NotifyOwner implements ClipboardOwner {
        @Override
        public void lostOwnership(Clipboard clipboard, Transferable transferable) {
//            System.out.println("clipboard lost");
        }
    }
}
