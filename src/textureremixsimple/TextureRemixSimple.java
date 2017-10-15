/*
The MIT License (MIT)

Copyright (C) 2017 EverythingBagel (US)
Copyright (c) 2015 Thomas "Peardian" Hernandez

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package textureremixsimple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.File;

public class TextureRemixSimple {
    public static String savepath = "";
    static List<MyImage> images = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Args: [-alpha] [-alphahalf] inputDir outputDir");
            System.exit(0);
        }
        boolean hasSetting = false, splitAlpha = false, splitAlphaHalf = false;
        String inputDirectoryPath = "";
        String outputDirectoryPath = "";
        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], "-alpha"))
                splitAlpha = hasSetting = true;
            else if (Objects.equals(args[i], "-alphahalf"))
                splitAlphaHalf = hasSetting = true;
            else {
                if (inputDirectoryPath.length() < 1)
                    inputDirectoryPath = args[i];
                else
                    outputDirectoryPath = args[i];
            }
        }
        if (outputDirectoryPath.length() < 1) { // side effect: input dir already > 0
            System.out.println("No input directory specified.");
            System.exit(1);
        }
        File inputDirectory = new File(inputDirectoryPath);
        File outputDirectory = new File(outputDirectoryPath);
        if (!inputDirectory.exists()) {
            System.err.println("Input directory does not exist.");
            System.exit(2);
        }
        if (!outputDirectory.exists()) {
            System.err.println("Output directory does not exist.");
            System.exit(2);
        }
        if (!hasSetting) {
            System.err.println("No conversion settings given.");
            System.exit(3);
        }
        savepath = outputDirectoryPath;
        File[] listOfFiles = inputDirectory.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            MyImage img = new MyImage(i, listOfFiles[i].getAbsolutePath());
            images.add(i, img);
            if (splitAlpha)
                img.splitAlpha();
            if (splitAlphaHalf)
                img.splitAlphaHalf();
        }
    }
    
}
