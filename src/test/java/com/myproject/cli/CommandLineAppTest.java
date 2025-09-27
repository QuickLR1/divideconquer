package com.myproject.cli;

import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineAppTest {

    @Test
    public void testCSVOutput() {
        String fileName = "test_output.csv";
        String[] args = {"--algo=deterministic", "--output=" + fileName};
        CommandLineApp.main(args);

        File file = new File(fileName);
        assertTrue(file.exists(), "CSV file should be created");
        file.delete();
    }
}
