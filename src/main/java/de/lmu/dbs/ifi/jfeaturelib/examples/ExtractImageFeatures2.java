package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.features.*;
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
        File[] list = root.listFiles();


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



        List<Path> list = ExtractImageFeatures2.walk("/Users/jjmacagnan/Documents/Jasiel/Databases/aPascal & aYahoo Datasets/bbox_images");

        Collections.sort(list);

        Descritores descritores = new Descritores();

//        ArrayList<Descritores> listDescriptors = new ArrayList<>();
//        listDescriptors = (ArrayList<Descritores>) descritores.getData();
//        descritores.getData();


        // initialize the descriptor
//        AutoColorCorrelogram autoColorCorrelogram = new AutoColorCorrelogram();
//        listDescriptors.add(autoColorCorrelogram);
//        CEDD cedd = new CEDD();
//        listDescriptors.add(cedd);
//            ColorHistogram colorHistogram = new ColorHistogram();
//            FCTH fcth = new FCTH();
//            FuzzyHistogram fuzzyHistogram = new FuzzyHistogram();
//            FuzzyOpponentHistogram fuzzyOpponentHistogram = new FuzzyOpponentHistogram();
//            Gabor gabor = new Gabor();
//            Haralick haralick = new Haralick();
//            Histogram histogram = new Histogram();
//            JCD jcd = new JCD();
//            JpegCoefficientHistogram jpegCoefficientHistogram = new JpegCoefficientHistogram();
//            LocalBinaryPatterns localBinaryPatterns = new LocalBinaryPatterns();
//            LuminanceLayout luminanceLayout = new LuminanceLayout();
//            MeanIntensityLocalBinaryPatterns meanIntensityLocalBinaryPatterns = new MeanIntensityLocalBinaryPatterns();
//            Moments moments = new Moments();
//            MPEG7ColorLayout mpeg7ColorLayout = new MPEG7ColorLayout();
//            MPEG7EdgeHistogram mpeg7EdgeHistogram = new MPEG7EdgeHistogram();
//            OpponentHistogram opponentHistogram = new OpponentHistogram();
//            PHOG phog = new PHOG();
//            Tamura tamura = new Tamura();

        for (int i = 0; i < descritores.getData().size(); i++) {

            FileWriter csvWriter = new FileWriter("descriptors2/AutoColorCorrelogram.csv");


            for (Path f : Objects.requireNonNull(list)) {

                InputStream stream = Files.newInputStream(f);
                ColorProcessor image = new ColorProcessor(ImageIO.read(stream));

//            LibProperties prop = LibProperties.get();
//            prop.setProperty(LibProperties.HISTOGRAMS_BINS, 256);
//            prop.setProperty(LibProperties.HISTOGRAMS_TYPE, "RGB");


//            descriptor.setProperties(prop);

                // run the descriptor and extract the features

                descritores.getData().get(i).run(image);

                // obtain the features
                List<double[]> features = descritores.getData().get(i).getFeatures();

//                 print the features to system out
                for (double[] feature : features) {
//                System.out.println(f.toString().substring(24) + " - " + Arrays2.join(feature, ",", "%.5f"));
                    System.out.println(f.toString().substring(83));

                    csvWriter.write(f.toString().substring(83) + ',' + Arrays2.join(feature, ",", "%.5f") + '\n');
                }
            }


            csvWriter.flush();
            csvWriter.close();

        }
    }
}