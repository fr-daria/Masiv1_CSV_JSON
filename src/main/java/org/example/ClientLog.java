package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List<String[]> log = new ArrayList<>();

    public void log(int productNum, int amount) {
        log.add(0, new String[]{"ProductNum" + "Amount"});
    }

    public void exportAsCSV(File txtFile) throws IOException {

        if (!txtFile.exists()) {
            log.add(0, new String[]{"ProductNum" + "Amount"});

        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile, true))) {
            writer.writer(log);
        }


    }
}
