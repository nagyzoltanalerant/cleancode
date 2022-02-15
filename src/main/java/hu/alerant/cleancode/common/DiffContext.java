package hu.alerant.cleancode.common;

import java.util.List;
import java.util.stream.Stream;

public interface DiffContext {
    public String getDataFileName();

    public int getSkipHeaders();

    public DataLine getInitDataLine();

    public List<DataLine> parseDataFile(Stream<String> allLines);

}
