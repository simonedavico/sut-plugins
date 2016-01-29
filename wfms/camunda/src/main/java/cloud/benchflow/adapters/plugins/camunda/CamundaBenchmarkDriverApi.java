package cloud.benchflow.adapters.plugins.camunda;

import cloud.benchflow.adapters.api.WfMSBenchmarkDriverApi;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Simone D'Avico (simonedavico@gmail.com)
 *
 * Created on 09/01/16.
 */
public class CamundaBenchmarkDriverApi extends WfMSBenchmarkDriverApi {

    public CamundaBenchmarkDriverApi(String sutEndpoint) {
        super(sutEndpoint);
    }

    @Override
    public String startProcessDefinition(String modelName) throws IOException {
        Map<String, String> JSONHeaders = new TreeMap<String, String>();
        JSONHeaders.put("Content-Type","application/json");
        String startURL = this.sutEndpoint + "/process-definition/" + modelsStartID.get(modelName) + "/start";
        StringBuilder responseStart = http.fetchURL(startURL, "{}", JSONHeaders);
        return responseStart.toString();
    }

}
