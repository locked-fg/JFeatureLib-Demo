package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.features.*;

public class Descriptors {

    AbstractFeatureDescriptor descriptor;

    public void setDescriptor(NameDescriptor nameDescriptor) {

        switch (nameDescriptor) {
            case AutoColorCorrelogram:
                descriptor = new AutoColorCorrelogram();
                break;
            case CEDD:
                descriptor = new CEDD();
                break;
            case ColorHistogram:
                descriptor = new ColorHistogram();
                break;
            case FCTH:
                descriptor = new FCTH();
                break;
            case FuzzyHistogram:
                descriptor = new FuzzyHistogram();
                break;
            case FuzzyOpponentHistogram:
                descriptor = new FuzzyOpponentHistogram();
                break;
            case Gabor:
                descriptor = new Gabor();
                break;
            case Haralick:
                descriptor = new Haralick();
                break;
            case Histogram:
                descriptor = new Histogram();
                break;
            case JCD:
                descriptor = new JCD();
                break;
            case JpegCoefficientHistogram:
                descriptor = new JpegCoefficientHistogram();
                break;
            case LuminanceLayout:
                descriptor = new LuminanceLayout();
                break;
            case MeanIntensityLocalBinaryPatterns:
                descriptor = new MeanIntensityLocalBinaryPatterns();
                break;
            case Moments:
                descriptor = new Moments();
                break;
            case MPEG7ColorLayout:
                descriptor = new MPEG7ColorLayout();
                break;
            case MPEG7EdgeHistogram:
                descriptor = new MPEG7EdgeHistogram();
                break;
            case OpponentHistogram:
                descriptor = new OpponentHistogram();
                break;
            case PHOG:
                descriptor = new PHOG();
                break;
            case Tamura:
                descriptor = new Tamura();
                break;
        }

    }

    public String getNameDescriptor (int i) {
        return String.valueOf(NameDescriptor.getName(i));
    }

    public AbstractFeatureDescriptor getDescriptor() {
        return descriptor;
    }
}
