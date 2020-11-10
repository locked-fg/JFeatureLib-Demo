package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.features.Haralick;
import de.lmu.ifi.dbs.utilities.Arrays2;
import ij.process.ColorProcessor;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Objects;


/**
 * This is is a very basic Class that demonstrates the usage of a descriptor
 * with plain Java without the commandline exctractor.
 *
 * @author Franz
 */
public class HaralickDemo {

    // File representing the folder that you select using a FileChooser
    static final File dir = new File("/Users/admin/IdeaProjects/JFeatureLib-Demo/src/main/resources");

    // array of supported extensions (use a List if you prefer)
    static final String[] EXTENSIONS = new String[]{
            "gif", "png", "jpg" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };


    public static void main(String[] args) throws IOException, URISyntaxException {
        // load the image

        FileWriter csvWriter = new FileWriter("new.csv");
        StringBuilder sb = new StringBuilder();


        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : Objects.requireNonNull(dir.listFiles(IMAGE_FILTER))) {
//                BufferedImage img = null;

                try {

//                    img = ImageIO.read(f);

                    // you probably want something more involved here
                    // to display in your UI
//                    System.out.println("image: " + f.getName());
//                    System.out.println(" width : " + img.getWidth());
//                    System.out.println(" height: " + img.getHeight());
//                    System.out.println(" size  : " + f.length());

                    InputStream stream = HaralickDemo.class.getClassLoader().getResourceAsStream(f.getName());
                    assert stream != null;
                    ColorProcessor image = new ColorProcessor(ImageIO.read(stream));

                    // initialize the descriptor
                    Haralick descriptor = new Haralick();

                    // run the descriptor and extract the features
                    descriptor.run(image);

                    // obtain the features
                    List<double[]> features = descriptor.getFeatures();

//                  comma substitution by dot
                    Locale.setDefault(Locale.US);

                    // print the features to system out
                    for (double[] feature : features) {
                        System.out.println(Arrays2.join(feature, ",", "%.5f"));

                        csvWriter.write(f.getName() + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');
                    }

                } catch (final IOException e) {
                    // handle errors here
                }


            }
            csvWriter.flush();
            csvWriter.close();
        }
    }
}
