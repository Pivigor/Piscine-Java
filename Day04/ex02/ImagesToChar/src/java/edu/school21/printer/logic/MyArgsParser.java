package edu.school21.printer.logic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class MyArgsParser {
    public MyArgsParser(String[] args) {
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(args);
    }

    @Parameter(names="--white")
    public String white;
    @Parameter(names="--black")
    public String black;
}
