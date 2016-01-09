package cloud.benchflow.adapters.plugins.camunda;

import cloud.benchflow.adapters.api.WfMSBenchmarkApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Simone D'Avico (simonedavico@gmail.com)
 *
 * Created on 09/01/16.
 */
public class CamundaBenchmarkApi extends WfMSBenchmarkApi{

    protected String processDefinitionAPI;

    public CamundaBenchmarkApi(String sutEndpoint) {
        super(sutEndpoint, "/deployment/create");
        this.processDefinitionAPI = sutEndpoint + "/process-definition";
    }

    @Override
    public Map<String, String> deploy(File file) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        StringPart deploymentName = new StringPart("deployment-name", model.getName());
        logger.info("Deploying model: " + model.getAbsolutePath());
        List<Part> parts = new ArrayList<Part>();

        FilePart process = new FilePart("*", model);

        parts.add(deploymentName);
        parts.add(process);
        logger.info("Deploying model at: " + deployAPI);
        StringBuilder deployDef = http.fetchURL(deployAPI, parts);

        logger.info("DEPLOYMENT RESPONSE: " + deployDef.toString());
        JsonObject deployObj = parser.parse(deployDef.toString()).getAsJsonObject();
        String deploymentId = deployObj.get("id").getAsString();

        logger.info("DEPLOYMENT ID: " + deploymentId);

        //Obtain process definition data
        StringBuilder procDef = http.fetchURL(processDefinitionAPI + "?deploymentId=" + deploymentId);
        logger.info("PROCESS DEFINITION RESPONSE: " + procDef.toString());
        String processDefinitionResponse = procDef.toString();

        JsonArray procDefArray = parser.parse(processDefinitionResponse).getAsJsonArray();
        //We only get 1 element using the deploymentId
        String processDefinitionId = procDefArray.get(0).getAsJsonObject().get("id").getAsString();
        result.put(model.getName(), processDefinitionId);
        return result;
    }
}
