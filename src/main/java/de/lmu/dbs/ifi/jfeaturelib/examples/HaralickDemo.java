package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.LibProperties;
import de.lmu.ifi.dbs.jfeaturelib.features.*;
import de.lmu.ifi.dbs.utilities.Arrays2;
import ij.process.ColorProcessor;
import net.semanticmetadata.lire.imageanalysis.SimpleColorHistogram;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

        FileWriter csvWriter = new FileWriter("descriptors/Tamura.csv");

        List<Path> list = HaralickDemo.walk("src/main/resources/images");


        for (Path f : Objects.requireNonNull(list)) {

            InputStream stream = Files.newInputStream(f);
            ColorProcessor image = new ColorProcessor(ImageIO.read(stream));

//            LibProperties prop = LibProperties.get();
//            prop.setProperty(LibProperties.HISTOGRAMS_BINS, 256);
//            prop.setProperty(LibProperties.HISTOGRAMS_TYPE, "RGB");

            // initialize the descriptor
//            AutoColorCorrelogram descriptor = new AutoColorCorrelogram();
//            CEDD descriptor = new CEDD();
//            ColorHistogram descriptor = new ColorHistogram();
//            FCTH descriptor = new FCTH();
//            FuzzyHistogram descriptor = new FuzzyHistogram();
//            FuzzyOpponentHistogram descriptor = new FuzzyOpponentHistogram();
//            Gabor descriptor = new Gabor();
//            Haralick descriptor = new Haralick();
//            Histogram descriptor = new Histogram();
//            JCD descriptor = new JCD();
//            JpegCoefficientHistogram descriptor = new JpegCoefficientHistogram();
//            LocalBinaryPatterns descriptor = new LocalBinaryPatterns();
//            LuminanceLayout descriptor = new LuminanceLayout();
//            MeanIntensityLocalBinaryPatterns descriptor = new MeanIntensityLocalBinaryPatterns();
//            Moments descriptor = new Moments();
//            MPEG7ColorLayout descriptor = new MPEG7ColorLayout();
//            MPEG7EdgeHistogram descriptor = new MPEG7EdgeHistogram();
//            OpponentHistogram descriptor = new OpponentHistogram();
//            PHOG descriptor = new PHOG();
//            SimpleColorHistogram descriptor = new SimpleColorHistogram();
            Tamura descriptor = new Tamura();

//            descriptor.setProperties(prop);

            // run the descriptor and extract the features
            descriptor.run(image);

            // obtain the features
            List<double[]> features = descriptor.getFeatures();

            // print the features to system out
            for (double[] feature : features) {
//                System.out.println(f.toString().substring(24) + " - " + Arrays2.join(feature, ",", "%.5f"));
                System.out.println(f.toString().substring(26));

                csvWriter.write(f.toString().substring(26) + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');
            }
        }

        csvWriter.flush();
        csvWriter.close();
    }
}