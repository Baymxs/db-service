package ru.nsu.bayramov.dbservice.arguments;

import com.beust.jcommander.JCommander;

public class CommandLineParser {
    private CommandLineArgs commandLineArgs;

    public CommandLineParser(String[] args) {
        commandLineArgs = new CommandLineArgs();
        JCommander.newBuilder()
                .addObject(commandLineArgs)
                .build()
                .parse(args);
    }

    public String getOperationName() {
        return commandLineArgs.getOperationName();
    }

    public String getInputFileName() {
        return commandLineArgs.getInputFileName();
    }

    public String getOutputFileName() {
        return commandLineArgs.getOutputFileName();
    }

    public boolean getHelp() {
        return commandLineArgs.getHelpOption();
    }

    public void showHelp() {
        JCommander.newBuilder().addObject(commandLineArgs).build().usage();
    }
}
