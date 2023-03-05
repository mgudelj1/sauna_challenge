package org.softwaresauna.processors;

import java.util.ArrayList;
import java.util.List;

public class ProcessorFactory {

    public static List<StepProcessor> getProcessors(){
        List<StepProcessor> processors = new ArrayList<>();
        processors.add(new StartingPositionProcessor());
        processors.add(new DashProcessor());
        processors.add(new TurnProcessor());
        processors.add(new UpperCaseCharacterProcessor());
        return processors;
    }
}
