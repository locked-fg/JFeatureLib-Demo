package de.lmu.dbs.ifi.jfeaturelib.examples;

import de.lmu.ifi.dbs.jfeaturelib.features.AutoColorCorrelogram;
import de.lmu.ifi.dbs.jfeaturelib.features.CEDD;

import java.util.ArrayList;
import java.util.List;

public class Descritores {

    AutoColorCorrelogram autoColorCorrelogram = new AutoColorCorrelogram();
    CEDD cedd = new CEDD();
    List<de.lmu.ifi.dbs.jfeaturelib.features.AbstractFeatureDescriptor> data = new ArrayList<>();


    public List<de.lmu.ifi.dbs.jfeaturelib.features.AbstractFeatureDescriptor> getData() {

        data.add( autoColorCorrelogram);
        data.add( cedd);

        return data;
    }
}
