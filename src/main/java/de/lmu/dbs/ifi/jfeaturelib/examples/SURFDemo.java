package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.features.Haralick;
import de.lmu.ifi.dbs.jfeaturelib.features.SURF;
import de.lmu.ifi.dbs.utilities.Arrays2;
import ij.process.ColorProcessor;

import javax.imageio.ImageIO;
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
public class SURFDemo {


    // File representing the folder that you select using a FileChooser
    static final File dir = new File("src/main/resources/training/Calc/");

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
        File[] list = root.listFiles();


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

        FileWriter csvWriter = new FileWriter("descriptors/SURF1.csv");

        List<Path> list = SURFDemo.walk("/Users/jjmacagnan/Documents/Jasiel/Databases/aPascal & aYahoo Datasets/bbox_images");


//        if (dir.isDirectory()) { // make sure it's a directory

        Collections.sort(list);


        for (Path f : Objects.requireNonNull(list)) {

            InputStream stream = Files.newInputStream(f);
            ColorProcessor image = new ColorProcessor(ImageIO.read(stream));

            // initialize the descriptor
            SURF descriptor = new SURF();

            // run the descriptor and extract the features
            descriptor.run(image);

            // obtain the features
            List<double[]> features = descriptor.getFeatures();

//                if (features.size() > 0) {
//                    System.out.println(f.toString().substring(95, 112) + ','
//                            + Arrays2.join(features.get(0), ",", "%.5f"));


//                    csvWriter.write(f.toString().substring(95, 111) + ',' + Arrays2.join(features.get(0), ",", "%.5f") + '\n');

//                } else {
//                    System.out.println(f.toString().substring(95, 112) + ','
//                            + 0.0);

//                    csvWriter.write(f.toString().substring(95, 111) + ',' + 0.0 + '\n');

//                }

            if (features.size() == 0) {
                System.out.println(f.toString());
            }

            features = Collections.singletonList(features.get(0));



//            if (features.size() > 1) {
//
//                features = features.subList(0, 1);
//            } else {
//                features = features.subList(0, features.size());
//                System.out.println("Size: " + features.size());
//                System.out.println("Parou: " + f.toString());
//                break;
//            }


            String toShow = "";


//              print the features to system out
            for (double[] feature : features) {
//                    System.out.println(Arrays2.join(feature, ",", "%.5f"));
//
//                    csvWriter.write(f.toString() + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');

                toShow = toShow + Arrays2.join(feature, ",", "%.5f");

//                    System.out.println(f.toString().substring(83));


            }

            csvWriter.write(f.toString().substring(83) + ',' + toShow + '\n');

            System.out.println(toShow);
        }
//        }

//        for (File f : Objects.requireNonNull(dir.listFiles())) {
//
//            try {
//                for (File f1 : f.listFiles()) {
//                    for (File f2 : f1.listFiles()) {
//                        InputStream stream = Files.newInputStream(f2.toPath());
//                        assert stream != null;
//                        ColorProcessor image = new ColorProcessor(ImageIO.read(stream));
//
//                        // initialize the descriptor
//                        Haralick descriptor = new Haralick();
//
//                        // run the descriptor and extract the features
//                        descriptor.run(image);
//
//                        // obtain the features
//                        List<double[]> features = descriptor.getFeatures();
//
//
//                        // print the features to system out
//                        for (double[] feature : features) {
//                            System.out.println(Arrays2.join(feature, ",", "%.5f"));
//
//                            csvWriter.write(f.toString() + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');
//                        }
//                    }
//                }
//
//
//            } catch (final IOException e) {
////                    // handle errors here
//            }
//
//        }
        csvWriter.flush();
        csvWriter.close();
    }
}