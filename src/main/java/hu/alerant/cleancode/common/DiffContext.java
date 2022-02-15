package hu.alerant.cleancode.common;

import java.util.List;
import java.util.stream.Stream;

public interface DiffContext {
    public String getDataFileName();

    public int getSkipHeaders();

    public List<DataLine> parseDataFile(Stream<String> allLines);

}
