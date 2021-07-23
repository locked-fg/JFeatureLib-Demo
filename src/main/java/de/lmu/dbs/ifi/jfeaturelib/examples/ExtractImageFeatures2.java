package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.LibProperties;
import de.lmu.ifi.dbs.utilities.Arrays2;
import ij.process.ColorProcessor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


/**
 * This is is a very basic Class that demonstrates the usage of a descriptor
 * with plain Java without the commandline exctractor.
 *
 * @author Franz
 */
public class ExtractImageFeatures2 {


    // File representing the folder that you select using a FileChooser
//    static final File dir = new File("src/main/resources/training/Calc/");

    static List<Path> paths = new ArrayList<>();

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

    public static List<Path> walk(String path) {

        File root = new File(path);
        File[] list = root.listFiles(IMAGE_FILTER);


        assert list != null;
        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
//                System.out.println( "Dir:" + f.getAbsoluteFile() );
            } else {
//                System.out.println( "File:" + f.toPath() );
                paths.add(f.toPath());
            }
        }

        return paths;

    }


    public static void main(String[] args) throws IOException, URISyntaxException {
        Locale.setDefault(Locale.US);


        List<Path> list = ExtractImageFeatures2.walk("/Users/jjmacagnan/Documents/Jasiel/Databases/mammoset/images_raw/DDSM_TRAIN");

        Collections.sort(list);

        for (int i = 0; i < 19; i++) {

            FileWriter csvWriter = new FileWriter("descriptors2/" + NameDescriptor.getName(i) + ".csv");


            for (Path f : Objects.requireNonNull(list)) {

                Descriptors descriptors = new Descriptors();

                descriptors.setDescriptor(NameDescriptor.getName(i));

                InputStream stream = Files.newInputStream(f);
                ColorProcessor image = new ColorProcessor(ImageIO.read(stream));

//                System.out.println(descriptors.getNameDescriptor(i));

                if (descriptors.getNameDescriptor(i).equals(String.valueOf(NameDescriptor.ColorHistogram)) ||
                        descriptors.getNameDescriptor(i).equals(String.valueOf(NameDescriptor.Histogram))) {

                    LibProperties prop = LibProperties.get();
                    prop.setProperty(LibProperties.HISTOGRAMS_BINS, 256);
                    prop.setProperty(LibProperties.HISTOGRAMS_TYPE, "RGB");


                    descriptors.getDescriptor().setProperties(prop);
                }


                // run the descriptor and extract the features

                descriptors.getDescriptor().run(image);

                // obtain the features
                List<double[]> features = descriptors.getDescriptor().getFeatures();

//                 print the features to system out
                for (double[] feature : features) {
//                System.out.println(f.toString().substring(24) + " - " + Arrays2.join(feature, ",", "%.5f"));
                    System.out.println(f.toString().substring(76));

                    csvWriter.write(f.toString().substring(76) + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');
                }

                csvWriter.flush();
                stream.close();
            }


//            csvWriter.
            csvWriter.flush();
            csvWriter.close();

//            JOptionPane.showMessageDialog(null, NameDescriptor.getName(i)+".csv successfully created");

            System.out.println(NameDescriptor.getName(i)+".csv successfully created");

        }
    }
}