package de.lmu.dbs.ifi.jfeaturelib.examples;

public enum NameDescriptor {


    AutoColorCorrelogram, CEDD, ColorHistogram, FCTH, FuzzyHistogram, FuzzyOpponentHistogram, Gabor, Haralick, Histogram,
    JCD, JpegCoefficientHistogram, LuminanceLayout, MeanIntensityLocalBinaryPatterns, Moments, MPEG7ColorLayout, MPEG7EdgeHistogram, OpponentHistogram, PHOG, Tamura;


    public static NameDescriptor getName(int i) {
        switch (i) {
            case 0:
                return AutoColorCorrelogram;
            case 1:
                return CEDD;
            case 2:
                return ColorHistogram;
            case 3:
                return FCTH;
            case 4:
                return FuzzyHistogram;
            case 5:
                return FuzzyOpponentHistogram;
            case 6:
                return Gabor;
            case 7:
                return Haralick;
            case 8:
                return Histogram;
            case 9:
                return JCD;
            case 10:
                return JpegCoefficientHistogram;
            case 11:
                return LuminanceLayout;
            case 12:
                return MeanIntensityLocalBinaryPatterns;
            case 13:
                return Moments;
            case 14:
                return MPEG7ColorLayout;
            case 15:
                return MPEG7EdgeHistogram;
            case 16:
                return OpponentHistogram;
            case 17:
                return PHOG;
            case 18:
                return Tamura;
        }


        return null;
    }




}
