package hello;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.CipherException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutionException;

@RestController
public class RESTController {

    // Dependendy injection, i declare the object I need and it will be instantiated only when I use it.
    @Autowired
    FirstService firstService;
    @Autowired
    SecondService secondService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!\n";
    }

    @RequestMapping("/test")
    public String getAaa()  throws IOException, ExecutionException, InterruptedException, CipherException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {

        return "The route seems to be working...\n";
    }

    @RequestMapping("/client/version")
    public String getClientVersion() {
        // This is the first time i use firstService, the instance is here automatically created.
        return secondService.getClientVersion()+"\n";
    }

    @RequestMapping("/initialise")
    public String deployContract() {
        // This is the first time i use firstService, the instance is here automatically created.
        return secondService.deployContranct()+"\n";
    }

    // Send a raw transaction hash
    @RequestMapping(value = "/rawtransaction", method = RequestMethod.POST)
    public ResponseEntity<String> postDdata(@RequestBody String hash) throws JSONException {

        JSONObject response = new JSONObject();
        System.out.println("api called (raw transaction: " + hash);
        String resp = secondService.sendRawTransaction(hash);

        response.put("status", 200);
        response.put("message", resp);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    // verify address
    @RequestMapping(value = "/verify/signature", method = RequestMethod.POST)
    public ResponseEntity<String> verifySignature(@RequestBody String jsonString) throws JSONException {

        ObjectMapper mapper = new ObjectMapper();
        Verify obj = null;
        try {
            obj = mapper.readValue(jsonString, Verify.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(obj.msg);


        JSONObject response = new JSONObject();
        System.out.println("Api called: verify signature");
        String resp = secondService.verify(obj);

        response.put("status", 200);
        response.put("message", resp);

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    // add a Social Record
    @RequestMapping("/add/socialrecord/{globalID}")
    public String addSocialRecord(@PathVariable("globalID") String globalID) {
        secondService.addSocialRecord(globalID);
        return "Social record with globalID: "+globalID+" added\n";
    }

    // get a Social Record
    @RequestMapping("/socialrecord/{globalID}")
    public String getSocialRecord(@PathVariable("globalID") String globalID) {
        String res = secondService.getSocialRecord(globalID);
        System.out.println(res);
        if(res.length()<3)
            return "The transaction still needs some time to be mined...\n";
        else
            return res+"\n";
    }
}
