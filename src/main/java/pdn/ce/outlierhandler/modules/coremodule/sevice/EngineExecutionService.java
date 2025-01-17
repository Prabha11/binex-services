package pdn.ce.outlierhandler.modules.coremodule.sevice;

import org.springframework.stereotype.Service;
import pdn.ce.outlierhandler.modules.coremodule.util.PropertyReader;

import java.util.*;
import java.io.IOException;

@Service
public class EngineExecutionService {
    private static final String PYTHON_PATH = PropertyReader.getInstance().getPythonPath();
    private static final String SCRIPT_PATH = PropertyReader.getInstance().getEnginePath();
//            +
//            "\"10s\" " +
//            "\"../engine/Model python file/sample_data/10s/10s_unbinned_contigs.OFDEG\" " +
//            "\"../engine/Model python file/sample_data/10s/10s_unbinned_contigs.n4\" " +
//            "\"../engine/Model python file/sample_data/10s/binned_points.csv\" " +
//            "\"../engine/Model python file/sample_data/10s/10s_view2.n4\" " +
//            "\"../engine/Model python file/sample_data/10s/sim.contig.ans\" " +
//            "\"../engine/Model python file/sample_data/10s/taxon_bins.csv\" " +
//            "\"../engine/Model python file/sample_data/10s/10s_output.L2_BINS\" " +
//            "\"../engine/Model python file/sample_data/10s/10s_model_results.csv\""
        ;

    public Process executeProcess(List<String> arguments) {
        Process process = null;
        try {
            System.out.println(PYTHON_PATH + SCRIPT_PATH + getArguments(arguments));
            process = Runtime.getRuntime().exec(
                    PYTHON_PATH + SCRIPT_PATH + getArguments(arguments),
                    null);
            System.out.println(process);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }

    private String getArguments(List<String> arguments) {
        String space = " ";
        String quoteMark = "\"";
        String line = "";
        for (String argument : arguments) {
            line = line + space + quoteMark + argument + quoteMark;
        }
        return line;
    }
}
