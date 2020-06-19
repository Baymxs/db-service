package ru.nsu.bayramov.dbservice.arguments;

import com.beust.jcommander.Parameter;

public class CommandLineArgs {
    @Parameter(names = "-o", description = "Database operation name", help = true)
    private String operationName;

    @Parameter(names = {"--input", "--in"}, description = "Input file name", help = true)
    private String inputFileName;

    @Parameter(names = {"--output", "--out"}, description = "Output file name", help = true)
    private String outputFileName;

    @Parameter(names = {"--help", "-h"}, description = "Shows information about program options", help = true)
    private boolean help;

    public String getOperationName() {
        return operationName;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean getHelpOption() {
        return help;
    }
}
